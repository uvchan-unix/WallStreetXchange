package com.wallstreet.wallstreetxchange.models.congiguration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.wallstreet.wallstreetxchange.models.DAO.DBConnection;
import com.wallstreet.wallstreetxchange.models.DAO.DBOperarion;

// @WebListener

public class confign implements ServletContextListener  {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();
        DBConnection dbcon = new DBConnection();
        DBOperarion dbOperarion = new DBOperarion(dbcon.getConnection());

        context.setAttribute("db",dbOperarion);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method
        
    }
    
}
