package model.transferObjects;

/**
 * Transfer object for students
 */
public class studentTO {
    public int roll;
    public String coll;
    public String cate;

    public studentTO(int roll, String coll, String cate) {
        this.roll = roll;
        this.coll = coll;
        this.cate = cate;
    }
}
