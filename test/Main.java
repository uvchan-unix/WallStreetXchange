package test;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String apiKey = "demo"; // Replace this with your Alpha Vantage API key
        String symbol = "MSFT"; // Symbol for Microsoft
        
        try {
            // Construct the API URL
            String apiUrl = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey;
            
            // Create a URL object
            URL url = new URL(apiUrl);
            
            // Open a connection to the URL
            Scanner scanner = new Scanner(url.openStream());
            
            // Read the JSON response
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            
            // Print the JSON response
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
