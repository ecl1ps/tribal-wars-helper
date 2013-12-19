package dkstatus.requests;

import dkstatus.Utils;
import dkstatus.world.IncomingAttack;
import dkstatus.world.Village;
import dkstatus.world.World;
import java.awt.Point;
import java.io.IOException;
import java.util.Date;
import java.util.List;
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
public class BasicDataRequest implements IUpdateRequest {
    
    private String getResult(int villageId) throws IOException {
        
        try (CloseableHttpClient httpclient = NetUtils.createClient()) {
            
            HttpGet request = NetUtils.prepareGetRequest("village=" + villageId + "&screen=overview");

            Logger.getLogger(BasicDataRequest.class.getName()).log(Level.FINE, "Executing request: {0}", request.getURI());
            
            // vyhazuje HttpResponseException pri chybe requestu
            return httpclient.execute(request, new BasicResponseHandler());
        }
    }

    @Override
    public void updateData(World world) throws IOException {
        boolean firstReq = true;
        
        for (Village v : world.getPlayer().getVillages()) {
            
            String resultHtml = getResult(v.getId());

            Logger.getLogger(BasicDataRequest.class.getName()).log(Level.FINER, resultHtml);

            Document doc = Jsoup.parse(resultHtml);

            if (firstReq) { // only once - common data
                if (!Utils.isUserLogged(doc)) {
                    world.getPlayer().setIsLoggedIn(false);
                    return;
                } else {
                    world.getPlayer().setIsLoggedIn(true);
                }

                Element menu = doc.select("#menu_row").first();

                world.getPlayer().setName(menu.children().get(10).select("tr").first().child(0).text());

                String points = menu.select("#rank_points").first().text();
                points = points.replaceAll("\\.", "");
                world.getPlayer().setPoints(Integer.parseInt(points));

                world.getPlayer().hasAnnounce(!menu.select("#new_report").first().hasAttr("style"));
                world.getPlayer().hasMessage(!menu.select("#new_mail").first().hasAttr("style"));
                Element forumMessage = menu.select("#tribe_forum_indicator").first(); // players without tribe don't have this element
                world.getPlayer().hasForumMessage(forumMessage != null && !forumMessage.hasAttr("style"));
            }

            Element headerInfo = doc.select("#header_info tr").first();

            v.getResources().setWood(Integer.parseInt(headerInfo.select("#wood").first().text()));
            v.getResources().setStone(Integer.parseInt(headerInfo.select("#stone").first().text()));
            v.getResources().setIron(Integer.parseInt(headerInfo.select("#iron").first().text()));
            v.getResources().setStorage(Integer.parseInt(headerInfo.select("#storage").first().text()));

            v.getPopulation().Current = Integer.parseInt(headerInfo.select("#pop_current_label").first().text());
            v.getPopulation().Max = Integer.parseInt(headerInfo.select("#pop_max_label").first().text());

            v.setName(headerInfo.select("#menu_row2_village a").first().text());
            String pos = headerInfo.select("#menu_row2 td b").first().text();
            v.setPosition(new Point(Integer.parseInt(pos.substring(1, 4)), Integer.parseInt(pos.substring(5, 8))));
            v.setContinent(pos.substring(10, 13));

            int currentAttacks = Integer.parseInt(headerInfo.select("#incomings_amount").first().text());
            
            Element incoming = doc.select("#show_incoming_units").first();
            
            List<IncomingAttack> recordedAttacks = v.getIncomingAttacks();
            if (currentAttacks != recordedAttacks.size()) {
                if (currentAttacks > recordedAttacks.size())
                    for (int i = recordedAttacks.size(); i <= currentAttacks; ++i)
                        recordedAttacks.add(new IncomingAttack("Name", 123, 456, new Date())); //TODO
                else
                    recordedAttacks.subList(0, currentAttacks + 1); // TODO
            }

            /*String supports = headerInfo.select("#supports_amount").first().text();
            v.getIncomingSupports() = supports.isEmpty() ? 0 : Integer.parseInt(supports);*/
        }
    }
}
