
package dkstatus.browser;

import org.apache.http.HttpRequest;
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
    
    public void setHeaders(HttpRequest request);
}
