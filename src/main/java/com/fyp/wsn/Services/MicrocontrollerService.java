package com.fyp.wsn.Services;

import com.fyp.wsn.DataAccess.MicrocontrollerDAO;
import com.fyp.wsn.DataAccess.SensorDAO;
import com.fyp.wsn.Entity.Microcontroller;
import com.fyp.wsn.Entity.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Nadith Premaratne on 04/05/2017.
 */

@Service
public class MicrocontrollerService {

    @Autowired
    private MicrocontrollerDAO microcontrollerDAO;


    public Collection<Microcontroller> getAllMicrocontrollers(){
        return this.microcontrollerDAO.getAllMicrocontrollers();
    }

    public Microcontroller getMicrocontrollerById(String id){

        return this.microcontrollerDAO.getMicrocontrollerById(id);
    }

    public void removeMicrocontrollerById(String id) {

        this.microcontrollerDAO.removeMicrocontrollerById(id);
    }

    public void updateMicrocontrollerById(Microcontroller microcontroller){

        this.microcontrollerDAO.updateMicrocontrollerById(microcontroller);
    }

    public void insertMicrocontroller(Microcontroller microcontroller) {

        this.microcontrollerDAO.insertMicrocontroller(microcontroller);
    }
}
