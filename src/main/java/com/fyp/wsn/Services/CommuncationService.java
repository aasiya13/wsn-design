package com.fyp.wsn.Services;

import com.fyp.wsn.DataAccess.CommunicationDAO;
import com.fyp.wsn.Entity.Communication;
import com.fyp.wsn.Entity.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by noahn on 7/19/2017.
 */
@Service
public class CommuncationService {

    @Autowired
    private CommunicationDAO communicationDAO;

    public Collection<Communication> getAllCommunications(){

//        insertfunction();
        return this.communicationDAO.getAllCommunications();
    }

    public Communication getCommunicationById(String id){

        return this.communicationDAO.getCommunicationById(id);

    }

    public void removeCommunicationById(String id) {

        this.communicationDAO.removeCommunicationById(id);
    }

    public void updateCommunicationById(Communication communication){

        this.communicationDAO.updateCommunicationById(communication);
    }

    public void insertCommunication(Communication communication) {

        this.communicationDAO.insertSensor(communication);
    }
}
