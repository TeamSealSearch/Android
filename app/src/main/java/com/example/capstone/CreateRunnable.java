package com.example.capstone;

import android.content.Intent;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateRunnable implements Runnable {

    private CreateProfile main;
    private String INSERT_APPLICANT = "INSERT INTO APPLICANT (a_hashedid, a_username, a_fname, a_lname, a_dob) VALUES (%s, %s, %s, %s, %s)";
    private JSONObject userData = new JSONObject();

    private boolean employerMode;
    private final String hashedID;
    private final String tableToQuery;
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String date_of_birth;
    private final String email;
    private final String phone;

    CreateRunnable (CreateProfile m, String id, String uname, String fName, String lName, String DOB, String em, String ph, boolean mode) throws SQLException {
        main = m;
        hashedID = id;
        userName = uname;
        firstName = fName;
        lastName = lName;
        date_of_birth = DOB;
        employerMode = mode;
        email = em;
        phone = ph;

        if (mode)
            tableToQuery = "EMPLOYER";
        else
            tableToQuery = "APPLICANT";
    }

    @Override
    public void run() {
        try {

            String baseURL = "http://5c7975e28534.ngrok.io/createApplicant?hashedid=%s&username=%s&fname=%s&lname=%s&dob=%s&city=%s&state=%s&phonenumber=%s&email=%s";
            baseURL = String.format(baseURL, hashedID, userName, firstName, lastName, date_of_birth, "chicago", "il", phone, email);
            URL url = new URL(baseURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //Fake data because connect statement won't work.
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();

            JSONObject userData = new JSONObject(sb.toString());

            JSONObject filter = new JSONObject();
            filter.put("f1", "Engineering");
            filter.put("f2", "Liberal Arts");
            filter.put("f3", "History");
            filter.put("f4", "Humanities");
            filter.put("f5", "Medical");
            filter.put("f6", "Business");

            userData.put("filters", filter);

            main.runOnUiThread(() -> main.catchLogin(userData));
        }

        catch (Exception e) {e.printStackTrace();}
    }
}
