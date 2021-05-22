package com.example.capstone;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginRunnable implements Runnable {

    private MainActivity main;
    private String QUERY = "SELECT * FROM %s WHERE a_username = '%s'"; //query for finding users with matching uName
    private JSONObject loginInformation;

    private final String tableToQuery;
    private final String userName;

    LoginRunnable (MainActivity m, String uname, boolean mode) throws SQLException {
        main = m;
        userName = uname;

        if (mode == true)
            tableToQuery = "EMPLOYER";
        else
            tableToQuery = "APPLICANT";
    }

    @Override
    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String azureURL = "jdbc:mysql://sealsearch.mysql.database.azure.com:3306/sealdb?useSSL=true&requireSSL=true";
            Connection conn = DriverManager.getConnection(azureURL, "kingSeal@sealsearch", "Password1");

            Statement queryStatement = conn.createStatement();
            QUERY = String.format(QUERY, tableToQuery, userName);

            StringBuilder sb = new StringBuilder();

            ResultSet rs = queryStatement.executeQuery(QUERY);
            rs.next();
            System.out.println("HERE -->\t" + rs.getString("a_fname"));
            System.out.println("HERE -->\t" + rs.getString("a_lname"));

        }

        catch (Exception e) {e.printStackTrace();}
    }
}
