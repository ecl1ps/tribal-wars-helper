
package dkstatus.cookies;

import dkstatus.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
            Class.forName("org.sqlite.JDBC");  
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + repositoryPath);  
            Statement statement = connection.createStatement();  
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cookies WHERE host_key LIKE '%" + Config.ROOT_DOMAIN + "%'");  

            while (resultSet.next()) {  
                String key = resultSet.getString("host_key");  
                ResultSetMetaData meta = resultSet.getMetaData();
                System.out.println(key +" Id: " + resultSet.getString("name") + "\n");
                
                BasicClientCookie cookie = new BasicClientCookie(resultSet.getString("name"), resultSet.getString("value"));
                //cookie.setVersion(0);
                cookie.setDomain(resultSet.getString("host_key"));
                cookie.setPath(resultSet.getString("path"));
                cookie.setExpiryDate(resultSet.getDate("expires_utc"));
                cookie.setSecure(resultSet.getBoolean("secure"));
                
                cookieStore.addCookie(cookie);                
            }  
        } catch(ClassNotFoundException | SQLException e) {
            Logger.getLogger(ChromeCookieProvider.class.toString()).log(Level.SEVERE, "error loading cookies", e);
        } 


        
        return cookieStore;
    }
    
}
