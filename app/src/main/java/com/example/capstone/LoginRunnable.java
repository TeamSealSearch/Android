package com.example.capstone;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginRunnable implements Runnable {

    private CreateProfile main;
    private String INSERT_APPLICANT = "INSERT INTO APPLICANT (a_hashedid, a_username, a_fname, a_lname, a_dob) VALUES (%s, %s, %s, %s, %s)";
    private JSONObject loginInformation;

    private final String hashedID;
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String date_of_birth;

    LoginRunnable(CreateProfile m, String id, String uname, String fName, String lName, String DOB) throws SQLException {
        main = m;
        hashedID = id;
        userName = uname;
        firstName = fName;
        lastName = lName;
        date_of_birth = DOB;
    }

    @Override
    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String azureURL = "jdbc:mysql://sealsearch.mysql.database.azure.com:3306/sealdb?useSSL=true&requireSSL=true";
            Connection conn = DriverManager.getConnection(azureURL, "kingSeal@sealsearch", "Password1");

            Statement queryStatement = conn.createStatement();
            INSERT_APPLICANT = String.format(INSERT_APPLICANT, "'testHash1'", "'testUname'", "'testFname'", "'testLname'", "'2020-04-20'");

            StringBuilder sb = new StringBuilder();

            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate(INSERT_APPLICANT);

            System.out.println("HERE -->\t" + rs);
        }

        catch (Exception e) {e.printStackTrace();}
    }
}
