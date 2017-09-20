package com.fyp.wsn.DataAccess;

import com.fyp.wsn.Entity.Microcontroller;
import com.fyp.wsn.Entity.SensorNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Nadith Premaratne on 11/05/2017.
 */

@Component
public class SensorNodeDAO {

    @Autowired
    private SensorNodeRepository sensorNodeRepository;


    public Collection<SensorNode> getAllSensorNode() {
        return sensorNodeRepository.findAll();
    }

    public SensorNode getSensorNodeById(String id) {
        return sensorNodeRepository.findOne(id);
    }

    public void insertSensorNode(SensorNode sensorNode) {
        sensorNodeRepository.save(sensorNode);
    }

    public void removeSensorNodeById(String id) {
        sensorNodeRepository.delete(sensorNodeRepository.findOne(id));
    }

    public SensorNode getSensorNodeByName(String Name){
       Collection<SensorNode> temp_list=getAllSensorNode();

       for(SensorNode x : temp_list){

           if(x.getName().equals(Name)) return  x;

       }

       return null;
    }

    public void updateSensorNodeById(SensorNode sensorNode) {

        SensorNode temp_sensorNode = sensorNodeRepository.findOne(sensorNode.getId());
        temp_sensorNode.setName(sensorNode.getName());
        temp_sensorNode.setType(sensorNode.getType());
        temp_sensorNode.setInterval(sensorNode.getInterval());
        temp_sensorNode.setDistance_from_base(sensorNode.getDistance_from_base());
        temp_sensorNode.setDoc(sensorNode.getDoc());
        temp_sensorNode.setInternet_password(sensorNode.getInternet_password());
        temp_sensorNode.setInternet_ssid(sensorNode.getInternet_ssid());

        sensorNodeRepository.save(temp_sensorNode);

    }
}
