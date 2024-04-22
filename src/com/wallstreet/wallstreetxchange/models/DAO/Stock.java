package com.wallstreet.wallstreetxchange.models.DAO;

public class Stock {

    private String stockName;    
    private String stockSymbol;
    private String stockExchange;
    private int curretPrice;
    
    public Stock(String stockName, String stockSymbol, String stockExchange) {
        this.stockName = stockName;
        this.stockSymbol = stockSymbol;
        this.stockExchange = stockExchange;
    }

    public Stock(String StockName , String stockSymbol , String stockExchange,int curretPrice ){

    }

    public String getStockName() {
        return stockName;
    }

    public int getCurretPrice() {
        return curretPrice;
    }

    public void setCurretPrice(int curretPrice) {
        this.curretPrice = curretPrice;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getStockExchange() {
        return stockExchange;
    }
    
    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

}
