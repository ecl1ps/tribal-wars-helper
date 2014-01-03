
package dkstatus.requests;

import dkstatus.Config;
import dkstatus.Utils;
import dkstatus.WebRequestService;
import dkstatus.ui.UpdateType;
import dkstatus.ui.WindowManager;
import dkstatus.world.MapPosition;
import dkstatus.world.MarchingArmy;
import dkstatus.world.Player;
import dkstatus.world.Unit;
import dkstatus.world.UnitType;
import dkstatus.world.Village;
import dkstatus.world.World;
import java.awt.Color;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Johny
 */
public class MapRequest extends AbstractUpdateRequest {

    private final Village v;
    
    public MapRequest(Village village) {
        v = village;
    }

    @Override
    public void updateData(World world) throws IOException {
        String resultHtml = getResult("village=" + v.getId() + "&screen=map#" + v.getPosition().getPosition().x + ";" + v.getPosition().getPosition().y);

        Logger.getLogger(BasicDataRequest.class.getName()).log(Level.FINER, resultHtml);

        Document doc = Jsoup.parse(resultHtml);

        if (!Utils.checkUserLogged(doc, world))
            return;


        Element script = doc.select("#content_value script").first();
        if (script == null)
            return;

        String content = script.html();
        String[] lines = content.split("[\\r\\n]+");
        String jsonData = null;
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("TWMap.sectorPrefech")) {
                jsonData = line.substring(22);
                break;
            }
        }

        if (jsonData == null)
            return;
            
        JSONArray data = new JSONArray(jsonData);
        for (int i = 0; i < data.length(); ++i) {
            JSONObject continent = data.getJSONObject(i).getJSONObject("data");
            int baseX = continent.getInt("x");
            int baseY = continent.getInt("y");
            
            //JSONObject tribes = continent.getJSONObject("allies");
            /*
                kazda polozka je:
                "646": [ 
                    "KILL!",
                    "4.018",
                    "KILL!"
                ],
            
                "id kmene": [
                    "cele jmeno",
                    "pocet bodu",
                    "zkratka"
                ],            
            */
            //JSONObject players = continent.getJSONObject("players");
            /*
                "3638127": [
                    "KabIcZ",
                    "1.135",
                    "1587",
                    "zítra v 16:45 hodin"
                ],
                "id hrace": [
                    "jmeno",
                    "pocet bodu",
                    "id kmene",
                    "konec ochrany" nebo 0
                ],* 
             */
            JSONObject villages = continent.getJSONObject("villages");
            /*
                "19": {
                    "10": [
                        "35761",
                        9,
                        "Broxigar",
                        "1.440",
                        "3663101",
                        "100"
                    ],
                 }
                "x souradnice (plus baseX)": {
                    "y souradnice (plus baseY)": [
                        "id vesnice",
                        9 nezname, nejaka konstanta,
                        "jmeno vesnice" nebo 0,
                        "pocet bodu vesnice",
                        "id hrace nebo 0 (barbar)",
                        "nezname, asi procenta, casto 25 nebo 100"
            
                        nekdy
                        ,
                        [
                            "33%  rychlejší produkce ve Stáji",
                            "bonus/stable.png"
                        ]
                    ],
                 }            
             */
            Iterator<String> it1 = villages.keys();
            while (it1.hasNext()) {
                String key = it1.next();
                int x = Integer.parseInt(key);
                JSONObject villagesX = villages.getJSONObject(key);
                Iterator<String> it2 = villagesX.keys();
                while (it2.hasNext()) {
                    String key2 = it2.next();
                    int y = Integer.parseInt(key2);
                    JSONArray village = villagesX.getJSONArray(key2);
                    
                    int id = village.getInt(0);
                    Object n = village.get(2);
                    String name;
                    if (n instanceof Integer)
                        name = "Barbarská vesnice";
                    else
                        name = (String)n;
                    Player owner = new Player();
                    owner.setId(village.getInt(4));
                    Village v = new Village(id, name, owner, new MapPosition(x + baseX, y + baseY));
                    String points = Utils.clearNumber(village.getString(3));
                    if (points.equals(""))
                        v.setPoints(0);
                    else
                        v.setPoints(Integer.parseInt(points));
                    world.addVillage(v);
                    
                    //System.out.println(v.toString());
                }                
            }
        }
        
        WebRequestService.scheduleTask(new MapRequest(v), calculateDelay());
    }

    public static int calculateDelay() {
        return 1000 * 60 * 60 * 2 + WebRequestService.getRandomGenerator().nextInt(1000 * 60 * 60); // 2 - 3 hours
    }   
}
