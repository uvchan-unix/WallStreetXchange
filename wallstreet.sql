USE wallstreet;


CREATE TABLE Users(
    userID BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(64),
    email VARCHAR(64),
    password VARCHAR(64),
    joinedAt TIMESTAMP,
    isVerified BOOLEAN
); 

CREATE TABLE Stocks(
    stockID INTEGER PRIMARY KEY AUTO_INCREMENT,
    stockname VARCHAR(64),
    stockSymbol VARCHAR(64),
    stockExchange VARCHAR(64)
);

CREATE TABLE Settings(
    userID BIGINT,
    userProfile BLOB,
    json VARCHAR(244),
    FOREIGN KEY (userID) REFERENCES Users(userID)
);

CREATE TABLE BankDetails(
    userID BIGINT,
    accountID BIGINT PRIMARY KEY AUTO_INCREMENT,
    accountNO VARCHAR(16),
    ifscCode VARCHAR(16),
    isverified BOOLEAN,
    FOREIGN KEY (userID) REFERENCES Users(userID)
);

CREATE TABLE StockTransactions(
    transactionID INTEGER,
    userID BIGINT,
    stockID INTEGER,
    quantity MEDIUMINT,
    transactionType BOOLEAN,
    transactionPrice INTEGER,
    transactionDate TIMESTAMP,
    FOREIGN KEY (userID) REFERENCES Users(userID),
    FOREIGN KEY (stockID) REFERENCES Stocks(stockID)
);
