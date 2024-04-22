package com.wallstreet.wallstreetxchange.models.stocks;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PriceExtractor {
    public static void main(String[] args) {
        String price = extractPrice();
        if (price != null) {
            System.out.println("Price: " + price);
        } else {
            System.out.println("Failed to extract price.");
        }
    }

    public static String extractPrice() {
        try {
            Document doc = Jsoup.connect("https://www.google.com/finance/quote/PSB:NSE").get();
            Elements elements = doc.select("div.YMlKec.fxKbKc");
            if (!elements.isEmpty()) {
                Element priceElement = elements.first();
                return priceElement.text();
            } else {
                System.out.println("Price element not found in the HTML.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        return null;
    }
}
