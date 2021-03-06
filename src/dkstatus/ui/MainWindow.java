package dkstatus.ui;

import com.jgoodies.looks.windows.WindowsLookAndFeel;
import dkstatus.Config;
import dkstatus.DKStatus;
import dkstatus.browser.BrowserManager;
import dkstatus.browser.ChromeDataProvider;
import dkstatus.browser.FirefoxDataProvider;
import dkstatus.world.Player;
import dkstatus.world.Village;
import dkstatus.world.World;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.joda.time.DateTime;

/**
 *
 * @author Johny
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setIconImage(new ImageIcon(getClass().getResource("/resources/images/favicon.png")).getImage());
        
        initComponents();
        
        String browser = Config.getStringProperty(Config.ConfigKey.BROWSER_CLASS);
        if (browser.equals(ChromeDataProvider.class.getName())) {
            rbmiChrome.setSelected(true);
            rbmiFirefox.setSelected(false);
        }
        
        UIUtils.transformToHyperlink(lblAnnounce, "screen=report");
        UIUtils.transformToHyperlink(lblMessage, "screen=mail");
        UIUtils.transformToHyperlink(lblForum, "screen=ally&mode=forum");
        UIUtils.transformToHyperlink(lblPlayerName, "screen=info_player");
        UIUtils.transformToHyperlink(lblPointCount, "screen=ranking&mode=player");
        
        pPlayer.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpVillages = new javax.swing.JTabbedPane();
        pPlayer = new javax.swing.JPanel();
        lblPlayer = new javax.swing.JLabel();
        lblPlayerName = new javax.swing.JLabel();
        lblPointCount = new javax.swing.JLabel();
        lblAnnounce = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();
        lblForum = new javax.swing.JLabel();
        lblIncomingAttackCount = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        lblNextUpdateIn = new javax.swing.JLabel();
        btnResetAnnounces = new javax.swing.JButton();
        mbMenu = new javax.swing.JMenuBar();
        mSettings = new javax.swing.JMenu();
        mBrowser = new javax.swing.JMenu();
        rbmiChrome = new javax.swing.JRadioButtonMenuItem();
        rbmiFirefox = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DK Pomocník");

        lblPlayer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPlayer.setText("Hráč:");

        lblPointCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/wheel.png"))); // NOI18N
        lblPointCount.setText("0");
        lblPointCount.setToolTipText("Body hráče");

        lblAnnounce.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/announce.png"))); // NOI18N
        lblAnnounce.setText("Oznámení");

        lblMessage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/message.png"))); // NOI18N
        lblMessage.setText("Zprávy");

        lblForum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/forum_active.png"))); // NOI18N
        lblForum.setText("Kmen");

        lblIncomingAttackCount.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblIncomingAttackCount.setForeground(java.awt.Color.red);
        lblIncomingAttackCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/attacked.png"))); // NOI18N
        lblIncomingAttackCount.setText("0");
        lblIncomingAttackCount.setToolTipText("Útočící vojska");

        btnUpdate.setText("Aktualizovat");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        lblNextUpdateIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/time.png"))); // NOI18N
        lblNextUpdateIn.setText("0");
        lblNextUpdateIn.setToolTipText("Příští aktualizace");

        btnResetAnnounces.setText("Ukončit poplach");
        btnResetAnnounces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetAnnouncesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pPlayerLayout = new javax.swing.GroupLayout(pPlayer);
        pPlayer.setLayout(pPlayerLayout);
        pPlayerLayout.setHorizontalGroup(
            pPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPlayer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPlayerName)
                .addGap(64, 64, 64)
                .addComponent(lblPointCount)
                .addGap(18, 18, 18)
                .addComponent(lblAnnounce)
                .addGap(18, 18, 18)
                .addComponent(lblMessage)
                .addGap(18, 18, 18)
                .addComponent(lblForum)
                .addGap(18, 18, 18)
                .addComponent(lblIncomingAttackCount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                .addComponent(lblNextUpdateIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnResetAnnounces, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pPlayerLayout.setVerticalGroup(
            pPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlayer)
                    .addComponent(lblPlayerName)
                    .addComponent(lblPointCount)
                    .addComponent(lblAnnounce)
                    .addComponent(lblMessage)
                    .addComponent(lblForum)
                    .addComponent(lblIncomingAttackCount)
                    .addComponent(btnUpdate)
                    .addComponent(lblNextUpdateIn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(btnResetAnnounces))
        );

        mSettings.setText("Nastavení");
        mbMenu.add(mSettings);

        mBrowser.setText("Prohlížeč");

        rbmiChrome.setText("Chrome");
        rbmiChrome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSourceBrowserToChrome(evt);
            }
        });
        mBrowser.add(rbmiChrome);

        rbmiFirefox.setSelected(true);
        rbmiFirefox.setText("Firefox");
        rbmiFirefox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSourceBrowserToFirefox(evt);
            }
        });
        mBrowser.add(rbmiFirefox);

        mbMenu.add(mBrowser);

        setJMenuBar(mbMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tpVillages)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tpVillages, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changeSourceBrowserToChrome(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSourceBrowserToChrome
        Config.setProperty(Config.ConfigKey.BROWSER_CLASS, ChromeDataProvider.class.getName());
        BrowserManager.setProvider(new ChromeDataProvider());
        DKStatus.refreshUpdate();
        rbmiFirefox.setSelected(false);
    }//GEN-LAST:event_changeSourceBrowserToChrome

    private void changeSourceBrowserToFirefox(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSourceBrowserToFirefox
        Config.setProperty(Config.ConfigKey.BROWSER_CLASS, FirefoxDataProvider.class.getName());
        BrowserManager.setProvider(new FirefoxDataProvider());
        DKStatus.refreshUpdate();
        rbmiChrome.setSelected(false);
    }//GEN-LAST:event_changeSourceBrowserToFirefox

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        DKStatus.refreshUpdate();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnResetAnnouncesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetAnnouncesActionPerformed
        DKStatus.resetAlert();
    }//GEN-LAST:event_btnResetAnnouncesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResetAnnounces;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel lblAnnounce;
    private javax.swing.JLabel lblForum;
    private javax.swing.JLabel lblIncomingAttackCount;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblNextUpdateIn;
    private javax.swing.JLabel lblPlayer;
    private javax.swing.JLabel lblPlayerName;
    private javax.swing.JLabel lblPointCount;
    private javax.swing.JMenu mBrowser;
    private javax.swing.JMenu mSettings;
    private javax.swing.JMenuBar mbMenu;
    private javax.swing.JPanel pPlayer;
    private javax.swing.JRadioButtonMenuItem rbmiChrome;
    private javax.swing.JRadioButtonMenuItem rbmiFirefox;
    private javax.swing.JTabbedPane tpVillages;
    // End of variables declaration//GEN-END:variables

    public void updateWindow(final World world) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Player plr = world.getPlayer();

                if (world.getPlayer().isLoggedIn()) {
                    lblPlayerName.setText(plr.getName());
                    lblPlayerName.setForeground(Color.black);
                } else {
                    lblPlayerName.setText("Není přihlášen");
                    lblPlayerName.setForeground(Color.red);
                }

                lblPointCount.setText(String.valueOf(plr.getPoints()));
                lblAnnounce.setVisible(plr.hasAnnounce());
                lblMessage.setVisible(plr.hasMessage());
                
                lblForum.setVisible(plr.hasForumMessage());

                pPlayer.setVisible(true);

                lblNextUpdateIn.setText(String.format("%s (%ds)", world.getNexUpdateTime().toString("HH:mm:ss"),
                        world.getNexUpdateTime().minus(new DateTime().getMillis()).getSecondOfMinute()));

                int incomingAttacks = plr.getIncomingAttackCount();
                lblIncomingAttackCount.setText(String.valueOf(incomingAttacks));
                lblIncomingAttackCount.setVisible(incomingAttacks > 0);

                updateVillages(plr.getVillages(), world);
            }
        });
    }

    private void updateVillages(List<Village> villages, World world) {
        List<Integer> newIds = new ArrayList<>();
        for (Village v : villages) // get ids of all current villages
            newIds.add(v.getId());

        for (int i = 0; i < tpVillages.getTabCount(); i++) {
            VillageTabsPanel vp = (VillageTabsPanel) tpVillages.getComponentAt(i);
            if (!newIds.contains(vp.getId())) { // remove tabs with non-existing villages
                tpVillages.remove(vp);
                i--;
            } else {
                newIds.remove(vp.getId()); // otherwise set id as not new
            }
        }

        for (Village v : villages) {
            VillageTabsPanel vp = null;
            if (newIds.contains(v.getId())) {
                vp = new VillageTabsPanel(v);
                if (Config.getBoolProperty(Config.ConfigKey.VILLAGE_NAME_WITH_COORDS))
                    tpVillages.add(v.toStringSimple(), vp);
                else
                    tpVillages.add(v.getName(), vp);                
            } else {
                for (int i = 0; i < tpVillages.getTabCount(); i++) {
                    VillageTabsPanel tmp = (VillageTabsPanel) tpVillages.getComponentAt(i);
                    if (tmp.getId() == v.getId()) {
                        vp = tmp;
                        if (Config.getBoolProperty(Config.ConfigKey.VILLAGE_NAME_WITH_COORDS))
                            tpVillages.setTitleAt(i, v.toStringSimple());
                        else
                            tpVillages.setTitleAt(i, v.getName());
                        break;
                    }
                }
            }

            if (vp != null) {
                vp.getVillagePanel().updateVillage(v, UpdateType.VILLAGE_COMMON);
                vp.getRaidpPanel().updateWorld(world);
            }
        }
    }

    public void updateVillagePanel(final Village v, final UpdateType type) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {        
                for (int i = 0; i < tpVillages.getTabCount(); i++) {
                    VillageTabsPanel vp = (VillageTabsPanel) tpVillages.getComponentAt(i);
                    if (vp.getId() != v.getId())
                        continue;

                    vp.getVillagePanel().updateVillage(v, type);
                    break;
                }
            }
        });
    }
    
    public void updateRaidHelpers(final World world) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {        
                for (int i = 0; i < tpVillages.getTabCount(); i++) {
                    VillageTabsPanel vp = (VillageTabsPanel) tpVillages.getComponentAt(i);
                    vp.getRaidpPanel().updateVillages(world.getVillages());
                    vp.getMapPanel().updateVillages(world.getVillages());
                }
            }
        });
    }    
}
