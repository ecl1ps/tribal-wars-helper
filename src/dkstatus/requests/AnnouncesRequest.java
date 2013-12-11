package dkstatus.requests;

import dkstatus.Utils;
import dkstatus.cookies.CookieStoreManager;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Johny
 */
public class AnnouncesRequest {
    
    public static String getResult() throws IOException {
        
        RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build();

        try (CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCookieStore(CookieStoreManager.getStore())
                .setDefaultRequestConfig(defaultRequestConfig)
                .build()) {
            
            HttpGet request = new HttpGet(Utils.getRootLink() + "game.php?village=28262&screen=report");
            request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");

            System.out.println("executing request " + request.getURI());
            try (CloseableHttpResponse response = httpclient.execute(request)) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }
        }
    }
}
