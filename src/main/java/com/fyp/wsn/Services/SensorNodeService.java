package com.fyp.wsn.Services;

import com.fyp.wsn.DataAccess.*;
import com.fyp.wsn.Entity.Sensor;
import com.fyp.wsn.Entity.SensorNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Nadith Premaratne on 15/05/2017.
 */
@Service
public class SensorNodeService {

    // connect with data access class
    @Autowired
    private SensorNodeDAO sensorNodeDAO;

    @Autowired
    private SensorDAO sensorDAO;

    @Autowired
    private MicrocontrollerDAO microcontrollerDAO;

    @Autowired
    private AllFunctionsDAO allFunctionsDAO;

    @Autowired
    private CommunicationDAO communicationDAO;

    public Collection<SensorNode> getAllSensorNode(){


        return this.sensorNodeDAO.getAllSensorNode();
    }

    public SensorNode getSensorNodeById(String id){

        return this.sensorNodeDAO.getSensorNodeById(id);


    }

    public void removeSensorNodeById(String id) {

        this.sensorNodeDAO.removeSensorNodeById(id);
    }

    public void updateSensorNodeById(SensorNode sensorNode){

        this.sensorNodeDAO.updateSensorNodeById(sensorNode);
    }

    public void insertSensorNode(SensorNode sensorNode) {

        this.sensorNodeDAO.insertSensorNode(sensorNode);

        CodeGenForNode temp_code = new CodeGenForNode(sensorNode, this.microcontrollerDAO, this.sensorNodeDAO, this.sensorDAO,this.allFunctionsDAO,this.communicationDAO);

        if(sensorNode.getType().equals("node")) {
            temp_code.CodeforNode();
        }
        else if(sensorNode.getType().equals("base")) {
            temp_code.CodeforBase();
        }


    }
}
