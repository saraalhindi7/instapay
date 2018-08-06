package makkah.wadi.instapay.instapay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 3;
    public String usertype;

    public FragmentAdapter(FragmentManager fragmentManager , String UserType) {
        super(fragmentManager);
        UserType = usertype;

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
                Bundle bundle =new Bundle();
                bundle.putString("usertype", usertype);

                return  barcodeFragment;
            case 2:
               //if (usertype == "User"){
                FrindsFragment frindsFragment = new FrindsFragment();
                return frindsFragment;
            //   }
            //    else return null;
            default:
                return null;
        }
    }
}