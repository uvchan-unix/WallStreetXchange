package com.wallstreet.wallstreetxchange.models.DAO;

import java.sql.Connection;
import com.wallstreet.wallstreetxchange.models.DAO.DBConnection;
import com.wallstreet.wallstreetxchange.models.DAO.DBOperarion;

public class Test {
    
    public static void main(String[] args) {
        
        Connection conn = (Connection)new DBConnection().getConnection();
        DBOperarion db = new DBOperarion(conn);
        db.test();
    }



    
}
