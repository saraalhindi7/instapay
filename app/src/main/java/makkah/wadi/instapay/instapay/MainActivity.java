package makkah.wadi.instapay.instapay;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.widget.Button;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity  {
    Button paymentMethod_btn;
    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapterViewPager = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        viewPager.setCurrentItem(1);
    }





}
