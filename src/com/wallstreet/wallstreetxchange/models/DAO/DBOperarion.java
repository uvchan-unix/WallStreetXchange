package com.wallstreet.wallstreetxchange.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.wallstreet.wallstreetxchange.properties.SQLqueries;

public class DBOperarion {
    
    private Connection con = null;

    public DBOperarion(Connection con){
        this.con = con;
    }

    public void test(){
        try (PreparedStatement stm = con.prepareStatement(SQLqueries.TEST)){

            stm.setString(1,"sunillkumar takkiiar");
            stm.setString(2, "iuygfc@gmail.com");
            stm.setString(3,"nishant");
            stm.setString(4,"nishant");
            stm.setString(5,"nishant");

            stm.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

 
}
