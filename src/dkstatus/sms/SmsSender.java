
package dkstatus.sms;

import dkstatus.browser.BrowserManager;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Johny
 */
public class SmsSender {
    
    public static void sendSms(String number, String text) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            
            text = Normalizer.normalize(text, Normalizer.Form.NFD);
            text = text.replaceAll("[^\\p{ASCII}]", "");

            List<NameValuePair> formparams = new ArrayList<>();
            formparams.add(new BasicNameValuePair("operator", "auto"));
            formparams.add(new BasicNameValuePair("number", number));
            formparams.add(new BasicNameValuePair("message", text));
            formparams.add(new BasicNameValuePair("submit", "Odeslat"));
            
            HttpPost httppost = new HttpPost("http://www.sms-o2.cz/");
            BrowserManager.setHeaders(httppost);
            httppost.setEntity(new UrlEncodedFormEntity(formparams, Consts.UTF_8));
            
            Logger.getLogger(SmsSender.class.getName()).log(Level.FINE, "Sending sms: {0}", text);
            
            httpclient.execute(httppost);
            
        } catch (IOException ex) {
            Logger.getLogger(SmsSender.class.getName()).log(Level.WARNING, "Error sending sms", ex);
        }
    }
     
}
