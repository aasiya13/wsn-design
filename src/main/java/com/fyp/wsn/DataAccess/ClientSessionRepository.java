package com.fyp.wsn.DataAccess;

import com.fyp.wsn.Entity.ClientSession;
import com.fyp.wsn.Entity.Microcontroller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nadith Premaratne on 04/05/2017.
 */

@Repository
public interface ClientSessionRepository extends MongoRepository<ClientSession, Integer> {
}
