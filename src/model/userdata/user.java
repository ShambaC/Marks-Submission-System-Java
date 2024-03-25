package model.userdata;

public class user {
    private String displayName;

    public user(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
