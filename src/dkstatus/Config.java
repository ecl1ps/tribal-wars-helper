package dkstatus;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Johny
 */
public class Config {
    private static final String CONFIG_FILE = "settings.config";
    private static Properties properties;
    
    public static final String LANG = "cs";
    public static final int WORLD = 33;
    public static final String ROOT_DOMAIN = "divokekmeny.cz";
    
    public static final double WORLD_SPEED = 1.6;
    public static final double WORLD_MARCH_SPEED = 0.8;
    
    public static final int UPDATE_MS = 10000;
    public static final int UPDATE_JITTER = 10000;
    
    public enum ConfigKey {
        USER_PHONE_NUMBER,
        
        PROXY_ENABLED,
        PROXY_IP,
        PROXY_PORT
    }
    
    public static void init() {
        // defaults
        properties = new Properties();
        properties.setProperty(ConfigKey.USER_PHONE_NUMBER.name(), "");
        properties.setProperty(ConfigKey.PROXY_ENABLED.name(), "false");
        properties.setProperty(ConfigKey.PROXY_IP.name(), "");
        properties.setProperty(ConfigKey.PROXY_PORT.name(), "8080");
        
        //init with defaults
        properties = new Properties(properties);
        try {
            // load file data
            properties.load(new FileInputStream(CONFIG_FILE));
        } catch (IOException e) {
            Logger.getLogger(Config.class.getName()).log(Level.INFO, "Couldn't load config file - using defaults");
            save();
        }
    }
    
    public static int getIntProperty(ConfigKey key) {
        return Integer.parseInt(properties.getProperty(key.name()));
    }
    
    public static boolean getBoolProperty(ConfigKey key) {
        return Boolean.parseBoolean(properties.getProperty(key.name()));
    }    
  
    public static String getStringProperty(ConfigKey key) {
        return properties.getProperty(key.name());
    }
    
    public static float getFloatProperty(ConfigKey key) {
        return Float.parseFloat(properties.getProperty(key.name()));
    }  
    
    public static void setProperty(ConfigKey key, String val) {
        properties.setProperty(key.name(), val);
        save();
    }   
    
    private static void save() {
        try {
            properties.store(new FileOutputStream(CONFIG_FILE), "");
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
