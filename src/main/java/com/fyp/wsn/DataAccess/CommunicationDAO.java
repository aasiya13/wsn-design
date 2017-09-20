package com.fyp.wsn.DataAccess;

import com.fyp.wsn.Entity.Communication;
import com.fyp.wsn.Entity.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by noahn on 7/18/2017.
 */
@Component
public class CommunicationDAO {

    @Autowired
    private CommunicationRepository communicationRepository;


    public Collection<Communication> getAllCommunications() {
        return communicationRepository.findAll();
    }

    public Communication getCommunicationById(String id) {
        return communicationRepository.findOne(id);
    }

    public void insertSensor(Communication communication) {
        communicationRepository.save(communication);
    }

    public Communication getCommunicationByName(String Name){
        Collection<Communication> temp_list=getAllCommunications();

        for(Communication x : temp_list){

            if(x.getModel_name().equals(Name)) return  x;

        }

        return null;
    }

    public void removeCommunicationById(String id) {
        communicationRepository.delete(communicationRepository.findOne(id));
    }


    public void updateCommunicationById(Communication com) {
        Communication temp_com = communicationRepository.findOne(com.getId());

        temp_com.setDisplay_name(com.getDisplay_name());
        temp_com.setModel_name(com.getModel_name());
        temp_com.setConfiguration(com.getConfiguration());
        temp_com.setDescription(com.getDescription());

        temp_com.setCpp_connect(com.getCpp_connect());
        temp_com.setCpp_send(com.getCpp_send());
        temp_com.setCpp_receive(com.getCpp_receive());
        temp_com.setCpp_setup(com.getCpp_setup());
        temp_com.setCpp_global(com.getCpp_global());
        temp_com.setCpp_disconnect(com.getCpp_disconnect());
        temp_com.setPin_map(com.getPin_map());

        communicationRepository.save(temp_com);

    }

}
