package makkah.wadi.instapay.instapay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;


public class FrindsFragment extends Fragment {

    public ArrayList<Friend> ListOfFriend;
    public TextView textViewname, textViewtran;
    ArrayList<String> friendlist, friendname;
    FirebaseAuth auth;
    DatabaseReference databaseUser;
    private RecyclerView recyclerView;
    private Adapterlist litsadapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frinds, container, false);
        final RecyclerView recyclerView = v.findViewById(R.id.RY_friends);
        friendlist = new ArrayList<String>();
        friendname = new ArrayList<String>();
        auth = FirebaseAuth.getInstance();
        databaseUser = FirebaseDatabase.getInstance().getReference();
//get friends ids
        DatabaseReference friendsdata = databaseUser.child("user").child(auth.getUid()).child("friend");
        friendsdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot SingleSnapshot : dataSnapshot.getChildren()) {
                    String friend = SingleSnapshot.getValue(String.class);
                    friendlist.add(friend);
                    Log.d("Friend id ", friend);
                    DatabaseReference friends_name = databaseUser.child("user").child(friend).child("name");
                     friends_name.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String temp = dataSnapshot.getValue(String.class);
                            friendname.add(temp);
                            litsadapter.notifyDataSetChanged();
                            Log.d("Friend name ", temp);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    }); }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//       if(friendlist.size()>0){
//            for(int i =0 ; i<= friendlist.size();i++){
//
//                final  DatabaseReference friends_name = databaseUser.child("user").child(friendlist.get(i)).child("name");
//                String x = friendlist.get(i);
//                Log.d("Friend id to show  ", x);
//                friends_name.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String temp = dataSnapshot.getValue(String.class);
//                        friendname.add(temp);
//                        Log.d("Friend name ", temp);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                    }
//                })  ;
//            }}

        litsadapter = new Adapterlist(getContext(), friendname);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(litsadapter);
        return v;
    }
}
