package com.wallstreet.wallstreetxchange.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wallstreet.wallstreetxchange.models.DAO.DBOModule;
import com.wallstreet.wallstreetxchange.models.congiguration.StockCollections;
import com.wallstreet.wallstreetxchange.properties.FunctionDefanitions;

@WebServlet("/main")
public class Main extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setContentType("application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

        PrintWriter out = resp.getWriter();

        DBOModule dbOperarion = FunctionDefanitions.getDbOperarion(req);
        dbOperarion.test();
        
        StockCollections stockCollections = FunctionDefanitions.getStockCollections(req);
        String stockInfo = stockCollections.getStocks("s").toString();
        out.println(stockInfo);
        
        
    }
}
