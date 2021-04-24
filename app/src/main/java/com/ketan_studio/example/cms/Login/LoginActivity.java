package com.ketan_studio.example.cms.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ketan_studio.example.cms.MainActivity;
import com.ketan_studio.example.cms.R;

public class LoginActivity extends AppCompatActivity {
    Button submit;
    EditText email,pass;
    TextView forgetPass;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText)findViewById(R.id.email_et_login);
        pass = (EditText)findViewById(R.id.pass_et_login);
        forgetPass = (TextView)findViewById(R.id.forgetPass_tv_login);
        submit = (Button)findViewById(R.id.submit_button_login);

        mAuth = FirebaseAuth.getInstance();


    }

    public void Login(View view) {
        submit.setVisibility(View.INVISIBLE);
        progressBar = (ProgressBar)findViewById(R.id.progressBar_Login);
        progressBar.setVisibility(View.VISIBLE);
        Sprite wave = new Wave();
        progressBar.setIndeterminateDrawable(wave);
        String uemail = email.getText().toString();
        String upass = pass.getText().toString();
        mAuth.signInWithEmailAndPassword(uemail,upass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login Sucessful", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                            SendUserToHomePage();
                        }
                        else {
                            submit.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                submit.setVisibility(View.VISIBLE);
                Toast.makeText(LoginActivity.this, "Exception"+e.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void SendUserToHomePage() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}