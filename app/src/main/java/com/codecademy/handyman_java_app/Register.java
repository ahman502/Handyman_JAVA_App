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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    //declaring the variables that will be used
    EditText firstName, lastName, email_address, password, confirm_password;
    boolean isFirstValid, isLastValid, isEmailValid, isPasswordValid, isConfirmPasswordValid;
    Button submit_btn, sign_up_btn;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing all the variables using the ids used in the layout files
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email_address = findViewById(R.id.email_address);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        sign_up_btn = findViewById(R.id.sign_up_btn);
        submit_btn = findViewById(R.id.submit_btn);

        //setting on click listeners on the buttons (sign in and sign up) of registration screen
        sign_up_btn.setOnClickListener(v -> SetValidation());               //sign up button to take to registration form
        submit_btn.setOnClickListener(v -> openNewActivity());              //sign in button to sign into the application

        //declaring a spinner
        spinner = findViewById(R.id.spinner);
        /*adapter for spinner that uses items in the strings.xml file to fill the spinner (drop down menu on registration page)
        and uses spinner_item.xml resource file for the layout (styling) of the spinner */
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.categories, R.layout.spinner_item);
        spinner.setAdapter(adapter);

    }

    //function to open and display a new activity
    public void openNewActivity(){
        //when this function is called, we will be navigated to the activity_main.xml file (MainActivity.java class)
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // function to check for validation of multiple data fields
    public void SetValidation() {

        // Check for a valid first name.
        if (firstName.getText().toString().isEmpty()) {
            //if the first name field is empty, we get an error that the field is empty
            firstName.setError(getResources().getString(R.string.name_error));
            isFirstValid = false;
        }
        //if it's filled with some information, we can proceed with the rest of the registration
        else {
            isFirstValid = true;
        }

        // Check for a valid last name.
        if (lastName.getText().toString().isEmpty()) {
            //if the last name field is empty, we get an error that the field is empty
            lastName.setError(getResources().getString(R.string.name_error));
            isLastValid = false;
        }
        //if it's filled with some information, we can proceed with the rest of the registration
        else {
            isLastValid = true;
        }

        // Check for a valid email address.
        if (email_address.getText().toString().isEmpty()) {
            //if the email field is empty we get an error that the field is empty
            email_address.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        }
        //if the email address is missing a pattern like @ symbol or .com extension then we get an error
        else if (!Patterns.EMAIL_ADDRESS.matcher(email_address.getText().toString()).matches()) {
            email_address.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        }
        // otherwise the email address is valid and proceed with the registration form
        else  {
            isEmailValid = true;
        }

        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            // if password field is empty, we get an error that the field is empty
            password.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        }
        // if the length of the password is less than 8 characters, we get an error
        else if (password.getText().length() < 8) {
            password.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        }
        // if the isValidPassword function returns false then the password is missing an uppercase, lowercase, special character or a digit
        else if (!isValidPassword(password.getText().toString())){
            password.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        }
        //otherwise we proceed with the registration form
        else  {
            isPasswordValid = true;
        }

        // Check for a valid confirm password.
        if (confirm_password.getText().toString().isEmpty()) {
            // if confirm password field is empty, we get an error that the field is empty
            confirm_password.setError(getResources().getString(R.string.confirm_password));
            isConfirmPasswordValid = false;
        }
        // if confirm password is not the same as the password, we get a password mismatch message
        else if (!confirm_password.getText().toString().equals(password.getText().toString())) {
            confirm_password.setError(getResources().getString(R.string.passwords_dont_match));
            isConfirmPasswordValid = false;
        }
        // otherwise both passwords match and the we can proceed
        else {
            isConfirmPasswordValid = true;
        }

        // if all the fields are true then we can go ahead and register
        if (isFirstValid && isLastValid && isEmailValid && isPasswordValid && isConfirmPasswordValid) {
            Toast.makeText(getApplicationContext(), "Sign up successful", Toast.LENGTH_SHORT).show();
        }

    }

    //function to check if the entered password is valid using Java Regex. Takes in a password as the argument
    public boolean isValidPassword(String password) {

        //using Java's pattern and matcher classes with Regex
        Pattern pattern;
        Matcher matcher;

        // password criteria not format: digit + lowercase char + uppercase char + punctuation + symbol + length
        // At least one lowercase character, one uppercase character, one digit, one special character, and length between 8 to 20
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        //get the result using matches() method
        return matcher.matches();

    }
}