package com.codecademy.handyman_java_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Random;

public class Handyman_home extends AppCompatActivity {

    //variables
    TextView firstname_used_for_signup, about, txtView;
    CheckBox homeCare, electricalServices, plumbingServices, pestControl, applianceServices, autoCare;
    EditText about_me, textView2, textView3, editTextTextEmailAddress;
    Button updateInfo, save_btn, add_more_images_btn, saveChangesButton, logout_btn;
    Uri selectedImageUri;
    Animation slideUp, slideDown;
    String str, user_lname, user_eaddress;
    LinearLayout ll, hll, linearLayout;
    ImageButton IVPreviewImage, IVPreviewImage2, IVPreviewImage3, profileImage;
    int SELECT_PICTURE = 200;
    boolean clicked = false;
    boolean clicked2 = false;
    boolean clicked3 = false;
    boolean clicked4 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handyman_home);

        //setting the first name to display in a specific TextView. This TextView is found by id (HandymanNameForHomeScreen, in this case)
        firstname_used_for_signup = findViewById(R.id.HandymanNameForHomeScreen);
        //getting the intent
        Intent intent1 = getIntent();
        str = intent1.getStringExtra("fname");
        //get the last name of the user that was used during registration
        user_lname = intent1.getStringExtra("lname");
        //get the email address of the user that was used during registration
        user_eaddress =  intent1.getStringExtra("eAddress");
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

        //initializing all the variables by getting their id
        homeCare = findViewById(R.id.check_home_care);
        electricalServices = findViewById(R.id.check_electric);
        plumbingServices = findViewById(R.id.check_plumbing);
        pestControl = findViewById(R.id.check_pest_control);
        applianceServices = findViewById(R.id.check_appliances);
        autoCare = findViewById(R.id.check_auto_care);
        about_me = findViewById(R.id.Handyman_about_me);
        about = findViewById(R.id.about_me_label);
        updateInfo = findViewById(R.id.update_info_btn);
        ll = findViewById(R.id.handyman_linear_layout);
        ll.setVisibility(View.VISIBLE);
        hll = findViewById(R.id.handyman_home_linear_layout);
        hll.setVisibility(View.GONE);
        txtView = findViewById(R.id.txtView);
        save_btn = findViewById(R.id.save_btn);
        add_more_images_btn = findViewById(R.id.add_more_images_btn);

        //this will make part of a TextView bold on the Handyman home screen
        SpannableString ss = new SpannableString(txtView.getText());
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD_ITALIC);
        ss.setSpan(boldSpan, 0, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtView.setText(ss);

        //this is for the optional description of the handyman
        about_me.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                int len = about_me.length();
                //to heck the character length of the about me description
                about.setText("About Me (Optional): " + String.valueOf(100 - len) + " char. left");

                if (100 - len < 0) {
                    //if 100 characters have been typed a message is shown and the text turns red color
                    about.setText("About Me (Optional): Limit Exceeded!");
                    about_me.setTextColor(Color.RED);
                } else
                    //otherwise the text remains white
                    about_me.setTextColor(Color.WHITE);
            }
        });

        //click event on a button that updates the Handyman's services offered
        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if the Plumbing checkbox is checked, and then the update button is clicked, the handyman is brought to their homescreen
                if(plumbingServices.isChecked()){
                    openPlumbingActivity();
                }
                if(electricalServices.isChecked()){
                    openElectricActivity();
                }
                //if no checkboxes are checked, a toast message is shown and update button doesn't work
                else if(!homeCare.isChecked() && !electricalServices.isChecked() && !plumbingServices.isChecked()
                        && !pestControl.isChecked() && !applianceServices.isChecked() && !autoCare.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Please select a checkbox to continue", Toast.LENGTH_SHORT).show();
                }
                //for all other cases, the feature doesn't work so a Toast message is shown
                else {
                Toast.makeText(getApplicationContext(), "Stay tuned, feature coming soon!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //register the UI widgets with their appropriate image views
        IVPreviewImage = findViewById(R.id.IVPreviewImage);
        IVPreviewImage2 = findViewById(R.id.IVPreviewImage2);
        IVPreviewImage3 = findViewById(R.id.IVPreviewImage3);
        profileImage = findViewById(R.id.profileImage);

        //handle the Choose Image button to trigger the image chooser function
        IVPreviewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = true;
                clicked2 = false;
                clicked3 = false;
                clicked4 = false;
                imageChooser();
            }
        });

        //handle the Choose Image button to trigger the image chooser function
        IVPreviewImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked2 = true;
                clicked = false;
                clicked3 = false;
                clicked4 = false;
                imageChooser();
            }
        });

        //handle the Choose Image button to trigger the image chooser function
        IVPreviewImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked3 = true;
                clicked2 = false;
                clicked = false;
                clicked4 = false;
                imageChooser();
            }
        });

        //to upload a profile picture for the handyman
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked3 = false;
                clicked2 = false;
                clicked = false;
                clicked4 = true;
                imageChooser();
            }
        });

        //this feature is not implemented yet so just a toast message is displayed when save button is clicked
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Stay tuned, feature coming soon!", Toast.LENGTH_SHORT).show();
            }
        });

        //this feature is not implemented yet so just a toast message is displayed when the user tries to upload more work photos
        add_more_images_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Stay tuned, feature coming soon!", Toast.LENGTH_SHORT).show();
            }
        });

        //this is the navigation bar at the bottom of client's home screen to view items like
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.layout_slide_up);
        slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.layout_slide_down);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            //when a client selects one of the options of the navigation bar
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //first we get the id of each menu item
                switch (item.getItemId()) {

                    //if the id is notification, we are taken to a page that displays their notifications
                    case R.id.notification:

                    //if the id is chat, then the chatting screen is displayed
                    case R.id.chat:
                        Toast.makeText(getApplicationContext(), "Stay tuned, feature coming soon!", Toast.LENGTH_SHORT).show();
                        return false;

                    //if the id is settings, then the settings are displayed
                    case R.id.settings:

                        if(linearLayout.getVisibility() == View.INVISIBLE){

                            linearLayout.startAnimation(slideUp);
                            linearLayout.setVisibility(View.VISIBLE);
                        }
                        return true;

                    //if the id is home, then the home is displayed
                    case R.id.home:

                        if(linearLayout.getVisibility() == View.VISIBLE){

                            linearLayout.startAnimation(slideDown);
                            linearLayout.setVisibility(View.INVISIBLE);
                        }
                        return true;

                    default: return true;
                }
            }
        });

        logout_btn = findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(Handyman_home.this, MainActivity.class);
                startActivity(logout);
                finish();
            }
        });
    }

    // this function is triggered when the ImageButton is clicked
    void imageChooser() {
        // create an instance of the intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select An Image"), SELECT_PICTURE);
    }

    // this function is triggered when user selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if the first add image - ImageButton is clicked, then we add an image to only that first image view
        if(clicked) {
            if (resultCode == RESULT_OK) {
                // compare the resultCode with the SELECT_PICTURE constant
                if (requestCode == SELECT_PICTURE) {
                    // Get the url of the image from data
                    selectedImageUri = data.getData();
                    if (null != selectedImageUri) {
                        IVPreviewImage.setImageURI(selectedImageUri);
                    }
                }
            }
        }

        //if the second add image - ImageButton is clicked, then we add an image to only that second image view
        if(clicked2) {
            if (resultCode == RESULT_OK) {
                // compare the resultCode with the SELECT_PICTURE constant
                if (requestCode == SELECT_PICTURE) {
                    // Get the url of the image from data
                    selectedImageUri = data.getData();
                    if (null != selectedImageUri) {
                        IVPreviewImage2.setImageURI(selectedImageUri);
                    }
                }
            }
        }

        //if the third add image - ImageButton is clicked, then we add an image to only that third image view
        if(clicked3) {
            if (resultCode == RESULT_OK) {
                // compare the resultCode with the SELECT_PICTURE constant
                if (requestCode == SELECT_PICTURE) {
                    // Get the url of the image from data
                    selectedImageUri = data.getData();
                    if (null != selectedImageUri) {
                        IVPreviewImage3.setImageURI(selectedImageUri);
                    }
                }
            }
        }

        //if the profile image - ImageButton is clicked, then we add an image to only that view
        if(clicked4) {
            if (resultCode == RESULT_OK) {
                // compare the resultCode with the SELECT_PICTURE constant
                if (requestCode == SELECT_PICTURE) {
                    // Get the url of the image from data
                    selectedImageUri = data.getData();
                    if (null != selectedImageUri) {
                        profileImage.setImageURI(selectedImageUri);
                    }
                }
            }
        }
    }

    //function to open and display a new activity
    public void openPlumbingActivity(){
        ll.setVisibility(View.GONE);
        hll.setVisibility(View.VISIBLE);
    }

    public void openElectricActivity() {

        //creating an intent to go to next screen
        Intent intent00 = new Intent(this, Client_home.class);

        //using the putExtra method (key, value) pair to get the name of the client
        intent00.putExtra("efname", str);
        //take the last name to the next activity
        intent00.putExtra("elname", user_lname);
        //take the email address to the next activity
        intent00.putExtra("eeAddress", user_eaddress);

        //starting the activity (going to next screen)
        startActivity(intent00);
        finish();
    }

    //when a checkbox is clicked on the Handyman screen, we display a toast message for which option was selected
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        String str = "";
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.check_home_care:
                str = checked?"Home Care Selected":"Home Care Unselected";
                break;
            case R.id.check_electric:
                str = checked?"Electric Selected":"Electric Unselected";
                break;
            case R.id.check_plumbing:
                str = checked?"Plumbing Selected":"Plumbing Unselected";
                break;
            case R.id.check_pest_control:
                str = checked?"Pest Control Selected":"Pest Control Unselected";
                break;
            case R.id.check_appliances:
                str = checked?"Appliances Selected":"Appliances Unselected";
                break;
            case R.id.check_auto_care:
                str = checked?"Auto Care Selected":"Auto Care Unselected";
                break;
        }
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    /* ----- THIS COMMENTED OUT CODE IS TO ENABLE LOCATION SERVICE BUT IT DOES NOT WORK -----
    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    } */



}