package com.wallstreet.wallstreetxchange.models.util;

import java.util.HashMap;
import java.util.Map;

public class StatusCode {
    
    String status = ""; 
    
    public static Map<String, String> statusmap =  new HashMap<>(){{
        put("0", null);
        
        put("200","Login Successfull");
        put("111","Login Credentials failed");


    }};

    public String toString(){
        return this.status;
    }

    public int toInt(){
        return Integer.parseInt(status);
    }

    public StatusCode(String code ){
        this.status = code;
    }
}
