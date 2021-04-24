package com.ketan_studio.example.cms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OptionsActivity extends AppCompatActivity {
    TextView childName;
    Button location,contact_btn,CallLogoBtn,MessageBtn;
    FirebaseDatabase database;
    String longitude;
    String latitude;
    float lat;
    float longi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Intent i = getIntent();
        String name = i.getStringExtra("Name");
        childName = (TextView)findViewById(R.id.childName_opt_tv);
        childName.setText(name);
        location = (Button)findViewById(R.id.Location_opt_buton);

        contact_btn = (Button)findViewById(R.id.Contact_opt_buton);
        CallLogoBtn = (Button)findViewById(R.id.CallLog_btn);
        MessageBtn = (Button)findViewById(R.id.message_btn_opt_select);


        database= FirebaseDatabase.getInstance();
        DatabaseReference myReff = database.getReference("Child").child(name).child("Location");
        myReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                longitude = snapshot.child("LONGITUDE").getValue().toString();
                latitude = snapshot.child("LATITUDE").getValue().toString();
                lat = Float.parseFloat(latitude);
                longi = Float.parseFloat(longitude);
                Toast.makeText(OptionsActivity.this, ""+lat+longi, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OptionsActivity.this,LocationMapsActivity.class);
                i.putExtra("Name",name);
                i.putExtra("Longitude",longi);
                i.putExtra("Latitude",lat);
                startActivity(i);
            }
        });
        contact_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OptionsActivity.this,ContactDisplayActivity.class);
                i.putExtra("Name",name);
                startActivity(i);
            }
        });
        CallLogoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OptionsActivity.this,CallLogActivity.class);
                i.putExtra("Name",name);
                startActivity(i);
            }
        });
        MessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(OptionsActivity.this,MessageActivity.class);
                i.putExtra("Name",name);
                startActivity(i);
            }
        });
    }
}