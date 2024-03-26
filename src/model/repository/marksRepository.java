package model.repository;

/**
 * concrete implementation of baseRepository for marks
 */
public class marksRepository extends baseRepository {
    /**
     * constructor to initialize storage repository of parent class
     * @param sr
     */
    public marksRepository(storageRepository sr) {
        super(sr);
    }
    
    @Override
    public void store(storageParams params) {
        sr.store(params);
    }

    @Override
    public storageParams retrieve() {
        return sr.retrieve("marks");
    }

    @Override
    public storageParams retrieveOne(String id) {
        return sr.retrieveOne("marks", id);
    }
}
