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

public class SlideAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 3;

    public SlideAdapter(FragmentManager fragmentManager) {
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
                Fragment2 fragment2 = new Fragment2();
                return fragment2;
            case 1:
                ScannerActivity QR_Scanner = new ScannerActivity();
                return QR_Scanner;
            case 2:
                Fragment3 fragment3 = new Fragment3();
                return fragment3;
            default:
                return null;
        }
    }
}
