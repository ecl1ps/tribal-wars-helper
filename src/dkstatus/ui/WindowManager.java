
package dkstatus.ui;

import java.awt.EventQueue;

/**
 *
 * @author Johny
 */
public class WindowManager {
    private static volatile MainWindow window;
    private static boolean initializationStarted = false;
    
    public static void initialize() {
        
        initializationStarted = true;
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                window = new MainWindow();
                window.setVisible(true);
            }
        });
    }

    public static MainWindow getWindow() {
        if (!initializationStarted)
            initialize();
        
        while (window == null) ; // bussy wait till window is created
        
        return window;
    }

    public static boolean isInitialized() {
        return window != null;
    }
}
