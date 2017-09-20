package com.fyp.wsn.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by noahn on 7/18/2017.
 */
@Document(collection = "communication")
public class Communication {


    //Id of record
    @Id
    private String id;
    private String display_name;
    private String model_name;
    private String rated_current;
    private String power_supply;
    private String description;
    private String configuration;
    private String color;

    private String cpp_global;
    private String cpp_send;
    private String cpp_receive;
    private String cpp_setup;
    private String cpp_connect;

    private String python_global;
    private String python_send;
    private String python_receive;
    private String python_setup;
    private String python_connect;

    private String pin_map;

    public Communication() {
    }

    public Communication(String id, String display_name, String model_name, String rated_current, String power_supply, String description, String configuration, String color, String cpp_global, String cpp_send, String cpp_receive, String cpp_setup, String cpp_connect, String python_global, String python_send, String python_receive, String python_setup, String python_connect, String pin_map) {
        this.id = id;
        this.display_name = display_name;
        this.model_name = model_name;
        this.rated_current = rated_current;
        this.power_supply = power_supply;
        this.description = description;
        this.configuration = configuration;
        this.color = color;
        this.cpp_global = cpp_global;
        this.cpp_send = cpp_send;
        this.cpp_receive = cpp_receive;
        this.cpp_setup = cpp_setup;
        this.cpp_connect = cpp_connect;
        this.python_global = python_global;
        this.python_send = python_send;
        this.python_receive = python_receive;
        this.python_setup = python_setup;
        this.python_connect = python_connect;
        this.pin_map = pin_map;
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

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCpp_global() {
        return cpp_global;
    }

    public void setCpp_global(String cpp_global) {
        this.cpp_global = cpp_global;
    }

    public String getCpp_send() {
        return cpp_send;
    }

    public void setCpp_send(String cpp_send) {
        this.cpp_send = cpp_send;
    }

    public String getCpp_receive() {
        return cpp_receive;
    }

    public void setCpp_receive(String cpp_receive) {
        this.cpp_receive = cpp_receive;
    }

    public String getCpp_setup() {
        return cpp_setup;
    }

    public void setCpp_setup(String cpp_setup) {
        this.cpp_setup = cpp_setup;
    }

    public String getCpp_connect() {
        return cpp_connect;
    }

    public void setCpp_connect(String cpp_connect) {
        this.cpp_connect = cpp_connect;
    }

    public String getPython_global() {
        return python_global;
    }

    public void setPython_global(String python_global) {
        this.python_global = python_global;
    }

    public String getPython_send() {
        return python_send;
    }

    public void setPython_send(String python_send) {
        this.python_send = python_send;
    }

    public String getPython_receive() {
        return python_receive;
    }

    public void setPython_receive(String python_receive) {
        this.python_receive = python_receive;
    }

    public String getPython_setup() {
        return python_setup;
    }

    public void setPython_setup(String python_setup) {
        this.python_setup = python_setup;
    }

    public String getPython_connect() {
        return python_connect;
    }

    public void setPython_connect(String python_connect) {
        this.python_connect = python_connect;
    }

    public String getPin_map() {
        return pin_map;
    }

    public void setPin_map(String pin_map) {
        this.pin_map = pin_map;
    }
}
