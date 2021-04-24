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
import com.ketan_studio.example.cms.AdapterPackage.ShowMessageRecyclerAdapterViewHolder;
import com.ketan_studio.example.cms.Models.ChildCallLogModel;
import com.ketan_studio.example.cms.Models.ChildMessageModel;

public class MessageActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_message_display);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ShowMessageDetails();
    }

    private void ShowMessageDetails() {
        Intent i = getIntent();
        String name = i.getStringExtra("Name");
        DatabaseReference mReff = FirebaseDatabase.getInstance().getReference("Child").child(name).child("MESSAGE");
        FirebaseRecyclerOptions opt = new FirebaseRecyclerOptions.Builder<ChildMessageModel>().setQuery(mReff,ChildMessageModel.class).build();
        FirebaseRecyclerAdapter<ChildMessageModel, ShowMessageRecyclerAdapterViewHolder> adapter = new FirebaseRecyclerAdapter<ChildMessageModel, ShowMessageRecyclerAdapterViewHolder>(opt) {
            @Override
            protected void onBindViewHolder(@NonNull ShowMessageRecyclerAdapterViewHolder holder, int position, @NonNull ChildMessageModel model) {
                holder.body.setText(model.getBody());
                holder.date.setText(model.getDate());
                holder.number.setText(model.getNumber());
            }

            @NonNull
            @Override
            public ShowMessageRecyclerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_display_message,parent,false);
                return new ShowMessageRecyclerAdapterViewHolder(v);
            }
        };
        adapter.notifyDataSetChanged();
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}