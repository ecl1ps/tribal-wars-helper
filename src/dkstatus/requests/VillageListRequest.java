package dkstatus.requests;

import dkstatus.Utils;
import dkstatus.WebRequestService;
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
        String resultHtml = getResult("screen=overview_villages");
        
        Logger.getLogger(VillageListRequest.class.getName()).log(Level.FINER, resultHtml);
        
        Document doc = Jsoup.parse(resultHtml);
        
        if (!Utils.checkUserLogged(doc, world))
            return;
        
        world.getPlayer().getVillages().clear();
        
        Elements villageRows= doc.select("#production_table tr");
        
        for (Element row : villageRows) {
            if (!row.hasAttr("class"))
                continue;
            
            Village v = new Village();
            String idLabel = row.select("span").first().attr("id"); //label_id
            v.setId(Integer.parseInt(idLabel.substring(6)));

            world.getPlayer().addVillage(v);
        }
        
        WebRequestService.scheduleTask(new BasicDataRequest(), 0);
    }
}
