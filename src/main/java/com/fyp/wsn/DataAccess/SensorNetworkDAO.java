package com.fyp.wsn.DataAccess;

import com.fyp.wsn.Entity.SensorNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Nadith Premaratne on 24/05/2017.
 */
@Component
public class SensorNetworkDAO {

    @Autowired
    private SensorNetworkRepository sensorNetworkRepository;


    public Collection<SensorNetwork> getAllSensorNetwork() {
        return sensorNetworkRepository.findAll();
    }

    public SensorNetwork getSensorNetworkById(String id) {
        return sensorNetworkRepository.findOne(id);
    }

    public void insertSensorNetwork(SensorNetwork sensorNetwork) {
        sensorNetworkRepository.save(sensorNetwork);
    }

    public void removeSensorNetworkById(String id) {
        sensorNetworkRepository.delete(sensorNetworkRepository.findOne(id));
    }

    public SensorNetwork getSensorNetworkByName(String Name){
        Collection<SensorNetwork> temp_list=getAllSensorNetwork();

        for(SensorNetwork x : temp_list){

            if(x.getName().equals(Name)) return  x;

        }

        return null;
    }

    public void updateSensorNetworkById(SensorNetwork sensorNetwork) {

        SensorNetwork temp_sensorNetwork = sensorNetworkRepository.findOne(sensorNetwork.getId());
        temp_sensorNetwork.setName(sensorNetwork.getName());
        temp_sensorNetwork.setAvarage_distance(sensorNetwork.getAvarage_distance());
        temp_sensorNetwork.setConfiguration(sensorNetwork.getConfiguration());
        temp_sensorNetwork.setDescription(sensorNetwork.getDescription());

        sensorNetworkRepository.save(temp_sensorNetwork);

    }
}
