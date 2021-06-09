package com.example.capstone;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginRunnable implements Runnable {

    private MainActivity main;
    private String QUERY = "SELECT * FROM %s WHERE %s = '%s'"; //query for finding users with matching uName
    private JSONObject loginInformation = new JSONObject();
    private final boolean mode;

    private final String tableToQuery;
    private final String userName;
    private final String usernameColumn;

    LoginRunnable (MainActivity m, String uname, boolean mode) throws SQLException {
        main = m;
        userName = uname;
        this.mode = mode;

        if (mode) {
            tableToQuery = "EMPLOYER";
            usernameColumn = "e_username";
        }

        else {
            tableToQuery = "APPLICANT";
            usernameColumn = "a_username";
        }

    }

    @Override
    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String azureURL = "jdbc:mysql://thesealsearchserver.mysql.database.azure.com:3306/sealdb?useSSL=true&requireSSL=false";
            Connection conn = DriverManager.getConnection(azureURL, "KingSeal@thesealsearchserver", "Password1");

            Statement queryStatement = conn.createStatement();
            QUERY = String.format(QUERY, tableToQuery, usernameColumn, userName);

            ResultSet rs = queryStatement.executeQuery(QUERY);
            rs.next();

            if (!mode) { //if applicant
                try {
                    JSONObject filter = new JSONObject();
                    filter.put("f1", "Engineering");
                    filter.put("f2", "Liberal Arts");
                    filter.put("f3", "History");
                    filter.put("f4", "Humanities");
                    filter.put("f5", "Medical");
                    filter.put("f6", "Business");

                    loginInformation.put("userName", rs.getString("a_username"));
                    loginInformation.put("firstName", rs.getString("a_fname"));
                    loginInformation.put("lastName", rs.getString("a_lname"));
                    loginInformation.put("DOB", rs.getString("a_dob"));

                    loginInformation.put("filters", filter);
                } catch (SQLException e) {
                    main.runOnUiThread(() -> main.invalidLogin());
                    return;
                }
            }

            else { //if employer
                try {
                    loginInformation.put("companyName", rs.getString("e_companyName"));
                    loginInformation.put("username", rs.getString("e_username"));
                    loginInformation.put("firstName", rs.getString("e_fname"));
                    loginInformation.put("lastName", rs.getString("e_lname"));
                    loginInformation.put("imageBytes", rs.getString("e_androidEncode"));
                    loginInformation.put("DOB", rs.getString("e_dob"));
                } catch (SQLException e) {
                    main.runOnUiThread(() -> main.invalidLogin());
                    return;
                }
            }


            //all the other fields?
            conn.close();

            main.runOnUiThread(() -> main.validLogin(loginInformation));
        }

        catch (Exception e) {e.printStackTrace();}
    }
}
