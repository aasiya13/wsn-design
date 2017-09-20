package com.fyp.wsn.Services;

import com.fyp.wsn.DataAccess.ClientSessionDAO;
import com.fyp.wsn.DataAccess.MicrocontrollerDAO;
import com.fyp.wsn.DataAccess.SensorDAO;
import com.fyp.wsn.Entity.ClientSession;
import com.fyp.wsn.Entity.Microcontroller;
import com.fyp.wsn.Entity.Sensor;
import com.fyp.wsn.Entity.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Nadith Premaratne on 03/05/2017.
 */
@Service
public class ValidationService {

    @Autowired
    private MicrocontrollerDAO microcontrollerDAO;
    @Autowired
    private SensorDAO sensorDAO;
    @Autowired
    private ClientSessionDAO clientSessionDAO;


    final int SUCCESSCODE=200;

    public Collection<ClientSession> getAllSessions(){
        return this.clientSessionDAO.getAllClientSessions();
    }

    public ClientSession getSessionById(int id){

        return this.clientSessionDAO.getClientSessionById(id);
    }

    public void removeSessionById(int id) {

        this.clientSessionDAO.removeClientSessionById(id);
    }

    public void updateSessionById(ClientSession session){

        this.clientSessionDAO.updateSessionById(session);
    }

    public void insertSession(ClientSession session) {

        this.clientSessionDAO.insertClientSession(session);
    }


    public Validation getValidation(Validation validation) {


        String [] temp_devices_ids=validation.getConfig_diagram().split(",");
        String newly_added_device_id=temp_devices_ids[temp_devices_ids.length-1];

        if(validation.getCode()==SUCCESSCODE){
            //handling the new user
            if(validation.getId()==0){
                int new_session_index=this.clientSessionDAO.getNoofSession();
                new_session_index++;

                HashMap<String,Integer> initial_map=new HashMap<String,Integer>();
                Microcontroller temp_micro=this.microcontrollerDAO.getMicrocontrollerById(newly_added_device_id);
                temp_micro.setConfiguration(temp_micro.setConfig(temp_micro.getPin_map()));
                ClientSession new_client=new ClientSession(new_session_index,temp_micro,this.clientSessionDAO);
                validation.setId(new_session_index);
                this.clientSessionDAO.insertClientSession(new_client);

            }
            //handling the currently working user
            else{

                int user_id=validation.getId();
                ClientSession client_design=clientSessionDAO.getClientSessionById(user_id);
                Sensor temp_sensor=this.sensorDAO.getSensorById(newly_added_device_id);
                boolean isvalid= client_design.isvalid(temp_sensor,validation);

                //update the current matrix logic
                if(isvalid==true){
                    client_design.updateCurrentPinConfiguration(temp_sensor,validation,this.clientSessionDAO);
                }

                //return the error of conneccting
                else {

                    return validation;

                }
            }

        }

        else return validation;


        return validation;
    }
}
