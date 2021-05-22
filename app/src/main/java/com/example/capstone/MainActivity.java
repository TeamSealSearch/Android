package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "LoginScreen";
    private boolean employerMode = false;
    private String toggleFormatString = "Switch to %s";

    private EditText usernameField;
    private EditText passwordField;
    private Button loginButton;
    private Button createButton;
    private Button toggleModeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = findViewById(R.id.loginUsername);
        passwordField = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        createButton = findViewById(R.id.loginCreateButton);

        //Graphical set up
        toggleModeButton = findViewById(R.id.loginToggleButton);
        toggleModeButton.setText(String.format(toggleFormatString, "Employer"));

        setTheme(R.style.EmployerTheme);
    }

    public void toggleMode(View v) {
        employerMode = !employerMode;

        if (employerMode) {
            setTheme(R.style.EmployerTheme);
            toggleModeButton.setText(String.format(toggleFormatString, "Applicant"));
        }
        else {
            setTheme(R.style.ApplicantTheme);
            toggleModeButton.setText(String.format(toggleFormatString, "Employer"));
        }
    }

    public void createUser(View v) throws SQLException {
        Intent toCreateProfile = new Intent(this, CreateProfile.class);
        toCreateProfile.putExtra("mode", employerMode);
        startActivity(toCreateProfile);
    }

    public void validateLogin(View v) throws SQLException {
        LoginRunnable toVal = new LoginRunnable(
            this,
            usernameField.getText().toString(),
            employerMode
        );
        new Thread(toVal).start();
    }

    public void validLogin (View v) throws SQLException {
//        Intent toProfile = new Intent(this, JobsHub.class);

        //check if valid login


//        if (employerMode) {
//            //build an employer intent
//            toProfile.putExtra("name", "John Sins");
//            toProfile.putExtra("mode", employerMode);
//            startActivity(toProfile);
//        }
//
//        else { //build an applicant intent
//            toProfile.putExtra("name", "John Sins");
//            toProfile.putExtra("mode", employerMode);
//            startActivity(toProfile);
//        }
    }
}