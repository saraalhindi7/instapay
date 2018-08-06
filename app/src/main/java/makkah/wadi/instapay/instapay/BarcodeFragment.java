package makkah.wadi.instapay.instapay;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;


import java.util.List;

import info.androidhive.barcode.BarcodeReader;

import static android.text.TextUtils.isEmpty;


public class BarcodeFragment extends Fragment implements BarcodeReader.BarcodeReaderListener {


    private static final String TAG = BarcodeFragment.class.getSimpleName();

    private BarcodeReader barcodeReader;


    public BarcodeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_barcode, container, false);

        barcodeReader = (BarcodeReader) getChildFragmentManager().findFragmentById(R.id.barcode_fragment);
        barcodeReader.setListener(this);
        Button profile = (Button) view.findViewById(R.id.profile_btn);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment profileFragment = new ProfileFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.barcode_fragment, profileFragment, "findThisFragment")
                        .addToBackStack(null).commit();
            }
        });
        return view;
    }


    @Override
    public void onScanned(final Barcode barcode) {
        Log.e(TAG, "onScanned: " + barcode.displayValue);
        barcodeReader.playBeep();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "Barcode: " + barcode.displayValue, Toast.LENGTH_SHORT).show();
                String flag = "friend";
                if (!isEmpty(barcode.displayValue)) {
                    String IDfriend = barcode.displayValue;
                    makkah.wadi.instapay.instapay.Dialog dialog = makkah.wadi.instapay.instapay.Dialog.newInstance();
                    String ID = barcode.displayValue;
                    Bundle bundle =new Bundle();
                    bundle.putString("message", ID);
                    dialog.setArguments(bundle);

                    dialog.show(getFragmentManager(), "dialog");

                    if (flag.equalsIgnoreCase("friend")) {
                        //Asmaa code ---------------------
                        final String userKey = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference().child("user").child(userKey);
                        DatabaseReference friendID = myRef.child("friend").child(ID);
                        friendID.setValue(ID);
                        myRef.child(ID);
                    }
                }
            }
        });
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {
        Log.e(TAG, "onScannedMultiple: " + barcodes.size());
        String codes = "";
        for (Barcode barcode : barcodes) {
            codes += barcode.displayValue + ", ";
        }
        final String finalCodes = codes;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "Barcodes: " + finalCodes, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {
        Log.e(TAG, "onScanError: " + errorMessage);

    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(getActivity(), "Camera permission denied!", Toast.LENGTH_LONG).show();
    }


}

