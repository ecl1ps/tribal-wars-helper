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
import dkstatus.world.Unit;
import dkstatus.world.UnitType;
import dkstatus.world.Village;
import dkstatus.world.World;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormat;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Johny
 */
public class RecruitmentRequest extends AbstractUpdateRequest {
    private final Village v;

    public RecruitmentRequest(Village village) {
        v = village;
    }

    @Override
    public void updateData(World world) throws IOException {
        String resultHtml = executeGet("village=" + v.getId() + "&screen=train");
        
        Logger.getLogger(RecruitmentRequest.class.getName()).log(Level.FINER, resultHtml);
        
        Document doc = Jsoup.parse(resultHtml);
        
        if (!Utils.checkUserLogged(doc, world))
            return;
        
        for (Unit u : v.getUnits())
            u.reset();
        
        Elements buildings = doc.select(".current_prod_wrapper");
        for (Element building : buildings) {
            
            DateTime finish = new DateTime();
            Elements rows = building.select(".trainqueue_wrap tr");
            
            for (Element row : rows) {
                Element typeDiv = row.select("div").first();
                if (typeDiv == null)
                    continue;

                Set<String> classes = typeDiv.classNames();

                Unit unit = getUnitByClass(classes);

                String count = row.select("td").first().text().split(" ")[0];
                unit.setRecruiting(unit.getRecruiting() + Integer.parseInt(count));

                String countdown = row.select("td").get(1).text();

                PeriodFormatter format = new PeriodFormatterBuilder()
                    .appendHours()
                    .appendSeparator(":")
                    .appendMinutes()
                    .appendSeparator(":")
                    .appendSeconds()
                    .toFormatter();

                Period duration;
                try {
                    duration = format.parsePeriod(countdown);
                    finish = finish.plus(duration);
                    unit.setRecruitmentFinishes(finish);
                } catch (IllegalArgumentException e) {
                    Logger.getLogger(RecruitmentRequest.class.getName()).log(Level.WARNING, "Error parsing period {0}", countdown);
                }
            }
        }

        WebRequestService.scheduleTask(new RecruitmentRequest(v), Utils.randSec(60, 180));
        WindowManager.getWindow().updateVillagePanel(v, UpdateType.VILLAGE_UNITS);
    }

    private Unit getUnitByClass(Set<String> classes) {
        for (String clazz : classes) {
            if (clazz.equals("unit_sprite") || clazz.equals("unit_sprite_smaller"))
                continue;
            
            UnitType type = UnitType.calculateUnitType(clazz);
            
            Unit u = v.getUnitByType(type);
            if (u == null) {
                u = new Unit(type);
                v.addUnit(u);
            }

            return u;                
        }
        return null;
    }
}
