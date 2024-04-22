package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceExtractor {
    public static void main(String[] args) {
        System.out.println("hello");
        try {
            URL url = new URL("https://www.google.com/finance/quote/CIEINDIA:NSE");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int code = con.getResponseCode();
            if (code == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line;
                boolean priceFound = false;
                String regexPattern = "<div class=\"YMlKec fxKbKc\">(.*?)<\\/div>";
                Pattern pattern = Pattern.compile(regexPattern);
                Matcher matcher = pattern.matcher("");
                while ((line = reader.readLine()) != null) {
                    matcher.reset(line); // Reset the matcher with the new line
                    if (matcher.find()) {
                        // Print the matched group which contains the price
                        System.out.println("Price: " + matcher.group(1));
                        priceFound = true;
                        break;
                    }
                }
                reader.close();
                if (!priceFound) {
                    System.out.println("Price not found in the HTML content.");
                }
            } else {
                System.out.println("Failed to fetch data. Response code: " + code);
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
