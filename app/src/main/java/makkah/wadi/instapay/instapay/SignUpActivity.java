package makkah.wadi.instapay.instapay;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.PrecomputedText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;


import java.io.ByteArrayOutputStream;

import static android.widget.Toast.LENGTH_SHORT;

public class SignUpActivity extends AppCompatActivity {

    public final static int QRcodeWidth = 500 ;
    Bitmap bitmap ;
    ImageView imageView;


    public double balance = 50 ;
    public String name, email, phone, password;
    public EditText userName, userEmail, userPhone, userPassword;
    Button signUpUserBtn;
    TextView haveAccount;
    RadioGroup radiogroup;
    String t;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    private StorageReference mStorageRef;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        auth = FirebaseAuth.getInstance();
        imageView = (ImageView) findViewById(R.id.codeSignup);

        /*FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        final ProfileFragment pf = new ProfileFragment();
        transaction.add(R.id.profileF,pf);
        transaction.commit();*/



//        mStorageRef = FirebaseStorage.getInstance().getReference();

        userEmail = (EditText) findViewById(R.id.email_address);
        userPassword = (EditText) findViewById(R.id.Password);
        userName = (EditText) findViewById(R.id.name);
        signUpUserBtn = (Button) findViewById(R.id.SignUpButton);
        userPhone = (EditText) findViewById(R.id.phone_number);
        final Button uploadbutton = (Button) findViewById(R.id.UploudImage);

        radiogroup = (RadioGroup) findViewById(R.id.Radiogroup);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group , int checkedId) {
               // checkedId is the RadioButton selected
                if (checkedId == R.id.useraccount ){
                    t = "User";
                    uploadbutton.setVisibility(View.GONE);
                }
                if (checkedId == R.id.radioStore ){
                    t="Store";
                    uploadbutton.setVisibility(View.VISIBLE);
                }

            }
        });

        signUpUserBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //trim to remove all spaces
                email = userEmail.getText().toString();
                password = userPassword.getText().toString();
                name = userName.getText().toString();
                phone = userPhone.getText().toString();

                //checking user inputs, if it is empty or not and return response
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter Email Adderss !", LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", LENGTH_SHORT).show();
                    return;
                }
                //checking user password length, must be longer than 6 characters.


                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter full name !", LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Write your phone number !", LENGTH_SHORT).show();
                    return;
                }
                //create a new user in database ,

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                           if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException(),

                                    LENGTH_SHORT).show();
                            Log.e("the error", String.valueOf(task.getException()));
                        } else {
                               DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("user").child(auth.getUid());
                              // User user = new User(name , t ,phone , balance);
                              // reference.setValue(user);
                               reference.child("name").setValue(name);
                               reference.child("phone_number").setValue(phone);
                               reference.child("Balance").setValue(balance);
                               reference.child("type").setValue(t);
                               Bundle bundle =new Bundle();
                               bundle.putString("UserType",t);
                               SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
                               editor.putString("Type", t);
                               editor.commit();


                               Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), LENGTH_SHORT).show();

                              // intent.putExtra("UserType", t );


                              // intent.putExtra("type",t);
                              // final String key = reference.child("user").push().getKey();

                               try {
                                   bitmap = TextToImageEncode(reference);
                                   imageView.setImageBitmap(bitmap);

                               } catch (WriterException e) {
                                   e.printStackTrace();
                               }
                               Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                               ByteArrayOutputStream bArray = new ByteArrayOutputStream();
                               bitmap.compress(Bitmap.CompressFormat.PNG, 50 , bArray);
                               intent.putExtra("image" , bArray.toByteArray());



                              /* Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.id.codeSignup);
                               ByteArrayOutputStream stream = new ByteArrayOutputStream();
                               bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                               byte[] byteArray = stream.toBy teArray();
                               */
                               /*Bundle bundle = new Bundle();
                               pf.setArguments(bundle);
                               transaction.add(R.id.profileF,pf);
                               transaction.commit();
                               intent.putExtra("BitmapImage", bitmap);*/


                              // intent.putExtra("picture", byteArray);
                               startActivity(intent);
                               finish();
                        }
                    }
                });
            }
        });

    }

    Bitmap TextToImageEncode(DatabaseReference Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    String.valueOf(Value),
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.colorAccent):getResources().getColor(R.color.colorPrimary);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}