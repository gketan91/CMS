package com.ketan_studio.example.cms.AdapterPackage;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ketan_studio.example.cms.R;

public class ShowMessageRecyclerAdapterViewHolder extends RecyclerView.ViewHolder {

    public TextView number,body,date;
    public ShowMessageRecyclerAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
        number = itemView.findViewById(R.id.number_tv);
        body = itemView.findViewById(R.id.body_tv);
        date = itemView.findViewById(R.id.date_tv);
    }
}
