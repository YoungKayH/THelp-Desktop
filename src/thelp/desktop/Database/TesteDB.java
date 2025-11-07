/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thelp.desktop.Database;

import thelp.desktop.Database.DatabaseConnection;
import java.sql.Connection;
/**
 *
 * @author kaka2
 */
public class TesteDB {
    public static void main(String[] args) {
        try {
            System.out.println("URL: " + thelp.desktop.constants.Constants.Database.CONNECTION_URL);

            Connection conn = DatabaseConnection.getConnection();

            if (conn != null) {
                System.out.println("✅ Conectado ao PostgreSQL!");
            } else {
                System.out.println("❌ Conexão retornou NULL!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

