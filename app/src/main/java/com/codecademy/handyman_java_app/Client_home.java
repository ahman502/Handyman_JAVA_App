package com.codecademy.handyman_java_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Client_home extends AppCompatActivity {

    //variables
    TextView firstname_used_for_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.notification:
                        Intent intent = new Intent(Client_home.this, Handyman_home.class);
                        startActivity(intent);
                        return true;
                    case R.id.settings:
                        Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.chat:
                        Toast.makeText(getApplicationContext(), "Chat", Toast.LENGTH_SHORT).show();
                        return true;

                    default: return true;
                }
            }
        });

        //setting the first name to display in a specific TextView. This TextView is found by id (clientName, in this case)
        firstname_used_for_signup = (TextView) findViewById(R.id.clientName);
        //getting the intent
        Intent intent1 = getIntent();
        String str = intent1.getStringExtra("fname");
        //displaying the string with welcome message
        firstname_used_for_signup.setText("Welcome, " + str + "!");

    }


}