package dkstatus.requests;

import dkstatus.Utils;
import dkstatus.WebRequestService;
import dkstatus.world.MapPosition;
import dkstatus.world.Village;
import dkstatus.world.World;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Johny
 */
public class VillageListRequest extends AbstractUpdateRequest {
    
    @Override
    public void updateData(World world) throws IOException {
        String resultHtml = getResult("screen=overview_villages&mode=prod");
        
        Logger.getLogger(VillageListRequest.class.getName()).log(Level.FINER, resultHtml);
        
        Document doc = Jsoup.parse(resultHtml);
        
        if (!Utils.checkUserLogged(doc, world))
            return;
        
        world.getPlayer().getVillages().clear();
        
        Elements villageRows = doc.select(".overview_table tr");
        
        int delay = 0;
        for (Element row : villageRows) {
            if (!row.hasAttr("class"))
                continue;
            
            Village v = new Village();
            
            String fullName = row.select("a span").first().text(); //some name (433|577) K54
            
            v.setName(fullName.substring(0, fullName.length() - 14));
            String pos = fullName.substring(fullName.length() - 13);
            v.setPosition(new MapPosition(Integer.parseInt(pos.substring(1, 4)), Integer.parseInt(pos.substring(5, 8)), pos.substring(10, 13)));
            
            String idLabel = row.select("span").first().attr("id"); //label_id
            v.setId(Integer.parseInt(idLabel.substring(6)));
            
            v.setOwner(world.getPlayer());

            world.getPlayer().addVillage(v);
            
            delay += 5000;
            WebRequestService.scheduleTask(new RecruitmentRequest(v), delay);
        }
        
        WebRequestService.scheduleTask(new BasicDataRequest(), 0);
    }
}
