
package dkstatus.requests;

import dkstatus.Config;
import dkstatus.Utils;
import dkstatus.WebRequestService;
import dkstatus.ui.WindowManager;
import dkstatus.world.MarchingArmy;
import dkstatus.world.Player;
import dkstatus.world.Unit;
import dkstatus.world.UnitType;
import dkstatus.world.Village;
import dkstatus.world.World;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Johny
 */
public class VillageDataRequest extends AbstractUpdateRequest {

    private final Village v;
    
    public VillageDataRequest(Village village) {
        v = village;
    }

    @Override
    public void updateData(World world) throws IOException {
        
            v.invalidate();
            
            String resultHtml = getResult("village=" + v.getId() + "&screen=overview");
            Logger.getLogger(BasicDataRequest.class.getName()).log(Level.FINER, resultHtml);
            Document doc = Jsoup.parse(resultHtml);
            
            if (!Utils.checkUserLogged(doc, world))
                return;
            
            parseCommonData(world, doc);
            
            Element headerInfo = doc.select("#header_info tr").first();
            v.getResources().setWood(Integer.parseInt(headerInfo.select("#wood").first().text()));
            v.getResources().setStone(Integer.parseInt(headerInfo.select("#stone").first().text()));
            v.getResources().setIron(Integer.parseInt(headerInfo.select("#iron").first().text()));
            v.getResources().setStorage(Integer.parseInt(headerInfo.select("#storage").first().text()));
            v.getPopulation().setCurrent(Integer.parseInt(headerInfo.select("#pop_current_label").first().text()));
            v.getPopulation().setMax(Integer.parseInt(headerInfo.select("#pop_max_label").first().text()));
            
            parseUnits(doc.select("#show_units td"), v);
            
            parseCommands(doc.select("#show_incoming_units tr"), v, true);
            parseCommands(doc.select("#show_outgoing_units tr"), v, false);
            
            v.cleanup();
            
            if (v.IsAttacked() && v.hasActiveAnnounce())
                Utils.tone(2000, 1000);
            
            WindowManager.getWindow().updateWindow(world); 
    }

    private void parseCommands(Elements rows, Village v, boolean incoming) throws IOException, NumberFormatException {
        int delay = 0;
        for (Element el : rows) {
            Element a = el.select("a").first();
            if (a == null)
                continue;
            
            String link = a.attr("href"); ///game.php?village=9845&amp;id=3124812&amp;type=other&amp;screen=info_command
            Pattern p = Pattern.compile(".*id=(\\d+).*");
            Matcher m = p.matcher(link);
            if (!m.find())
                continue;
            
            int id = Integer.parseInt(m.group(1));
            
            MarchingArmy att = v.getCommandId(id);
            if (att != null && att.getArmyArrives().isAfterNow())
                att.validate();
            else {
                delay += incoming ? 100 : Utils.randSec(1, 4);
                WebRequestService.scheduleTask(new CommandInfoRequest(id, v.getId(), incoming, v.getLastUpdateIn()), delay);
            }
        }
        
        v.setLastUpdateIn(new DateTime());
        Logger.getLogger(VillageDataRequest.class.getName()).log(Level.FINEST, "after creating - setting last updated to now: {0}", v.getLastUpdateIn());
    }   

    private synchronized void parseCommonData(World world, Document doc) {
        if (world.hasCommonDataUpdated())
            return;
        
        world.setCommonDataUpdated(true);
        Player player = world.getPlayer();
        
        Element menu = doc.select("#menu_row").first();
        player.setName(menu.children().get(10).select("tr").first().child(0).text());
        player.setPoints(Integer.parseInt(Utils.clearNumber(menu.select("#rank_points").first().text())));
        player.hasAnnounce(!menu.select("#new_report").first().hasAttr("style"));
        Element forumMessage = menu.select("#tribe_forum_indicator").first(); // players without tribe don't have this element
        player.hasForumMessage(forumMessage != null && !forumMessage.hasClass("no_new_post"));
        
        boolean hasNewMessage = !menu.select("#new_mail").first().hasAttr("style");
        if (hasNewMessage && Config.getBoolProperty(Config.ConfigKey.HAS_MESSAGE_ALERT)) { 
            if (Config.getBoolProperty(Config.ConfigKey.HAS_MESSAGE_ALERT_CONTINUOUS)) {
                if (!player.hasMessageAlertDeactivated())
                    Utils.tone(500,500);
                else if (!player.hasMessage()) {
                    player.setHasMessageAlertDeactivated(false);
                    Utils.tone(500,500);
                }
            } else if (!player.hasMessage()) {
                Utils.tone(500,500);
            }
        }
                            
        player.hasMessage(hasNewMessage);
    }

    private void parseUnits(Elements tds, Village v) {
        for (Unit u : v.getUnits())
            u.setInVillage(0);
        
        for (Element td : tds) {
            Element a = td.select("a").first();
            if (a == null || !a.hasAttr("onclick"))
                continue;
            
            String onClick = a.attr("onclick");
            String unitShortcut = onClick.substring(30, onClick.length() - 2); // return UnitPopup.open(event, 'spear')
            
            UnitType type = UnitType.calculateUnitType(unitShortcut);
            
            Unit u = v.getUnitByType(type);
            if (u == null) {
                u = new Unit(type);
                v.addUnit(u);
            }
            
            if (u.getType() == UnitType.PALADIN)
                u.setInVillage(1);
            else
                u.setInVillage(Integer.parseInt(td.select("strong").first().text()));
        }
    }
}
