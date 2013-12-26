
package dkstatus.browser;

import dkstatus.Config;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpRequest;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Johny
 */
public class FirefoxDataProvider implements IBrowserDataProvider {
    
    @Override
    public CookieStore getBrowserCookies() {
        
        CookieStore cookieStore = new BasicCookieStore();
        
        String repositoryPath = System.getenv("APPDATA") + "\\Mozilla\\Firefox\\Profiles\\";
        byte[] encoded = null;
        
        try {
            File profilesDir = new File(repositoryPath);
            String[] files = profilesDir.list();
            if (files.length <= 0)
                return cookieStore;
            
            repositoryPath += files[0];
            repositoryPath += "\\sessionstore.js";

            encoded = Files.readAllBytes(Paths.get(repositoryPath));
        } catch (IOException ex) {
            Logger.getLogger(FirefoxDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        String content = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(encoded)).toString();
        
        JSONObject jsonObj = new JSONObject(content);
        JSONArray cookiesJSON = jsonObj.getJSONArray("windows").getJSONObject(0).getJSONArray("cookies");
        for (int i = 0; i < cookiesJSON.length(); i++)
        {
            JSONObject cookieJSON = cookiesJSON.getJSONObject(i);
            String host = cookieJSON.getString("host");
            
            if (host.matches(".*(" + Config.ROOT_DOMAIN + ").*")) {  
                  
                String name = cookieJSON.getString("name");
                String value = cookieJSON.getString("value");

                /*if (!name.equals("sid") && !name.equals("cid") && !name.equals("user") && !name.equals("password"))
                    continue;*/

                Logger.getLogger(FirefoxDataProvider.class.getName()).log(Level.FINER, "Cookie: " + host + " Name: " + name + " Value: " + value);

                BasicClientCookie cookie = new BasicClientCookie(name, value);
                cookie.setDomain(host);
                cookie.setPath(cookieJSON.getString("path"));

                cookieStore.addCookie(cookie);                
            }
        }

        return cookieStore;
    }

    @Override
    public void setHeaders(HttpRequest request) {
        request.setHeader(HTTP.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
        request.addHeader(HTTP.CONN_DIRECTIVE, "keep-alive");
        request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        request.addHeader("Accept-Language", "cs-CZ,cs;q=0.8,en-US,en;q=0.2");
        request.addHeader("Accept-Encoding", "gzip, deflate");
        request.addHeader("Referer", String.format("http://www.%s/", Config.ROOT_DOMAIN));
        request.addHeader("Cache-Control", "max-age=0");
    }
}
