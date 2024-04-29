package com.wallstreet.wallstreetxchange.properties;

public class SQLqueries{

    public static final String TEST = "INSERT INTO Users VALUES(?,?,?,?,?,\"false\")";
    public static final String GET_USER_CREAD = "SELECT * FROM Users WHERE username = ? AND password = ?";

    // Stocks related Query goes here....
    
    // Admin
    public static final String INSERT_STOCKS = "INSERT INTO Stocks(stockName,stockSymbol,stockExchange) VALUES(?,?,?)";
    public static final String INITIATE_WALLET = "INSERT INTO UserWallet(userID,availableFunds,usedFunds) Values(?,?,?)";
    // Users
    public static final String GET_STOCK = "SELECT * FROM Stocks WHERE stockSymbol = ?";
    public static final String ADD_TRANSACTION = "INSERT INTO StockTransactions(transactionID,userID,stockID,quantity,transactionType,transactionPrice) VALUES(?,?,?,?,?,?)";
    public static final String UPDATE_STOCK_TRANSACTION = "UPDATE StockTransactions SET quantity = ?, transactionType  = ? ,transactionPrice = ? WHERE userID = ? AND stockID = (SELECT stockID FROM Stocks WHERE stockSymbol = ?)";
    
    public static final String GET_USER_STOCKS_TRANSACTION = "SELECT stockName,stockSymbol,stockExchange,quantity, transactionType, transactionPrice, transactionDate FROM StockTransactions LEFT JOIN Stocks ON StockTransactions.stockID = Stocks.stockID WHERE userID = ?";
    public static final String GET_USER_STOCK_TRANSACTION = "SELECT stockName,stockSymbol,stockExchange,quantity, transactionType, transactionPrice, transactionDate FROM StockTransactions LEFT JOIN Stocks ON StockTransactions.stockID = Stocks.stockID WHERE userID = ? AND stockSymbol = ?";

    // Wallet Realated Queries .././././UPDATE UserWallet  SET availableFunds = "10000000", usedFunds = "50000" WHERE userID = "1"

    public static final String UPDATE_FUNDS = "UPDATE UserWallet  SET availableFunds = ?, usedFunds = ? WHERE userID = ?";
    public static final String GET_WALLET_META = "SELECT availableFunds , usedFunds , lastDeposited , lastWithdrawal FROM UserWallet WHERE userID = ?";




}