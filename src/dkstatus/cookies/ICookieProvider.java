
package dkstatus.cookies;

import org.apache.http.client.CookieStore;

/**
 *
 * @author Johny
 */
public interface ICookieProvider {
    
    /**
     * gets following cookies retrieved from browser: sid, cid, user, password
     * @return CookieStore
     */
    public CookieStore getBrowserCookies();
}
