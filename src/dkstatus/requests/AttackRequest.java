package dkstatus.requests;

import dkstatus.Config;
import dkstatus.Utils;
import dkstatus.WebRequestService;
import dkstatus.browser.BrowserManager;
import dkstatus.sms.SmsSender;
import dkstatus.world.AttackData;
import dkstatus.world.MapPosition;
import dkstatus.world.Unit;
import dkstatus.world.UnitType;
import dkstatus.world.Village;
import dkstatus.world.World;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.rmi.CORBA.Util;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Johny
 */
public class AttackRequest extends AbstractUpdateRequest {

    private final AttackData data;
    
    public AttackRequest(AttackData data) {
        this.data = data;
    }
    
    @Override
    public void updateData(World world) throws IOException {
        String resultHtml = getResult("village=" + data.getAttacker().getId() + "&screen=place");
        
        Logger.getLogger(AttackRequest.class.getName()).log(Level.FINER, resultHtml);
        
        Document doc = Jsoup.parse(resultHtml);
        
        if (!Utils.checkUserLogged(doc, world))
            return;
        
        Element attackHash = doc.select("#units_form input").first();
        String hashKey = attackHash.attr("name");
        String hashValue = attackHash.attr("value");
        
        Map<String, String> pairs = new HashMap<>();
        pairs.put(hashKey, hashValue);
        pairs.put("attack", "Útok");
        pairs.put("template_id", "");
        pairs.put("x", String.valueOf(data.getTarget().getPosition().getPosition().x));
        pairs.put("y", String.valueOf(data.getTarget().getPosition().getPosition().y));
        
        for (UnitType type : UnitType.values())
            pairs.put(type.getShortcut(), "");
        
        for (Unit unit : data.getAttackingUnits())
            pairs.put(unit.getType().getShortcut(), String.valueOf(unit.getInVillage()));
        
        Map<String, String> extraHeaders = new HashMap<>();
        extraHeaders.put("Referer", Utils.getLink("village=" + data.getAttacker().getId() + "&screen=place"));
        
            /*
            archer	0
            attack	Útok
            axe	0
            c4cdb5b7398fb0e3997e41	c396b186c4cdb5
            catapult	0
            heavy	0
            knight	0
            light	30
            marcher	0
            ram	0
            snob	0
            spear	0
            spy	1
            sword	0
            template_id
            x	435
            y	580
            */
        
        try {    
            Thread.sleep(Utils.randSec(2, 3));
        } catch (InterruptedException ex) {
        }
        
        resultHtml = executePost("village=" + data.getAttacker().getId() + "&try=confirm&screen=place", pairs, extraHeaders);
        
        Logger.getLogger(AttackRequest.class.getName()).log(Level.FINER, resultHtml);
        
        doc = Jsoup.parse(resultHtml);
        
        if (!Utils.checkUserLogged(doc, world))
            return;        
        
        Element confirmForm = doc.select("#content_value form").first();
        if (confirmForm == null)
            return;
        
        String link = confirmForm.attr("action").substring(10); // /game.php?village=9845&action=command&h=a8ae&screen=place
        
        pairs.clear();
        for (Element input : confirmForm.select("input"))
            pairs.put(input.attr("name"), input.attr("value"));
        
        extraHeaders.clear();
        extraHeaders.put("Referer", Utils.getLink("village=" + data.getAttacker().getId() + "&try=confirm&screen=place"));

        try {    
            Thread.sleep(Utils.randSec(1, 2));
        } catch (InterruptedException ex) {
        }
        
        executePost(link, pairs, extraHeaders);
        
        Logger.getLogger(AttackRequest.class.getName()).log(Level.FINE, "Executed attack on {0}", data.getTarget());
    }
   
}
