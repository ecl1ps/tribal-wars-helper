
package dkstatus.browser;

import org.apache.http.HttpRequest;
import org.apache.http.client.CookieStore;

/**
 *
 * @author Johny
 */
public class BrowserManager {
    
    private static IBrowserDataProvider browserProvider = new FirefoxDataProvider();
    private static CookieStore cookieStore = null;
    
    public static void setProvider(IBrowserDataProvider provider) {
        browserProvider = provider;
    }
    
    public static CookieStore getCookieStore() {
        if (cookieStore == null)
            cookieStore = browserProvider.getBrowserCookies();
        
        return cookieStore;
    }
    
    public static void refreshCookies() {
        cookieStore = browserProvider.getBrowserCookies();
    }    

    public static void setHeaders(HttpRequest request) {
        browserProvider.setHeaders(request);
    }
}
