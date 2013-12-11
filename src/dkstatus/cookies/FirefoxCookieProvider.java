
package dkstatus.cookies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

/**
 *
 * @author Johny
 */
public class FirefoxCookieProvider implements ICookieProvider {

    @Override
    public CookieStore getBrowserCookies() {
        
        CookieStore cookieStore = new BasicCookieStore();

        String repositoryPath = System.getenv("APPDATA") + "\\Mozilla\\Firefox\\Profiles\\2f9swmun.default\\cookies.sqlite";
        try {  
            Class.forName("org.sqlite.JDBC");  
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + repositoryPath);  
            Statement statement = connection.createStatement();  
            ResultSet resultSet = statement.executeQuery("SELECT * FROM moz_cookies");  

            while (resultSet.next()) {  
                String key = resultSet.getString("baseDomain");  
                ResultSetMetaData meta = resultSet.getMetaData();
                System.out.println(key +" Id: " + resultSet.getString("id") +" Name: " + resultSet.getString("name"));
                
                BasicClientCookie cookie = new BasicClientCookie(resultSet.getString("name"), resultSet.getString("value"));
                //cookie.setVersion(0);
                cookie.setDomain(resultSet.getString("baseDomain"));
                cookie.setPath(resultSet.getString("path"));
                cookie.setExpiryDate(resultSet.getDate("expiry"));
                cookie.setSecure(resultSet.getBoolean("isSecure"));
                
                cookieStore.addCookie(cookie);                
            }  
        } catch(ClassNotFoundException | SQLException e) {
            Logger.getLogger(FirefoxCookieProvider.class.toString()).log(Level.SEVERE, "error loading cookies", e);
        } 


        
        return cookieStore;
    }
    
}
