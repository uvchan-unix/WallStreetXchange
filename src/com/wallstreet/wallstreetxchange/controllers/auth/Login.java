package com.wallstreet.wallstreetxchange.controllers.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import com.wallstreet.wallstreetxchange.models.DAO.*;
import com.wallstreet.wallstreetxchange.models.util.StatusCode;
import com.wallstreet.wallstreetxchange.properties.FunctionDefanitions;

@WebServlet("/auth/login")

public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject json = FunctionDefanitions.inputReader(req);
        String username = json.getString("apparaw");
        String password = json.getString("suparaw");

        DBOModule db = FunctionDefanitions.getDbOperarion(req);
        StatusCode code = db.authendicate(username, password);

        if (code.toString() == "200") {
            HttpSession session = req.getSession();
            User verifiedUser = db.getUserObj(username, password);
            session.setAttribute("User", verifiedUser);
            System.out.println(verifiedUser.getUsername());
            FunctionDefanitions.outputWriter("Success"+verifiedUser.getUserID()+"k", resp);

        }else if(code.toString() == "111") {
            FunctionDefanitions.outputWriter("tappuuu" + code.toString() , resp);
        }

    }

}
