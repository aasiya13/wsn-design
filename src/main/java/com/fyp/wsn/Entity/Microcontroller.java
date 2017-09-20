package com.fyp.wsn.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nadith Premaratne on 03/05/2017.
 */
@Document(collection = "microcontroller")
public class Microcontroller {


    @Id
    private String id;
    private String display_name;
    private String model_name;
    private String color;
    private String rated_current;
    private String power_supply;
    private String description;


    private String configuration;
    private String supported_lan;
    private String pin_map;

    private String initialize;
    private String wifi_network;
    private String lora_network;


    public Microcontroller() {
    }


    public String getPin_map() {
        return pin_map;
    }

    public void setPin_map(String pin_map) {
        this.pin_map = pin_map;
    }

//    public Microcontroller(String id, String display_name, String model_name, String color, String rated_current, String power_supply, String description, String supported_lan, String pin_map, String initialize, String wifi_network, String lora_network) {
//        this.id = id;
//        this.display_name = display_name;
//        this.model_name = model_name;
//        this.color = color;
//        this.rated_current = rated_current;
//        this.power_supply = power_supply;
//        this.description = description;
//        this.supported_lan = supported_lan;
//        this.pin_map = pin_map;
//        this.initialize = initialize;
//        this.wifi_network = wifi_network;
//        this.lora_network = lora_network;
//        this.configuration=setConfig(pin_map);
//
//
//    }

    public String  setConfig(String pinmap){
        HashMap<String,Integer> temp_map=new HashMap<>();

        String [] feilds=pinmap.split(",");

        for(String x: feilds){
            String [] temp_key =x.split("-");

            if(temp_map.containsKey(temp_key[1])){

                int value=temp_map.get(temp_key[1]);
                value++;
                temp_map.put(temp_key[1],value);

            }
            else{
                temp_map.put(temp_key[1],1);

            }


        }

        String config="";
        for(Map.Entry<String,Integer> entry: temp_map.entrySet()){
            String key=entry.getKey();
            int value=entry.getValue();

            config += key+"-"+value+",";

        }

        return config.substring(0,config.length()-1);

    }

//    public Microcontroller(String id, String display_name, String model_name, String color, String rated_current, String power_supply, String description, String supported_lan, String pin_map, String initialize, String wifi_network, String lora_network, String configuration) {
//        this.id = id;
//        this.display_name = display_name;
//        this.model_name = model_name;
//        this.color = color;
//        this.rated_current = rated_current;
//        this.power_supply = power_supply;
//        this.description = description;
//
//        this.supported_lan = supported_lan;
//        this.pin_map = pin_map;
//        this.initialize = initialize;
//        this.wifi_network = wifi_network;
//        this.lora_network = lora_network;
//        this.configuration = setConfig(pin_map);
//    }

    public String getRated_current() {
        return rated_current;
    }

    public void setRated_current(String rated_current) {
        this.rated_current = rated_current;
    }


    public String getPower_supply() {
        return power_supply;
    }

    public void setPower_supply(String power_supply) {
        this.power_supply = power_supply;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInitialize() {
        return initialize;
    }

    public void setInitialize(String initialize) {
        this.initialize = initialize;
    }

    public String getWifi_network() {
        return wifi_network;
    }

    public void setWifi_network(String wifi_network) {
        this.wifi_network = wifi_network;
    }

    public String getLora_network() {
        return lora_network;
    }

    public void setLora_network(String lora_network) {
        this.lora_network = lora_network;
    }

    public String getSupported_lan() {
        return supported_lan;
    }

    public void setSupported_lan(String supported_lan) {
        this.supported_lan = supported_lan;
    }

//    public Microcontroller(String id, String display_name, String allias, String color, String model_name) {
//        this.id = id;
//        this.display_name = display_name;
//        this.color = color;
//        this.model_name = model_name;
//    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = setConfig(this.getPin_map());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }
}
