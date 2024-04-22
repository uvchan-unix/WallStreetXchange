package com.wallstreet.wallstreetxchange.models.stocks;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetStockPrices {
    private static final String URL_FORMAT = "https://www.google.com/finance/quote/%s:%s";
    private static final String REGEX = "<div class=\"YMlKec fxKbKc\">(.*?)</div>";
    private static final OkHttpClient client = new OkHttpClient();

    private String getResponse(String stockName, String exchangeName) {
        String url = String.format(URL_FORMAT, stockName, exchangeName);
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                Pattern pattern = Pattern.compile(REGEX);
                Matcher matcher = pattern.matcher(responseBody);
                if (matcher.find()) {
                    return matcher.group(1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public String getStockPrice(String stockSymbol, String stockExchange) {
        return getResponse(stockSymbol, stockExchange);
    }


}
