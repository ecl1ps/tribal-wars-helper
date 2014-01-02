package dkstatus.requests;

import dkstatus.Config;
import dkstatus.Utils;
import dkstatus.WebRequestService;
import dkstatus.sms.SmsSender;
import dkstatus.ui.UpdateType;
import dkstatus.ui.WindowManager;
import dkstatus.world.CommandType;
import dkstatus.world.MarchingArmy;
import dkstatus.world.MapPosition;
import dkstatus.world.Player;
import dkstatus.world.Village;
import dkstatus.world.World;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CommandInfoRequest extends AbstractUpdateRequest {
    private final int commandId;
    private final int villageId;
    private final boolean incoming;

    public CommandInfoRequest(int commandId, int villageId, boolean incoming) {
        this.commandId = commandId;
        this.villageId = villageId;
        this.incoming = incoming;
    }

    @Override
    public void updateData(World world) throws IOException {
        String resultHtml = getResult("village=" + villageId + "&id=" + commandId + "&type=" + (incoming ? "other" : "own") + "&screen=info_command");
        
        Logger.getLogger(CommandInfoRequest.class.getName()).log(Level.FINER, resultHtml);
        
        Document doc = Jsoup.parse(resultHtml);
        
        if (!Utils.checkUserLogged(doc, world))
            return;
        
        Element commandHeaderEl = doc.select("#content_value h2").first();
        if (commandHeaderEl == null)
            return; // command doesn't exist anymore
                
        String commandHeader = commandHeaderEl.text();

        
        CommandType type = parseCommandType(commandHeader);
        
        Elements commandRows = doc.select("#content_value table").first().select("tr");
        
        Player otherPlayer = new Player();
        Village otherVillage = new Village();
        otherPlayer.addVillage(otherVillage);
        
        otherPlayer.setName(commandRows.get(incoming ? 1 : 3).select("td").get(2).text());
        
        Element otherVillageElem = commandRows.get(incoming ? 2 : 4).select("span").first();
        otherPlayer.setId(Integer.parseInt(otherVillageElem.attr("data-player")));
        otherVillage.setId(Integer.parseInt(otherVillageElem.attr("data-id")));
        
        String otherVillageText = otherVillageElem.text();
        int len = otherVillageText.length();
        otherVillage.setName(otherVillageText.substring(0, len - 14));
        otherVillage.setPosition(new MapPosition(
                Integer.parseInt(otherVillageText.substring(len - 12, len - 9)), 
                Integer.parseInt(otherVillageText.substring(len - 8, len - 5)),
                otherVillageText.substring(len - 3, len)));
        
        DateTimeFormatter format = DateTimeFormat.forPattern("dd.MM.yy HH:mm:ss:SSS"); //19.12.13 13:34:29:397
        
        String arrivalTime = commandRows.get(incoming ? 5 : 6).select("td").get(1).text();
        
        DateTime arrival;
        try {
            arrival = format.parseDateTime(arrivalTime);
        } catch (IllegalArgumentException e) {
            try {
                // there can be extra line when catapult target is specified
                arrivalTime = commandRows.get(incoming ? 6 : 7).select("td").get(1).text();
                arrival = format.parseDateTime(arrivalTime);
            } catch (IllegalArgumentException e2) {
                arrival = new DateTime();
                Logger.getLogger(CommandInfoRequest.class.getName()).log(Level.WARNING, "Couldn't parse datetime of command", e2);
            }
        }

        Village v = world.getPlayer().getVillage(villageId);
        MarchingArmy army;
        if (incoming) {
            army = new MarchingArmy(commandId, type, otherPlayer, otherVillage, world.getPlayer(), v, v.getLastUpdateIn(), arrival);
            if (v.getIncomingArmies().contains(army))
                v.getIncomingArmies().remove(army);
            else if (army.getCommandType() == CommandType.INCOMING_ATTACK) {
                army.setActiveAnnounce(true);
                SmsSender.sendSms(Config.getStringProperty(Config.ConfigKey.USER_PHONE_NUMBER), army.toShortString());
            }
            
            v.getIncomingArmies().add(army);
        } else {
            army = new MarchingArmy(commandId, type, world.getPlayer(), v, otherPlayer, otherVillage, v.getLastUpdateIn(), arrival);
            if (v.getIncomingArmies().contains(army))
                v.getIncomingArmies().remove(army);
            
            v.getOutgoingArmies().add(army);
        }
        
        v.setLastUpdateIn(new DateTime());
        WindowManager.getWindow().updateVillagePanel(v, UpdateType.VILLAGE_COMMON);
    }

    private CommandType parseCommandType(String commandHeader) {
        for (CommandType t : CommandType.values())
            if (t.isIncoming() == incoming && commandHeader.startsWith(t.getParseString()))
                return t;
        return CommandType.UNKNOWN_O;
    }
    
    public static int calculateDelay() {
        return 1000 + WebRequestService.getRandomGenerator().nextInt(3000); // 1 - 4 sec
    }     
}
