package com.fyp.wsn.Services;

import com.fyp.wsn.DataAccess.AllFunctionsDAO;
import com.fyp.wsn.DataAccess.MicrocontrollerDAO;
import com.fyp.wsn.DataAccess.SensorDAO;
import com.fyp.wsn.DataAccess.SensorNodeDAO;
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

        SensorNode temp_sensornode=this.sensorNodeDAO.getSensorNodeByName(sensorNode.getName());
        CodeGenForNode temp_code = new CodeGenForNode(sensorNode.getConfiguration(), sensorNode.getName(), this.microcontrollerDAO, this.sensorNodeDAO, this.sensorDAO,this.allFunctionsDAO);

        if(temp_sensornode.getType().equals("node")) {
            temp_code.CodeforNode();
        }
        else if(temp_sensornode.getType().equals("base")) {
            temp_code.CodeforBase();
        }


    }
}
