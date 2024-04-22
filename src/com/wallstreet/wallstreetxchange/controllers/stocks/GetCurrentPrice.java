package com.wallstreet.wallstreetxchange.controllers.stocks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wallstreet.wallstreetxchange.models.stocks.GetStockPrice;
import com.wallstreet.wallstreetxchange.properties.FunctionDefanitions;

@WebServlet("/getprice")
public class GetCurrentPrice extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String sym = req.getParameter("s");
        GetStockPrice p = new GetStockPrice();
        String pe = p.getStockPrice(sym,"NSE");

        FunctionDefanitions.outputWriter(pe, resp);

        
    }
    
}
