package com.wallstreet.wallstreetxchange.models.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import com.wallstreet.wallstreetxchange.properties.*;

public class DBConnection {
    
    private Connection con = null;

    public Connection getConnection(){

        if (con == null) {
            
            try {
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + Properties.HOST + ":" + Properties.PORT + "/" + Properties.DB_NAME, Properties.USERNAME, Properties.PASSWORD);
                
            }
            catch (Exception e) {

                e.printStackTrace();

            }
        }

        return con;
    }

    public void closeConnection(){
        try {
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
