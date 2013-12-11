
package dkstatus.cookies;

import org.apache.http.client.CookieStore;

/**
 *
 * @author Johny
 */
public class CookieStoreManager {
    
    private static ICookieProvider cookieProvider = new FirefoxCookieProvider();
    private static CookieStore cookieStore = null;
    
    public static void setProvider(ICookieProvider provider) {
        cookieProvider = provider;
    }
    
    public static CookieStore getStore() {
        if (cookieStore == null)
            cookieStore = cookieProvider.getBrowserCookies();
        
        return cookieStore;
    }
    
    public static void refreshCookies() {
        cookieStore = cookieProvider.getBrowserCookies();
    }    
}
