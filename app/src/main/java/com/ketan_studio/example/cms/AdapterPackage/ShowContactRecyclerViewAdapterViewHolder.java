package com.ketan_studio.example.cms.AdapterPackage;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ketan_studio.example.cms.R;

public class ShowContactRecyclerViewAdapterViewHolder extends RecyclerView.ViewHolder {

    public TextView name,phone;
    public ShowContactRecyclerViewAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.name_textView_display_contact);
        phone = (TextView)itemView.findViewById(R.id.PhoneNo_textView_display_contact);
    }
}
