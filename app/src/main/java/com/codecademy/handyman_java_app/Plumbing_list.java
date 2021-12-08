package com.codecademy.handyman_java_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Plumbing_list extends AppCompatActivity {

    TextView txt_input, p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumbing_list);

        txt_input = findViewById(R.id.txt_input);
        p2 = findViewById(R.id.p2);

        //this is the navigation bar at the bottom of client's home screen to view items like
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().setGroupCheckable(0, false, true);

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
                        /* when we click home button again, we are taken back to the client's home
                        screen where their name is shown which they used for registration */
                        finish();
                        return true;

                    default: return true;
                }
            }
        });
    }
}