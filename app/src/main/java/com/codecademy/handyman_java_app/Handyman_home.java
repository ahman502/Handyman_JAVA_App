package com.codecademy.handyman_java_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Handyman_home extends AppCompatActivity {

    TextView firstname_used_for_signup;
    CheckBox android, java, angular, python;

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

        android = findViewById(R.id.chkAndroid);
        angular = findViewById(R.id.chkAngular);
        java = findViewById(R.id.chkJava);
        python = findViewById(R.id.chkPython);
        Button btn = findViewById(R.id.getBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "Selected Courses";
                if(android.isChecked()){
                    result += "\nAndroid";
                }
                if(angular.isChecked()){
                    result += "\nAngularJS";
                }
                if(java.isChecked()){
                    result += "\nJava";
                }
                if(python.isChecked()){
                    result += "\nPython";
                }
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        String str="";
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.chkAndroid:
                str = checked?"Android Selected":"Android Deselected";
                break;
            case R.id.chkAngular:
                str = checked?"AngularJS Selected":"AngularJS Deselected";
                break;
            case R.id.chkJava:
                str = checked?"Java Selected":"Java Deselected";
                break;
            case R.id.chkPython:
                str = checked?"Python Selected":"Python Deselected";
                break;
        }
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}