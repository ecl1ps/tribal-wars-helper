
package dkstatus.cookies;

import org.apache.http.client.CookieStore;

/**
 *
 * @author Johny
 */
public interface IBrowserDataProvider {
    
    /**
     * gets following cookies retrieved from browser: sid, cid, user, password
     * @return CookieStore
     */
    public CookieStore getBrowserCookies();
    
    public String getUserAgent();
}
