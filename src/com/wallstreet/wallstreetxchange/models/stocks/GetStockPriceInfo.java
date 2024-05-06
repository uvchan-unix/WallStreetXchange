package com.wallstreet.wallstreetxchange.models.stocks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.wallstreet.wallstreetxchange.properties.FunctionDefanitions;

public class GetStockPriceInfo {

    private final String apiUrl = "https://query1.finance.yahoo.com/v8/finance/chart/";

    private JSONObject getJsonResp(String stockSymbol) {

        JSONObject json = null;
        try {
            URL url = new URL(apiUrl + stockSymbol + ".NS");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int code = con.getResponseCode();
            // System.out.println(code);
            if (code == 200) {
                json = FunctionDefanitions.inputReader(new InputStreamReader(con.getInputStream()));
            } else {
                json.put("error", "while ferching api" + String.valueOf(code));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return json;
    }

    public JSONObject getStockPrice(String stockSymbol) {
        JSONObject json = getJsonResp(stockSymbol);
        JSONObject njson = new JSONObject();
        njson.put("currentPrice", 00.0);
        njson.put("previousClose", 0.00);
        njson.put("todayMovement", 0.00);
        if (json != null) {
            try {
                JSONObject jsonobj = json.getJSONObject("chart").getJSONArray("result").getJSONObject(0)
                        .getJSONObject("meta");
                double currentPrice = jsonobj.getDouble("regularMarketPrice");
                double previousClose = jsonobj.getDouble("previousClose");
                double todayMovement = ((currentPrice - previousClose) / previousClose) * 100;
                System.out.println(todayMovement);
                double todayMovementinPercent = Math.round(todayMovement * 100);
                njson.put("currentPrice", currentPrice);
                njson.put("previousClose", previousClose);
                njson.put("todayMovement", todayMovementinPercent / 100);
                return njson;
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
                return njson;
            }
        }
        return njson;
    }

    public double getCurrentPrice(String symbol) {
        try {
            JSONObject json = getJsonResp(symbol).getJSONObject("chart").getJSONArray("result").getJSONObject(0)
                    .getJSONObject("meta");
            if (json!=null) {
                
                double currentPrice = json.getDouble("regularMarketPrice");
                return currentPrice;
            }        

        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return 0.00;
    }

    // public static void main(String[] args) {
    //     GetStockPriceInfo obj = new GetStockPriceInfo();
    //     // System.out.println(obj.getStockPrice("RPOWER"));
    //     System.out.println(obj.getCurrentPrice("RPOWER"));

    // }
}
