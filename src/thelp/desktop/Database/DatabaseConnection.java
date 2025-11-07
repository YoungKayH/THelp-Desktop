/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thelp.desktop.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import thelp.desktop.constants.Constants;

/**
 *
 * @author kaka2
 */
public class DatabaseConnection 
{
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                    Constants.Database.CONNECTION_URL,
                    Constants.Database.USERNAME,
                    Constants.Database.PASSWORD
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
