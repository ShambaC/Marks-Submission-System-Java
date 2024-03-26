package model.repository;

/**
 * Concrete implementation of base repository for user table
 */
public class userRepository extends baseRepository {
    /**
     * constructor to initialize storage repository of parent class
     * @param sr
     */
    public userRepository(storageRepository sr) {
        super(sr);
    }

    @Override
    public void store(storageParams params) {
        sr.store(params);
    }

    @Override
    public storageParams retrieve() {
        return sr.retrieve("user");
    }

    @Override
    public storageParams retrieveOne(String id) {
        return sr.retrieveOne("user", id);
    }

    /**
     * Method to replace value of a column specified by filter
     * @param filter used to filter rows
     * @param value column value
     */
    public void replaceField(String filter, String value) {
        sr.replaceField(filter, value);
    }
}
