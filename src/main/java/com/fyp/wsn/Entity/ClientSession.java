package com.fyp.wsn.Entity;

import com.fyp.wsn.DataAccess.ClientSessionDAO;
import com.fyp.wsn.DataAccess.ClientSessionRepository;
import com.fyp.wsn.DataAccess.MicrocontrollerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

/**
 * Created by Nadith Premaratne on 04/05/2017.
 */
@Document(collection = "clientsession")
public class ClientSession {

    @Autowired
    private ClientSessionDAO clientSessionDAO;

    @Id
    private int id;
    private HashMap<String,Integer> current_usage_of_pins;

    public ClientSession() {


    }

    public ClientSession(int id, Microcontroller micro_id,ClientSessionDAO clientSessionDAO) {
        this.id = id;
        this.clientSessionDAO=clientSessionDAO;
        current_usage_of_pins=new HashMap<>();
        String [] pair_list =micro_id.getConfiguration().split(",");
        for(String x: pair_list){
            String [] key_value=x.split("-");
            current_usage_of_pins.put(key_value[0],Integer.parseInt(key_value[1]));
        }
    }

    public  boolean isvalid(Sensor sensor_id,Validation validation){

        String [] pair_list =sensor_id.getConfiguration().split(",");

        HashMap<String,Integer> temp_configuration_of_sensor=new HashMap<>();
        for(String x: pair_list){
            String [] key_value=x.split("-");
            temp_configuration_of_sensor.put(key_value[0],Integer.parseInt(key_value[1]));
        }

        for (String key: temp_configuration_of_sensor.keySet()){
            if (this.current_usage_of_pins.keySet().contains(key)){

                int value = this.current_usage_of_pins.get(key) - temp_configuration_of_sensor.get(key);
                if(value <0 ) {

                    validation.setError_description("Running Out of "+key +" Pins");
                    return false;
                }
            }
        }

        return true;
    }

    public  void updateCurrentPinConfiguration(Sensor sensor_id,Validation validation,ClientSessionDAO clientSessionDAOa){

        String [] pair_list =sensor_id.getConfiguration().split(",");

        HashMap<String,Integer> temp_configuration_of_sensor=new HashMap<>();
        for(String x: pair_list){
            String [] key_value=x.split("-");
            temp_configuration_of_sensor.put(key_value[0],Integer.parseInt(key_value[1]));
        }

        for (String key: temp_configuration_of_sensor.keySet()){
            if (this.current_usage_of_pins.keySet().contains(key)){

                int value = this.current_usage_of_pins.get(key) - temp_configuration_of_sensor.get(key);
                this.current_usage_of_pins.put(key, value);
            }
        }

        clientSessionDAOa.insertClientSession(this);


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String,Integer> getCurrent_usage_of_pins() {
        return current_usage_of_pins;
    }

    public void setCurrent_usage_of_pins(HashMap<String,Integer> map) {
        this.current_usage_of_pins = current_usage_of_pins;
    }
}
