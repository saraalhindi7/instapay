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

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;


public class FrindsFragment extends Fragment {

    private RecyclerView recyclerView ;
    private Adapterlist litsadapter;
    private ArrayList<Friendinfo> friendlist;
    public TextView textViewname , textViewtran ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

                 View v = inflater.inflate(R.layout.fragment_frinds, container, false);
                 RecyclerView recyclerView = v.findViewById(R.id.RY_friends);
        friendlist = new ArrayList<>();
        for (int i =0 ; i < 10 ; i++){
            friendlist.add (new Friendinfo("friendname"+i , "friendtransaction"+i , ""));

        }
        litsadapter = new Adapterlist(getContext() , friendlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(litsadapter);
        return v;
    }
}

