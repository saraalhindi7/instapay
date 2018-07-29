package makkah.wadi.instapay.instapay;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dialog extends AppCompatDialogFragment {
    FirebaseAuth auth;


    Context context;
    private EditText AmountEditText;
    public Dialog (){
        context = getActivity();
    }
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog , null);


        builder.setView(view).setTitle("Specify Amount").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               String amount = AmountEditText.getText().toString();
                auth = FirebaseAuth.getInstance();
                final Double a = Double.valueOf(amount);
                final DatabaseReference databaseUser = FirebaseDatabase.getInstance().getReference();

                databaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Double artist =   dataSnapshot.child("user").child(auth.getUid()).child("Balance").getValue(Double.class);

                        databaseUser.child("user").child(auth.getUid()).child("Balance").setValue(a+artist);

                        Log.e("artist data",artist+"");
                        Log.e("***************","");

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {


                    }
                });



            }
        });
        AmountEditText = view.findViewById(R.id.amount);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    public static  Dialog newInstance() {
        Dialog d = new Dialog();
        return d;
    }

}
