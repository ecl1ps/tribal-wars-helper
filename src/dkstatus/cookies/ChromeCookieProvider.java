
package dkstatus.cookies;

import dkstatus.Config;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

/**
 *
 * @author Johny
 */
public class ChromeCookieProvider implements ICookieProvider {

    @Override
    public CookieStore getBrowserCookies() {
        
        CookieStore cookieStore = new BasicCookieStore();

        String repositoryPath = System.getenv("LOCALAPPDATA") + "\\Google\\Chrome\\User Data\\Default\\cookies";
        try {
            Files.copy(Paths.get(repositoryPath), Paths.get(repositoryPath + ".copy"), REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(ChromeCookieProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {  
            Class.forName("org.sqlite.JDBC");  
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + repositoryPath + ".copy");  
            Statement statement = connection.createStatement();  
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cookies WHERE host_key LIKE '%" + Config.ROOT_DOMAIN + "%'");  

            while (resultSet.next()) {  
                String host = resultSet.getString("host_key");  
                String name = resultSet.getString("name");
                String value = resultSet.getString("value");
                
                //System.out.println(host +" Name: " + name + " Value: " + value);
                
                /*if (!name.equals("sid") && !name.equals("cid") && !name.equals("user") && !name.equals("password"))
                    continue;*/
                
                System.out.println(host +" Name: " + name + " Value: " + value);
                
                BasicClientCookie cookie = new BasicClientCookie(name, value);
                cookie.setDomain(host);
                cookie.setPath(resultSet.getString("path"));
                /*String date = resultSet.getString("expires_utc");
                if (!date.equals("0"))
                    cookie.setExpiryDate(new java.util.Date(Long.parseLong(date)*1000l));*/
                cookie.setSecure(resultSet.getBoolean("secure"));
                
                cookieStore.addCookie(cookie);                
            }  
        } catch(ClassNotFoundException | SQLException e) {
            Logger.getLogger(ChromeCookieProvider.class.toString()).log(Level.SEVERE, "error loading cookies", e);
        } 
        
        return cookieStore;
    }
    
}
