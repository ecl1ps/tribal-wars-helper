package dkstatus.requests;

import dkstatus.Config;
import dkstatus.world.World;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Johny
 */
public class BasicDataRequest {
    
    public static String getResult() throws IOException {
        
        try (CloseableHttpClient httpclient = NetUtils.createClient()) {
            
            HttpGet request = NetUtils.prepareGetRequest("game.php?village=" + Config.VILLAGE_ID + "&screen=report");

            Logger.getLogger(BasicDataRequest.class.getName()).log(Level.INFO, "Executing request: {0}", request.getURI());
            
            // vyhazuje HttpResponseException pri chybe requestu
            return httpclient.execute(request, new BasicResponseHandler());
        }
    }

    public static void updateData(World world) throws IOException {
        Document doc = Jsoup.parse(getResult());
        
        Element headerInfo = doc.select("#header_info tr").first();
        
        world.getResources().Wood = Integer.parseInt(headerInfo.select("#wood").first().text());
        world.getResources().Stone = Integer.parseInt(headerInfo.select("#stone").first().text());
        world.getResources().Iron = Integer.parseInt(headerInfo.select("#iron").first().text());
        world.getResources().Storage = Integer.parseInt(headerInfo.select("#storage").first().text());
        
        world.getPopulation().Current = Integer.parseInt(headerInfo.select("#pop_current_label").first().text());
        world.getPopulation().Max = Integer.parseInt(headerInfo.select("#pop_max_label").first().text());
        
        Element menu = doc.select("#menu_row").first();
        
        world.getPlayer().HasAnnounce = !menu.select("#new_report").first().hasAttr("style");
        world.getPlayer().HasMessage = !menu.select("#new_mail").first().hasAttr("style");
        Element forumMessage = menu.select("#tribe_forum_indicator").first(); // players without tribe don't have this element
        world.getPlayer().HasForumMessage = forumMessage != null && !forumMessage.hasAttr("style");
        
        world.getPlayer().VillageName = headerInfo.select("#menu_row2_village a").first().text();
        String pos = headerInfo.select("#menu_row2 td b").first().text();
        world.getPlayer().VillagePosX = Integer.parseInt(pos.substring(1, 4));
        world.getPlayer().VillagePosY = Integer.parseInt(pos.substring(5, 8));
        
        world.getPlayer().IncomingAttacks = Integer.parseInt(headerInfo.select("#incomings_amount").first().text());
        String supports = headerInfo.select("#supports_amount").first().text();
        world.getPlayer().IncomingSupports = supports.isEmpty() ? 0 : Integer.parseInt(supports);
    }
}
