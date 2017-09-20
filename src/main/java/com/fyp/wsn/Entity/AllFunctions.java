package com.fyp.wsn.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

/**
 * Created by Nadith Premaratne on 11/05/2017.
 */

@Document(collection = "allfunctions")
public class AllFunctions {

    @Id
    private String id;
    private HashMap<String,String> function_map;

    public AllFunctions() {
    }

    public AllFunctions(String id, HashMap<String, String> function_map) {
        this.id = id;
        this.function_map = function_map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, String> getFunction_map() {
        return function_map;
    }

    public void setFunction_map(HashMap<String, String> function_map) {
        this.function_map = function_map;
    }
}
