package dkstatus;

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
}
