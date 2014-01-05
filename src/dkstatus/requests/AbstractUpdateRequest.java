
package dkstatus.requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Johny
 */
public abstract class AbstractUpdateRequest implements IUpdateRequest {
    
    protected String getResult(String link) throws IOException {
        
        try (CloseableHttpClient httpclient = NetUtils.createClient()) {
            
            HttpGet request = NetUtils.prepareGetRequest(link);

            Logger.getLogger(this.getClass().getName()).log(Level.FINE, "Executing GET request: {0}", request.getURI());
            
            return httpclient.execute(request, new BasicResponseHandler());
        }
    }
    
    protected String executePost(String link, Map<String, String> pairs) throws IOException {
        return executePost(link, pairs, null);
    } 
    
    protected String executePost(String link, Map<String, String> pairs, Map<String, String> extraHeaders) throws IOException {
        
        try (CloseableHttpClient httpclient = NetUtils.createClient()) {
            
            List<NameValuePair> formparams = new ArrayList<>();
            for (Map.Entry<String, String> pair : pairs.entrySet())
                formparams.add(new BasicNameValuePair(pair.getKey(), pair.getValue()));
            
            HttpPost httppost = NetUtils.preparePostRequest(link);
            if (extraHeaders != null)
                for (Map.Entry<String, String> pair : extraHeaders.entrySet())
                    httppost.addHeader(pair.getKey(), pair.getValue()); 
            
            httppost.setEntity(new UrlEncodedFormEntity(formparams, Consts.UTF_8));
            
            Logger.getLogger(this.getClass().getName()).log(Level.FINE, "Executing POST request: {0}", httppost.getURI());
            
            return httpclient.execute(httppost, new BasicResponseHandlerWith302());
        }
    }     
}
