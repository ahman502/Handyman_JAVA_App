package com.codecademy.handyman_java_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    //declaring the variables that will be used
    EditText email_input, password_input;
    Button submit_btn, sign_up_btn, forgot_password;
    boolean isEmailValid, isPasswordValid;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing all the variables using the ids used in the layout files
        email_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);
        submit_btn = findViewById(R.id.submit_btn);
        sign_up_btn = findViewById(R.id.sign_up_btn);
        forgot_password = findViewById(R.id.forgot_password);
        fAuth = FirebaseAuth.getInstance();

        //setting on click listeners on the buttons (sign in and sign up) of registration screen
        submit_btn.setOnClickListener(v -> SetValidation());                      //sign in button to sign into the application
        sign_up_btn.setOnClickListener(v -> openNewActivity());                   //sign up button to take to registration form

        fAuth = FirebaseAuth.getInstance();

        /*sign_up_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String sEmail = email_input.getText().toString().trim();
                String sPassword = password_input.getText().toString().trim();

                if(TextUtils.isEmpty(sEmail)){
                    email_input.setError("Email Required");
                    return;
                }
                if(TextUtils.isEmpty(sPassword)){
                    password_input.setError("Password Required");
                    return;
                }
                //reg user
                fAuth.createUserWithEmailAndPassword(sEmail,sPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),Register.class));
                        }else{

                        }
                    }
                });
            }
        });*/

    }

    //function to open and display a new activity
    public void openNewActivity(){
        //when this function is called, we will be navigated to the register.xml file (Register.java class)
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    // function to check for validation of multiple data fields
    public void SetValidation() {

        // Check for a valid email address.
        if (email_input.getText().toString().isEmpty()) {
            //if the email field is empty we get an error that the field is empty
            email_input.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        }
        //if the email address is missing a pattern like @ symbol or .com extension then we get an error
        else if (!Patterns.EMAIL_ADDRESS.matcher(email_input.getText().toString()).matches()) {
            email_input.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        }
        // otherwise the email address is valid and proceed with the registration form
        else  {
            isEmailValid = true;
        }

        // Check for a valid password.
        if (password_input.getText().toString().isEmpty()) {
            // if password field is empty, we get an error that the field is empty
            password_input.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        }
        // if the length of the password is less than 8 characters, we get an error
        else if (password_input.getText().length() < 8) {
            password_input.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        }
        // if the isValidPassword function returns false then the password is missing an uppercase, lowercase, special character or a digit
        else if (!isValidPassword(password_input.getText().toString())){
            password_input.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        }
        // otherwise the password is valid
        else {
            isPasswordValid = true;
        }

        // if both email and password are valid then we can proceed with the login
        if (isEmailValid && isPasswordValid) {
            //display a login successful toast message
            Toast.makeText(getApplicationContext(), "Sign in successful", Toast.LENGTH_SHORT).show();
            //hide the keyboard after we are done typing on the emulator's keyboard
            password_input.onEditorAction(EditorInfo.IME_ACTION_DONE);
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


