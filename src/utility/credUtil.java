package utility;
import java.io.File;
import config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.HashMap;
import java.util.Map;

// Utility class to handle the user DB
public class credUtil {
    private Map<String, String> creds = new HashMap<String, String>();

    // Constructor initializes the DB map from the file
    public credUtil() {
        File f = new File("C:/Users/DELL/Desktop/Creds.db");

        if(f.exists() && !f.isDirectory()) {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
                creds = (HashMap) in.readObject();
                in.close();
            }
            catch(IOException | ClassNotFoundException err) {
                err.printStackTrace();
            }
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

    private void saveCredsToFile() {
        try {
            FileOutputStream fout = new FileOutputStream(config.credentialsFileLocation);
            ObjectOutputStream out = new ObjectOutputStream(fout);

            out.writeObject(creds);
            out.flush();
            out.close();
        }
        catch(IOException err) {
            err.printStackTrace();
        }
    }

    /**
     * Adds a new user to the DB
     * @param mail  Mail to add to the DB
     * @param pass  Password for the user to be associated with the mail
     */
    public void addEntry(String mail, String pass) {
        creds.put(mail, pass);
        saveCredsToFile();
    }

    /**
     * Resets the password with a new one for the specified mail
     * @param mail  The mail for which the password will be changed
     * @param pass  The new password
     */
    public void resetPassWord(String mail, String pass) {
        creds.replace(mail, pass);
        saveCredsToFile();
    }
}