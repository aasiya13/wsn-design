package com.fyp.wsn.Controller;

import com.fyp.wsn.Entity.ClientSession;
import com.fyp.wsn.Entity.Sensor;
import com.fyp.wsn.Entity.Validation;
import com.fyp.wsn.Services.SensorService;
import com.fyp.wsn.Services.ValidationService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Nadith Premaratne on 03/05/2017.
 */

@RestController
// This is how you map your end points to entire class
@RequestMapping("/validation")
//This annotation for auto generate API documentation
@Api(
        name = "Validation API",
        description = "This API provides set of methords that can use to validate what user is designed can be done"
)
public class ValidationController {

    // connecting with sensor service class(where business logic is implemented)
    @Autowired
    private ValidationService validationService;


    @RequestMapping(method = RequestMethod.GET)
    //For API documentation
    @ApiMethod(description = "Get all available sensors in System ")
    public Collection<ClientSession> getAllSensors(){
        return validationService.getAllSessions();
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiMethod(description = "Get specific sensor details by Id ")
    public String SayHello(){
        return "Hello All Good";
    }


    @RequestMapping(method = RequestMethod.POST)
    @ApiMethod(description = "Validation is done")
    public Validation getValidation(@RequestBody Validation validation){
        return validationService.getValidation(validation);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiMethod(description = "Get specific sensor details by Id ")
    public ClientSession getSensorById(@ApiPathParam(name = "Id") @PathVariable("id") int id){
        return validationService.getSessionById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiMethod(description = "Delete sensors by its Id")
    public void removeSensorById(@ApiPathParam(name = "Id") @PathVariable("id") int id){
        validationService.removeSessionById(id);
    }


}
