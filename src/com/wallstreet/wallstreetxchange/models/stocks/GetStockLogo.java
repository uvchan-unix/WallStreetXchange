package com.wallstreet.wallstreetxchange.models.stocks;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetStockLogo {

    public static final String URL_FORMAT = "https://symbol-search.tradingview.com/symbol_search/v3/?text=%s&hl=1&exchange=NSE&lang=en&search_type=stocks&domain=production&sort_by_country=US";
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build();

    public static String getLogoId(String symbol) throws JSONException {

        String url = String.format(URL_FORMAT, symbol);
        String resBody = "";
        Request req = new Request.Builder().url(url)
                // .addHeader("User-Agent",
                // "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko)
                // Chrome/124.0.0.0 Safari/537.36")
                // .addHeader("Accept", "*/*")
                // .addHeader("Accept-Language", "en-GB,en-US;q=0.9,en;q=0.8")
                // .addHeader("Accept-Encoding", "gzip, deflate, br, zstd")
                // .addHeader("Referer", "https://www.tradingview-widget.com/")
                // Important
                .addHeader("Origin", "https://www.tradingview-widget.com")
                // .addHeader("Cache-Control", "no-cache")
                // .addHeader("Pragma", "no-cache")
                // .addHeader("Sec-Fetch-Dest", "empty")
                // .addHeader("Sec-Fetch-Mode", "cors")
                // .addHeader("Sec-Fetch-Site", "cross-site")
                // .addHeader("Sec-Ch-Ua", "\"Chromium\";v=\"124\", \"Google Chrome\";v=\"124\",
                // \"Not-A.Brand\";v=\"99\"")
                // .addHeader("Sec-Ch-Ua-Mobile", "?0")
                // .addHeader("Sec-Ch-Ua-Platform", "\"Linux\"")
                .build();
        try (Response response = client.newCall(req).execute()) {
            if (response.isSuccessful()) {
                return parseResponse(response);
            } 
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        finally {
            // Close the OkHttpClient to release resources
            client.dispatcher().executorService().shutdown();
            client.connectionPool().evictAll();
        }
        
        return "default";
    }

    private static String parseResponse(Response response) {
        try {
            
            ResponseBody body = response.body();
            
            if (body != null) {
                JSONObject json = new JSONObject(body.string()).getJSONArray("symbols").getJSONObject(0);
    
                if (json.getString("logoid") != null) {
                    return json.getString("logoid");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "default";
    }

    public static void main(String[] args) {
        // GetStockLogo obj = new GetStockLogo();
        System.out.println(GetStockLogo.getLogoId("SBICARD"));
    }
}
