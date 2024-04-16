package test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    public static void main(String[] args) {
        String itcPrice = getITCPrice();
        if (itcPrice != null) {
            System.out.println("Current price of ITC shares: " + itcPrice);
        }
    }

    public static String getITCPrice() {
        String url = "https://query1.finance.yahoo.com/v8/finance/chart/ITC.NS";

        String params = "?region=US&lang=en-US&includePrePost=false&interval=1d&range=1d&corsDomain=finance.yahoo.com";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + params))
                .build();

        
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error sending HTTP request: " + e.getMessage());
            return null;
        }

        String responseBody = response.body();
        if (response.statusCode() != 200) {
            System.out.println("Error: HTTP status code " + response.statusCode());
            System.out.println("Response content: " + responseBody);
            return null;
        }

    
        // System.out.println("Response content: " + responseBody.);

        return "Placeholder price";
    }
}
