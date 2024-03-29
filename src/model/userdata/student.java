package model.userdata;

/**
 * Student user
 */
public class student extends user {
    private int roll;
    private String coll;
    private String cate;

    public student(String roll, String coll, String cate) {
        super(roll + "-" + coll + "-" + cate);

        this.roll = Integer.parseInt(roll);
        this.coll = coll;
        this.cate = cate;
    }

    @Override
    public boolean isAdmin() {
        return false;
    }

    public int getRoll() {
        return roll;
    }

    public String getColl() {
        return coll;
    }

    public String getCate() {
        return cate;
    }
}
