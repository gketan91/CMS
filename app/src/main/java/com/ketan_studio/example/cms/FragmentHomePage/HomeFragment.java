package com.ketan_studio.example.cms.FragmentHomePage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ketan_studio.example.cms.AdapterPackage.RecyclerViewAdapther;
import com.ketan_studio.example.cms.Models.ShowChildInHomeModel;
import com.ketan_studio.example.cms.R;
import com.ketan_studio.example.cms.Login.WelcomeActivity;


import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private DatabaseReference myReff;
    RecyclerViewAdapther myAdapter;
    ArrayList<ShowChildInHomeModel> arrayList;
    Toolbar toolbar;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        setHasOptionsMenu(true);

        Toast.makeText(getActivity(), "MAIN", Toast.LENGTH_SHORT).show();
        mAuth = FirebaseAuth.getInstance();

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_fragment_home);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        try{
            ShowList();
        }catch (Exception e){
            Toast.makeText(getContext(), "Main Exception", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getContext(),WelcomeActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    onCreateAnimation(0,true,0);
                    startActivity(i);
        }

        return v;
    }



    private void ShowList() {
        myReff = FirebaseDatabase.getInstance().getReference("Parent").child(mAuth.getUid()).child("CHILD");
        arrayList = new ArrayList<>();
        Toast.makeText(getActivity(), "Level 1", Toast.LENGTH_SHORT).show();
        myAdapter = new RecyclerViewAdapther(getContext(),arrayList);
        recyclerView.setAdapter(myAdapter);
        myReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ShowChildInHomeModel model  = dataSnapshot.getValue(ShowChildInHomeModel.class);
                    Toast.makeText(getActivity(), "Level 2"+model.getName(), Toast.LENGTH_SHORT).show();
                    arrayList.add(model);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void SendUserToWelcomePage() {
        Intent i = new Intent(getContext(),WelcomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.logout_menu_toolbar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                mAuth.signOut();
                SendUserToWelcomePage();
                break;
        }
        return true;

    }
}