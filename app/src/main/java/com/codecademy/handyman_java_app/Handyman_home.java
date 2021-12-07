package com.codecademy.handyman_java_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Handyman_home extends AppCompatActivity {

    TextView firstname_used_for_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handyman_home);

        //setting the first name to display in a specific TextView. This TextView is found by id (HandymanNameForHomeScreen, in this case)
        firstname_used_for_signup = (TextView) findViewById(R.id.HandymanNameForHomeScreen);
        //getting the intent
        Intent intent1 = getIntent();
        String str = intent1.getStringExtra("fname");
        //displaying the string with welcome message
        firstname_used_for_signup.setText("Welcome, " + str + "!");
    }
}