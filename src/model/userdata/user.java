package model.userdata;

/**
 * Generalized user class for listing
 */
public class user {
    /**
     * Display name for the JList
     */
    private String displayName;

    public user(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Method to determine if user is an admin
     * @return admin status boolean
     */
    public boolean isAdmin() {
        return false;
    }

    public int getRoll() {
        return 1;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
