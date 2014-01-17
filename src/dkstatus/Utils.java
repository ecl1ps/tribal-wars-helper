package dkstatus;

import dkstatus.ui.WindowManager;
import dkstatus.world.ArmyType;
import dkstatus.world.MapPosition;
import dkstatus.world.World;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.jsoup.nodes.Document;

/**
 *
 * @author Johny
 */
public class Utils {
    public static String getRootLink() {
        return String.format("http://%s%s.%s/", Config.LANG, Config.WORLD, Config.ROOT_DOMAIN);
    }

    public static String getGameLink(String page) {
        return Utils.getRootLink() + "game.php?" + page;
    }
    
    public static String getMapLink(String page) {
        return Utils.getRootLink() + "map.php?" + page;
    }
    
    public static boolean isUserLogged(Document doc) {
        return !doc.select(".top_bar").isEmpty();
    }
    
    public static boolean checkUserLogged(Document doc, World world) {
        if (!Utils.isUserLogged(doc)) {
            world.reset();
            WindowManager.getWindow().updateWindow(world);
            return false;
        }

        world.getPlayer().setIsLoggedIn(true);
        return true;
    }
    
    public static ArmyType calculateAttackType(MapPosition from, MapPosition to, DateTime start, DateTime end) {
        for (ArmyType  a : ArmyType.values()) {
            Period p = a.getTimeToTravell(from, to);
            Logger.getLogger(Utils.class.getName()).log(Level.INFO, "army: {0} period: {1}", new Object[]{a, p});
            DateTime temp = start.plus(p);
            Logger.getLogger(Utils.class.getName()).log(Level.INFO, "start: {0} plus period: {1} end: {2}", new Object[]{start, temp, end});
            if (temp.isBefore(end))
               return a;
        }
        
        return ArmyType.INVALID;
    }    
    
    public static float SAMPLE_RATE = 8000f;

    public static void tone(int hz, int msecs) {
        tone(hz, msecs, 1.0);
    }

    public static void tone(int hz, int msecs, double vol) {
        byte[] buf = new byte[1];
        AudioFormat af
                = new AudioFormat(
                        SAMPLE_RATE, // sampleRate
                        8, // sampleSizeInBits
                        1, // channels
                        true, // signed
                        false);      // bigEndian
        try (SourceDataLine sdl = AudioSystem.getSourceDataLine(af)) {
            sdl.open(af);
            sdl.start();
            for (int i = 0; i < msecs * 8; i++) {
                double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
                buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
                sdl.write(buf, 0, 1);
            }
            sdl.drain();
            sdl.stop();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.WARNING, null, ex);
        }
    }   
    
    public static String clearNumber(String number) {
        return number.replaceAll("\\.", "");
    }
    
    public static int rand(int min, int max) {
        return min + WebRequestService.getRandomGenerator().nextInt(max - min);
    }
    
    /**
     * returns value between min and max multiplied by 1000<br>
     * eg. randSec(10, 20) might be 14568 (miliseconds)
     * @param minSec
     * @param maxSec
     * @return 
     */
    public static int randSec(int minSec, int maxSec) {
        return minSec * 1000 + WebRequestService.getRandomGenerator().nextInt((maxSec - minSec) * 1000);
    }    
}
