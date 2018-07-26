package makkah.wadi.instapay.instapay;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dialog extends AppCompatDialogFragment {
    DatabaseReference databaseUser;
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

        builder.setView(view).setTitle("Pay")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               String amount = AmountEditText.getText().toString();
                auth = FirebaseAuth.getInstance();
                Double a = Double.valueOf(amount);

                DatabaseReference databaseUser = FirebaseDatabase.getInstance().getReference();

                databaseUser.child("user").child(auth.getUid()).child("Balance").setValue(a);


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
