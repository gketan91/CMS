package com.ketan_studio.example.cms.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ketan_studio.example.cms.Login.LoginActivity;
import com.ketan_studio.example.cms.Login.RegisterActivity;
import com.ketan_studio.example.cms.R;

public class WelcomeActivity extends AppCompatActivity {

    Button login,reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        login = (Button)findViewById(R.id.login_btn_Welcome);
        reg = (Button)findViewById(R.id.register_btn_Welcome);

    }

    public void GoToLogin(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    public void GoToRegister(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
        finish();
    }
}