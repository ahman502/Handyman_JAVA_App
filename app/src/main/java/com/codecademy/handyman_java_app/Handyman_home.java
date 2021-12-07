package com.codecademy.handyman_java_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Handyman_home extends AppCompatActivity {

    TextView firstname_used_for_signup;
    CheckBox homeCare, electricalServices, plumbingServices, pestControl, applianceServices, autoCare;
    EditText about_me;
    TextView about;

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

        homeCare = findViewById(R.id.check_home_care);
        electricalServices = findViewById(R.id.check_electric);
        plumbingServices = findViewById(R.id.check_plumbing);
        pestControl = findViewById(R.id.check_pest_control);
        applianceServices = findViewById(R.id.check_appliances);
        autoCare = findViewById(R.id.check_auto_care);
        about_me = findViewById(R.id.Handyman_about_me);
        about = findViewById(R.id.about_me_label);
        Button updateInfo = findViewById(R.id.update_info_btn);

        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "Selected: ";

                if(!homeCare.isChecked() && !electricalServices.isChecked() && !plumbingServices.isChecked()
                        && !pestControl.isChecked() && !applianceServices.isChecked() && !autoCare.isChecked()) {
                    result += "Nothing\nPlease select a checkbox to continue";
                }
                if(homeCare.isChecked()){
                    result += "\nHome Care";
                }
                if(electricalServices.isChecked()){
                    result += "\nElectric";
                }
                if(plumbingServices.isChecked()){
                    result += "\nPlumbing";
                }
                if(pestControl.isChecked()){
                    result += "\nPest Control";
                }
                if(applianceServices.isChecked()){
                    result += "\nAppliances";
                }
                if(autoCare.isChecked()){
                    result += "\nAuto Care";
                }
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
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
                about.setText("About Me: " + String.valueOf(100 - len) + " char. left");
                if (100 - len < 0) {
                    about.setText("About Me: Limit Exceeded!");
                    about_me.setTextColor(Color.RED);
                } else
                    about_me.setTextColor(Color.WHITE);
            }
        });
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