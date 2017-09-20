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
    private String description;
    private String configuration;


    private String cpp_global;
    private String cpp_send;
    private String cpp_receive;
    private String cpp_setup;
    private String cpp_connect;
    private String cpp_disconnect;

    private String pin_map;

    public Communication() {
    }

    public Communication(String id, String display_name, String model_name, String description, String configuration, String cpp_global, String cpp_send, String cpp_receive, String cpp_setup, String cpp_connect, String cpp_disconnect, String pin_map) {
        this.id = id;
        this.display_name = display_name;
        this.model_name = model_name;
        this.description = description;
        this.configuration = configuration;
        this.cpp_global = cpp_global;
        this.cpp_send = cpp_send;
        this.cpp_receive = cpp_receive;
        this.cpp_setup = cpp_setup;
        this.cpp_connect = cpp_connect;
        this.cpp_disconnect = cpp_disconnect;
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

    public String getCpp_disconnect() {
        return cpp_disconnect;
    }

    public void setCpp_disconnect(String cpp_disconnect) {
        this.cpp_disconnect = cpp_disconnect;
    }

    public String getPin_map() {
        return pin_map;
    }

    public void setPin_map(String pin_map) {
        this.pin_map = pin_map;
    }
}

