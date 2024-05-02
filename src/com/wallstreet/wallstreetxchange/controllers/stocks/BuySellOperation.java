package com.wallstreet.wallstreetxchange.controllers.stocks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.wallstreet.wallstreetxchange.models.DAO.DBOModule;
import com.wallstreet.wallstreetxchange.models.DAO.User;
import com.wallstreet.wallstreetxchange.models.congiguration.StockCollections;
import com.wallstreet.wallstreetxchange.models.util.StatusCode;
import com.wallstreet.wallstreetxchange.properties.FunctionDefanitions;

@WebServlet("/stocks/trade")
public class BuySellOperation extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        
        try {


            JSONObject json = FunctionDefanitions.inputReader(req);
            System.out.println(json.toString());
            String symbol = json.getString("stockSymbol");
            int quantity = json.getInt("quantity");
            boolean type = json.getBoolean("transactionType");

            System.out.println("hi");
            DBOModule db = FunctionDefanitions.getDbOperarion(req);
            System.out.println("hi");
            
            User user = new User();
            user.setUserID(1);
            user.setUsername("uvchan");
            user.setVerified(true);
            System.out.println(user.getUsername());

            StockCollections stock = FunctionDefanitions.getStockCollections(req);

            JSONArray ja = stock.getStocks(symbol, false);
            JSONObject jo = ja.getJSONObject(0);
            System.out.println(jo.toString());
            double stockPrice = jo.getDouble("currentPrice");

            System.out.println(stockPrice);

            double availableFunds = (db.getUserWalletMETA(user)).getDouble("availableFunds:");
            double totalFundsNeeded = stockPrice * quantity;

            System.out.println(availableFunds);

            if (availableFunds >= totalFundsNeeded) {
                int code = db.tradeStock(user, json, stockPrice).toInt();

                if (code == 1111) {

                    FunctionDefanitions.outputWriter(String.valueOf(code), resp);
        
                } else if (code == 1112) {
                    FunctionDefanitions.outputWriter("{ resp :"+symbol+"}", resp);
                } else if (code == 1113) {
                    FunctionDefanitions.outputWriter("{  resp :"+symbol+"}", resp);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

}
