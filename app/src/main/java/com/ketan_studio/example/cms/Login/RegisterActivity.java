package com.ketan_studio.example.cms.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ketan_studio.example.cms.MainActivity;
import com.ketan_studio.example.cms.R;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText name,email,pass,ConfirmPass;
    ProgressBar progressBar;
    Button submit;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Parent");
        name = (EditText)findViewById(R.id.name_et_register);
        email = (EditText)findViewById(R.id.email_et_register);
        pass = (EditText)findViewById(R.id.pass_et_register);
        ConfirmPass = (EditText)findViewById(R.id.Confirm_pass_et_register);

        submit = (Button)findViewById(R.id.submit_button_login);


    }

    public void GoToLoginFromReg(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void Register(View view) {
        submit.setVisibility(View.INVISIBLE);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        Sprite wave = new Wave();
        wave.setColor(R.color.black);
        progressBar.setIndeterminateDrawable(wave);

        final String uname = name.getText().toString();
        final String uemail = email.getText().toString();
        final String upass = pass.getText().toString();
        final String Confirm = ConfirmPass.getText().toString();
        if (TextUtils.isEmpty(uname)){
            Toast.makeText(this, "Please Enter Username...", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
            submit.setVisibility(View.VISIBLE);
        }
        else if (TextUtils.isEmpty(uemail)){
            Toast.makeText(this, "Please Enter Email...", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
            submit.setVisibility(View.VISIBLE);
        }
        else if (TextUtils.isEmpty(upass)){

            Toast.makeText(this, "Please Enter Password...", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
            submit.setVisibility(View.VISIBLE);
        }
        else if (TextUtils.isEmpty(Confirm)){
            Toast.makeText(this, "Please Enter Confirm Password...", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
            submit.setVisibility(View.VISIBLE);
        }else if (!upass.equals(Confirm)){
            Toast.makeText(this, "Password and Confirm Pass Do not match...", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
            submit.setVisibility(View.VISIBLE);
        }else {
            CreateNewUser(uname,uemail,upass,Confirm);
            Toast.makeText(this, "Sucess...", Toast.LENGTH_SHORT).show();

        }
    }

    private void CreateNewUser(String uname, String uemail, String upass, String confirm) {
        Log.d("Error","inside");
        mAuth.createUserWithEmailAndPassword(uemail,upass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Registed", Toast.LENGTH_SHORT).show();
                            HashMap<String,Object> map = new HashMap<>();
                            map.put("Name",uname);
                            map.put("Email",uemail);
                            myRef.child(mAuth.getCurrentUser().getUid()).setValue(map)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            SendUserToHomePage();
                                            progressBar.setVisibility(View.INVISIBLE);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegisterActivity.this, "Data is not Added to Database ", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            
                        }else{
                            submit.setVisibility(View.VISIBLE);
                            Toast.makeText(RegisterActivity.this, "Failed : Please Enter Data Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.VISIBLE);
            }
        });
    }

    private void SendUserToHomePage() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }


}