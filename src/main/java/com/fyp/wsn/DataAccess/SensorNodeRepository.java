package com.fyp.wsn.DataAccess;

import com.fyp.wsn.Entity.SensorNode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nadith Premaratne on 11/05/2017.
 */

@Repository
public interface SensorNodeRepository extends MongoRepository<SensorNode, String> {
}
