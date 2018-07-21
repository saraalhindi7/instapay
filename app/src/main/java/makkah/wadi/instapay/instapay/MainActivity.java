package makkah.wadi.instapay.instapay;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class MainActivity extends AppCompatActivity  {
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
