package com.fyp.wsn.DataAccess;

import com.fyp.wsn.Entity.Communication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by noahn on 7/18/2017.
 */
@Repository
public interface CommunicationRepository extends MongoRepository<Communication, String> {
}
