package com.wallstreet.wallstreetxchange.controllers.stocks;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import com.wallstreet.wallstreetxchange.models.DAO.DBOModule;
import com.wallstreet.wallstreetxchange.models.DAO.User;
import com.wallstreet.wallstreetxchange.properties.FunctionDefanitions;

@WebServlet("/serice/getUserStocks")

public class GetUserStocks extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        resp.setContentType("application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

        User user = (User)req.getSession().getAttribute("User");
        DBOModule db = FunctionDefanitions.getDbOperarion(req);
        
        JSONArray json = db.getUserStocks(user);
        FunctionDefanitions.outputWriter(json.toString(), resp);
        

    }
    
    
}
