package model.repository;

/**
 * abstract class to generalize repositories
 */
public abstract class baseRepository {
    /**
     * storage repository object to bridge connection
     */
    protected storageRepository sr;

    /**
     * constructor to initialize the bridge
     * @param sr the storageRepository object
     */
    public baseRepository(storageRepository sr) {
        this.sr = sr;
    }

    /**
     * method to store data in db
     * @param params parameters containing data
     */
    public abstract void store(storageParams params);
    /**
     * method to retrieve all data from db
     * @return params
     */
    public abstract storageParams retrieve();
    /**
     * method to filter out and retrieve a particular data
     * @param id specifier to filter
     * @return params
     */
    public abstract storageParams retrieveOne(String id);
}
