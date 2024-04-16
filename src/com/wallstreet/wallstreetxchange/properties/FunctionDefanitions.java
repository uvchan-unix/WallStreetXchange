package com.wallstreet.wallstreetxchange.properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.wallstreet.wallstreetxchange.models.DAO.DBOperarion;

public class FunctionDefanitions {
    
    private static ServletContext getServletContexti(HttpServletRequest request) {
        return request.getServletContext();
    } 

    public static DBOperarion getDbOperarion(HttpServletRequest request) {

        ServletContext context = getServletContexti(request);
        DBOperarion dbOperarion = (DBOperarion)context.getAttribute("db");
        return dbOperarion;
    } 

}
