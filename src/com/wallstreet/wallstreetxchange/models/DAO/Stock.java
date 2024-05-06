package com.wallstreet.wallstreetxchange.models.DAO;

public class Stock {

    private String stockName;
    private String stockSymbol;
    private String stockExchange;
    private int curretPrice;
    private int stockID;
    private String logoId;

    public Stock() {

    }

    public Stock(String stockName, String stockSymbol, String stockExchange) {
        this.stockName = stockName;
        this.stockSymbol = stockSymbol;
        this.stockExchange = stockExchange;
    }

    public Stock(int stockID, String stockName, String stockSymbol, String stockExchange) {

        this.stockID = stockID;
        this.stockName = stockName;
        this.stockSymbol = stockSymbol;
        this.stockExchange = stockExchange;

    }

    public Stock(String stockName, String stockSymbol, String stockExchange,String logoId) {
        
        this.stockName = stockName;
        this.stockSymbol = stockSymbol;
        this.stockExchange = stockExchange;
        this.logoId = logoId;

    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
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

    public String getLogoId() {
        return logoId;
    }

    public void setLogoId(String logoId) {
        this.logoId = logoId;
    }
}
