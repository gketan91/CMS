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
import com.ketan_studio.example.cms.AdapterPackage.ShowContactRecyclerViewAdapterViewHolder;
import com.ketan_studio.example.cms.Models.ChildContactModel;

public class ContactDisplayActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_display);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_contact_display);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ShowContactList();
    }

    private void ShowContactList() {
        Intent i = getIntent();
        String name = i.getStringExtra("Name");
        DatabaseReference mReff = FirebaseDatabase.getInstance().getReference("Child").child(name).child("Contact");
        FirebaseRecyclerOptions opt = new FirebaseRecyclerOptions.Builder<ChildContactModel>().setQuery(mReff,ChildContactModel.class).build();
        FirebaseRecyclerAdapter<ChildContactModel, ShowContactRecyclerViewAdapterViewHolder> adapter = new FirebaseRecyclerAdapter<ChildContactModel, ShowContactRecyclerViewAdapterViewHolder>(opt) {
            @Override
            protected void onBindViewHolder(@NonNull ShowContactRecyclerViewAdapterViewHolder holder, int position, @NonNull ChildContactModel model) {
                holder.name.setText(model.getName().toString());
                holder.phone.setText(model.getPhoneNumber().toString());
            }

            @NonNull
            @Override
            public ShowContactRecyclerViewAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_display_contact,parent,false);
                return new ShowContactRecyclerViewAdapterViewHolder(v);
            }
        };
        adapter.notifyDataSetChanged();
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}