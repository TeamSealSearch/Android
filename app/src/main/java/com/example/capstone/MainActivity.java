package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
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
        toggleModeButton = findViewById(R.id.loginToggleButton);
    }

    public void toggleMode(View v) {
        employerMode = !employerMode;

        if (employerMode)
            findViewById(R.id.mainConstraint).setBackgroundColor(Color.LTGRAY);
        else
            findViewById(R.id.mainConstraint).setBackgroundColor(Color.BLUE);
    }

    public void createUser(View v) throws SQLException {
        Intent toCreateProfile = new Intent(this, CreateProfile.class);
        toCreateProfile.putExtra("mode", employerMode);
        startActivity(toCreateProfile);
//        try {
//            LoginRunnable login = new LoginRunnable(this, "", "", "", "", "");
//            new Thread(login).start();
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void login (View v) throws SQLException {
        Intent toProfile = new Intent(this, JobsHub.class);

        if (employerMode) {
            //build an employer intent
            toProfile.putExtra("name", "John Sins");
            toProfile.putExtra("mode", employerMode);
            startActivity(toProfile);
        }

        else { //build an applicant intent
            toProfile.putExtra("name", "John Sins");
            toProfile.putExtra("mode", employerMode);
            startActivity(toProfile);
        }
    }
}