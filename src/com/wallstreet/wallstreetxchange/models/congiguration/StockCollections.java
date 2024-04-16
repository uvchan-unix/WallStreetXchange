package com.wallstreet.wallstreetxchange.models.congiguration;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import com.wallstreet.wallstreetxchange.models.DAO.Stock;

public class StockCollections {

    private TrieNode rootNode;

    public StockCollections() {
        rootNode = new TrieNode();
    }

    public void insert(String stockNameOG, String stockSymbol, String stockExchange) {

        TrieNode temp = rootNode;
        String stockName = stockNameOG;
        stockName = stockName.replaceAll("[\\s&.()'-]", "");

        for (int i = 0; i < stockName.length(); i++) {
            char c = stockName.charAt(i);
            int index = Character.toLowerCase(c) - 'a';
            if (temp.children[index] == null) {
                temp.children[index] = new TrieNode();
            }
            temp = temp.children[index];

        }

        temp.isEndOfWord = true;
        temp.stock = new Stock();
        temp.stock.setStockName(stockNameOG);
        temp.stock.setStockSymbol(stockSymbol);
        temp.stock.setStockExchange(stockExchange);

    }

    private TrieNode findNode(String prefix) {

        TrieNode temp = rootNode;

        for (int i = 0; i < prefix.length(); i++) {

            char c = prefix.charAt(i);
            int index = Character.toLowerCase(c) - 'a';

            if (temp.children[index] == null) {

                return null;
            }

            temp = temp.children[index];

        }
        return temp;

    }

    private JSONArray collectAllWords(TrieNode node, JSONArray array, String prefix) {

        if (node == null)
            return null;

        if (node.isEndOfWord) {
            JSONObject json = new JSONObject();
            json.put("stockName", node.stock.getStockName());
            json.put("stockSymbol", node.stock.getStockSymbol());
            json.put("stockExchange", node.stock.getStockExchange());

            array.put(json);
        }

        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            collectAllWords(node.children[i], array, prefix + c);
        }

        return array;

    }

    public JSONArray getStocks(String prefix) {

        JSONArray array = collectAllWords(findNode(prefix), new JSONArray(), prefix);
        return array;
    }

    public static void main(String[] args) {
        StockCollections obj = new StockCollections();
        obj.insert("Tata Motors", "TATAMOTORS", "BSE/NSE");
        obj.insert("ITC Limited", "ITC", "BSE/NSE");
        obj.insert("Sun Pharmaceutical Industries", "SUNPHARMA", "BSE/NSE");
        obj.insert("Mahindra & Mahindra", "M&M", "BSE/NSE");
        obj.insert("Nestle India", "NESTLEIND", "BSE/NSE");
        obj.insert("Hindustan Unilever", "HINDUNILVR", "BSE/NSE");
        obj.insert("Bajaj Finance", "BAJFINANCE", "BSE/NSE");
        obj.insert("Maruti Suzuki India", "MARUTI", "BSE/NSE");
        obj.insert("Asian Paints", "ASIANPAINT", "BSE/NSE");
        obj.insert("Power Grid Corporation of India", "POWERGRID", "BSE/NSE");
        obj.insert("Tech Mahindra", "TECHM", "BSE/NSE");
        obj.insert("NTPC Limited", "NTPC", "BSE/NSE");
        obj.insert("Titan Company", "TITAN", "BSE/NSE");
        obj.insert("UltraTech Cement", "ULTRACEMCO", "BSE/NSE");
        obj.insert("Coal India", "COALINDIA", "BSE/NSE");
        obj.insert("Dr. Reddy's Laboratories", "DRREDDY", "BSE/NSE");
        obj.insert("Wipro", "WIPRO", "BSE/NSE");
        obj.insert("HCL Technologies", "HCLTECH", "BSE/NSE");
        obj.insert("Hero MotoCorp", "HEROMOTOCO", "BSE/NSE");
        obj.insert("IndusInd Bank", "INDUSINDBK", "BSE/NSE");
        obj.insert("Bajaj Auto", "BAJAJ-AUTO", "BSE/NSE");
        obj.insert("Indian Oil Corporation", "IOC", "BSE/NSE");
        obj.insert("Reliance Infrastructure", "RELINFRA", "BSE/NSE");
        obj.insert("GAIL (India) Ltd", "GAIL", "BSE/NSE");
        obj.insert("Cipla", "CIPLA", "BSE/NSE");
        obj.insert("JSW Steel", "JSWSTEEL", "BSE/NSE");
        obj.insert("Shree Cement", "SHREECEM", "BSE/NSE");
        obj.insert("Hindalco Industries", "HINDALCO", "BSE/NSE");
        obj.insert("Grasim Industries", "GRASIM", "BSE/NSE");
        obj.insert("UPL Limited", "UPL", "BSE/NSE");
        obj.insert("Adani Ports and Special Economic Zone", "ADANIPORTS", "BSE/NSE");
        obj.insert("Tata Steel", "TATASTEEL", "BSE/NSE");
        obj.insert("SBI Life Insurance Company", "SBILIFE", "BSE/NSE");
        obj.insert("Bharat Petroleum Corporation Ltd", "BPCL", "BSE/NSE");
        obj.insert("Oil and Natural Gas Corporation", "ONGC", "BSE/NSE");
        obj.insert("Bajaj Finserv", "BAJAJFINSV", "BSE/NSE");
        obj.insert("Hindustan Zinc", "HINDZINC", "BSE/NSE");
        obj.insert("Tata Consumer Products", "TATACONSUM", "BSE/NSE");
        obj.insert("Adani Green Energy Ltd", "ADANIGREEN", "BSE/NSE");
        obj.insert("Adani Enterprises", "ADANIENT", "BSE/NSE");
        obj.insert("Divi's Laboratories", "DIVISLAB", "BSE/NSE");
        obj.insert("Axis Bank", "AXISBANK", "BSE/NSE");
        obj.insert("Bharti Infratel", "INFRATEL", "BSE/NSE");
        obj.insert("Hindustan Petroleum Corporation Ltd", "HINDPETRO", "BSE/NSE");
        obj.insert("Tata Power Company Ltd", "TATAPOWER", "BSE/NSE");
        obj.insert("Cipla", "CIPLA", "BSE/NSE");
        obj.insert("Bharat Heavy Electricals Ltd", "BHEL", "BSE/NSE");
        obj.insert("Tata Chemicals", "TATACHEM", "BSE/NSE");
        obj.insert("NTPC Ltd", "NTPC", "BSE/NSE");
        obj.insert("Tech Mahindra", "TECHM", "BSE/NSE");
        obj.insert("Titan Company Ltd", "TITAN", "BSE/NSE");
        obj.insert("Power Grid Corporation of India Ltd", "POWERGRID", "BSE/NSE");
        obj.insert("ONGC Ltd", "ONGC", "BSE/NSE");
        obj.insert("GAIL (India) Ltd", "GAIL", "BSE/NSE");
        obj.insert("Bharat Electronics Ltd", "BEL", "BSE/NSE");
        obj.insert("Oil India Ltd", "OIL", "BSE/NSE");
        obj.insert("Container Corporation of India Ltd", "CONCOR", "BSE/NSE");
        obj.insert("Bharat Petroleum Corporation Ltd", "BPCL", "BSE/NSE");
        obj.insert("Steel Authority of India Ltd", "SAIL", "BSE/NSE");
        obj.insert("Bharat Electronics Ltd", "BEL", "BSE/NSE");
        obj.insert("Container Corporation of India Ltd", "CONCOR", "BSE/NSE");
        obj.insert("Dabur India Ltd", "DABUR", "BSE/NSE");
        obj.insert("Grasim Industries Ltd", "GRASIM", "BSE/NSE");
        obj.insert("ICICI Lombard General Insurance Company Ltd", "ICICIGI", "BSE/NSE");
        obj.insert("Hero MotoCorp Ltd", "HEROMOTOCO", "BSE/NSE");
        obj.insert("ITC Ltd", "ITC", "BSE/NSE");
        obj.insert("Larsen & Toubro Ltd", "LT", "BSE/NSE");
        obj.insert("Oil & Natural Gas Corporation Ltd", "ONGC", "BSE/NSE");
        obj.insert("Piramal Enterprises Ltd", "PEL", "BSE/NSE");
        obj.insert("State Bank of India", "SBIN", "BSE/NSE");
        obj.insert("Tata Chemicals Ltd", "TATACHEM", "BSE/NSE");
        obj.insert("Vedanta Ltd", "VEDL", "BSE/NSE");
        obj.insert("Wipro Ltd", "WIPRO", "BSE/NSE");
        obj.insert("Ambuja Cements Ltd", "AMBUJACEM", "BSE/NSE");
        obj.insert("Bharat Forge Ltd", "BHARATFORG", "BSE/NSE");
        obj.insert("Bosch Ltd", "BOSCHLTD", "BSE/NSE");
        obj.insert("DLF Ltd", "DLF", "BSE/NSE");
        obj.insert("Havells India Ltd", "HAVELLS", "BSE/NSE");
        obj.insert("Hindalco Industries Ltd", "HINDALCO", "BSE/NSE");
        obj.insert("Hindustan Zinc Ltd", "HINDZINC", "BSE/NSE");
        obj.insert("HDFC Life Insurance Company Ltd", "HDFCLIFE", "BSE/NSE");
        obj.insert("Indraprastha Gas Ltd", "IGL", "BSE/NSE");
        obj.insert("JSW Steel Ltd", "JSWSTEEL", "BSE/NSE");
        obj.insert("Mahindra & Mahindra Ltd", "M&M", "BSE/NSE");
        obj.insert("NCC Ltd", "NCC", "BSE/NSE");
        obj.insert("NMDC Ltd", "NMDC", "BSE/NSE");
        obj.insert("Pidilite Industries Ltd", "PIDILITIND", "BSE/NSE");
        obj.insert("Reliance Industries Ltd", "RELIANCE", "BSE/NSE");
        obj.insert("SBI Cards and Payment Services Ltd", "SBICARD", "BSE/NSE");
        obj.insert("Sun Pharmaceutical Industries Ltd", "SUNPHARMA", "BSE/NSE");
        obj.insert("Tata Power Company Ltd", "TATAPOWER", "BSE/NSE");
        obj.insert("Tata Steel Ltd", "TATASTEEL", "BSE/NSE");
        obj.insert("Tata Motors Ltd", "TATAMOTORS", "BSE/NSE");
        obj.insert("Tata Consumer Products Ltd", "TATACONSUM", "BSE/NSE");
        obj.insert("Vodafone Idea Ltd", "IDEA", "BSE/NSE");
        obj.insert("Abbott India Ltd", "ABBOTINDIA", "BSE/NSE");
        obj.insert("Adani Enterprises Ltd", "ADANIENT", "BSE/NSE");
        obj.insert("AIA Engineering Ltd", "AIAENG", "BSE/NSE");
        obj.insert("Apollo Hospitals Enterprise Ltd", "APOLLOHOSP", "BSE/NSE");
        obj.insert("Apollo Tyres Ltd", "APOLLOTYRE", "BSE/NSE");
        obj.insert("Ashok Leyland Ltd", "ASHOKLEY", "BSE/NSE");
        obj.insert("Aurobindo Pharma Ltd", "AUROPHARMA", "BSE/NSE");
        obj.insert("Axis Bank Ltd", "AXISBANK", "BSE/NSE");
        obj.insert("Bajaj Auto Ltd", "BAJAJ-AUTO", "BSE/NSE");
        obj.insert("Bajaj Finance Ltd", "BAJFINANCE", "BSE/NSE");
        obj.insert("Bajaj Finserv Ltd", "BAJAJFINSV", "BSE/NSE");
        obj.insert("Balkrishna Industries Ltd", "BALKRISIND", "BSE/NSE");
        obj.insert("Bata India Ltd", "BATAINDIA", "BSE/NSE");
        obj.insert("Berger Paints India Ltd", "BERGEPAINT", "BSE/NSE");
        obj.insert("Bharat Electronics Ltd", "BEL", "BSE/NSE");
        obj.insert("Bharat Forge Ltd", "BHARATFORG", "BSE/NSE");
        obj.insert("Biocon Ltd", "BIOCON", "BSE/NSE");
        obj.insert("Britannia Industries Ltd", "BRITANNIA", "BSE/NSE");
        obj.insert("Cadila Healthcare Ltd", "CADILAHC", "BSE/NSE");
        obj.insert("Canara Bank", "CANBK", "BSE/NSE");
        obj.insert("Cholamandalam Investment & Finance Company Ltd", "CHOLAFIN", "BSE/NSE");
        obj.insert("Cipla Ltd", "CIPLA", "BSE/NSE");
        obj.insert("Colgate-Palmolive India Ltd", "COLPAL", "BSE/NSE");
        obj.insert("Container Corporation of India Ltd", "CONCOR", "BSE/NSE");
        obj.insert("Cummins India Ltd", "CUMMINSIND", "BSE/NSE");
        obj.insert("Dabur India Ltd", "DABUR", "BSE/NSE");
        obj.insert("Divi's Laboratories Ltd", "DIVISLAB", "BSE/NSE");
        obj.insert("Dixon Technologies (India) Ltd", "DIXON", "BSE/NSE");
        obj.insert("Dr. Reddy's Laboratories Ltd", "DRREDDY", "BSE/NSE");
        obj.insert("Eicher Motors Ltd", "EICHERMOT", "BSE/NSE");
        obj.insert("GAIL (India) Ltd", "GAIL", "BSE/NSE");
        obj.insert("Godrej Consumer Products Ltd", "GODREJCP", "BSE/NSE");
        obj.insert("Godrej Industries Ltd", "GODREJIND", "BSE/NSE");
        obj.insert("Godrej Properties Ltd", "GODREJPROP", "BSE/NSE");
        obj.insert("Grasim Industries Ltd", "GRASIM", "BSE/NSE");
        obj.insert("HCL Technologies Ltd", "HCLTECH", "BSE/NSE");
        obj.insert("HDFC Bank Ltd", "HDFCBANK", "BSE/NSE");
        obj.insert("HDFC Life Insurance Company Ltd", "HDFCLIFE", "BSE/NSE");
        obj.insert("Hero MotoCorp Ltd", "HEROMOTOCO", "BSE/NSE");
        obj.insert("Hindalco Industries Ltd", "HINDALCO", "BSE/NSE");
        obj.insert("Hindustan Unilever Ltd", "HINDUNILVR", "BSE/NSE");
        obj.insert("Hindustan Zinc Ltd", "HINDZINC", "BSE/NSE");
        obj.insert("ICICI Bank Ltd", "ICICIBANK", "BSE/NSE");
        obj.insert("ICICI Lombard General Insurance Company Ltd", "ICICIGI", "BSE/NSE");
        obj.insert("ICICI Prudential Life Insurance Company Ltd", "ICICIPRULI", "BSE/NSE");
        obj.insert("Suna panaa", "SUPA", "SAKTHIMAN AGENCY");
        obj.insert("IndusInd Bank Ltd", "INDUSINDBK", "BSE/NSE");
        obj.insert("Indian Oil Corporation Ltd", "IOC", "BSE/NSE");
        obj.insert("JSW Steel Ltd", "JSWSTEEL", "BSE/NSE");
        obj.insert("Kotak Mahindra Bank Ltd", "KOTAKBANK", "BSE/NSE");
        obj.insert("Larsen & Toubro Ltd", "LT", "BSE/NSE");
        obj.insert("Mahindra & Mahindra Ltd", "M&M", "BSE/NSE");
        obj.insert("Marico Ltd", "MARICO", "BSE/NSE");
        obj.insert("Maruti Suzuki India Ltd", "MARUTI", "BSE/NSE");
        obj.insert("MindTree Ltd", "MINDTREE", "BSE/NSE");
        obj.insert("Motherson Sumi Systems Ltd", "MOTHERSUMI", "BSE/NSE");
        obj.insert("Nestle India Ltd", "NESTLEIND", "BSE/NSE");
        obj.insert("NMDC Ltd", "NMDC", "BSE/NSE");
        obj.insert("Oracle Financial Services Software Ltd", "OFSS", "BSE/NSE");
        obj.insert("Oil & Natural Gas Corporation Ltd", "ONGC", "BSE/NSE");
        obj.insert("Power Finance Corporation Ltd", "PFC", "BSE/NSE");
        obj.insert("Petronet LNG Ltd", "PETRONET", "BSE/NSE");
        obj.insert("Pidilite Industries Ltd", "PIDILITIND", "BSE/NSE");
        obj.insert("Piramal Enterprises Ltd", "PEL", "BSE/NSE");
        obj.insert("Punjab National Bank", "PNB", "BSE/NSE");
        obj.insert("Reliance Industries Ltd", "RELIANCE", "BSE/NSE");
        obj.insert("Shriram Transport Finance Company Ltd", "SRTRANSFIN", "BSE/NSE");
        obj.insert("State Bank of India", "SBIN", "BSE/NSE");
        obj.insert("Siemens Ltd", "SIEMENS", "BSE/NSE");
        obj.insert("Sun Pharmaceutical Industries Ltd", "SUNPHARMA", "BSE/NSE");
        obj.insert("Tata Consumer Products Ltd", "TATACONSUM", "BSE/NSE");
        obj.insert("Tata Motors Ltd", "TATAMOTORS", "BSE/NSE");
        obj.insert("Tata Steel Ltd", "TATASTEEL", "BSE/NSE");
        obj.insert("Tech Mahindra Ltd", "TECHM", "BSE/NSE");
        obj.insert("Titan Company Ltd", "TITAN", "BSE/NSE");
        obj.insert("Tata Power Company Ltd", "TATAPOWER", "BSE/NSE");
        obj.insert("UltraTech Cement Ltd", "ULTRACEMCO", "BSE/NSE");
        obj.insert("United Breweries Ltd", "UBL", "BSE/NSE");
        obj.insert("United Spirits Ltd", "MCDOWELL-N", "BSE/NSE");
        obj.insert("UPL Ltd", "UPL", "BSE/NSE");
        obj.insert("Vedanta Ltd", "VEDL", "BSE/NSE");
        obj.insert("Wipro Ltd", "WIPRO", "BSE/NSE");
        obj.insert("Yes Bank Ltd", "YESBANK", "BSE/NSE");
        obj.insert("Adani Enterprises Ltd", "ADANIENT", "BSE/NSE");
        obj.insert("Adani Ports and Special Economic Zone Ltd", "ADANIPORTS", "BSE/NSE");
        obj.insert("Adani Green Energy Ltd", "ADANIGREEN", "BSE/NSE");
        obj.insert("Adani Power Ltd", "ADANIPOWER", "BSE/NSE");
        obj.insert("Apollo Hospitals Enterprise Ltd", "APOLLOHOSP", "BSE/NSE");
        obj.insert("Bharat Petroleum Corporation Ltd", "BPCL", "BSE/NSE");
        obj.insert("Bharat Electronics Ltd", "BEL", "BSE/NSE");
        obj.insert("Bharat Heavy Electricals Ltd", "BHEL", "BSE/NSE");
        obj.insert("Bharti Infratel Ltd", "INFRATEL", "BSE/NSE");
        obj.insert("Britannia Industries Ltd", "BRITANNIA", "BSE/NSE");
        obj.insert("Cipla Ltd", "CIPLA", "BSE/NSE");
        obj.insert("Container Corporation of India Ltd", "CONCOR", "BSE/NSE");
        obj.insert("Dabur India Ltd", "DABUR", "BSE/NSE");
        obj.insert("GAIL (India) Ltd", "GAIL", "BSE/NSE");
        obj.insert("Grasim Industries Ltd", "GRASIM", "BSE/NSE");
        obj.insert("Havells India Ltd", "HAVELLS", "BSE/NSE");
        obj.insert("Hero MotoCorp Ltd", "HEROMOTOCO", "BSE/NSE");
        obj.insert("Hindalco Industries Ltd", "HINDALCO", "BSE/NSE");
        obj.insert("Hindustan Petroleum Corporation Ltd", "HINDPETRO", "BSE/NSE");
        obj.insert("ICICI Lombard General Insurance Company Ltd", "ICICIGI", "BSE/NSE");
        obj.insert("ICICI Prudential Life Insurance Company Ltd", "ICICIPRULI", "BSE/NSE");
        obj.insert("Indiabulls Housing Finance Ltd", "IBULHSGFIN", "BSE/NSE");
        obj.insert("IndusInd Bank Ltd", "INDUSINDBK", "BSE/NSE");
        obj.insert("Larsen & Toubro Infotech Ltd", "LTI", "BSE/NSE");
        obj.insert("Mahanagar Gas Ltd", "MGL", "BSE/NSE");
        obj.insert("Marico Ltd", "MARICO", "BSE/NSE");
        obj.insert("MRF Ltd", "MRF", "BSE/NSE");
        obj.insert("Nestle India Ltd", "NESTLEIND", "BSE/NSE");
        obj.insert("PVR Ltd", "PVR", "BSE/NSE");
        obj.insert("Shree Cement Ltd", "SHREECEM", "BSE/NSE");
        obj.insert("Siemens Ltd", "SIEMENS", "BSE/NSE");
        obj.insert("State Bank of India", "SBIN", "BSE/NSE");
        obj.insert("The Indian Hotels Company Ltd", "INDHOTEL", "BSE/NSE");
        obj.insert("Titan Company Ltd", "TITAN", "BSE/NSE");
        obj.insert("Torrent Pharmaceuticals Ltd", "TORNTPHARM", "BSE/NSE");
        obj.insert("TVS Motor Company Ltd", "TVSMOTOR", "BSE/NSE");
        obj.insert("UPL Ltd", "UPL", "BSE/NSE");
        obj.insert("Vedanta Ltd", "VEDL", "BSE/NSE");
        obj.insert("Vodafone Idea Ltd", "IDEA", "BSE/NSE");
        obj.insert("Wipro Ltd", "WIPRO", "BSE/NSE");

        JSONArray json = obj.getStocks("d");

        for (int i = 0; i < json.length(); i++) {

            JSONObject on = json.getJSONObject(i);
            System.out.println(on.getString("stockName"));

        }

    }
}

class TrieNode {

    TrieNode[] children;
    boolean isEndOfWord;
    Stock stock = null;

    TrieNode() {
        this.isEndOfWord = false;
        this.children = new TrieNode[26];
    }
}
