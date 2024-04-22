package com.wallstreet.wallstreetxchange.models.stocks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wallstreet.wallstreetxchange.models.DAO.Stock;

public class GetStockPrice {

    private static final String URL_FORMAT = "https://www.google.com/finance/quote/%s:%s";
    private static final String REGEX = "<div class=\"YMlKec fxKbKc\">(.*?)</div>";

    private String getResponse(String stockName, String exchangeName) {
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(String.format(URL_FORMAT, stockName, exchangeName));
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int code = con.getResponseCode();

            if (code == 200) {
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()), 16000);

                Pattern pattern = Pattern.compile(REGEX);
                Matcher matcher = pattern.matcher("");

                String line;
                while ((line = reader.readLine()) != null) {
                    matcher.reset(line);
                    if (matcher.find()) {
                        response.append(matcher.group(1));
                        break; 
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (con != null) con.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return response.toString();
    }

    public String getStockPrice(String stockSymbol , String stockExchange) {

        return getResponse(stockSymbol, "NSE");

    }

    public static void main(String[] args) {
        GetStockPrice obj = new GetStockPrice();
        System.out.println("hee");
        System.out.println(obj.getStockPrice("RPOWER","NSE"));
    }
}
