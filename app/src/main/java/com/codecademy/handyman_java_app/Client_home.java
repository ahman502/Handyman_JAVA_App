package com.codecademy.handyman_java_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Client_home extends AppCompatActivity {

    //variables
    TextView firstname_used_for_signup;
    ImageButton plumbing_list;
    ImageButton electrician_list;
    LinearLayout linearLayout;
    Animation slideUp, slideDown;
    Button saveChangesButton;
    EditText textView2, textView3, editTextTextEmailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);

        //setting the first name to display in a specific TextView. This TextView is found by id (clientName, in this case)
        firstname_used_for_signup = findViewById(R.id.clientName);
        //getting the intent
        Intent intent1 = getIntent();
        String str = intent1.getStringExtra("fname");
        //get the last name of the user that was used during registration
        String user_lname = intent1.getStringExtra("lname");
        //get the email address of the user that was used during registration
        String user_eaddress =  intent1.getStringExtra("eAddress");
        //displaying the string with welcome message
        firstname_used_for_signup.setText("Welcome, " + str + "!");

        //settings page layout
        linearLayout = findViewById(R.id.settings_linearLayout);

        //getting the textView that will display the first name of user used during sign up
        textView2 = findViewById(R.id.textView2);
        //getting the textView that will display the last name of user used during sign up
        textView3 = findViewById(R.id.textView3);
        //getting the textView that will display the email of user used during sign up
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);

        //this will set the text of the textView in settings to the user's first name used for registration
        textView2.setText(str);
        //this will set the text of the textView in settings to the user's last name used for registration
        textView3.setText(user_lname);
        //this will set the text of the textView in settings to the user's email used for registration
        editTextTextEmailAddress.setText(user_eaddress);

        //this is the save changes button on the settings layout
        saveChangesButton = findViewById(R.id.saveChangesButton);
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //upon clicking the button, the first name of the user is changed on the home screen in the welcome message, and a toast message is shown
                firstname_used_for_signup.setText("Welcome, " + textView2.getText().toString() + "!");
                Toast.makeText(getApplicationContext(), "Changes Saved!", Toast.LENGTH_LONG).show();
            }
        });

        //this is the navigation bar at the bottom of client's home screen to view items like
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            //when a client selects one of the options of the navigation bar
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //first we get the id of each menu item
                switch (item.getItemId()) {

                    //if the id is notification, we are taken to a page that displays their notifications
                    case R.id.notification:

                    //if the id is chat, then the chatting screen is displayed
                    case R.id.chat:
                        //bottomNavigationView.getMenu().setGroupCheckable(0, true, true);
                        Toast.makeText(getApplicationContext(), "Stay tuned, feature coming soon!", Toast.LENGTH_SHORT).show();
                        return false;

                    //if the id is settings, then the settings are displayed
                    case R.id.settings:
                        slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.layout_slide_up);
                        slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.layout_slide_down);

                        if(linearLayout.getVisibility() == View.INVISIBLE){

                            linearLayout.startAnimation(slideUp);
                            linearLayout.setVisibility(View.VISIBLE);
                        }
                        return true;

                    //if the id is home, then the home is displayed
                    case R.id.home:
                        slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.layout_slide_up);
                        slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.layout_slide_down);

                        if(linearLayout.getVisibility() == View.VISIBLE){

                            linearLayout.startAnimation(slideDown);
                            linearLayout.setVisibility(View.INVISIBLE);
                        }
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