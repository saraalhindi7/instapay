package makkah.wadi.instapay.instapay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.widget.Toast.LENGTH_SHORT;

public class SignInActivity extends AppCompatActivity {


    EditText inputEmail;
    EditText inputPassword;
    Button btnSignIn;
    TextView resetPass;
    FirebaseAuth auth;
    DatabaseReference databaseUser ;
    //genera code
    ImageView QR_Profile;
    TextView userNameProfile;
    TextView balancProfile;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        QR_Profile = (ImageView) findViewById(R.id.profile_btn);
        userNameProfile = (TextView) findViewById(R.id.userNameTextView);
        balancProfile = (TextView) findViewById(R.id.balaneTextView);


        auth = FirebaseAuth.getInstance();
        //Firebase auththentication instance
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
            finish();
        }

        //view initlilaztion
        inputEmail = (EditText) findViewById(R.id.Adress_email);
        inputPassword = (EditText) findViewById(R.id.EPassword);
        btnSignIn = (Button) findViewById(R.id.SignInButton);
        resetPass = (TextView) findViewById(R.id.resetPass);

        //if user forget password
        TextView ResetPass = (TextView) findViewById(R.id.resetPass) ;
        ResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(SignInActivity.this,MainActivity.class));
            }
        });

        //sign In
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get user inputs
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                //checking user inputs, if it is empty or not and return response
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter Email Adderss !", LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", LENGTH_SHORT).show();
                    return;
                }

                //Sign In to user account using email and password ,
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError("your password is too short");
                                    } else {
                                        Toast.makeText(SignInActivity.this, "Authenticaion faild ", Toast.LENGTH_LONG).show();
                                    }
                                } else {
// getType
                                    databaseUser = FirebaseDatabase.getInstance().getReference();
                                    DatabaseReference userdata = databaseUser.child("user").child(auth.getUid());
                                    userdata.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            User usertype = dataSnapshot.getValue(User.class);
                                            String type = usertype.getType();

                                            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
                                            editor.putString("Type", type);
                                            editor.commit();
                                            // Log.d("user type",usertype);
                                        }
                                        @Override
                                        public void onCancelled (@NonNull DatabaseError databaseError){

                                        }

                                    });


                                    //genera code
                                    /*MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                                    try{
                                        BitMatrix bitMatrix = multiFormatWriter.encode("text2Qr" , BarcodeFormat.QR_CODE,200,200);
                                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                                        QR_Profile.setImageBitmap(bitmap);
                                    }
                                    catch (WriterException e){
                                        e.printStackTrace();
                                    }*/
                                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}
