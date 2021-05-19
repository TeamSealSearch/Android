package com.example.capstone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginRunnable implements Runnable {

    private MainActivity main;
    private String INSERT_APPLICANT = "INSERT INTO APPLICANT (a_hashedid, a_username, a_fname, a_lname, a_dob) VALUES (%s, %s, %s, %s, %s)";

    private String azureURL = "jdbc:mysql://teamseal.mysql.database.azure.com:3306?useSSL=true&requireSSL=TRUE";
    private Connection conn = DriverManager.getConnection(azureURL, "seal_admin@teamseal", "Password1");

    private final String hashedID;
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String date_of_birth;

    LoginRunnable(MainActivity m, String id, String uname, String fName, String lName, String DOB) throws SQLException {
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
            Statement insertStatement = conn.createStatement();
            INSERT_APPLICANT = String.format(INSERT_APPLICANT, "testHash", "testUname", "testFname", "testLname", "testDOB");

            StringBuilder sb = new StringBuilder();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(INSERT_APPLICANT);

            System.out.println("HERE -->\t" + rs.toString());
        }

        catch (Exception e) {e.printStackTrace();}
    }
}
