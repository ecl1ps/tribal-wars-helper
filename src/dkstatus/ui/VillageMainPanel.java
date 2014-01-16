
package dkstatus.ui;

import dkstatus.Utils;
import dkstatus.world.MarchingArmy;
import dkstatus.world.RecruitmentData;
import dkstatus.world.Unit;
import dkstatus.world.UnitType;
import dkstatus.world.Village;
import java.awt.Color;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Johny
 */
public class VillageMainPanel extends javax.swing.JPanel {

    private final Integer id;
    
    /**
     * Creates new form VillagePanel
     * @param id
     */
    public VillageMainPanel(Integer id) {
        this.id = id;
        
        initComponents();
        hideUnits();        
        
        final int villageId = id;
        lstIncoming.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting())
                    return;
                
                MarchingArmy att = (MarchingArmy)lstIncoming.getSelectedValue();
                if (att == null)
                    return;
                
                try {
                    Desktop.getDesktop().browse(new URI(Utils.getLink("village=" + villageId + "&id=" + att.getCommandId() + "&type=other&screen=info_command")));
                } catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(UIUtils.class.getName()).log(Level.INFO, null, ex);
                }
            }
        }); 
        lstOutgoing.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting())
                    return;
                
                MarchingArmy att = (MarchingArmy)lstOutgoing.getSelectedValue();
                if (att == null)
                    return;
                
                try {
                    Desktop.getDesktop().browse(new URI(Utils.getLink("village=" + villageId + "&id=" + att.getCommandId() + "&type=own&screen=info_command")));
                } catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(UIUtils.class.getName()).log(Level.INFO, null, ex);
                }
            }
        });         
        
        UIUtils.transformToHyperlink(lblWoodCount, "village=" + id + "&screen=wood");
        UIUtils.transformToHyperlink(lblStoneCount, "village=" + id + "&screen=stone");
        UIUtils.transformToHyperlink(lblIronCount, "village=" + id + "&screen=iron");
        UIUtils.transformToHyperlink(lblStorageCount, "village=" + id + "&screen=storage");
        UIUtils.transformToHyperlink(lblPopulation, "village=" + id + "&screen=farm");
        
        UIUtils.transformToHyperlink(lblVillageName, "village=" + id + "&screen=overview");
        
        UIUtils.transformToHyperlink(lblBarracksImage, "village=" + id + "&screen=train");
        UIUtils.transformToHyperlink(lblStableImage, "village=" + id + "&screen=train");
        UIUtils.transformToHyperlink(lblGarageImage, "village=" + id + "&screen=train");
    }

    private void hideUnits() {
        lblArcher.setVisible(false);
        lblAxe.setVisible(false);
        lblCatapult.setVisible(false);
        lblHeavy.setVisible(false);
        lblLight.setVisible(false);
        lblMarcher.setVisible(false);
        lblNoble.setVisible(false);
        lblPaladin.setVisible(false);
        lblRam.setVisible(false);
        lblSpear.setVisible(false);
        lblSpy.setVisible(false);
        lblSword.setVisible(false);
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pHeader = new javax.swing.JPanel();
        pVillageInfo = new javax.swing.JPanel();
        lblVillageName = new javax.swing.JLabel();
        pResources = new javax.swing.JPanel();
        lblWoodCount = new javax.swing.JLabel();
        lblStoneCount = new javax.swing.JLabel();
        lblIronCount = new javax.swing.JLabel();
        lblStorageCount = new javax.swing.JLabel();
        lblPopulation = new javax.swing.JLabel();
        pRecruitment = new javax.swing.JPanel();
        lblBarracksImage = new javax.swing.JLabel();
        lblStableImage = new javax.swing.JLabel();
        lblBarracksCount = new javax.swing.JLabel();
        lblBarracksTime = new javax.swing.JLabel();
        lblStableCount = new javax.swing.JLabel();
        lblStableTime = new javax.swing.JLabel();
        lblGarageImage = new javax.swing.JLabel();
        lblGarageCount = new javax.swing.JLabel();
        lblGarageTime = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        pUnits = new javax.swing.JPanel();
        pUnits1 = new javax.swing.JPanel();
        lblSpear = new javax.swing.JLabel();
        lblSword = new javax.swing.JLabel();
        lblAxe = new javax.swing.JLabel();
        lblArcher = new javax.swing.JLabel();
        lblRam = new javax.swing.JLabel();
        lblCatapult = new javax.swing.JLabel();
        pUnits2 = new javax.swing.JPanel();
        lblSpy = new javax.swing.JLabel();
        lblLight = new javax.swing.JLabel();
        lblMarcher = new javax.swing.JLabel();
        lblHeavy = new javax.swing.JLabel();
        lblPaladin = new javax.swing.JLabel();
        lblNoble = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0));
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5));
        pIncoming = new javax.swing.JPanel();
        spIncoming = new javax.swing.JScrollPane();
        lstIncoming = new javax.swing.JList();
        pOutgoing = new javax.swing.JPanel();
        spOutgoing = new javax.swing.JScrollPane();
        lstOutgoing = new javax.swing.JList();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        pHeader.setLayout(new javax.swing.BoxLayout(pHeader, javax.swing.BoxLayout.X_AXIS));

        pVillageInfo.setPreferredSize(new java.awt.Dimension(370, 90));

        lblVillageName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/village.png"))); // NOI18N
        lblVillageName.setText("Uber Vesnice (111|222) K01");

        pResources.setPreferredSize(new java.awt.Dimension(301, 76));
        pResources.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5));

        lblWoodCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/wood.png"))); // NOI18N
        lblWoodCount.setText("0");
        lblWoodCount.setToolTipText("Dřevo");
        pResources.add(lblWoodCount);

        lblStoneCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/stone.png"))); // NOI18N
        lblStoneCount.setText("0");
        lblStoneCount.setToolTipText("Hlína");
        pResources.add(lblStoneCount);

        lblIronCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/iron.png"))); // NOI18N
        lblIronCount.setText("0");
        lblIronCount.setToolTipText("Železo");
        pResources.add(lblIronCount);

        lblStorageCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/storage.png"))); // NOI18N
        lblStorageCount.setText("0");
        lblStorageCount.setToolTipText("Skladiště");
        pResources.add(lblStorageCount);

        lblPopulation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/worker.png"))); // NOI18N
        lblPopulation.setText("0/0");
        lblPopulation.setToolTipText("Selský dvůr");
        pResources.add(lblPopulation);

        javax.swing.GroupLayout pVillageInfoLayout = new javax.swing.GroupLayout(pVillageInfo);
        pVillageInfo.setLayout(pVillageInfoLayout);
        pVillageInfoLayout.setHorizontalGroup(
            pVillageInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pVillageInfoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblVillageName)
                .addContainerGap(251, Short.MAX_VALUE))
            .addGroup(pVillageInfoLayout.createSequentialGroup()
                .addComponent(pResources, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pVillageInfoLayout.setVerticalGroup(
            pVillageInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pVillageInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVillageName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pResources, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pHeader.add(pVillageInfo);

        lblBarracksImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/buildings/barracks3.png"))); // NOI18N
        lblBarracksImage.setToolTipText("Kasárna");

        lblStableImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/buildings/stable3.png"))); // NOI18N
        lblStableImage.setToolTipText("Stáje");

        lblBarracksCount.setText("+100");

        lblBarracksTime.setText("16:45:00");

        lblStableCount.setText("+23");

        lblStableTime.setText("16:45:00");

        lblGarageImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/buildings/garage3.png"))); // NOI18N
        lblGarageImage.setToolTipText("Dílna");

        lblGarageCount.setText("+5");

        lblGarageTime.setText("16:45:00");

        javax.swing.GroupLayout pRecruitmentLayout = new javax.swing.GroupLayout(pRecruitment);
        pRecruitment.setLayout(pRecruitmentLayout);
        pRecruitmentLayout.setHorizontalGroup(
            pRecruitmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pRecruitmentLayout.createSequentialGroup()
                .addGroup(pRecruitmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pRecruitmentLayout.createSequentialGroup()
                        .addComponent(lblStableImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pRecruitmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStableCount)
                            .addComponent(lblStableTime)))
                    .addGroup(pRecruitmentLayout.createSequentialGroup()
                        .addComponent(lblBarracksImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pRecruitmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBarracksCount)
                            .addComponent(lblBarracksTime)))
                    .addGroup(pRecruitmentLayout.createSequentialGroup()
                        .addComponent(lblGarageImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pRecruitmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGarageCount)
                            .addComponent(lblGarageTime))))
                .addGap(20, 20, 20))
        );
        pRecruitmentLayout.setVerticalGroup(
            pRecruitmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pRecruitmentLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pRecruitmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblBarracksImage)
                    .addGroup(pRecruitmentLayout.createSequentialGroup()
                        .addComponent(lblBarracksCount)
                        .addGap(0, 0, 0)
                        .addComponent(lblBarracksTime)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pRecruitmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pRecruitmentLayout.createSequentialGroup()
                        .addComponent(lblStableCount)
                        .addGap(0, 0, 0)
                        .addComponent(lblStableTime))
                    .addComponent(lblStableImage, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pRecruitmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblGarageImage)
                    .addGroup(pRecruitmentLayout.createSequentialGroup()
                        .addComponent(lblGarageCount)
                        .addGap(0, 0, 0)
                        .addComponent(lblGarageTime)))
                .addGap(5, 5, 5))
        );

        pHeader.add(pRecruitment);
        pHeader.add(filler2);

        pUnits1.setLayout(new javax.swing.BoxLayout(pUnits1, javax.swing.BoxLayout.Y_AXIS));

        lblSpear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_spear.png"))); // NOI18N
        lblSpear.setText("40000 +100");
        lblSpear.setToolTipText("Kopiníci");
        pUnits1.add(lblSpear);

        lblSword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_sword.png"))); // NOI18N
        lblSword.setText("0 +100");
        lblSword.setToolTipText("Šermíři");
        pUnits1.add(lblSword);

        lblAxe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_axe.png"))); // NOI18N
        lblAxe.setText("3000 +32");
        lblAxe.setToolTipText("Sekerníci");
        pUnits1.add(lblAxe);

        lblArcher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_archer.png"))); // NOI18N
        lblArcher.setText("0 +1");
        lblArcher.setToolTipText("Lučištníci");
        pUnits1.add(lblArcher);

        lblRam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_ram.png"))); // NOI18N
        lblRam.setText("3333 +10");
        lblRam.setToolTipText("Beranidla");
        pUnits1.add(lblRam);

        lblCatapult.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_catapult.png"))); // NOI18N
        lblCatapult.setText("3333 +1000");
        lblCatapult.setToolTipText("Katapulty");
        pUnits1.add(lblCatapult);

        pUnits2.setLayout(new javax.swing.BoxLayout(pUnits2, javax.swing.BoxLayout.Y_AXIS));

        lblSpy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_spy.png"))); // NOI18N
        lblSpy.setText("3333 +10");
        lblSpy.setToolTipText("Zvědi");
        pUnits2.add(lblSpy);

        lblLight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_light.png"))); // NOI18N
        lblLight.setText("3333 +100");
        lblLight.setToolTipText("Lehká kavalérie");
        pUnits2.add(lblLight);

        lblMarcher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_marcher.png"))); // NOI18N
        lblMarcher.setText("3333");
        lblMarcher.setToolTipText("Lučištníci na koni");
        pUnits2.add(lblMarcher);

        lblHeavy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_heavy.png"))); // NOI18N
        lblHeavy.setText("3333 +5");
        lblHeavy.setToolTipText("Těžká kavalérie");
        pUnits2.add(lblHeavy);

        lblPaladin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_knight.png"))); // NOI18N
        lblPaladin.setText("1");
        lblPaladin.setToolTipText("Paladin");
        pUnits2.add(lblPaladin);

        lblNoble.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_snob.png"))); // NOI18N
        lblNoble.setText("3 +1");
        lblNoble.setToolTipText("Šlechtic");
        pUnits2.add(lblNoble);

        javax.swing.GroupLayout pUnitsLayout = new javax.swing.GroupLayout(pUnits);
        pUnits.setLayout(pUnitsLayout);
        pUnitsLayout.setHorizontalGroup(
            pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pUnitsLayout.createSequentialGroup()
                .addComponent(pUnits1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(pUnits2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pUnitsLayout.setVerticalGroup(
            pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pUnits1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pUnits2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pHeader.add(pUnits);
        pHeader.add(filler3);

        add(pHeader);
        add(filler1);

        spIncoming.setViewportView(lstIncoming);

        javax.swing.GroupLayout pIncomingLayout = new javax.swing.GroupLayout(pIncoming);
        pIncoming.setLayout(pIncomingLayout);
        pIncomingLayout.setHorizontalGroup(
            pIncomingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pIncomingLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(spIncoming, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pIncomingLayout.setVerticalGroup(
            pIncomingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pIncomingLayout.createSequentialGroup()
                .addComponent(spIncoming, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        add(pIncoming);

        spOutgoing.setViewportView(lstOutgoing);

        javax.swing.GroupLayout pOutgoingLayout = new javax.swing.GroupLayout(pOutgoing);
        pOutgoing.setLayout(pOutgoingLayout);
        pOutgoingLayout.setHorizontalGroup(
            pOutgoingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 712, Short.MAX_VALUE)
            .addGroup(pOutgoingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(spOutgoing, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE))
        );
        pOutgoingLayout.setVerticalGroup(
            pOutgoingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 169, Short.MAX_VALUE)
            .addGroup(pOutgoingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(spOutgoing, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        add(pOutgoing);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel lblArcher;
    private javax.swing.JLabel lblAxe;
    private javax.swing.JLabel lblBarracksCount;
    private javax.swing.JLabel lblBarracksImage;
    private javax.swing.JLabel lblBarracksTime;
    private javax.swing.JLabel lblCatapult;
    private javax.swing.JLabel lblGarageCount;
    private javax.swing.JLabel lblGarageImage;
    private javax.swing.JLabel lblGarageTime;
    private javax.swing.JLabel lblHeavy;
    private javax.swing.JLabel lblIronCount;
    private javax.swing.JLabel lblLight;
    private javax.swing.JLabel lblMarcher;
    private javax.swing.JLabel lblNoble;
    private javax.swing.JLabel lblPaladin;
    private javax.swing.JLabel lblPopulation;
    private javax.swing.JLabel lblRam;
    private javax.swing.JLabel lblSpear;
    private javax.swing.JLabel lblSpy;
    private javax.swing.JLabel lblStableCount;
    private javax.swing.JLabel lblStableImage;
    private javax.swing.JLabel lblStableTime;
    private javax.swing.JLabel lblStoneCount;
    private javax.swing.JLabel lblStorageCount;
    private javax.swing.JLabel lblSword;
    private javax.swing.JLabel lblVillageName;
    private javax.swing.JLabel lblWoodCount;
    private javax.swing.JList lstIncoming;
    private javax.swing.JList lstOutgoing;
    private javax.swing.JPanel pHeader;
    private javax.swing.JPanel pIncoming;
    private javax.swing.JPanel pOutgoing;
    private javax.swing.JPanel pRecruitment;
    private javax.swing.JPanel pResources;
    private javax.swing.JPanel pUnits;
    private javax.swing.JPanel pUnits1;
    private javax.swing.JPanel pUnits2;
    private javax.swing.JPanel pVillageInfo;
    private javax.swing.JScrollPane spIncoming;
    private javax.swing.JScrollPane spOutgoing;
    // End of variables declaration//GEN-END:variables

    void updateVillage(Village v, UpdateType type) {
        switch (type) {
            case VILLAGE_COMMON:
                v.removeWarningFlag(0x1);
                updateCommonData(v);
                updateUnits(v, false);
                break;
            case VILLAGE_UNITS:
                v.removeWarningFlag(0x2);
                updateUnits(v, true);
                break;    
            case VILLAGE_BUILDINGS:
                v.removeWarningFlag(0x4);
                updateBuildings(v);
                break;                 
        }
        
        if (v.IsAttacked())
            v.setWarningFlag(0x8);
        else
            v.removeWarningFlag(0x8);

        if (v.hasWarning()) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Warning: {0} {1}", new Object[]{v.getName(), v.getWarningFlags()});
            UIUtils.setTabForeground(getParent().getParent(), 
                    (JTabbedPane)getParent().getParent().getParent(), Color.red);
        } else {
            UIUtils.setTabForeground(getParent().getParent(), 
                    (JTabbedPane)getParent().getParent().getParent(), Color.black);
        }
    }

    private void updateCommonData(Village v) {
        lblVillageName.setText(v.toString());
        
        if (v.getPopulation().getMax() > 0 && v.getResources().getStorage() > 0) {
            
            lblWoodCount.setText(String.valueOf(v.getResources().getWood()));
            if (v.getResources().getWood() * 100 / v.getResources().getStorage() >= 95) {
                v.setWarningFlag(0x1);
                lblWoodCount.setForeground(Color.red);
            } else
                lblWoodCount.setForeground(Color.black); 
            
            lblStoneCount.setText(String.valueOf(v.getResources().getStone()));
            if (v.getResources().getStone() * 100 / v.getResources().getStorage() >= 95) {
                v.setWarningFlag(0x1);
                lblStoneCount.setForeground(Color.red);
            } else
                lblStoneCount.setForeground(Color.black); 
            
            lblIronCount.setText(String.valueOf(v.getResources().getIron()));
            if (v.getResources().getIron() * 100 / v.getResources().getStorage() >= 95) {
                v.setWarningFlag(0x1);
                lblIronCount.setForeground(Color.red);
            } else
                lblIronCount.setForeground(Color.black);         
            
            lblStorageCount.setText(String.valueOf(v.getResources().getStorage()));
        
            int pct = v.getPopulation().getCurrent() * 100 / v.getPopulation().getMax();
            lblPopulation.setText(String.format("%s/%s (%d%%)", v.getPopulation().getCurrent(), v.getPopulation().getMax(), pct));
            if (pct >= 95 && v.getPopulation().getMax() != 24000) {
                v.setWarningFlag(0x1);
                lblPopulation.setForeground(Color.red);
            } else
                lblPopulation.setForeground(Color.black);
        }
        
        Collections.sort(v.getIncomingArmies());
        Collections.sort(v.getOutgoingArmies());
        lstIncoming.setListData(v.getIncomingArmies().toArray());
        lstOutgoing.setListData(v.getOutgoingArmies().toArray());
        
        if (v.IsAttacked())
            requestFocusInWindow(); // TODO: not working
    }

    private void updateUnits(Village v, boolean withWarnings) {
        hideUnits();
        
        for (Unit u : v.getUnits()) {
            JLabel unitLabel = getUnitLabel(u.getType());
            if (u.getTotal() > 0) {
                unitLabel.setVisible(true);
                if (u.getRecruiting() <= 0)
                    unitLabel.setText(String.valueOf(u.getInVillage()));
                else
                    unitLabel.setText(String.format("%d +%d", u.getInVillage(), u.getRecruiting()));
            }                
        }
        
        RecruitmentData d = v.getRecruitmentData();
        if (d.getBarracksCount() > 0) {
            lblBarracksCount.setText(String.format("+%d", d.getBarracksCount()));
            lblBarracksTime.setText(d.getBarracksFinished().toString("E HH:mm:ss"));
        } else {
            // dont't show warning when farm is at max rank and it is almost full
            if (withWarnings && (v.getPopulation().getMax() != 24000 || v.getPopulation().getCurrent() * 100 / v.getPopulation().getMax() < 95))
                v.setWarningFlag(0x2);
            lblBarracksCount.setText("");
            lblBarracksTime.setText("");  
        }
        
        if (d.getStableCount()> 0) {
            lblStableCount.setText(String.format("+%d", d.getStableCount()));
            lblStableTime.setText(d.getStableFinished().toString("E HH:mm:ss"));
        } else {
            lblStableCount.setText("");
            lblStableTime.setText("");            
        }
        
        if (d.getGarageCount()> 0) {
            lblGarageCount.setText(String.format("+%d", d.getGarageCount()));
            lblGarageTime.setText(d.getGarageFinished().toString("E HH:mm:ss"));
        } else {
            lblGarageCount.setText("");
            lblGarageTime.setText("");            
        }        
    }

    private JLabel getUnitLabel(UnitType type) {
        switch (type) {
            case SPEARMAN:
                return lblSpear;
            case SWORDSMAN:
                return lblSword;
            case AXEMAN:
                return lblAxe;
            case ARCHER:
                return lblArcher;
            case SPY:
               return lblSpy;
            case LIGHT_CAVALRY:
                return lblLight;
            case MOUNTED_ARCHER:
                return lblMarcher;
            case HEAVY_CAVALRY:
                return lblHeavy;
            case RAM:
                return lblRam;
            case CATAPULT:
                return lblCatapult;
            case PALADIN:
                return lblPaladin;
            case NOBLE:
                return lblNoble;
        }
        
        return null;
    }
    
    private void updateBuildings(Village v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
