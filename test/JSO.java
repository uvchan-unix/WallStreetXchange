package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSO {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.google.com/finance/quote/PSB:NSE");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int code = con.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line;
                boolean priceFound = false;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("<div class=\"YMlKec fxKbKc\">")) {
                        // Assuming the price is the next line after this marker
                        String priceLine = reader.readLine();
                        // Extract the price from priceLine
                        System.out.println("Price: " + extractPrice(priceLine));
                        priceFound = true;
                        break;
                    }
                }
                reader.close();
                if (!priceFound) {
                    System.out.println("Price element not found in the HTML.");
                }
            } else {
                System.out.println("Failed to fetch data. Response code: " + code);
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static String extractPrice(String line) {
        // Implement your logic to extract the price from the line
        // Example: Assuming the price is enclosed in a span tag with a specific class
        int startIndex = line.indexOf("<span class=\"...\">");
        int endIndex = line.indexOf("</span>", startIndex);
        if (startIndex != -1 && endIndex != -1) {
            return line.substring(startIndex + "<span class=\"...\">".length(), endIndex);
        }
        return "Price not found";
    }
}
