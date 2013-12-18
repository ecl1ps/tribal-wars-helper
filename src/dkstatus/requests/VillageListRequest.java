package dkstatus.requests;

import dkstatus.Config;
import dkstatus.Utils;
import dkstatus.world.IncomingAttack;
import dkstatus.world.Village;
import dkstatus.world.World;
import java.awt.Point;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Johny
 */
public class VillageListRequest implements IUpdateRequest {
    
    private String getResult() throws IOException {
        
        try (CloseableHttpClient httpclient = NetUtils.createClient()) {
            
            HttpGet request = NetUtils.prepareGetRequest("game.php?screen=overview_villages");

            Logger.getLogger(VillageListRequest.class.getName()).log(Level.FINE, "Executing request: {0}", request.getURI());
            
            // vyhazuje HttpResponseException pri chybe requestu
            return httpclient.execute(request, new BasicResponseHandler());
        }
    }

    @Override
    public void updateData(World world) throws IOException {
        String resultHtml = getResult();
        
        Logger.getLogger(VillageListRequest.class.getName()).log(Level.FINER, resultHtml);
        
        Document doc = Jsoup.parse(resultHtml);
        
        if (!Utils.isUserLogged(doc)) {
            world.getPlayer().setIsLoggedIn(false);
            return;
        } else {
            world.getPlayer().setIsLoggedIn(true);
        }
        
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

    }
}
