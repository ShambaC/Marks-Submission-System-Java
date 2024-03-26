package model.repository;

import java.util.ArrayList;
import java.util.List;

import model.transferObjects.marksTO;
import model.transferObjects.studentTO;
import model.transferObjects.userTO;

/**
 * A class to store various parameters for Data Access of variable types
 */
public class storageParams {
    public userTO uTO;
    public marksTO mTO;
    public studentTO sTO;
    public List<userTO> uTOList = new ArrayList<userTO>();
    public List<marksTO> mTOList = new ArrayList<marksTO>();
    public List<studentTO> sTOList = new ArrayList<studentTO>();

    public String type;

    /**
     * Constructor to create a storage params for generalized information transfer
     * <p>
     * The types are:
     * <ul>
     * <li> user </li>
     * <li> student </li>
     * <li> marks </li></ul>
     * <p> Type is passed to the parameter
     * @param type the type of the data transfer
     */
    public storageParams(String type) {
        this.type = type;
    }
}
