package com.wallstreet.wallstreetxchange.controllers.stocks;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wallstreet.wallstreetxchange.models.congiguration.StockCollections;
import com.wallstreet.wallstreetxchange.properties.FunctionDefanitions;

// @WebServlet("/services/stocks/getStocks")

public class GetStockInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String prefix = req.getParameter("prefix");

        StockCollections stockCollections = FunctionDefanitions.getStockCollections(req);
        String stockInfo = stockCollections.getStocks(prefix).toString();

        FunctionDefanitions.outputWriter(stockInfo, resp);
        // FunctionDefanitions.outputWriter(stockInfo, resp);

    }      
}
