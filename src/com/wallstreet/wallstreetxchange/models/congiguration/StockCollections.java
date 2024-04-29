package com.wallstreet.wallstreetxchange.models.congiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import com.wallstreet.wallstreetxchange.models.DAO.Stock;
import com.wallstreet.wallstreetxchange.models.stocks.*;

public class StockCollections {
    
    private TrieNode rootNode;
    
    // PriceExtractor obj = new PriceExtractor();
    // GetStockPrices obj = new GetStockPrices();
    
    public StockCollections() {
        rootNode = new TrieNode();
    }

    public void insert(String stockNameOG, String stockSymbol, String stockExchange) {

        TrieNode temp = rootNode;
        String stockName = stockNameOG;
        stockName = stockName.replaceAll("[\\s&.()'-/]", "");

        for (int i = 0; i < stockName.length(); i++) {
            char c = stockName.charAt(i);
            int index = Character.toLowerCase(c) - 'a';
            if (temp.children[index] == null) {
                temp.children[index] = new TrieNode();
            }
            temp = temp.children[index];

        }

        temp.isEndOfWord = true;
        temp.stock = new Stock(stockNameOG,stockSymbol,stockExchange);
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

    private JSONArray collectAllWords(TrieNode node, JSONArray array, String prefix,boolean price) {

        if (node == null)
            return null;

        if (node.isEndOfWord) {
            JSONObject json = new JSONObject();
            json.put("stockName", node.stock.getStockName());
            json.put("stockSymbol", node.stock.getStockSymbol());
            json.put("stockExchange", node.stock.getStockExchange());
            
            if (price) {
                GetStockPrice obj = new GetStockPrice();
                json.put("curretPrice", obj.getStockPrice(node.stock.getStockSymbol(),"NSE"));
            }
            else{
                GetStockPriceInfo obj = new GetStockPriceInfo();
                json.put("currentPrice", obj.getStockPrice(node.stock.getStockSymbol()));
            }
            array.put(json);
        }

        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            collectAllWords(node.children[i], array, prefix + c,price);
        }

        return array;

    }

    public JSONArray getStocks(String prefix , boolean price) {

        JSONArray array = collectAllWords(findNode(prefix), new JSONArray(), prefix,price);
        if (array!=null) {    
            return array;
        }
        return null;
    }
    
    public void dataInsertion(){

        insert("Tata Motors", "RPOWER", "BSE/NSE");
        insert("ITC Limited", "ITC", "BSE/NSE");
        insert("Sun Pharmaceutical Industries", "SUNPHARMA", "BSE/NSE");
        insert("Mahindra & Mahindra", "M&M", "BSE/NSE");
        insert("Nestle India", "NESTLEIND", "BSE/NSE");
        insert("Hindustan Unilever", "HINDUNILVR", "BSE/NSE");
        insert("Bajaj Finance", "BAJFINANCE", "BSE/NSE");
        insert("Maruti Suzuki India", "MARUTI", "BSE/NSE");
        insert("Asian Paints", "ASIANPAINT", "BSE/NSE");
        insert("Power Grid Corporation of India", "POWERGRID", "BSE/NSE");
        insert("Tech Mahindra", "TECHM", "BSE/NSE");
        insert("NTPC Limited", "NTPC", "BSE/NSE");
        insert("Titan Company", "TITAN", "BSE/NSE");
        insert("UltraTech Cement", "ULTRACEMCO", "BSE/NSE");
        insert("Coal India", "COALINDIA", "BSE/NSE");
        insert("Dr. Reddy's Laboratories", "DRREDDY", "BSE/NSE");
        insert("Wipro", "WIPRO", "BSE/NSE");
        insert("HCL Technologies", "HCLTECH", "BSE/NSE");
        insert("Hero MotoCorp", "HEROMOTOCO", "BSE/NSE");
        insert("IndusInd Bank", "INDUSINDBK", "BSE/NSE");
        insert("Bajaj Auto", "BAJAJ-AUTO", "BSE/NSE");
        insert("Indian Oil Corporation", "IOC", "BSE/NSE");
        insert("Reliance Infrastructure", "RELINFRA", "BSE/NSE");
        insert("GAIL (India) Ltd", "GAIL", "BSE/NSE");
        insert("Cipla", "CIPLA", "BSE/NSE");
        insert("JSW Steel", "JSWSTEEL", "BSE/NSE");
        insert("Shree Cement", "SHREECEM", "BSE/NSE");
        insert("Hindalco Industries", "HINDALCO", "BSE/NSE");
        insert("Grasim Industries", "GRASIM", "BSE/NSE");
        insert("UPL Limited", "UPL", "BSE/NSE");
        insert("Adani Ports and Special Economic Zone", "ADANIPORTS", "BSE/NSE");
        insert("Tata Steel", "TATASTEEL", "BSE/NSE");
        insert("SBI Life Insurance Company", "SBILIFE", "BSE/NSE");
        insert("Bharat Petroleum Corporation Ltd", "BPCL", "BSE/NSE");
        insert("Oil and Natural Gas Corporation", "ONGC", "BSE/NSE");
        insert("Bajaj Finserv", "BAJAJFINSV", "BSE/NSE");
        insert("Hindustan Zinc", "HINDZINC", "BSE/NSE");
        insert("Tata Consumer Products", "TATACONSUM", "BSE/NSE");
        insert("Adani Green Energy Ltd", "ADANIGREEN", "BSE/NSE");
        insert("Adani Enterprises", "ADANIENT", "BSE/NSE");
        insert("Divi's Laboratories", "DIVISLAB", "BSE/NSE");
        insert("Axis Bank", "AXISBANK", "BSE/NSE");
        insert("Bharti Infratel", "INFRATEL", "BSE/NSE");
        insert("Hindustan Petroleum Corporation Ltd", "HINDPETRO", "BSE/NSE");
        insert("Tata Power Company Ltd", "TATAPOWER", "BSE/NSE");
        insert("Cipla", "CIPLA", "BSE/NSE");
        insert("Bharat Heavy Electricals Ltd", "BHEL", "BSE/NSE");
        insert("Tata Chemicals", "TATACHEM", "BSE/NSE");
        insert("NTPC Ltd", "NTPC", "BSE/NSE");
        insert("Tech Mahindra", "TECHM", "BSE/NSE");
        insert("Titan Company Ltd", "TITAN", "BSE/NSE");
        insert("Power Grid Corporation of India Ltd", "POWERGRID", "BSE/NSE");
        insert("ONGC Ltd", "ONGC", "BSE/NSE");
        insert("GAIL (India) Ltd", "GAIL", "BSE/NSE");
        insert("Bharat Electronics Ltd", "BEL", "BSE/NSE");
        insert("Oil India Ltd", "OIL", "BSE/NSE");
        insert("Container Corporation of India Ltd", "CONCOR", "BSE/NSE");
        insert("Bharat Petroleum Corporation Ltd", "BPCL", "BSE/NSE");
        insert("Steel Authority of India Ltd", "SAIL", "BSE/NSE");
        insert("Bharat Electronics Ltd", "BEL", "BSE/NSE");
        insert("Container Corporation of India Ltd", "CONCOR", "BSE/NSE");
        insert("Dabur India Ltd", "DABUR", "BSE/NSE");
        insert("Grasim Industries Ltd", "GRASIM", "BSE/NSE");
        insert("ICICI Lombard General Insurance Company Ltd", "ICICIGI", "BSE/NSE");
        insert("Hero MotoCorp Ltd", "HEROMOTOCO", "BSE/NSE");
        insert("ITC Ltd", "ITC", "BSE/NSE");
        insert("Larsen & Toubro Ltd", "LT", "BSE/NSE");
        insert("Oil & Natural Gas Corporation Ltd", "ONGC", "BSE/NSE");
        insert("Piramal Enterprises Ltd", "PEL", "BSE/NSE");
        insert("State Bank of India", "SBIN", "BSE/NSE");
        insert("Tata Chemicals Ltd", "TATACHEM", "BSE/NSE");
        insert("Vedanta Ltd", "VEDL", "BSE/NSE");
        insert("Wipro Ltd", "WIPRO", "BSE/NSE");
        insert("Ambuja Cements Ltd", "AMBUJACEM", "BSE/NSE");
        insert("Bharat Forge Ltd", "BHARATFORG", "BSE/NSE");
        insert("Bosch Ltd", "BOSCHLTD", "BSE/NSE");
        insert("DLF Ltd", "DLF", "BSE/NSE");
        insert("Havells India Ltd", "HAVELLS", "BSE/NSE");
        insert("Hindalco Industries Ltd", "HINDALCO", "BSE/NSE");
        insert("Hindustan Zinc Ltd", "HINDZINC", "BSE/NSE");
        insert("HDFC Life Insurance Company Ltd", "HDFCLIFE", "BSE/NSE");
        insert("Indraprastha Gas Ltd", "IGL", "BSE/NSE");
        insert("JSW Steel Ltd", "JSWSTEEL", "BSE/NSE");
        insert("Mahindra & Mahindra Ltd", "M&M", "BSE/NSE");
        insert("NCC Ltd", "NCC", "BSE/NSE");
        insert("NMDC Ltd", "NMDC", "BSE/NSE");
        insert("Pidilite Industries Ltd", "PIDILITIND", "BSE/NSE");
        insert("Reliance Industries Ltd", "RELIANCE", "BSE/NSE");
        insert("SBI Cards and Payment Services Ltd", "SBICARD", "BSE/NSE");
        insert("Sun Pharmaceutical Industries Ltd", "SUNPHARMA", "BSE/NSE");
        insert("Tata Power Company Ltd", "TATAPOWER", "BSE/NSE");
        insert("Tata Steel Ltd", "TATASTEEL", "BSE/NSE");
        insert("Tata Motors Ltd", "TATAMOTORS", "BSE/NSE");
        insert("Tata Consumer Products Ltd", "TATACONSUM", "BSE/NSE");
        insert("Vodafone Idea Ltd", "IDEA", "BSE/NSE");
        insert("Abbott India Ltd", "ABBOTINDIA", "BSE/NSE");
        insert("Adani Enterprises Ltd", "ADANIENT", "BSE/NSE");
        insert("AIA Engineering Ltd", "AIAENG", "BSE/NSE");
        insert("Apollo Hospitals Enterprise Ltd", "APOLLOHOSP", "BSE/NSE");
        insert("Apollo Tyres Ltd", "APOLLOTYRE", "BSE/NSE");
        insert("Ashok Leyland Ltd", "ASHOKLEY", "BSE/NSE");
        insert("Aurobindo Pharma Ltd", "AUROPHARMA", "BSE/NSE");
        insert("Axis Bank Ltd", "AXISBANK", "BSE/NSE");
        insert("Bajaj Auto Ltd", "BAJAJ-AUTO", "BSE/NSE");
        insert("Bajaj Finance Ltd", "BAJFINANCE", "BSE/NSE");
        insert("Bajaj Finserv Ltd", "BAJAJFINSV", "BSE/NSE");
        insert("Balkrishna Industries Ltd", "BALKRISIND", "BSE/NSE");
        insert("Bata India Ltd", "BATAINDIA", "BSE/NSE");
        insert("Berger Paints India Ltd", "BERGEPAINT", "BSE/NSE");
        insert("Bharat Electronics Ltd", "BEL", "BSE/NSE");
        insert("Bharat Forge Ltd", "BHARATFORG", "BSE/NSE");
        insert("Biocon Ltd", "BIOCON", "BSE/NSE");
        insert("Britannia Industries Ltd", "BRITANNIA", "BSE/NSE");
        insert("Cadila Healthcare Ltd", "CADILAHC", "BSE/NSE");
        insert("Canara Bank", "CANBK", "BSE/NSE");
        insert("Cholamandalam Investment & Finance Company Ltd", "CHOLAFIN", "BSE/NSE");
        insert("Cipla Ltd", "CIPLA", "BSE/NSE");
        insert("Colgate-Palmolive India Ltd", "COLPAL", "BSE/NSE");
        insert("Container Corporation of India Ltd", "CONCOR", "BSE/NSE");
        insert("Cummins India Ltd", "CUMMINSIND", "BSE/NSE");
        insert("Dabur India Ltd", "DABUR", "BSE/NSE");
        insert("Divi's Laboratories Ltd", "DIVISLAB", "BSE/NSE");
        insert("Dixon Technologies (India) Ltd", "DIXON", "BSE/NSE");
        insert("Dr. Reddy's Laboratories Ltd", "DRREDDY", "BSE/NSE");
        insert("Eicher Motors Ltd", "EICHERMOT", "BSE/NSE");
        insert("GAIL (India) Ltd", "GAIL", "BSE/NSE");
        insert("Godrej Consumer Products Ltd", "GODREJCP", "BSE/NSE");
        insert("Godrej Industries Ltd", "GODREJIND", "BSE/NSE");
        insert("Godrej Properties Ltd", "GODREJPROP", "BSE/NSE");
        insert("Grasim Industries Ltd", "GRASIM", "BSE/NSE");
        insert("HCL Technologies Ltd", "HCLTECH", "BSE/NSE");
        insert("HDFC Bank Ltd", "HDFCBANK", "BSE/NSE");
        insert("HDFC Life Insurance Company Ltd", "HDFCLIFE", "BSE/NSE");
        insert("Hero MotoCorp Ltd", "HEROMOTOCO", "BSE/NSE");
        insert("Hindalco Industries Ltd", "HINDALCO", "BSE/NSE");
        insert("Hindustan Unilever Ltd", "HINDUNILVR", "BSE/NSE");
        insert("Hindustan Zinc Ltd", "HINDZINC", "BSE/NSE");
        insert("ICICI Bank Ltd", "ICICIBANK", "BSE/NSE");
        insert("ICICI Lombard General Insurance Company Ltd", "ICICIGI", "BSE/NSE");
        insert("ICICI Prudential Life Insurance Company Ltd", "ICICIPRULI", "BSE/NSE");
        insert("Suna panaa", "SUPA", "SAKTHIMAN AGENCY");
        insert("IndusInd Bank Ltd", "INDUSINDBK", "BSE/NSE");
        insert("Indian Oil Corporation Ltd", "IOC", "BSE/NSE");
        insert("JSW Steel Ltd", "JSWSTEEL", "BSE/NSE");
        insert("Kotak Mahindra Bank Ltd", "KOTAKBANK", "BSE/NSE");
        insert("Larsen & Toubro Ltd", "LT", "BSE/NSE");
        insert("Mahindra & Mahindra Ltd", "M&M", "BSE/NSE");
        insert("Marico Ltd", "MARICO", "BSE/NSE");
        insert("Maruti Suzuki India Ltd", "MARUTI", "BSE/NSE");
        insert("MindTree Ltd", "MINDTREE", "BSE/NSE");
        insert("Motherson Sumi Systems Ltd", "MOTHERSUMI", "BSE/NSE");
        insert("Nestle India Ltd", "NESTLEIND", "BSE/NSE");
        insert("NMDC Ltd", "NMDC", "BSE/NSE");
        insert("Oracle Financial Services Software Ltd", "OFSS", "BSE/NSE");
        insert("Oil & Natural Gas Corporation Ltd", "ONGC", "BSE/NSE");
        insert("Power Finance Corporation Ltd", "PFC", "BSE/NSE");
        insert("Petronet LNG Ltd", "PETRONET", "BSE/NSE");
        insert("Pidilite Industries Ltd", "PIDILITIND", "BSE/NSE");
        insert("Piramal Enterprises Ltd", "PEL", "BSE/NSE");
        insert("Punjab National Bank", "PNB", "BSE/NSE");
        insert("Reliance Industries Ltd", "RELIANCE", "BSE/NSE");
        insert("Shriram Transport Finance Company Ltd", "SRTRANSFIN", "BSE/NSE");
        insert("State Bank of India", "SBIN", "BSE/NSE");
        insert("Siemens Ltd", "SIEMENS", "BSE/NSE");
        insert("Sun Pharmaceutical Industries Ltd", "SUNPHARMA", "BSE/NSE");
        insert("Tata Consumer Products Ltd", "TATACONSUM", "BSE/NSE");
        insert("Tata Motors Ltd", "TATAMOTORS", "BSE/NSE");
        insert("Tata Steel Ltd", "TATASTEEL", "BSE/NSE");
        insert("Tech Mahindra Ltd", "TECHM", "BSE/NSE");
        insert("Titan Company Ltd", "TITAN", "BSE/NSE");
        insert("Tata Power Company Ltd", "TATAPOWER", "BSE/NSE");
        insert("UltraTech Cement Ltd", "ULTRACEMCO", "BSE/NSE");
        insert("United Breweries Ltd", "UBL", "BSE/NSE");
        insert("United Spirits Ltd", "MCDOWELL-N", "BSE/NSE");
        insert("UPL Ltd", "UPL", "BSE/NSE");
        insert("Vedanta Ltd", "VEDL", "BSE/NSE");
        insert("Wipro Ltd", "WIPRO", "BSE/NSE");
        insert("Yes Bank Ltd", "YESBANK", "BSE/NSE");
        insert("Adani Enterprises Ltd", "ADANIENT", "BSE/NSE");
        insert("Adani Ports and Special Economic Zone Ltd", "ADANIPORTS", "BSE/NSE");
        insert("Adani Green Energy Ltd", "ADANIGREEN", "BSE/NSE");
        insert("Adani Power Ltd", "ADANIPOWER", "BSE/NSE");
        insert("Apollo Hospitals Enterprise Ltd", "APOLLOHOSP", "BSE/NSE");
        insert("Bharat Petroleum Corporation Ltd", "BPCL", "BSE/NSE");
        insert("Bharat Electronics Ltd", "BEL", "BSE/NSE");
        insert("Bharat Heavy Electricals Ltd", "BHEL", "BSE/NSE");
        insert("Bharti Infratel Ltd", "INFRATEL", "BSE/NSE");
        insert("Britannia Industries Ltd", "BRITANNIA", "BSE/NSE");
        insert("Cipla Ltd", "CIPLA", "BSE/NSE");
        insert("Container Corporation of India Ltd", "CONCOR", "BSE/NSE");
        insert("Dabur India Ltd", "DABUR", "BSE/NSE");
        insert("GAIL (India) Ltd", "GAIL", "BSE/NSE");
        insert("Grasim Industries Ltd", "GRASIM", "BSE/NSE");
        insert("Havells India Ltd", "HAVELLS", "BSE/NSE");
        insert("Hero MotoCorp Ltd", "HEROMOTOCO", "BSE/NSE");
        insert("Hindalco Industries Ltd", "HINDALCO", "BSE/NSE");
        insert("Hindustan Petroleum Corporation Ltd", "HINDPETRO", "BSE/NSE");
        insert("ICICI Lombard General Insurance Company Ltd", "ICICIGI", "BSE/NSE");
        insert("ICICI Prudential Life Insurance Company Ltd", "ICICIPRULI", "BSE/NSE");
        insert("Indiabulls Housing Finance Ltd", "IBULHSGFIN", "BSE/NSE");
        insert("IndusInd Bank Ltd", "INDUSINDBK", "BSE/NSE");
        insert("Larsen & Toubro Infotech Ltd", "LTI", "BSE/NSE");
        insert("Mahanagar Gas Ltd", "MGL", "BSE/NSE");
        insert("Marico Ltd", "MARICO", "BSE/NSE");
        insert("MRF Ltd", "MRF", "BSE/NSE");
        insert("Nestle India Ltd", "NESTLEIND", "BSE/NSE");
        insert("PVR Ltd", "PVR", "BSE/NSE");
        insert("Shree Cement Ltd", "SHREECEM", "BSE/NSE");
        insert("Siemens Ltd", "SIEMENS", "BSE/NSE");
        insert("State Bank of India", "SBIN", "BSE/NSE");
        insert("The Indian Hotels Company Ltd", "INDHOTEL", "BSE/NSE");
        insert("Titan Company Ltd", "TITAN", "BSE/NSE");
        insert("Torrent Pharmaceuticals Ltd", "TORNTPHARM", "BSE/NSE");
        insert("TVS Motor Company Ltd", "TVSMOTOR", "BSE/NSE");
        insert("UPL Ltd", "UPL", "BSE/NSE");
        insert("Vedanta Ltd", "VEDL", "BSE/NSE");
        insert("Vodafone Idea Ltd", "IDEA", "BSE/NSE");
        insert("Wipro Ltd", "WIPRO", "BSE/NSE");
        insert("USD/SGD", "USDSGD", "Forex");
        insert("USD/HKD", "USDHKD", "Forex");
        insert("EUR/GBP", "EURGBP", "Forex");
        insert("USD/INR", "USDINR", "Forex");
        insert("USD/AUD", "USDAUD", "Forex");
        insert("GBP/JPY", "GBPJPY", "Forex");
        insert("EUR/JPY", "EURJPY", "Forex");
        insert("AUD/JPY", "AUDJPY", "Forex");
        insert("USD/NOK", "USDNOK", "Forex");
        insert("USD/SEK", "USDSEK", "Forex");
        insert("EUR/AUD", "EURAUD", "Forex");
        insert("EUR/CHF", "EURCHF", "Forex");
        insert("GBP/CHF", "GBPCHF", "Forex");
        insert("AUD/CAD", "AUDCAD", "Forex");
        insert("NZD/USD", "NZDUSD", "Forex");
        insert("USD/DKK", "USDDKK", "Forex");
        insert("GBP/CAD", "GBPCAD", "Forex");
        insert("GBP/AUD", "GBPAUD", "Forex");
        insert("EUR/CAD", "EURCAD", "Forex");
        insert("EUR/SEK", "EURSEK", "Forex");
        insert("NZD/JPY", "NZDJPY", "Forex");
        insert("AUD/NZD", "AUDNZD", "Forex");
        insert("USD/CNH", "USDCNH", "Forex");
        insert("USD/ZAR", "USDZAR", "Forex");
        insert("EUR/NZD", "EURNZD", "Forex");
        insert("EUR/NOK", "EURNOK", "Forex");
        insert("EUR/TRY", "EURTRY", "Forex");
        insert("USD/TRY", "USDTRY", "Forex");
        insert("USD/RUB", "USDRUB", "Forex");
        insert("USD/MXN", "USDMXN", "Forex");
        insert("USD/BRL", "USDBRL", "Forex");
        insert("USD/THB", "USDTHB", "Forex");
        insert("USD/IDR", "USDIDR", "Forex");
        insert("USD/KRW", "USDKRW", "Forex");
        insert("USD/PHP", "USDPHP", "Forex");
        insert("USD/TWD", "USDTWD", "Forex");
        insert("USD/MYR", "USDMYR", "Forex");
        insert("Copper", "XCUUSD", "Metals");
        insert("Nickel", "XNIUSD", "Metals");
        insert("Zinc", "XZNUSD", "Metals");
        insert("Lead", "XPBUSD", "Metals");
        insert("Aluminium", "XALUSD", "Metals");
        insert("Rhodium", "XRHUSD", "Metals");
        insert("Iridium", "XIRUSD", "Metals");
        insert("Osmium", "XOSUSD", "Metals");
        insert("Ruthenium", "XRUSD", "Metals");
        insert("Gold/Silver Ratio", "XAU/XAG", "Metals");
        insert("Gold/Platinum Ratio", "XAU/XPT", "Metals");
        insert("Gold/Palladium Ratio", "XAU/XPD", "Metals");
        insert("EUR/CNY", "EURCNY", "Forex");
        insert("GBP/SGD", "GBPSGD", "Forex");
        insert("USD/CHF", "USDCHF", "Forex");
        insert("USD/SGD", "USDSGD", "Forex");
        insert("AUD/CHF", "AUDCHF", "Forex");
        insert("CAD/JPY", "CADJPY", "Forex");
        insert("CHF/JPY", "CHFJPY", "Forex");
        insert("EUR/AUD", "EURAUD", "Forex");
        insert("EUR/GBP", "EURGBP", "Forex");
        insert("EUR/HKD", "EURHKD", "Forex");
        insert("EUR/NZD", "EURNZD", "Forex");
        insert("GBP/AUD", "GBPAUD", "Forex");
        insert("GBP/CAD", "GBPCAD", "Forex");
        insert("GBP/JPY", "GBPJPY", "Forex");
        insert("NZD/CAD", "NZDCAD", "Forex");
        insert("NZD/CHF", "NZDCHF", "Forex");
        insert("NZD/JPY", "NZDJPY", "Forex");
        insert("USD/CNH", "USDCNH", "Forex");
        insert("USD/HKD", "USDHKD", "Forex");
        insert("USD/INR", "USDINR", "Forex");
        insert("USD/JPY", "USDJPY", "Forex");
        insert("AUD/JPY", "AUDJPY", "Forex");
        insert("AUD/NZD", "AUDNZD", "Forex");
        insert("CAD/CHF", "CADCHF", "Forex");
        insert("EUR/CHF", "EURCHF", "Forex");
        insert("EUR/JPY", "EURJPY", "Forex");
        insert("GBP/NZD", "GBPNZD", "Forex");
        insert("NZD/USD", "NZDUSD", "Forex");
        insert("USD/CAD", "USDCAD", "Forex");
        insert("USD/SEK", "USDSEK", "Forex");
        insert("USD/THB", "USDTHB", "Forex");
        insert("Tin", "XTIUSD", "Metals");
        insert("Steel", "XSTUSD", "Metals");
        insert("Cobalt", "XCOUSD", "Metals");
        insert("Titanium", "XTNUSD", "Metals");
        insert("Vanadium", "XVAUSD", "Metals");
        insert("Magnesium", "XMGUSD", "Metals");
        insert("Lithium", "XLITHUSD", "Metals");
        insert("Antimony", "XANTUSD", "Metals");
        insert("Manganese", "XMNUSD", "Metals");
        insert("Zirconium", "XZIRUSD", "Metals");
        insert("Tungsten", "XTUUSD", "Metals");
        insert("Bismuth", "XBIUSD", "Metals");
        insert("Cadmium", "XCADUSD", "Metals");
        insert("Indium", "XINDUSD", "Metals");
        insert("Germanium", "XGERUSD", "Metals");
        insert("Rhenium", "XREUSD", "Metals");
        insert("Scandium", "XSCUSD", "Metals");
        insert("Tellurium", "XTEUSD", "Metals");
        insert("Hafnium", "XHAFUSD", "Metals");
        insert("Niobium", "XNBUSD", "Metals");
        insert("fooood", "XNBUSD", "Metals");

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
