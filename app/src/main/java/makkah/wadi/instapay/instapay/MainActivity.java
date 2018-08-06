package makkah.wadi.instapay.instapay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity  {
    Button paymentMethod_btn;
    FragmentPagerAdapter adapterViewPager;
    FirebaseAuth auth ;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);


     // String UserType = getIntent().getStringExtra("UserType");

       // String data = getIntent().getExtras().getString("keyName","defaultKey");
       // Log.d("typeuser",data);
       SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
       String UserType = sharedPref.getString("Type", null);

        adapterViewPager = new FragmentAdapter(getSupportFragmentManager(),UserType);
        viewPager.setAdapter(adapterViewPager);

        viewPager.setCurrentItem(1);
    }

        }
