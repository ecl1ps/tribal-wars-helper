package dkstatus;

import dkstatus.world.ArmyType;
import dkstatus.world.World;
import java.awt.Point;
import org.joda.time.DateTime;
import org.jsoup.nodes.Document;

/**
 *
 * @author Johny
 */
public class Utils {
    public static String getRootLink() {
        return String.format("http://%s%s.%s/", Config.LANG, Config.WORLD, Config.ROOT_DOMAIN);
    }

    public static String getLink(String page) {
        return Utils.getRootLink() + "game.php?" + page;
    }
        
    public static boolean isUserLogged(Document doc) {
        return !doc.select(".top_bar").isEmpty();
    }
    
    public static boolean checkUserLogged(Document doc, World world) {
        if (!Utils.isUserLogged(doc)) {
            world.getPlayer().setIsLoggedIn(false);
            return false;
        }

        world.getPlayer().setIsLoggedIn(true);
        return true;
    }
    
    public static ArmyType calculateAttackType(Point from, Point to, DateTime start, DateTime end) {
        for (ArmyType  a : ArmyType.values()) {
           if (start.plus(a.getTimeToTravell(from, to)).minus(Config.UPDATE_MS + Config.UPDATE_JITTER).isBefore(end))
               return a;
        }
        return ArmyType.INVALID;
    }    
}
