package utility;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;

import java.util.HashMap;
import java.util.Map;

/**
 * An Utility class to load env files into java projects
 */

public class envUtil {
    /**
     * The map storing the key value pairs of the env file
     */
    private Map<String, String> envHashMap = new HashMap<String, String>();

    /**
     * Initializes an envUtil object to access data from an .env file.
     * <p>Use the {@link envUtil#get(String) get} method to get data from the file
     * 
     * @param envFilePath   path to the env file
     */
    public envUtil(String envFilePath) {
        File envFile = new File(envFilePath);
        try {
            String fileContent = new String(Files.readAllBytes(envFile.toPath()));

            String lines[] = fileContent.split("\\r?\\n");
            for(int i = 0; i < lines.length; i++) {
                if(lines[i].charAt(0) == '#' || lines[i].length() == 0) {
                    continue;
                }

                envHashMap.put(lines[i].split("=")[0], lines[i].split("=")[1]);
            }
        }
        catch(IOException | IndexOutOfBoundsException err) {
            err.printStackTrace();
        }        
    }

    /**
     * Returns the value associated with a key from the env file
     * @param key Key to get the associated value of.
     * @return Value associated with the key.
     */
    public String get(String key) {
        return envHashMap.get(key);
    }
}
