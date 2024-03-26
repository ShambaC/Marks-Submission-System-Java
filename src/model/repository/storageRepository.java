package model.repository;

import model.dao.marksDAO;
import model.dao.userDAO;

/**
 * Storage repository class that defines various retrieval and storage methods
 */
public class storageRepository {
    
    /**
     * method to store data to db
     * @param params storage info
     */
    public void store(storageParams params) {
        if(params.type.equalsIgnoreCase("marks")) {
            marksDAO mD = new marksDAO();
            mD.storeStudents(params.sTO);
            mD.store(params.mTO);
        }
        else if(params.type.equalsIgnoreCase("user")) {
            userDAO uD = new userDAO();
            uD.store(params.uTO);
        }
    }

    /**
     * method to retrieve data from db
     * @param type type of data to retrieve
     * @return params
     */
    public storageParams retrieve(String type) {
        storageParams params = new storageParams(type);

        if(type.equalsIgnoreCase("marks")) {
            marksDAO mD = new marksDAO();            
            params.mTOList = mD.retrieve();
            params.sTOList = mD.retrieveStudent();
        }
        else if(type.equalsIgnoreCase("user")) {
            userDAO uD = new userDAO();
            params.uTOList = uD.retrieve();
        }

        return params;
    }

    /**
     * method to retrieve a specific row(s) of data from db
     * @param type type of data to retrieve
     * @param id specifier string to filter data
     * @return params
     */
    public storageParams retrieveOne(String type, String id) {
        storageParams params = new storageParams(type);

        if(type.equalsIgnoreCase("marks")) {
            marksDAO mD = new marksDAO();
            params.mTOList = mD.retrieveOne(id);
        }
        else if(type.equalsIgnoreCase("user")) {
            userDAO uD = new userDAO();
            params.uTO = uD.retrieveOne(id);
        }

        return params;
    }

    /**
     * Method to replace value of a column specified by filter
     * @param filter used to filter rows
     * @param value column value
     */
    public void replaceField(String filter, String value) {
        userDAO uD = new userDAO();
        uD.replaceField(filter, value);
    }
}
