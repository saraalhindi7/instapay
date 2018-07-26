package makkah.wadi.instapay.instapay;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {
   TextView Username;
   TextView balance ;
   DatabaseReference databaseUser;
    FirebaseAuth auth;


    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        Username = (TextView) v.findViewById(R.id.userNameTextView);
        balance = (TextView) v.findViewById(R.id.balaneTextView);
        return v;




    }
    @Override
    public void onStart() {
        super.onStart();

        databaseUser = FirebaseDatabase.getInstance().getReference();

        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("user").child(auth.getUid()).child("name").getValue(String.class);
                Double balance1 = dataSnapshot.child("user").child(auth.getUid()).child("Balance").getValue(Double.class);
             //
                Log.e("name", name);
                Log.e("id", auth.getUid());
                Log.e("balance", balance1.toString());

                Username.setText(name);
                balance.setText(balance1.toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}