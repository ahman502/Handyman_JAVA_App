package com.codecademy.handyman_java_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Client_home extends AppCompatActivity {

    //variables
    TextView firstname_used_for_signup;
    ImageButton plumbing_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);

        //setting the first name to display in a specific TextView. This TextView is found by id (clientName, in this case)
        firstname_used_for_signup = (TextView) findViewById(R.id.clientName);
        //getting the intent
        Intent intent1 = getIntent();
        String str = intent1.getStringExtra("fname");
        //displaying the string with welcome message
        firstname_used_for_signup.setText("Welcome, " + str + "!");


        //this is the navigation bar at the bottom of client's home screen to view items like
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            //when a client selects one of the options of the navigation bar
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //first we get the id of each menu item
                switch (item.getItemId()) {

                    //if the id is notification, we are taken to a page that displays their notifications
                    case R.id.notification:
                        /* Intent function below works fine, but will be implemented if we have time
                        Intent intent = new Intent(Client_home.this, Handyman_home.class);
                        startActivity(intent); */
                        Toast.makeText(getApplicationContext(), "Notifications", Toast.LENGTH_SHORT).show();
                        return true;

                    //if the id is settings, then the settings are displayed
                    case R.id.settings:
                        Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                        return true;

                    //if the id is chat, then the chatting screen is displayed
                    case R.id.chat:
                        Toast.makeText(getApplicationContext(), "Chat", Toast.LENGTH_SHORT).show();
                        return true;

                    //if the id is home, then the home is displayed
                    case R.id.home:
                        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                        return true;

                    default: return true;
                }
            }
        });

        //setting onClickListener on plumbing button to open a list a plumbing related jobs
        plumbing_list = findViewById(R.id.plumbing_btn);
        plumbing_list.setOnClickListener(v -> openPlumbingActivity());

    }

    //function to open and display a new activity
    public void openPlumbingActivity(){
        //when this function is called, we will be navigated to the activity_plumbing_list.xml file (Plumbing_list.java class)
        Intent plumbing_list_activity = new Intent(this, Plumbing_list.class);
        startActivity(plumbing_list_activity);
    }





}