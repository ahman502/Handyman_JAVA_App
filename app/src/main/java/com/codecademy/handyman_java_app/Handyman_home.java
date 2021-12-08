package com.codecademy.handyman_java_app;

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
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Handyman_home extends AppCompatActivity {

    TextView firstname_used_for_signup, about, txtView;
    CheckBox homeCare, electricalServices, plumbingServices, pestControl, applianceServices, autoCare;
    EditText about_me;
    Button updateInfo;
    String str, str2;
    LinearLayout ll, hll;
    // One Preview Image
    ImageButton IVPreviewImage;
    // constant to compare the activity result code
    int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handyman_home);

        //setting the first name to display in a specific TextView. This TextView is found by id (HandymanNameForHomeScreen, in this case)
        firstname_used_for_signup = findViewById(R.id.HandymanNameForHomeScreen);
        //getting the intent
        Intent intent1 = getIntent();
        str = intent1.getStringExtra("fname");
        str2 = intent1.getStringExtra("lname");
        //displaying the string with welcome message
        firstname_used_for_signup.setText("Welcome, " + str + "!");

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

        SpannableString ss = new SpannableString(txtView.getText());
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD_ITALIC);
        ss.setSpan(boldSpan, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtView.setText(ss);

        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(electricalServices.isChecked()){
                    openElectricActivity();
                }
                else if(plumbingServices.isChecked()){
                    openPlumbingActivity();
                }
                else if(!homeCare.isChecked() && !electricalServices.isChecked() && !plumbingServices.isChecked()
                        && !pestControl.isChecked() && !applianceServices.isChecked() && !autoCare.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Please select a checkbox to continue", Toast.LENGTH_LONG).show();
                }
                else {
                Toast.makeText(getApplicationContext(), "Stay tuned, feature coming soon!", Toast.LENGTH_LONG).show(); }

            }
        });

        about_me.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                int len = about_me.length();
                about.setText("About Me (Optional): " + String.valueOf(100 - len) + " char. left");
                if (100 - len < 0) {
                    about.setText("About Me (Optional): Limit Exceeded!");
                    about_me.setTextColor(Color.RED);
                } else
                    about_me.setTextColor(Color.WHITE);
            }
        });

        // register the UI widgets with their appropriate
        IVPreviewImage = findViewById(R.id.IVPreviewImage);

        // handle the Choose Image button to trigger the image chooser function
        IVPreviewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

    }

    // this function is triggered when the Select Image Button is clicked
    void imageChooser() {

        // create an instance of the intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    IVPreviewImage.setImageURI(selectedImageUri);
                }
            }
        }
    }

    //function to open and display a new activity
    public void openPlumbingActivity(){
        //Intent intent6 = new Intent(Handyman_home.this, Plumbing_list.class);
        //startActivity(intent6);
        ll.setVisibility(View.GONE);
        hll.setVisibility(View.VISIBLE);
    }

    //function to open and display a new activity
    public void openElectricActivity(){
        //when this function is called, we will be navigated to the activity_main.xml file (MainActivity.java class)
        Intent intent7 = new Intent(Handyman_home.this, Electric_list.class);
        startActivity(intent7);
    }

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
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }

}