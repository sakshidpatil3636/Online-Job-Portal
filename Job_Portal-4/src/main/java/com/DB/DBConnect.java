/*package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

private static Connection Conn;


public static Connection getconn() {

try {
if(Conn==null) {
Class.forName("com.mysql.cj.jdbc.Driver");
Conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal?autoReconnect=true&useSSL=false", "root", "root");
            //Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal","root","root");

}

} catch (Exception e){
e.printStackTrace();
}


return Conn;
}
}*/

package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static Connection conn = null;

    // Get the database connection
    public static Connection getconn() {
        try {
            // Check if connection is null or closed
            if (conn == null || conn.isClosed()) {
                // Load MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Replace with your database details
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/job_portal?autoReconnect=true&useSSL=false", "root", "@sakshi1292");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Log the error to understand the failure
        }
        return conn;
    }
}
