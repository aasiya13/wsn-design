package com.fyp.wsn.Controller;

import com.fyp.wsn.Entity.Microcontroller;
import com.fyp.wsn.Entity.Sensor;
import com.fyp.wsn.Services.MicrocontrollerService;
import com.fyp.wsn.Services.SensorService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Nadith Premaratne on 04/05/2017.
 */

@RestController
// This is how you map your end points to entire class
@RequestMapping("/microcontroller")
@CrossOrigin()
//This annotation for auto generate API documentation
@Api(
        name = "microcontroller data API",
        description = "This API provides set of methords that can use to manipulate microcontroller data"
)
public class MicrocontrollerController {

    @Autowired
    private MicrocontrollerService microcontrollerService;

    //Defining the request method (this time it is GET)
    @RequestMapping(method = RequestMethod.GET)
    //For API documentation
    @ApiMethod(description = "Get all available sensors in System ")
    public Collection<Microcontroller> getAllMicrocontrollers(){
        return microcontrollerService.getAllMicrocontrollers();
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
    public Microcontroller getMicrocontrollerById(@ApiPathParam(name = "Id") @PathVariable("id") String id){
        return microcontrollerService.getMicrocontrollerById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiMethod(description = "Delete sensors by its Id")
    public void removeMicrocontrollerById(@ApiPathParam(name = "Id") @PathVariable("id") String id){
        microcontrollerService.removeMicrocontrollerById(id);
    }

    // If request is POST or PUT and it contains request body @RequestBody annotation is need
    @RequestMapping(method = RequestMethod.PUT)
    @ApiMethod(description = "Update Sensor details")
    public void updateMicrocontrollerById(@RequestBody Microcontroller microcontroller){
        microcontrollerService.updateMicrocontrollerById(microcontroller);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiMethod(description = "Add new sensors to system")
    public void insertSensorById(@RequestBody Microcontroller microcontroller){
        microcontrollerService.insertMicrocontroller(microcontroller);
    }
}
