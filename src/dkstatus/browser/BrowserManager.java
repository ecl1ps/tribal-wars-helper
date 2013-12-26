
package dkstatus.cookies;

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
    
    public static String getUserAgent() {
        return browserProvider.getUserAgent();
    }
}
