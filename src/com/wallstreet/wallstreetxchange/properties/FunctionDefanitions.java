package com.wallstreet.wallstreetxchange.properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.wallstreet.wallstreetxchange.models.DAO.DBOModule;
import com.wallstreet.wallstreetxchange.models.DAO.User;
import com.wallstreet.wallstreetxchange.models.congiguration.StockCollections;

public class FunctionDefanitions {

    private static ServletContext getServletContexti(HttpServletRequest request) {
        return request.getServletContext();
    }

    public static DBOModule getDbOperarion(HttpServletRequest request) {

        ServletContext context = getServletContexti(request);
        DBOModule dbOperarion = (DBOModule) context.getAttribute("db");
        return dbOperarion;
    }

    public static StockCollections getStockCollections(HttpServletRequest request) {

        ServletContext context = getServletContexti(request);
        StockCollections stockCollections = (StockCollections) context.getAttribute("stockcollection");
        return stockCollections;
    }

    public static User getuserObj(HttpServletRequest request){
        
        User user = (User) request.getSession().getAttribute("User");
        return user;
    }
        

    public static void outputWriter(String output, HttpServletResponse resp) throws ServletException, IOException{

        try {

            PrintWriter write = resp.getWriter();
            write.write(output);
            write.flush();

        } catch (IOException e) {
            
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error processing request");

        } catch (Exception e) {
            
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error processing request");
        }

    }

    public static JSONObject inputReader(HttpServletRequest req){
        
        JSONObject json = null;
        StringBuilder payload = new StringBuilder();
        try {
            
            BufferedReader reader = req.getReader();
            String line;

            while ((line = reader.readLine())!= null) {
                payload.append(line);
            }
            json = new JSONObject(payload.toString());

        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return json;

    }

    public static JSONObject inputReader(InputStreamReader req){
        
        JSONObject json = null;
        StringBuilder payload = new StringBuilder();
        try {
            
            BufferedReader reader = new BufferedReader(req);
            String line;

            while ((line = reader.readLine())!= null) {
                payload.append(line);
            }
            json = new JSONObject(payload.toString());

        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return json;

    }


}
