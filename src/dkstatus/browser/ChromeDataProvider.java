
package dkstatus.browser;

import dkstatus.Config;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.protocol.HTTP;
import org.apache.http.HttpRequest;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

/**
 *
 * @author Johny
 */
public class ChromeDataProvider implements IBrowserDataProvider {

    @Override
    public CookieStore getBrowserCookies() {
        
        CookieStore cookieStore = new BasicCookieStore();

        String repositoryPath = System.getenv("LOCALAPPDATA") + "\\Google\\Chrome\\User Data\\Default\\cookies";
        try {
            Files.copy(Paths.get(repositoryPath), Paths.get(repositoryPath + ".copy"), REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(ChromeDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {  
            Class.forName("org.sqlite.JDBC");  
            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + repositoryPath + ".copy")) {
                try (Statement statement = connection.createStatement()) {
                    try (ResultSet resultSet = statement.executeQuery("SELECT * FROM cookies WHERE host_key LIKE '%" + Config.ROOT_DOMAIN + "%'")) {

                        while (resultSet.next()) {  
                            String host = resultSet.getString("host_key");  
                            String name = resultSet.getString("name");
                            String value = resultSet.getString("value");

                            /*if (!name.equals("sid") && !name.equals("cid") && !name.equals("user") && !name.equals("password"))
                                continue;*/

                            Logger.getLogger(ChromeDataProvider.class.getName()).log(Level.FINER, "Cookie: " + host + " Name: " + name + " Value: " + value);

                            BasicClientCookie cookie = new BasicClientCookie(name, value);
                            cookie.setDomain(host);
                            cookie.setPath(resultSet.getString("path"));

                            cookieStore.addCookie(cookie);                
                        }
                    }
                }
            }
        } catch(ClassNotFoundException | SQLException e) {
            Logger.getLogger(ChromeDataProvider.class.toString()).log(Level.SEVERE, "error loading cookies", e);
        } 
        
        return cookieStore;
    }

    @Override
    public void setHeaders(HttpRequest request) {
        request.setHeader(HTTP.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
        request.addHeader(HTTP.CONN_DIRECTIVE, "keep-alive");
        request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        request.addHeader("Accept-Language", "cs-CZ,cs;q=0.8,en-US,en;q=0.2");
        request.addHeader("Accept-Encoding", "gzip, deflate");
        request.addHeader("Referer", String.format("http://www.%s/", Config.ROOT_DOMAIN));
        request.addHeader("Cache-Control", "max-age=0");
    }
    
}
