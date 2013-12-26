
package dkstatus.requests;

import dkstatus.Utils;
import dkstatus.browser.BrowserManager;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author Johny
 */
public class NetUtils {
    
    public static CloseableHttpClient createClient() {
        return HttpClients.custom()
            .setDefaultCookieStore(BrowserManager.getCookieStore())
            .setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build())
            .build();
    }
    
    public static HttpGet prepareGetRequest(String requestParams) {
        HttpGet request = new HttpGet(Utils.getLink(requestParams));
        BrowserManager.setHeaders(request);
        return request;
    }    
}
