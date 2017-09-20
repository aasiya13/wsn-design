package com.fyp.wsn.Controller;

import com.fyp.wsn.Entity.Communication;
import com.fyp.wsn.Services.CommuncationService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by noahn on 7/19/2017.
 */
@RestController
// This is how you map your end points to entire class
@RequestMapping("/communication")
@CrossOrigin()
//This annotation for auto generate API documentation
@Api(
        name = "communication data API",
        description = "This API provides set of methords that can use to manipulate communication data"
)
public class CommuncationController {

    // connecting with sensor service class(where business logic is implemented)
    @Autowired
    private CommuncationService communcationService;

    //Defining the request method (this time it is GET)
    @RequestMapping(method = RequestMethod.GET)
    //For API documentation
    @ApiMethod(description = "Get all available sensors in System ")
    public Collection<Communication> getAllCommunications(){
        return communcationService.getAllCommunications();
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
    public Communication getCommunicationById(@ApiPathParam(name = "Id") @PathVariable("id") String id){
        return communcationService.getCommunicationById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiMethod(description = "Delete sensors by its Id")
    public void removeCommunicationById(@ApiPathParam(name = "Id") @PathVariable("id") String id){
        communcationService.removeCommunicationById(id);
    }

    // If request is POST or PUT and it contains request body @RequestBody annotation is need
    @RequestMapping(method = RequestMethod.PUT)
    @ApiMethod(description = "Update Sensor details")
    public void updateCommunicationById(@RequestBody Communication com){
        communcationService.updateCommunicationById(com);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiMethod(description = "Add new sensors to system")
    public void insertCommunicationById(@RequestBody Communication com){
        communcationService.insertCommunication(com);
    }
}
