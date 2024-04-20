package com.wallstreet.wallstreetxchange.models.DAO;

import java.sql.Connection;


public class Test {
    
    public static void main(String[] args) {
        
        Connection conn = (Connection)new DBConnection().getConnection();
        DBOModule db = new DBOModule(conn);
        db.test();
    }



    
}
