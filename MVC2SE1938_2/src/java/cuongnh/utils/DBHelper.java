/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cuongnh.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DBHelper {
    public static Connection getConnection() 
        throws ClassNotFoundException, SQLException {
        Connection con = null;
        
        //1. Load Driver(List presents datatype using converts from DBMS to programming)
        //1.1 Add driver to project/ application
        //1.2 Write code to add Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. Create connection string to connects Container and DB
        //Syntax: protocol:DBMS_NAME://ip:port;databaseName=BD_Name
        String url = "jdbc:sqlserver://"
                + "localhost:1433;"
                + "databaseName=PRJSE1938";
        //3. Open connection using driver Manager ()
        con = DriverManager.getConnection(url, "sa", "12345");
        
        return con;
    }
}
