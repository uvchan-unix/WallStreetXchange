package com.wallstreet.wallstreetxchange.models.congiguration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.wallstreet.wallstreetxchange.models.DAO.DBConnection;
import com.wallstreet.wallstreetxchange.models.DAO.DBOModule;

// @WebListener

public class confign implements ServletContextListener  {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();
        DBConnection dbcon = new DBConnection();
        DBOModule dbOperarion = new DBOModule(dbcon.getConnection());

        StockCollections stocktree = new StockCollections();
        stocktree.dataInsertion();

        context.setAttribute("stockcollection", stocktree);
        context.setAttribute("db",dbOperarion);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method
        
    }
    
}
