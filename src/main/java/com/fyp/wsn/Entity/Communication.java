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


    private String global_server;
    private String global_client;
    private String cpp_send;
    private String cpp_receive;
    private String client_setup;
    private String server_setup;
    private String cpp_connect;
    private String cpp_disconnect;
    private String includes;
    private String helper_function;
    private String hardware_enable;

    private String pin_map;

    public Communication() {
    }

    public Communication(String id, String display_name, String model_name, String description, String configuration, String global_server, String global_client, String cpp_send, String cpp_receive, String client_setup, String server_setup, String cpp_connect, String cpp_disconnect, String includes, String helper_function, String hardware_enable, String pin_map) {
        this.id = id;
        this.display_name = display_name;
        this.model_name = model_name;
        this.description = description;
        this.configuration = configuration;
        this.global_server = global_server;
        this.global_client = global_client;
        this.cpp_send = cpp_send;
        this.cpp_receive = cpp_receive;
        this.client_setup = client_setup;
        this.server_setup = server_setup;
        this.cpp_connect = cpp_connect;
        this.cpp_disconnect = cpp_disconnect;
        this.includes = includes;
        this.helper_function = helper_function;
        this.hardware_enable = hardware_enable;
        this.pin_map = pin_map;
    }

    public String getGlobal_server() {
        return global_server;
    }

    public void setGlobal_server(String global_server) {
        this.global_server = global_server;
    }

    public String getGlobal_client() {
        return global_client;
    }

    public void setGlobal_client(String global_client) {
        this.global_client = global_client;
    }

    public String getHardware_enable() {
        return hardware_enable;
    }

    public void setHardware_enable(String hardware_enable) {
        this.hardware_enable = hardware_enable;
    }

    public String getHelper_function() {
        return helper_function;
    }

    public void setHelper_function(String helper_function) {
        this.helper_function = helper_function;
    }

    public String getClient_setup() {
        return client_setup;
    }

    public void setClient_setup(String client_setup) {
        this.client_setup = client_setup;
    }

    public String getServer_setup() {
        return server_setup;
    }

    public void setServer_setup(String server_setup) {
        this.server_setup = server_setup;
    }

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
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

