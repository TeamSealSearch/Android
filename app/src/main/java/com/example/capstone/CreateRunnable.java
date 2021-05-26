package com.example.capstone;

import android.content.Intent;

import org.json.JSONObject;

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

    CreateRunnable (CreateProfile m, String id, String uname, String fName, String lName, String DOB, boolean mode) throws SQLException {
        main = m;
        hashedID = id;
        userName = uname;
        firstName = fName;
        lastName = lName;
        date_of_birth = DOB;
        employerMode = mode;

        if (mode)
            tableToQuery = "EMPLOYER";
        else
            tableToQuery = "APPLICANT";
    }

    @Override
    public void run() {
        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            String azureURL = "jdbc:mysql://sealsearch.mysql.database.azure.com:3306/sealdb?useSSL=true&requireSSL=true";
//            Connection conn = DriverManager.getConnection(azureURL, "kingSeal@sealsearch", "Password1");
//
//            Statement stmt = conn.createStatement();
//            INSERT_APPLICANT = String.format(INSERT_APPLICANT, "'WhatsAHashedID'", userName, firstName, lastName, date_of_birth);
//
//            int rs = stmt.executeUpdate(INSERT_APPLICANT);
//
//            System.out.println("HERE -->\t" + rs);

            //Fake data because connect statement won't work.
            JSONObject userData = new JSONObject();
            userData.put("userName", userName);
            userData.put("firstName", firstName);
            userData.put("lastName", lastName);
            userData.put("DOB", date_of_birth);
            
            main.runOnUiThread(() -> main.catchLogin(userData));
        }

        catch (Exception e) {e.printStackTrace();}
    }
}
