package com.ketan_studio.example.cms.AdapterPackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ketan_studio.example.cms.FragmentHomePage.HomeFragment;
import com.ketan_studio.example.cms.Models.ShowChildInHomeModel;
import com.ketan_studio.example.cms.OptionsActivity;
import com.ketan_studio.example.cms.R;

import java.util.ArrayList;

public class RecyclerViewAdapther extends RecyclerView.Adapter<RecyclerViewAdapther.MyViewHolder>{

    ArrayList<ShowChildInHomeModel> mlist;
    Context context;

    public RecyclerViewAdapther(Context context,ArrayList<ShowChildInHomeModel> mlist){
        this.mlist = mlist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_show_child_in_homefragment,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ShowChildInHomeModel model = mlist.get(position);
        holder.NameofCHild.setText(model.getName());
        holder.code.setText(model.getKey_Code());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,OptionsActivity.class);
                i.putExtra("Name",model.getName());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView NameofCHild,code;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            NameofCHild = (TextView)itemView.findViewById(R.id.name_textView_seeAppoinment);
            code = (TextView)itemView.findViewById(R.id.code);
            cardView = (CardView)itemView.findViewById(R.id.card_layout);
        }
    }

}
