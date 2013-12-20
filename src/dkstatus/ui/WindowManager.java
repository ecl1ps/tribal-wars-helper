
package dkstatus.ui;

import java.awt.EventQueue;

/**
 *
 * @author Johny
 */
public class WindowManager {
    private static volatile MainWindow window;
    
    public static void initialize() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                window = new MainWindow();
                window.setVisible(true);
            }
        });
    }

    public static MainWindow getWindow() {
        return window;
    }

    public static boolean isInitialized() {
        return window != null;
    }
}
