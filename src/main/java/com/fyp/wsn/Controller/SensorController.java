package com.fyp.wsn.Controller;

import com.fyp.wsn.Entity.Sensor;
import com.fyp.wsn.Services.SensorService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

import java.util.Collection;

/**
 * Created by Asela on 5/3/2017.
 */

/*
* This is the controller of routing. Using this kind of class all the routing in Application will be handled.
* This class defines what are the end points that front-end can access and what type of requests can be enter to those
* end points.
* */

/*
* This @RestController annotation defines that this is a RESTFull controller( CRUD operations possible )
* */
@RestController
// This is how you map your end points to entire class
@RequestMapping("/sensors")
@CrossOrigin()
//This annotation for auto generate API documentation
@Api(
        name = "Sensor data API",
        description = "This API provides set of methords that can use to manipulate sensor data"
)
public class SensorController {

    // connecting with sensor service class(where business logic is implemented)
    @Autowired
    private SensorService sensorService;
    private Logger LOGGER = Logger.getLogger(this.getClass().getName());
    //Defining the request method (this time it is GET)
  
    @RequestMapping(method = RequestMethod.GET)
    //For API documentation
    @ApiMethod(description = "Get all available sensors in System ")
    public Collection<Sensor> getAllSensors(){
    	LOGGER.info("Return all the sensors in the database");
    	return sensorService.getAllSensors();
    }


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiMethod(description = "Get specific sensor details by Id ")
    public String SayHello(){
        return "Hello All Good";
    }
    // here has some path variable id
    // @PathVariable for mark it as path variable
    // @ApiPathParam for add documentation
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Get specific sensor details by Id ")
    public Sensor getSensorById(@ApiPathParam(name = "Id") @PathVariable("id") String id){
        return sensorService.getSensorById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiMethod(description = "Delete sensors by its Id")
    public void removeSensorById(@ApiPathParam(name = "Id") @PathVariable("id") String id){
        sensorService.removeSensorById(id);
    }

    // If request is POST or PUT and it contains request body @RequestBody annotation is need
    @RequestMapping(method = RequestMethod.PUT)
    @ApiMethod(description = "Update Sensor details")
    public void updateSensorById(@RequestBody Sensor sensor){
        sensorService.updateSensorById(sensor);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiMethod(description = "Add new sensors to system")
    public void insertSensorById(@RequestBody Sensor sensor){
    	LOGGER.info("Add new sensors to system");
        sensorService.insertSensor(sensor);
    }
}

