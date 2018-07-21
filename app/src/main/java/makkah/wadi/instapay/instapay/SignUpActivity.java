package makkah.wadi.instapay.instapay;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import static android.widget.Toast.LENGTH_SHORT;

public class SignUpActivity extends AppCompatActivity {

    public int balance = 50 ;
    public String name, email, phone, password;
    public EditText userName, userEmail, userPhone, userPassword;
    Button signUpUserBtn;
    TextView haveAccount;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        auth = FirebaseAuth.getInstance();

        userEmail = (EditText) findViewById(R.id.email_address);
        userPassword = (EditText) findViewById(R.id.Password);
        userName = (EditText) findViewById(R.id.name);
        signUpUserBtn = (Button) findViewById(R.id.SignUpButton);
        userPhone = (EditText) findViewById(R.id.phone_number);
        haveAccount = (TextView) findViewById(R.id.haveAccountTextView) ;
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(SignUpActivity.this, SignInActivity.class));
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

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(auth.getUid());
                        FirebaseUser user = auth.getCurrentUser();
                        reference.child("name").setValue(name);
                        reference.child("phone_number").setValue(phone);
                        reference.child("Balance").setValue(balance);


                        Toast.makeText(SignUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), LENGTH_SHORT).show();
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException(),

                                    LENGTH_SHORT).show();
                            Log.e("the error", String.valueOf(task.getException()));
                        } else {
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                });


            }

        });
    }

}