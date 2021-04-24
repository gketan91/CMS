package com.ketan_studio.example.cms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ketan_studio.example.cms.AdapterPackage.ShowCallLogRecyclerAdapterViewHolder;
import com.ketan_studio.example.cms.AdapterPackage.ShowContactRecyclerViewAdapterViewHolder;
import com.ketan_studio.example.cms.Models.ChildCallLogModel;
import com.ketan_studio.example.cms.Models.ChildContactModel;

public class CallLogActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_calllog_display);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ShowCallLogDetails();
    }

    private void ShowCallLogDetails() {
        Intent i = getIntent();
        String name = i.getStringExtra("Name");
        DatabaseReference mReff = FirebaseDatabase.getInstance().getReference("Child").child(name).child("CallLog");
        FirebaseRecyclerOptions opt = new FirebaseRecyclerOptions.Builder<ChildCallLogModel>().setQuery(mReff,ChildCallLogModel.class).build();
        FirebaseRecyclerAdapter<ChildCallLogModel,ShowCallLogRecyclerAdapterViewHolder> adapter = new FirebaseRecyclerAdapter<ChildCallLogModel, ShowCallLogRecyclerAdapterViewHolder>(opt) {
            @Override
            protected void onBindViewHolder(@NonNull ShowCallLogRecyclerAdapterViewHolder holder, int position, @NonNull ChildCallLogModel model) {
                holder.name.setText(model.getContact_Name());
                holder.phone.setText(model.getCall_Number());
                holder.calltype.setText(model.getCall_Type());
                holder.date.setText(model.getCallDate());
                holder.duration.setText(model.getCall_Duration());
                holder.time.setText(model.getCall_Time());
            }

            @NonNull
            @Override
            public ShowCallLogRecyclerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_display_calllog,parent,false);
                return new ShowCallLogRecyclerAdapterViewHolder(v);
            }
        };
        adapter.notifyDataSetChanged();
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}