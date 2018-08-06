package makkah.wadi.instapay.instapay;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AmountDialog extends AppCompatDialogFragment {
    FirebaseAuth auth;


    Context context;
    private EditText AmountEditText;

    public AmountDialog() {
        context = getActivity();
    }

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        final String strtext=getArguments().getString("message");
       // final String UserType = getArguments().getString("UserType");

        auth = FirebaseAuth.getInstance();

        builder.setView(view).setTitle("Specify Amount").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String amount = AmountEditText.getText().toString();
                final Double a = Double.valueOf(amount);
                final DatabaseReference databaseUser = FirebaseDatabase.getInstance().getReference();


                databaseUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                     //   String Storname = dataSnapshot.child("user").child(auth.getUid()).child("name").getValue(String.class);
                        Double userbalance = dataSnapshot.child("user").child(auth.getUid()).child("Balance").getValue(Double.class);
                  //      if (UserType == "User"){
                        Double friendbalance = dataSnapshot.child("user").child(strtext).child("Balance").getValue(Double.class);
                        friendbalance = friendbalance +a;
                        userbalance=userbalance -a;
                        databaseUser.child("user").child(auth.getUid()).child("Balance").setValue(userbalance);
                        databaseUser.child("user").child(strtext).child("Balance").setValue(friendbalance);




                        // if (UserType == "Store"){
             //            String currentDate = DateFormat.getDateTimeInstance().format(new Date());
               //           String CoponId = databaseUser.child("user").child(strtext).child("copon").push().getKey();
                 //         DatabaseReference Storerefrence = databaseUser.child("user").child(strtext).child("copon");
                   //        Storerefrence.child("date").setValue(currentDate);
                     //      Storerefrence.child("Storename").setValue(Storname);


                        //TODO if (type = store) send copon to user child(user).child(barcode.value).child(copons).push().key()
                        //TODO child(copons).child(key()).child(date)
                        // TODO child(copons)child(key()).child(amount)
                        // TODO child(copons)child(key()).child(store)
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

    public static AmountDialog newInstance() {
        AmountDialog d = new AmountDialog();
        return d;
    }


}
