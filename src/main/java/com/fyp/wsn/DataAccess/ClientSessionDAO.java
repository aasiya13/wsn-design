package com.fyp.wsn.DataAccess;

import com.fyp.wsn.Entity.ClientSession;
import com.fyp.wsn.Entity.Microcontroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Nadith Premaratne on 04/05/2017.
 */

@Component
public class ClientSessionDAO {


    @Autowired
    private ClientSessionRepository clientSessionRepository;


    public Collection<ClientSession> getAllClientSessions() {
        return clientSessionRepository.findAll();
    }

    public int getNoofSession() {
        return clientSessionRepository.findAll().size();
    }

    public ClientSession getClientSessionById(int id) {
        return clientSessionRepository.findOne(id);
    }

    public void insertClientSession(ClientSession clientSession) {
        clientSessionRepository.save(clientSession);

    }

    public void removeClientSessionById(int id) {
        clientSessionRepository.delete(clientSessionRepository.findOne(id));
    }

    public void updateSessionById(ClientSession clientSession) {
        ClientSession temp_clientsession = clientSessionRepository.findOne(clientSession.getId());
        temp_clientsession.setCurrent_usage_of_pins(clientSession.getCurrent_usage_of_pins());
        clientSessionRepository.save(temp_clientsession);

    }
}
