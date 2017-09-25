package com.fyp.wsn.DataAccess;

import com.fyp.wsn.Entity.Microcontroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Nadith Premaratne on 03/05/2017.
 */

@Component
public class MicrocontrollerDAO {

    @Autowired
    private MicrocontrollerRepository microcontrollerRepository;


    public Collection<Microcontroller> getAllMicrocontrollers() {
        return microcontrollerRepository.findAll();
    }

    public Microcontroller getMicrocontrollerById(String id) {
        return microcontrollerRepository.findOne(id);
    }

    public void insertMicrocontroller(Microcontroller microcontroller) {
        microcontrollerRepository.save(microcontroller);
    }

    public Microcontroller getMicrocontrollerByName(String Name){
        Collection<Microcontroller> temp_list=getAllMicrocontrollers();

        for(Microcontroller x : temp_list){

            if(x.getModel_name().equals(Name)) return  x;

        }

        return null;
    }

    public void removeMicrocontrollerById(String id) {
        microcontrollerRepository.delete(microcontrollerRepository.findOne(id));
    }

    public void updateMicrocontrollerById(Microcontroller microcontroller) {
        Microcontroller temp_microcontroller = microcontrollerRepository.findOne(microcontroller.getId());
        temp_microcontroller.setDisplay_name(microcontroller.getDisplay_name());
        temp_microcontroller.setModel_name(microcontroller.getModel_name());
        temp_microcontroller.setConfiguration(microcontroller.getConfiguration());
        microcontrollerRepository.save(temp_microcontroller);

    }
}
