package com.application.masl7tak.constents;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public  static final String SOMETHING_WENT_WRONG="Something went wrong";
    public  static final String INVALID_DATA="Invalid data";
    public  static final String DATA_Inserted="Data Inserted";
    public  static final String WRONG_PASSWORD="Wrong Password";
    public static  Map<String, Object> responseMessage(Object responseMessage,Object error_code){
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", responseMessage);
        responseMap.put("error_code", error_code);
       return responseMap;
    }


}
