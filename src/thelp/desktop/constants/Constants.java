/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thelp.desktop.constants;

/**
 *
 * @author kaka2
 */
public class Constants {

    public static class Database {

        public static final String DATABASE_NAME = "thelp";
        public static final String HOST          = "localhost";
        public static final String PORT          = "5432";
        public static final String USERNAME      = "admin_thelp";
        public static final String PASSWORD      = "S3nh4Sup3rS3gur@!2025";

        public static final String CONNECTION_URL =
                "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE_NAME;
    }
}
