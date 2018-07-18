package makkah.wadi.instapay.instapay;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 3;

    public FragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }



    @Override
    public int getCount() {
        return NUM_ITEMS;
    }



    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            case 1:
                BarcodeFragment barcodeFragment = new BarcodeFragment();
                return  barcodeFragment;
            case 2:
                FrindsFragment frindsFragment = new FrindsFragment();
                return frindsFragment;
            default:
                return null;
        }
    }
}
