package makkah.wadi.instapay.instapay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ProfileActivity extends AppCompatActivity {
    ImageView QR_Profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        QR_Profile = (ImageView) findViewById(R.id.profile_imageView);


    }
}
