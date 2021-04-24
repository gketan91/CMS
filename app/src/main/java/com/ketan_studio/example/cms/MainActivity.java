package com.ketan_studio.example.cms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.ketan_studio.example.cms.FragmentHomePage.HomeFragment;
import com.ketan_studio.example.cms.FragmentHomePage.SettingsFragment;
import com.ketan_studio.example.cms.Login.WelcomeActivity;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class MainActivity extends AppCompatActivity {
    public FirebaseAuth mAuth;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton AddNewChildFloatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.nav);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Container,new HomeFragment()).commit();//seting Default Frgment as Home Fragment




        //Floating button Which has plus Sign
        AddNewChildFloatingButton = (FloatingActionButton)findViewById(R.id.fab);
        AddNewChildFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddNewChildActivity.class);
                startActivity(i);
            }
        });



    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.placehoder:
                    Intent i = new Intent(getApplicationContext(),AddNewChildActivity.class);
                    startActivity(i);
                case R.id.download:
                    selectedFragment = new SettingsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Container,selectedFragment).commit();
            return true;
        }
    };



    @Override
    public void onBackPressed() {
        finish();
        finish();
        System.exit(0);
    }





}