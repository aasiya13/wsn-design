package com.fyp.wsn.DataAccess;

import com.fyp.wsn.Entity.AllFunctions;
import com.fyp.wsn.Entity.ClientSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nadith Premaratne on 11/05/2017.
 */
@Repository
public interface AllFunctionRepository extends MongoRepository<AllFunctions, String> {
}
