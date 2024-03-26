package utility;

import java.util.HashMap;
import java.util.Map;

import model.repository.storageParams;
import model.repository.storageRepository;
import model.repository.userRepository;

import model.transferObjects.userTO;

/**
 * Utility class to handle the user DB
 */
public class credUtil {
    private Map<String, String> creds = new HashMap<String, String>();

    /**
     * Constructor initializes the DB map from the file
     */
    public credUtil() {
        userRepository userRepo = new userRepository(new storageRepository());
        storageParams userParams = userRepo.retrieve();

        for(userTO uTO : userParams.uTOList) {
            creds.put(uTO.email, uTO.passHash);
        }
    }

    /**
     * Checks the database to find if the mail exists
     * @param mail The email address to check against the database
     * @return  Whether the mail is found in DB
     */
    public boolean isMailExists(String mail) {
        return creds.containsKey(mail);
    }

    /**
     * Checks if the mail and password match the entry in the database
     * @param mail  The mail to check
     * @param pass  The pass associated with the mail
     * @return  The result of matching
     */
    public boolean isMailPassMatch(String mail, String pass) {
        String obtainPass = creds.get(mail);

        return obtainPass.equals(pass);
    }

    /**
     * Method to update the admin database
     */
    private void saveCredsToDB() {
        for(String email : creds.keySet()) {
            String passHash = creds.get(email);

            userRepository userRepo = new userRepository(new storageRepository());
            storageParams userParams = new storageParams("user");
            userTO uTO = new userTO(email, passHash);
            userParams.uTO = uTO;
            userRepo.store(userParams);
        }
    }

    /**
     * Method to reset password
     * @param mail
     * @param pass
     */
    private void replaceInDB(String mail, String pass) {
        userRepository userRepo = new userRepository(new storageRepository());
        userRepo.replaceField(mail, pass);
    }

    /**
     * Adds a new user to the DB
     * @param mail  Mail to add to the DB
     * @param pass  Password for the user to be associated with the mail
     */
    public void addEntry(String mail, String pass) {
        creds.put(mail, pass);
        saveCredsToDB();
    }

    /**
     * Resets the password with a new one for the specified mail
     * @param mail  The mail for which the password will be changed
     * @param pass  The new password
     */
    public void resetPassWord(String mail, String pass) {
        creds.replace(mail, pass);
        replaceInDB(mail, pass);
        
    }
}