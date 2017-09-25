package com.fyp.wsn.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Nadith Premaratne on 07/05/2017.
 */
@Document(collection = "sensornode")
public class SensorNode {
    //Id of record
    @Id
    private String id;
    private String name;
    private String type;
    private String interval;
    private String color;
    private String doc;
    private String distance_from_base;
    private String internet_ssid;
    private String internet_password;
    private  String configuration;
    private String communication_method;
    private String description;


    public String getCommunication_method() {
        return communication_method;
    }

    public void setCommunication_method(String communication_method) {
        this.communication_method = communication_method;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }


    public SensorNode(String id, String name, String type, String interval, String color, String doc, String distance_from_base, String internet_ssid, String internet_password, String configuration, String communication_method, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.interval = interval;
        this.color = color;
        this.doc = doc;
        this.distance_from_base = distance_from_base;
        this.internet_ssid = internet_ssid;
        this.internet_password = internet_password;
        this.configuration = configuration;
        this.communication_method = communication_method;
        this.description = description;
    }

    public SensorNode() {
    }

    public String getId() {
        return id;
    }

    public String getInternet_ssid() {
        return internet_ssid;
    }

    public void setInternet_ssid(String internet_ssid) {
        this.internet_ssid = internet_ssid;
    }

    public String getInternet_password() {
        return internet_password;
    }

    public void setInternet_password(String internet_password) {
        this.internet_password = internet_password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getDistance_from_base() {
        return distance_from_base;
    }

    public void setDistance_from_base(String distance_from_base) {
        this.distance_from_base = distance_from_base;
    }
}
