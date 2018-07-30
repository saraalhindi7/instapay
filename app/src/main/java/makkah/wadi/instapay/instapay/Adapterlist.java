package makkah.wadi.instapay.instapay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapterlist extends RecyclerView.Adapter<Adapterlist.ViewHolder> {



    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView friendname ,friendtran;

        public ViewHolder(View itemView) {
            super(itemView);
            friendname = itemView.findViewById(R.id.Friend_Name);
            friendtran= itemView.findViewById(R.id.Friend_transaction);
        }
    }

    private Context context ;
    private ArrayList<String> friendinfo ;

    public Adapterlist(Context c , ArrayList<String> friendinfo){
        this.context=c;
        this.friendinfo=friendinfo;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.friend_list,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String f =friendinfo.get(position);

        holder.friendname.setText(f);
       // holder.friendtran.setText(f.getFriend_transaction());

    }

    @Override
    public int getItemCount()
    {

        return friendinfo.size();
    }
}
