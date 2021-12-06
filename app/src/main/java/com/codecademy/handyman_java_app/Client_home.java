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
    ImageButton electrician_list;

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

                        //if the id is settings, then the settings are displayed
                    case R.id.settings:

                        //if the id is chat, then the chatting screen is displayed
                    case R.id.chat:
                        Toast.makeText(getApplicationContext(), "Stay tuned, feature coming soon!", Toast.LENGTH_SHORT).show();
                        return true;

                    //if the id is home, then the home is displayed
                    case R.id.home:
                        //Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                        return true;

                    default: return true;
                }
            }
        });

        //setting onClickListener on plumbing button to open a list of plumbers
        plumbing_list = findViewById(R.id.plumbing_btn);
        plumbing_list.setOnClickListener(v -> openPlumbingActivity());

        //setting onClickListener on electric button to open a list of electricians
        electrician_list = findViewById(R.id.electric_btn);
        electrician_list.setOnClickListener(v -> openElectricActivity());

    }

    //function to open and display a new activity
    public void openPlumbingActivity(){
        //when this function is called, we will be navigated to the activity_plumbing_list.xml file (Plumbing_list.java class)
        Intent plumbing_list_activity = new Intent(this, Plumbing_list.class);
        startActivity(plumbing_list_activity);
    }

    //function to open and display a new activity
    public void openElectricActivity(){
        //when this function is called, we will be navigated to the activity_electric_list.xml file (Electric_list.java class)
        Intent electric_list_activity = new Intent(this, Electric_list.class);
        startActivity(electric_list_activity);
    }

    public void toastMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    public void displayToastMsg(View v) {
        toastMsg("Stay tuned, feature coming soon!");
    }

}