package makkah.wadi.instapay.instapay;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StartActivity extends AppCompatActivity {
    FirebaseAuth auth;
    private static int SPLASH_TIME_OUT = 4;

    DatabaseReference databaseUser ;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button SignUpButton = (Button) findViewById(R.id.Signupbutton);
        Button LoginButton = (Button) findViewById(R.id.loginbutton);


        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(StartActivity.this, SignUpActivity.class));
            }
        });


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(StartActivity.this, SignInActivity.class));
            }
        });
        auth = FirebaseAuth.getInstance();

        //Firebase auththentication instance
        if (auth.getCurrentUser() != null) {
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
                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                    finish();
                    // Log.d("user type",usertype);
                }
                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }
            });


        }

    }
}

