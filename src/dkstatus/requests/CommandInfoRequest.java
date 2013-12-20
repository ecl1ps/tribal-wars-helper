package dkstatus.requests;

import dkstatus.Utils;
import dkstatus.world.IncomingAttack;
import dkstatus.world.Village;
import dkstatus.world.World;
import java.awt.Point;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Johny
 */
public class CommandInfoRequest implements IUpdateRequest {
    private int commandId;
    private int villageId;

    public CommandInfoRequest(int commandId, int villageId) {
        this.commandId = commandId;
        this.villageId = villageId;
    }
    
    private String getResult() throws IOException {
        
        try (CloseableHttpClient httpclient = NetUtils.createClient()) {
            
            HttpGet request = NetUtils.prepareGetRequest("id=" + commandId + "&type=other&screen=info_command");

            Logger.getLogger(CommandInfoRequest.class.getName()).log(Level.FINE, "Executing request: {0}", request.getURI());
            
            return httpclient.execute(request, new BasicResponseHandler());
        }
    }

    @Override
    public void updateData(World world) throws IOException {
        String resultHtml = getResult();
        
        Logger.getLogger(CommandInfoRequest.class.getName()).log(Level.FINER, resultHtml);
        
        Document doc = Jsoup.parse(resultHtml);
        
        if (!Utils.checkUserLogged(doc, world))
            return;
        
        Elements commandRows = doc.select("#content_value tr");
        
        String attackerName = commandRows.get(1).select("a").first().text();
        
        Element attackingVillage = commandRows.get(2).select("span").first();
        int attackerId = Integer.parseInt(attackingVillage.attr("data-player"));
        int attackingVillageId = Integer.parseInt(attackingVillage.attr("data-id"));
        
        String attacker = attackingVillage.text();
        int len = attacker.length();
        String attackerVillageName = attacker.substring(0, len - 14);
        Point attackerPos = new Point(
                Integer.parseInt(attacker.substring(len - 12, len - 9)), 
                Integer.parseInt(attacker.substring(len - 8, len - 5)));
        String attackerContinent = attacker.substring(len - 3, len);
        
        String arrivalTime = commandRows.get(5).select("td").get(1).text();
        
        DateTimeFormatter format = DateTimeFormat.forPattern("dd-MM-yy HH:mm:ss:SSS"); //19.12.13 13:34:29:397
        DateTime arrival = format.parseDateTime(arrivalTime);

        Village v = world.getPlayer().getVillage(villageId);
        v.getIncomingAttacks().add(
                new IncomingAttack(commandId, v, attackerId, attackerName, attackingVillageId, 
                        attackerVillageName, attackerPos, attackerContinent, arrival));
    }
}
