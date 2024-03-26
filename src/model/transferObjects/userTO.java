package model.transferObjects;

/**
 * Transfer object for users(Admin)
 */
public class userTO {
    public String email;
    public String passHash;

    public userTO(String email, String passHash) {
        this.email = email;
        this.passHash = passHash;
    }
}
