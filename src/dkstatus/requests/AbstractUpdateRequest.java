
package dkstatus.requests;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 *
 * @author Johny
 */
public abstract class AbstractUpdateRequest implements IUpdateRequest {
    
    protected String getResult(String requestData) throws IOException {
        
        try (CloseableHttpClient httpclient = NetUtils.createClient()) {
            
            HttpGet request = NetUtils.prepareGetRequest(requestData);

            Logger.getLogger(this.getClass().getName()).log(Level.FINE, "Executing request: {0}", request.getURI());
            
            return httpclient.execute(request, new BasicResponseHandler());
        }
    }
}
