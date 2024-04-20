package com.wallstreet.wallstreetxchange.properties;

public class SQLqueries{

    public static final String TEST = "INSERT INTO Users VALUES(?,?,?,?,?,\"false\")";
    public static final String GET_USER_CREAD = "SELECT * FROM Users WHERE username = ? AND password = ?";

    // Stocks related Query goes here....
    // Admin
    public static final String INSERT_STOCKS = "INSERT INTO Stocks(stockName,stockSymbol,stockExchange) VALUES(?,?,?)";
    
    // Users
    public static final String ADD_TRANSACTION = "INSERT INTO StockTransactions(transactionID,userID,stockID,quantity,transactionType,transactionPrice) VALUES(?,?,?,?,?,?)";
    public static final String GET_USER_STOCK_TRANSACTION = "SELECT stockName,stockSymbol,stockExchange,quantity, transactionType, transactionPrice, transactionDate FROM StockTransactions LEFT JOIN Stocks ON StockTransactions.stockID = Stocks.stockID WHERE userID = ?";

}