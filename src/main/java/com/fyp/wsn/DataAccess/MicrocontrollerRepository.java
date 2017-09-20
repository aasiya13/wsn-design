package com.fyp.wsn.DataAccess;

import com.fyp.wsn.Entity.Microcontroller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nadith Premaratne on 03/05/2017.
 */

@Repository
public interface MicrocontrollerRepository extends MongoRepository<Microcontroller, String> {
}
