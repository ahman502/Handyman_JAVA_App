package com.codecademy.handyman_java_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText firstName, lastName, email_address, password, confirm_password;
    boolean isFirstValid, isLastValid, isEmailValid, isPasswordValid;
    Button submit_btn, sign_up_btn;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email_address = findViewById(R.id.email_address);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        sign_up_btn = findViewById(R.id.sign_up_btn);
        submit_btn = findViewById(R.id.submit_btn);

        sign_up_btn.setOnClickListener(v -> SetValidation());

        submit_btn.setOnClickListener(v -> {
            // redirect to Login Activity
            openNewActivity();
        });

        spinner = findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.categories, R.layout.spinner_item);
        spinner.setAdapter(adapter);

    }

    public void openNewActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void SetValidation() {
        // Check for a valid first name.
        if (firstName.getText().toString().isEmpty()) {
            firstName.setError(getResources().getString(R.string.name_error));
            isFirstValid = false;
        } else  {
            isFirstValid = true;
        }

        // Check for a valid last name.
        if (lastName.getText().toString().isEmpty()) {
            lastName.setError(getResources().getString(R.string.name_error));
            isLastValid = false;
        } else  {
            isLastValid = true;
        }

        // Check for a valid email address.
        if (email_address.getText().toString().isEmpty()) {
            email_address.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email_address.getText().toString()).matches()) {
            email_address.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }

        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            password.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            password.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
        }

        if (isFirstValid && isLastValid && isEmailValid && isPasswordValid) {
            Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
        }

    }
}