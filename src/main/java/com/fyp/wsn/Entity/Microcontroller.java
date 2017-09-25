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
    private String description;
    private String configuration;
    private String pin_map;

    private String code_structure_path_to_xml;
    private String avarage_operating_current;
    private String voltage;
    private String number_of_sleep_level;
    private String current_for_level;
    private String time_for_current_levels;


    public Microcontroller() {
    }


    public String setConfig(String pinmap) {
        HashMap<String, Integer> temp_map = new HashMap<>();

        String[] feilds = pinmap.split(",");

        for (String x : feilds) {
            String[] temp_key = x.split("-");

            if (temp_map.containsKey(temp_key[1])) {

                int value = temp_map.get(temp_key[1]);
                value++;
                temp_map.put(temp_key[1], value);

            } else {
                temp_map.put(temp_key[1], 1);

            }


        }

        String config = "";
        for (Map.Entry<String, Integer> entry : temp_map.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();

            config += key + "-" + value + ",";

        }

        return config.substring(0, config.length() - 1);

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

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getPin_map() {
        return pin_map;
    }

    public void setPin_map(String pin_map) {
        this.pin_map = pin_map;
    }

    public String getCode_structure_path_to_xml() {
        return code_structure_path_to_xml;
    }

    public void setCode_structure_path_to_xml(String code_structure_path_to_xml) {
        this.code_structure_path_to_xml = code_structure_path_to_xml;
    }

    public String getAvarage_operating_current() {
        return avarage_operating_current;
    }

    public void setAvarage_operating_current(String avarage_operating_current) {
        this.avarage_operating_current = avarage_operating_current;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getNumber_of_sleep_level() {
        return number_of_sleep_level;
    }

    public void setNumber_of_sleep_level(String number_of_sleep_level) {
        this.number_of_sleep_level = number_of_sleep_level;
    }

    public String getCurrent_for_level() {
        return current_for_level;
    }

    public void setCurrent_for_level(String current_for_level) {
        this.current_for_level = current_for_level;
    }

    public String getTime_for_current_levels() {
        return time_for_current_levels;
    }

    public void setTime_for_current_levels(String time_for_current_levels) {
        this.time_for_current_levels = time_for_current_levels;
    }
}