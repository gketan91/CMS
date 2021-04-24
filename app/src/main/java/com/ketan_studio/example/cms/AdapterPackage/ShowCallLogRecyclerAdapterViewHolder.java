package com.ketan_studio.example.cms.AdapterPackage;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ketan_studio.example.cms.R;

public class ShowCallLogRecyclerAdapterViewHolder extends RecyclerView.ViewHolder {

    public TextView name,phone,date,duration,time,calltype;

    public ShowCallLogRecyclerAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.name_textView_display_CALLLOG);
        phone = (TextView)itemView.findViewById(R.id.PhoneNo_textView_display_CALLLOG);
        date = (TextView)itemView.findViewById(R.id.date_textView_display_CALLLOG);
        duration = (TextView)itemView.findViewById(R.id.duration_textView_display_CALLLOG);
        time = (TextView)itemView.findViewById(R.id.time_textView_display_CALLLOG);
        calltype = (TextView)itemView.findViewById(R.id.callType_textView_display_CALLLOG);
    }
}
