package com.ketan_studio.example.cms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Random;

public class AddNewChildActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText name,age;
    Button Submit_btn;
    FirebaseDatabase database;
    DatabaseReference ParentReff;
    private FirebaseAuth mAuth;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_add_child);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ParentReff = database.getReference("Parent");
        radioGroup = (RadioGroup)findViewById(R.id.Gender_radioGroup_addChild);
        int radId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radId);
        name = (EditText)findViewById(R.id.name_et_addChild);
        age = (EditText)findViewById(R.id.age_et_addChild);
        Submit_btn = (Button)findViewById(R.id.Submit_btn_AddChild);
        Submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendDataToDataBase();
            }
        });

    }

    private void SendDataToDataBase() {
        String cname = name.getText().toString();
        String cage = age.getText().toString();
        String gender = radioButton.getText().toString();
        if (TextUtils.isEmpty(cname)){
            Toast.makeText(this, "Name Cannot be Empty", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(cage)){
            Toast.makeText(this, "Age is Empty", Toast.LENGTH_SHORT).show();
        }
        else{
            Random random = new Random();
            int n = 100000 + random.nextInt(900000);
            String code = String.valueOf(n);
//            Toast.makeText(this, "KEY" + code, Toast.LENGTH_SHORT).show();

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ChildReff = database.getReference("Child");
            ChildReff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!snapshot.child(cname+"").exists()){
                        HashMap<String,Object> map = new HashMap<>();
                        map.put("Name",cname);
                        map.put("Age",cage);
                        map.put("Key",code);
                        map.put("Gender",gender);
                        map.put("Parent UID",mAuth.getUid().toString());
                        ChildReff.child(cname).setValue(map);
//                        Toast.makeText(AddNewChildActivity.this, "Data Added to database", Toast.LENGTH_SHORT).show();
                        //Adding to Parent Data
                        HashMap<String,Object> mapping = new HashMap<>();
                        mapping.put("Name",cname);
                        mapping.put("Key_Code",code);
                        ParentReff.child(mAuth.getCurrentUser().getUid()).child("CHILD").child(cname).updateChildren(mapping).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(AddNewChildActivity.this, "Child added Sucessfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(AddNewChildActivity.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddNewChildActivity.this, "Failed Child added to Parent"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else {
                        Toast.makeText(AddNewChildActivity.this, "User name aldready added", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AddNewChildActivity.this, "Error in Retriving Username"+error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public void CheckButton(View view) {
        int radId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radId);
        Toast.makeText(this, "Selecetd"+radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}