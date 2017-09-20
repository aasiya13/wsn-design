package com.fyp.wsn.DataAccess;

import com.fyp.wsn.Entity.AllFunctions;
import com.fyp.wsn.Entity.ClientSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Nadith Premaratne on 11/05/2017.
 */

@Component
public class AllFunctionsDAO {


    @Autowired
    private AllFunctionRepository allFunctionRepository;

    public Collection<AllFunctions> getAllFunctions() {
        return allFunctionRepository.findAll();
    }

    public AllFunctions getAllFunctionsById(String id) {
        return allFunctionRepository.findOne(id);
    }

    public void insertAllFunctions(AllFunctions clientSession) {
        allFunctionRepository.save(clientSession);

    }

    public void removeAllFunctionsById(String id) {
        allFunctionRepository.delete(allFunctionRepository.findOne(id));
    }

    public void updateAllFunctionsById(AllFunctions allFunctions) {
        AllFunctions temp_allfunctions = allFunctionRepository.findOne(allFunctions.getId());
        temp_allfunctions.setFunction_map(allFunctions.getFunction_map());
        allFunctionRepository.save(temp_allfunctions);

    }
}
