package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateProfile extends AppCompatActivity {
    Intent i;
    boolean employerMode;

    EditText fnameField;
    EditText lnameField;
    EditText unameField;
    EditText dobField;

    EditText passwordField;
    EditText emailField;
    EditText phoneNumberField;

    EditText experienceNumber;
    EditText problemSolvingNumber;
    EditText degreeNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        i = getIntent();
        employerMode = Boolean.parseBoolean(i.getStringExtra("mode"));

        fnameField = findViewById(R.id.createFirstName);
        lnameField = findViewById(R.id.createLastName);
        unameField = findViewById(R.id.createUsername);
        dobField = findViewById(R.id.createDOB);

        experienceNumber = findViewById(R.id.createExperience);
        problemSolvingNumber = findViewById(R.id.createProblemSolving);
        degreeNumber = findViewById(R.id.createDegree);
        setupFields();
    }

    public void createUser(View v) throws SQLException {
        Log.d("CREATE", "WORKED");

        CreateRunnable login = new CreateRunnable(this,
        "whatIsThisSupposedTobe",
            unameField.getText().toString(),
            fnameField.getText().toString(),
            lnameField.getText().toString(),
            dobField.getText().toString(),
            employerMode
        );
//        login.run();
    }

    private void setupFields() {
        Calendar myCalendar = Calendar.getInstance();
        /* find textview*/

        /* and copy the fallowing code*/

        final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                dobField.setText(sdf.format(myCalendar.getTime()));
            }

        };


        dobField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CreateProfile.this, datePickerListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

    }
}