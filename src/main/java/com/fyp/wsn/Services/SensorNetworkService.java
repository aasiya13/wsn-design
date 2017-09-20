package com.fyp.wsn.Services;

import com.fyp.wsn.DataAccess.MicrocontrollerDAO;
import com.fyp.wsn.DataAccess.SensorDAO;
import com.fyp.wsn.DataAccess.SensorNetworkDAO;
import com.fyp.wsn.DataAccess.SensorNodeDAO;
import com.fyp.wsn.Entity.SensorNetwork;
import com.fyp.wsn.Entity.SensorNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Nadith Premaratne on 24/05/2017.
 */
@Service
public class SensorNetworkService {

    @Autowired
    private SensorNodeDAO sensorNodeDAO;
    @Autowired
    private MicrocontrollerDAO microcontrollerDAO;
    @Autowired
    private SensorNetworkDAO sensorNetworkDAO;

    public Collection<SensorNetwork> getAllSensorNetwork(){


        return this.sensorNetworkDAO.getAllSensorNetwork();
    }

    public SensorNetwork getSensorNetworkById(String id){

        return this.sensorNetworkDAO.getSensorNetworkById(id);


    }

    public void removeSensorNetworkById(String id) {

        this.sensorNetworkDAO.removeSensorNetworkById(id);
    }

    public void updateSensorNodeById(SensorNetwork sensorNetwork){

        this.sensorNetworkDAO.updateSensorNetworkById(sensorNetwork);
    }

    public void insertSensorNetwork(SensorNetwork sensorNetwork) {

        this.sensorNetworkDAO.insertSensorNetwork(sensorNetwork);

        CodeGenForNetwork temp_code = new CodeGenForNetwork(sensorNetwork.getConfiguration(),this.sensorNodeDAO,this.microcontrollerDAO,this.sensorNetworkDAO);

        temp_code.finalizedNetworkCode();


    }
}
