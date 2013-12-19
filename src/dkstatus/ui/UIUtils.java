package dkstatus.ui;

import dkstatus.Utils;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Johny
 */
public class UIUtils {

    public static void transformToHyperlink(JLabel element, String url) {
        final String fullUrl = Utils.getLink(url);
        //element.setText("<html><a href=\"" + fullUrl + "\">" + element.getText() + "</a></html>");
        element.setCursor(new Cursor(Cursor.HAND_CURSOR));
        element.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(fullUrl));
                } catch (URISyntaxException | IOException ex) {
                    //It looks like there's a problem
                    Logger.getLogger(UIUtils.class.getName()).log(Level.INFO, null, ex);
                }
            }
        });
    }
}
