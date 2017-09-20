package com.fyp.wsn.Controller;

import com.fyp.wsn.Entity.Sensor;
import com.fyp.wsn.Entity.SensorNode;
import com.fyp.wsn.Services.SensorNodeService;
import com.fyp.wsn.Services.SensorService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Nadith Premaratne on 07/05/2017.
 */


@RestController
// This is how you map your end points to entire class
@RequestMapping("/sensornode")
@CrossOrigin()

//This annotation for auto generate API documentation
@Api(
        name = "sensornode data API",
        description = "This API provides set of methords that can use to manipulate sensornode data"
)
public class CreationOfSensorNodeController {

    // connecting with sensor service class(where business logic is implemented)
    @Autowired
    private SensorNodeService sensorNodeService;

    //Defining the request method (this time it is GET)
    @RequestMapping(method = RequestMethod.GET)
    //For API documentation
    @ApiMethod(description = "Get all available sensors in System ")
    public Collection<SensorNode> getAllSensorNode(){
        return sensorNodeService.getAllSensorNode();
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiMethod(description = "Get specific sensor details by Id ")
    public String SayHello(){
        return "Hello All Good we are in sensornode endpoint";
    }
    // here has some path variable id
    // @PathVariable for mark it as path variable
    // @ApiPathParam for add documentation
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Get specific sensor details by Id ")
    public SensorNode getSensorById(@ApiPathParam(name = "Id") @PathVariable("id") String id){
        return sensorNodeService.getSensorNodeById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiMethod(description = "Delete sensors by its Id")
    public void removeSensorNodeById(@ApiPathParam(name = "Id") @PathVariable("id") String id){
        sensorNodeService.removeSensorNodeById(id);
    }

    // If request is POST or PUT and it contains request body @RequestBody annotation is need
    @RequestMapping(method = RequestMethod.PUT)
    @ApiMethod(description = "Update Sensor details")
    public void updateSensorById(@RequestBody SensorNode sensorNode){
        sensorNodeService.updateSensorNodeById(sensorNode);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiMethod(description = "Add new sensors to system")
    public void insertSensorById(@RequestBody SensorNode sensorNode){
        sensorNodeService.insertSensorNode(sensorNode);
    }

}
