
package dkstatus.requests;

import dkstatus.Utils;
import dkstatus.WebRequestService;
import dkstatus.ui.WindowManager;
import dkstatus.world.MapPosition;
import dkstatus.world.Player;
import dkstatus.world.Village;
import dkstatus.world.World;
import java.awt.Point;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Johny
 */
public class MapRequest extends AbstractUpdateRequest {

    private final int MAP_STEP_SIZE = 20;
    
    private final int LOADED_NEIGHBOUR_DISTANCE = 40;
    private final boolean WITH_TILES = false;
    private final boolean WITH_POLITICAL = false;
    

    @Override
    public void updateData(World world) throws IOException {
        if (world.getPlayer().getVillages().isEmpty()) {
            Logger.getLogger(MapRequest.class.getName()).log(Level.INFO, "Player villages are empty -> rescheduling");
            reschedule();
            return;
        }
        
        int xMin = 1000, xMax = 0, yMin = 1000, yMax = 0;
        
        for (Village v : world.getPlayer().getVillages()) {
            Point p = v.getPosition().getPosition();
            if (p.x < xMin)
                xMin = p.x;
            if (p.x > xMax)
                xMax = p.x;   
            if (p.y < yMin)
                yMin = p.y;
            if (p.y > yMax)
                yMax = p.y;             
        }
        
        // convert coords to tiles: 53 / 20 = 2; 2 * 20 = 40
        xMin = (xMin / MAP_STEP_SIZE) * MAP_STEP_SIZE - LOADED_NEIGHBOUR_DISTANCE;
        xMax = (xMax / MAP_STEP_SIZE) * MAP_STEP_SIZE + LOADED_NEIGHBOUR_DISTANCE + MAP_STEP_SIZE;
        yMin = (yMin / MAP_STEP_SIZE) * MAP_STEP_SIZE - LOADED_NEIGHBOUR_DISTANCE;
        yMax = (yMax / MAP_STEP_SIZE) * MAP_STEP_SIZE + LOADED_NEIGHBOUR_DISTANCE + MAP_STEP_SIZE;
        
        if (xMin < 0)
            xMin = 0;
        if (xMax > 980)
            xMax = 980;
        if (yMin < 0)
            yMin = 0;
        if (yMax > 980)
            yMax = 980;        
        
        int type = 0;
        if (WITH_TILES)
            type += 1;
        if (WITH_POLITICAL)
            type += 2;
        
        StringBuilder sb = new StringBuilder("v=2&e=");
        sb.append(DateTime.now().getMillis());
        for (int x = xMin; x <= xMax; x += MAP_STEP_SIZE) {
            for (int y = yMin; y <= yMax; y += MAP_STEP_SIZE) {
               sb.append("&");
               sb.append(x);
               sb.append("_");
               sb.append(y);
               sb.append("=");
               sb.append(type);
            }
        }
        
        // http://cs33.divokekmeny.cz/map.php?v=2&e=1389952055927&460_560=1&460_580=1
        String jsonData = executeGet(Utils.getMapLink(sb.toString()));

        if (jsonData == null || jsonData.isEmpty())
            return;
        
        Logger.getLogger(BasicDataRequest.class.getName()).log(Level.FINER, jsonData);
            
        JSONArray data = new JSONArray(jsonData);
        if (data == null)
            return;
        
        //System.out.println(data.toString(4));
        for (int i = 0; i < data.length(); ++i) {
            JSONObject continent = data.getJSONObject(i).getJSONObject("data");
            //System.out.println(continent.toString(4));
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
            Object villages = continent.get("villages");
            //System.out.println(villages.toString(4));
            /*          
                    var xy = (sx + x) * 1000 + sy + y;
                    TWMap.villageKey[v[0]] = xy;
                    TWMap.villages[xy] = {
                        id: v[0],
                        img: v[1],
                        name: v[2],
                        points: v[3],
                        owner: v[4],
                        mood: v[5],
                        bonus: v[6],
                        xy: xy
                    };            
                {
                    "10": [
                        "35761",
                        9,
                        "Broxigar",
                        "1.440",
                        "3663101",
                        "100"
                    ],
                 }
            
                 "x souradnice (plus baseX)": { nebo { (zalezi jestli jsou data strukturovana jako array nebo mapa)
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
            // there are two possible structures: array and map - lets check them both
            if (villages instanceof JSONArray) {
                for (int x = 0; x < ((JSONArray)villages).length(); ++x)
                    parseVillageData(((JSONArray)villages).getJSONObject(x), x, baseX, baseY, world);                
            } else {
                Iterator<String> it1 = ((JSONObject)villages).keys();
                while (it1.hasNext()) {
                    String key = it1.next();
                    int x = Integer.parseInt(key);
                    parseVillageData(((JSONObject)villages).getJSONObject(key), x, baseX, baseY, world);                
                }
            }
        }
        
        WindowManager.getWindow().updateRaidHelpers(world);
        WebRequestService.scheduleTask(new MapRequest(), Utils.randSec(60 * 60 * 2, 60 * 60 * 3)); // 2 - 3 hours
    }

    private void parseVillageData(JSONObject villagesX, int x, int baseX, int baseY, World world) throws NumberFormatException, JSONException {
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
            Village vil = new Village(id, name, owner, new MapPosition(x + baseX, y + baseY));
            String points = Utils.clearNumber(village.getString(3));
            if (points.equals(""))
                vil.setPoints(0);
            else
                vil.setPoints(Integer.parseInt(points));
            world.addVillage(vil);
        }
    }

    @Override
    public void reschedule() {
        WebRequestService.scheduleTask(new MapRequest(), Utils.randSec(60 * 10, 60 * 15)); // 10 - 15 min
    }
}
