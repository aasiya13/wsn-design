package com.fyp.wsn.DataAccess;

import com.fyp.wsn.Entity.SensorNetwork;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nadith Premaratne on 24/05/2017.
 */
@Repository
public interface SensorNetworkRepository extends MongoRepository<SensorNetwork, String> {
}
