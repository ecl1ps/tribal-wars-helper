package dkstatus;

import dkstatus.world.ArmyType;
import dkstatus.world.World;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
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
}
