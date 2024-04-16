package com.wallstreet.wallstreetxchange.controllers;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wallstreet.wallstreetxchange.models.DAO.DBOperarion;
import com.wallstreet.wallstreetxchange.properties.FunctionDefanitions;

// @WebServlet("/test")

public class Main extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DBOperarion dbOperarion = FunctionDefanitions.getDbOperarion(req);
        dbOperarion.test();
        
        
    }

    
}