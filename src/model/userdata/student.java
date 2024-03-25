package model.userdata;

public class student extends user {
    private int roll;
    private String coll;
    private String cate;

    public student(String roll, String coll, String cate) {
        super(roll.toString() + "-" + coll + "-" + cate);
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
