package com.fyp.wsn.DataAccess;

import com.fyp.wsn.Entity.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Asela on 4/30/2017.
 * This is a Repository that contains inbuilt data access fictionalises. Always extends from MongoRepository and pass
 * suitable data entity class and primary key type of that entity
 *
 * @Repository
 * public interface {name} extends MongoRepository<{Entity type}, {Entity ID type}> {
 * }
 *
 */

@Repository
public interface SensorRepository extends MongoRepository<Sensor, String> {

}
