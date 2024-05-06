package com.wallstreet.wallstreetxchange.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.wallstreet.wallstreetxchange.models.util.StatusCode;
import com.wallstreet.wallstreetxchange.models.util.StockCalcOperations;
import com.wallstreet.wallstreetxchange.properties.SQLqueries;

public class DBOModule {

    private Connection con = null;

    public DBOModule(Connection con) {
        this.con = con;
    }

    public void test() {
        try (PreparedStatement stm = con.prepareStatement(SQLqueries.TEST)) {

            stm.setString(1, "sunapnaaa");
            stm.setString(2, "iuygfc@gmail.com");
            stm.setString(3, "nishant");
            stm.setString(4, "nishant");
            stm.setString(5, "nishant");

            stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public StatusCode authendicate(String username, String password) {

        try (PreparedStatement stm = con.prepareStatement(SQLqueries.GET_USER_CREAD)) {

            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet result = stm.executeQuery();

            if (result.next()) {
                return new StatusCode("200");
            }
            return new StatusCode("111");
        } catch (Exception e) {
            System.out.println(e);
            return new StatusCode("0");
        }

    }

    public User getUserObj(String username, String password) {

        User userObj = null;
        try (PreparedStatement stm = con.prepareStatement(SQLqueries.GET_USER_CREAD)) {

            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet result = stm.executeQuery();

            if (result.isBeforeFirst()) {
                result.next();
                userObj = new User();
                userObj.setUserID(result.getInt("userID"));
                userObj.setUsername(result.getString("username"));
                userObj.setEmail(result.getString("email"));
                userObj.setVerified(result.getBoolean("isVerified"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userObj;
    }

    public Stock getStockObj(String symbol){
        
        Stock stock = null;
        try(PreparedStatement stm = con.prepareStatement(SQLqueries.GET_STOCK)){

            System.out.println(stm.toString());
            stm.setString(1, symbol);
            ResultSet result = stm.executeQuery();
            result.next();
            stock = new Stock(result.getInt("stockID"),result.getString("stockname"),result.getString("stockSymbol"),result.getString("stockExchange"));
            System.out.println(result.getInt("stockID"));
            System.out.println(result.getString("stockname"));

            
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return stock;

    }

    public JSONArray getUserStocks(User user) {

        JSONArray jsonarray = new JSONArray();
        try (PreparedStatement stm = con.prepareStatement(SQLqueries.GET_USER_STOCKS_TRANSACTION)) {

            stm.setInt(1, user.getUserID());
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                JSONObject json = new JSONObject();
                
                json.put("stockName", result.getString("stockName"));
                json.put("stockSymbol", result.getString("stockSymbol"));
                json.put("stockExchange", result.getString("stockExchange"));
                json.put("quantity", result.getInt("quantity"));
                json.put("transactionType", result.getBoolean("transactionType"));
                json.put("transactionPrice", result.getDouble("transactionPrice"));
                json.put("transactionDate", result.getTimestamp("transactionDate"));

                double totalInvestment = result.getInt("quantity") * result.getDouble("transactionPrice");
                json.put("totalInvestment", totalInvestment);
                
                jsonarray.put(json);

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

        return jsonarray;
    }

    public JSONObject getUserWalletMETA(User user) {
        JSONObject json = new JSONObject();

        try (PreparedStatement stm = con.prepareStatement(SQLqueries.GET_WALLET_META)) {
            stm.setInt(1, user.getUserID());
            ResultSet result = stm.executeQuery();
            
            result.next();
            json.put("availableFunds:", result.getDouble("availableFunds"));
            json.put("usedFunds:", result.getDouble("usedFunds"));
            json.put("lastWithdrawal", result.getString("lastWithdrawal"));
            json.put("lastDeposited", result.getString("lastDeposited"));

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return json;

    }

    public JSONObject getUserStock(User user, String symbol) {

        JSONObject json = null;

        try (PreparedStatement stm = con.prepareStatement(SQLqueries.GET_USER_STOCK_TRANSACTION)) {
            stm.setInt(1, user.getUserID());
            stm.setString(2, symbol);
            ResultSet result = stm.executeQuery();

            if (result.next()) {
                json = new JSONObject();
                json.put("stockName", result.getString("stockName"));
                json.put("stockSymbol", result.getString("stockSymbol"));
                json.put("stockExchange", result.getString("stockExchange"));
                json.put("quantity", result.getString("quantity"));
                json.put("transactionType", result.getBoolean("transactionType"));
                json.put("transactionPrice", result.getString("transactionPrice"));
                json.put("transactionDate", result.getTimestamp("transactionDate"));
            
            }

            

        } catch (Exception e) {
            System.out.println(e);
        }

        return json;
    }

    private boolean isAlreadyTraded(User user, String symbol) {
        
        JSONObject json = getUserStock(user, symbol);
        if (json != null) {
            return true;  
        }
        return false;

    }

    public StatusCode tradeStock(User user,JSONObject request,double currentPrice){

        System.out.println(request.toString());
        String stockSymbol = request.getString("stockSymbol");
        Stock stock = getStockObj(stockSymbol);
        System.out.println(stock.getStockID());

        if (isAlreadyTraded(user, stockSymbol)) {

            JSONObject json = getUserStock(user,stockSymbol);
            StockCalcOperations obj = new StockCalcOperations();
            request.put("transactionPrice", currentPrice);

            JSONObject njson = obj.getStockUpdateCalc(json, request);

            if (njson == null) {
                return new StatusCode("1111");
            }
  
            try (PreparedStatement stm = con.prepareStatement(SQLqueries.UPDATE_STOCK_TRANSACTION)) {

                stm.setInt(1, njson.getInt("quantity"));
                stm.setBoolean(2, njson.getBoolean("type"));
                stm.setDouble(3, njson.getDouble("price"));
                stm.setInt(4, user.getUserID());
                stm.setString(5, request.getString("stockSymbol"));
                stm.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
            return new StatusCode("1112");
        } else {

            try (PreparedStatement stm = con.prepareStatement(SQLqueries.ADD_TRANSACTION)) {
                stm.setInt(1,10);
                stm.setInt(2, user.getUserID());
                System.out.println(stock.getStockID());
                stm.setInt(3, stock.getStockID());
                stm.setInt(4, request.getInt("quantity"));
                stm.setBoolean(5, request.getBoolean("transactionType"));
                stm.setDouble(6, currentPrice);
                stm.executeUpdate();
                
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
            return new StatusCode("1113");
        }

    }


    public static void main(String[] args) {
        User user = new User();
        user.setUserID(1);
        user.setUsername("uvchan");
        DBConnection con = new DBConnection();
        DBOModule db = new DBOModule(con.getConnection());
        // System.out.println(db.getUserWalletMETA(user));
        System.out.println(db.getUserStocks(user));
    }
}
