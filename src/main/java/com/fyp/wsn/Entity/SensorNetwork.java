package com.fyp.wsn.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Nadith Premaratne on 24/05/2017.
 */
@Document(collection = "sensornetwork")
public class SensorNetwork {


    @Id
    private String id;
    private String configuration;
    private String  name;
    private String description;
    private String avarage_distance;


    public SensorNetwork() {
    }

    public SensorNetwork(String id, String configuration, String name, String description, String avarage_distance) {
        this.id = id;
        this.configuration = configuration;
        this.name = name;
        this.description = description;
        this.avarage_distance = avarage_distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvarage_distance() {
        return avarage_distance;
    }

    public void setAvarage_distance(String avarage_distance) {
        this.avarage_distance = avarage_distance;
    }
}
