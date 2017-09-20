package com.fyp.wsn.Controller;

import com.fyp.wsn.DataAccess.SensorDAO;
import com.fyp.wsn.Entity.SensorNetwork;
import com.fyp.wsn.Entity.SensorNode;
import com.fyp.wsn.Services.SensorNetworkService;
import com.fyp.wsn.Services.SensorNodeService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Nadith Premaratne on 23/05/2017.
 */

@RestController
// This is how you map your end points to entire class
@RequestMapping("/sensornetwork")
@CrossOrigin()
//This annotation for auto generate API documentation
@Api(
        name = "sensornetwork data API",
        description = "This API provides set of methods that can use to manipulate sensornetwork data"
)



public class CreationOfNetworkController {
    // connecting with sensor service class(where business logic is implemented)
    @Autowired
    private SensorNetworkService sensorNetworkService;

    //Defining the request method (this time it is GET)
    @RequestMapping(method = RequestMethod.GET)
    //For API documentation
    @ApiMethod(description = "Get all available sensors in System ")
    public Collection<SensorNetwork> getAllSensorNode(){
        return sensorNetworkService.getAllSensorNetwork();
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
    public SensorNetwork getSensorById(@ApiPathParam(name = "Id") @PathVariable("id") String id){
        return sensorNetworkService.getSensorNetworkById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiMethod(description = "Delete sensors by its Id")
    public void removeSensorNodeById(@ApiPathParam(name = "Id") @PathVariable("id") String id){
        sensorNetworkService.removeSensorNetworkById(id);
    }

    // If request is POST or PUT and it contains request body @RequestBody annotation is need
    @RequestMapping(method = RequestMethod.PUT)
    @ApiMethod(description = "Update Sensor details")
    public void updateSensorById(@RequestBody SensorNetwork sensorNetwork){
        sensorNetworkService.updateSensorNodeById(sensorNetwork);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiMethod(description = "Add new sensors to system")
    public void insertSensorById(@RequestBody SensorNetwork sensorNetwork){
        sensorNetworkService.insertSensorNetwork(sensorNetwork);
    }

}
