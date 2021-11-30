package com.codecademy.handyman_java_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email_input, password_input;
    Button submit_btn, sign_up_btn, forgot_password;
    boolean isEmailValid, isPasswordValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);
        submit_btn = findViewById(R.id.submit_btn);
        sign_up_btn = findViewById(R.id.sign_up_btn);
        forgot_password = findViewById(R.id.forgot_password);

        submit_btn.setOnClickListener(v -> SetValidation());

        sign_up_btn.setOnClickListener(v -> {
            // redirect to Register Activity
            openNewActivity();
        });
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void SetValidation() {
        // Check for a valid email address.
        if (email_input.getText().toString().isEmpty()) {
            email_input.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email_input.getText().toString()).matches()) {
            email_input.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }

        // Check for a valid password.
        if (password_input.getText().toString().isEmpty()) {
            password_input.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password_input.getText().length() < 6) {
            password_input.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
        }

        if (isEmailValid && isPasswordValid) {
            Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
        }

    }


}