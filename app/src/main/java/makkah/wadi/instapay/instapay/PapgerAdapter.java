package makkah.wadi.instapay.instapay;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PapgerAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 3;
     public  PapgerAdapter(FragmentManager fm , int Number)
     {
         super(fm);
         this.NUM_ITEMS = Number;
     }

    @Override
    public Fragment getItem(int i) {
        switch(i)
        {

            case 0:
                Fragment f1 = new Fragment();
                return f1;
            default:
                return null;
    }}

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
