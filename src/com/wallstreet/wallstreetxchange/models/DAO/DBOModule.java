package com.wallstreet.wallstreetxchange.models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.wallstreet.wallstreetxchange.models.util.StatusCode;
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

    public User getUserObj(String username,String password) {

        User userObj = null;
        try (PreparedStatement stm = con.prepareStatement(SQLqueries.GET_USER_CREAD)) {

            stm.setString(1,username);
            stm.setString(2,password);
            ResultSet result = stm.executeQuery();

            if (result.isBeforeFirst()) {
                result.next();
                userObj = new User();
                userObj.setUserID(result.getInt("userID"));
                userObj.setUsername(result.getNString("username"));
                userObj.setEmail(result.getNString("email"));
                userObj.setVerified(result.getBoolean("isVerified"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userObj;
    }

    public JSONArray getUserStocks(User user){

        JSONArray jsonarray = new JSONArray(); 
        try(PreparedStatement stm = con.prepareStatement(SQLqueries.GET_USER_STOCK_TRANSACTION)){
            
            stm.setInt(1,user.getUserID());
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                JSONObject json = new JSONObject();
                json.put("stockName", result.getString("stockName"));
                json.put("stockSymbol", result.getString("stockSymbol"));
                json.put("stockExchange", result.getString("stockExchange"));
                json.put("quantity", result.getString("quantity"));
                json.put("transactionType", result.getBoolean("transactionType"));
                json.put("transactionPrice", result.getString("transactionPrice"));
                json.put("transactionDate", result.getTimestamp("transactionDate"));
                
                jsonarray.put(json);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonarray;
    }

}
