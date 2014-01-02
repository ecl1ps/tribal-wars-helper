
package dkstatus.ui;

import dkstatus.Utils;
import dkstatus.world.MarchingArmy;
import dkstatus.world.Unit;
import dkstatus.world.UnitType;
import dkstatus.world.Village;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Johny
 */
public class VillagePanel extends javax.swing.JPanel {

    private final Integer id;
    
    /**
     * Creates new form VillagePanel
     * @param id
     */
    public VillagePanel(Integer id) {
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
    }

    private void hideUnits() {
        lblArcher.setVisible(false);
        lblAxe.setVisible(false);
        lblCatapult.setVisible(false);
        lblHeavy.setVisible(false);
        lblIronCount.setVisible(false);
        lblLight.setVisible(false);
        lblMarcher.setVisible(false);
        lblNoble.setVisible(false);
        lblPaladin.setVisible(false);
        lblPopulation.setVisible(false);
        lblRam.setVisible(false);
        lblSpear.setVisible(false);
        lblSpy.setVisible(false);
    }

    public Integer getId() {
        return id;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pResources = new javax.swing.JPanel();
        lblVillageName = new javax.swing.JLabel();
        lblWoodCount = new javax.swing.JLabel();
        lblStoneCount = new javax.swing.JLabel();
        lblIronCount = new javax.swing.JLabel();
        lblStorageCount = new javax.swing.JLabel();
        lblPopulation = new javax.swing.JLabel();
        pUnits = new javax.swing.JPanel();
        lblSpear = new javax.swing.JLabel();
        lblSword = new javax.swing.JLabel();
        lblAxe = new javax.swing.JLabel();
        lblArcher = new javax.swing.JLabel();
        lblSpy = new javax.swing.JLabel();
        lblLight = new javax.swing.JLabel();
        lblMarcher = new javax.swing.JLabel();
        lblHeavy = new javax.swing.JLabel();
        lblRam = new javax.swing.JLabel();
        lblCatapult = new javax.swing.JLabel();
        lblPaladin = new javax.swing.JLabel();
        lblNoble = new javax.swing.JLabel();
        pIncoming = new javax.swing.JPanel();
        spIncoming = new javax.swing.JScrollPane();
        lstIncoming = new javax.swing.JList();
        pOutgoing = new javax.swing.JPanel();
        spOutgoing = new javax.swing.JScrollPane();
        lstOutgoing = new javax.swing.JList();

        lblVillageName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/village.png"))); // NOI18N
        lblVillageName.setText("Uber Vesnice (111|222) K01");

        lblWoodCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/wood.png"))); // NOI18N
        lblWoodCount.setText("0");

        lblStoneCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/stone.png"))); // NOI18N
        lblStoneCount.setText("0");

        lblIronCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/iron.png"))); // NOI18N
        lblIronCount.setText("0");

        lblStorageCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/storage.png"))); // NOI18N
        lblStorageCount.setText("0");

        lblPopulation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/worker.png"))); // NOI18N
        lblPopulation.setText("0/0");

        javax.swing.GroupLayout pResourcesLayout = new javax.swing.GroupLayout(pResources);
        pResources.setLayout(pResourcesLayout);
        pResourcesLayout.setHorizontalGroup(
            pResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pResourcesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVillageName)
                    .addGroup(pResourcesLayout.createSequentialGroup()
                        .addComponent(lblWoodCount)
                        .addGap(18, 18, 18)
                        .addComponent(lblStoneCount)
                        .addGap(18, 18, 18)
                        .addComponent(lblIronCount)
                        .addGap(18, 18, 18)
                        .addComponent(lblStorageCount)
                        .addGap(18, 18, 18)
                        .addComponent(lblPopulation)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pResourcesLayout.setVerticalGroup(
            pResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pResourcesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblVillageName)
                .addGap(18, 18, 18)
                .addGroup(pResourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWoodCount)
                    .addComponent(lblStoneCount)
                    .addComponent(lblIronCount)
                    .addComponent(lblStorageCount)
                    .addComponent(lblPopulation))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        lblSpear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_spear.png"))); // NOI18N
        lblSpear.setText("40000 +100 (3:15:16 v 16:45:11)");

        lblSword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_sword.png"))); // NOI18N
        lblSword.setText("0 +100 (3:15:16 v 16:45:11)");

        lblAxe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_axe.png"))); // NOI18N
        lblAxe.setText("3000 +100 (3:15:16 v 16:45:11)");

        lblArcher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_archer.png"))); // NOI18N
        lblArcher.setText("0 +100 (3:15:16 v 16:45:11)");

        lblSpy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_spy.png"))); // NOI18N
        lblSpy.setText("3333 +100 (3:15:16 v 16:45:11)");

        lblLight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_light.png"))); // NOI18N
        lblLight.setText("3333 +100 (3:15:16 v 16:45:11)");

        lblMarcher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_marcher.png"))); // NOI18N
        lblMarcher.setText("3333 +100 (3:15:16 v 16:45:11)");

        lblHeavy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_heavy.png"))); // NOI18N
        lblHeavy.setText("3333 +100 (3:15:16 v 16:45:11)");

        lblRam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_ram.png"))); // NOI18N
        lblRam.setText("3333 +100 (3:15:16 v 16:45:11)");

        lblCatapult.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_catapult.png"))); // NOI18N
        lblCatapult.setText("3333 +100 (3:15:16 v 16:45:11)");

        lblPaladin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_knight.png"))); // NOI18N
        lblPaladin.setText("1");

        lblNoble.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_snob.png"))); // NOI18N
        lblNoble.setText("3 +1 (3:15:16 v 16:45:11)");

        javax.swing.GroupLayout pUnitsLayout = new javax.swing.GroupLayout(pUnits);
        pUnits.setLayout(pUnitsLayout);
        pUnitsLayout.setHorizontalGroup(
            pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pUnitsLayout.createSequentialGroup()
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSpear)
                    .addComponent(lblSword)
                    .addComponent(lblAxe)
                    .addComponent(lblArcher)
                    .addComponent(lblSpy)
                    .addComponent(lblLight)
                    .addComponent(lblMarcher)
                    .addComponent(lblHeavy)
                    .addComponent(lblRam)
                    .addComponent(lblCatapult)
                    .addComponent(lblPaladin)
                    .addComponent(lblNoble))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        pUnitsLayout.setVerticalGroup(
            pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pUnitsLayout.createSequentialGroup()
                .addComponent(lblSpear)
                .addGap(0, 0, 0)
                .addComponent(lblSword)
                .addGap(0, 0, 0)
                .addComponent(lblAxe)
                .addGap(0, 0, 0)
                .addComponent(lblArcher)
                .addGap(0, 0, 0)
                .addComponent(lblSpy)
                .addGap(0, 0, 0)
                .addComponent(lblLight)
                .addGap(0, 0, 0)
                .addComponent(lblMarcher)
                .addGap(0, 0, 0)
                .addComponent(lblHeavy)
                .addGap(0, 0, 0)
                .addComponent(lblRam)
                .addGap(0, 0, 0)
                .addComponent(lblCatapult)
                .addGap(0, 0, 0)
                .addComponent(lblPaladin)
                .addGap(0, 0, 0)
                .addComponent(lblNoble)
                .addGap(6, 6, 6))
        );

        spIncoming.setViewportView(lstIncoming);

        javax.swing.GroupLayout pIncomingLayout = new javax.swing.GroupLayout(pIncoming);
        pIncoming.setLayout(pIncomingLayout);
        pIncomingLayout.setHorizontalGroup(
            pIncomingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spIncoming, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
        );
        pIncomingLayout.setVerticalGroup(
            pIncomingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pIncomingLayout.createSequentialGroup()
                .addComponent(spIncoming, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        spOutgoing.setViewportView(lstOutgoing);

        javax.swing.GroupLayout pOutgoingLayout = new javax.swing.GroupLayout(pOutgoing);
        pOutgoing.setLayout(pOutgoingLayout);
        pOutgoingLayout.setHorizontalGroup(
            pOutgoingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pOutgoingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(spOutgoing, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE))
        );
        pOutgoingLayout.setVerticalGroup(
            pOutgoingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
            .addGroup(pOutgoingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(spOutgoing, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pIncoming, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pOutgoing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pResources, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(529, Short.MAX_VALUE)
                    .addComponent(pUnits, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pResources, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addComponent(pIncoming, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pOutgoing, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(pUnits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(240, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblArcher;
    private javax.swing.JLabel lblAxe;
    private javax.swing.JLabel lblCatapult;
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
    private javax.swing.JLabel lblStoneCount;
    private javax.swing.JLabel lblStorageCount;
    private javax.swing.JLabel lblSword;
    private javax.swing.JLabel lblVillageName;
    private javax.swing.JLabel lblWoodCount;
    private javax.swing.JList lstIncoming;
    private javax.swing.JList lstOutgoing;
    private javax.swing.JPanel pIncoming;
    private javax.swing.JPanel pOutgoing;
    private javax.swing.JPanel pResources;
    private javax.swing.JPanel pUnits;
    private javax.swing.JScrollPane spIncoming;
    private javax.swing.JScrollPane spOutgoing;
    // End of variables declaration//GEN-END:variables

    void updateVillage(Village v, UpdateType type) {
        switch (type) {
            case VILLAGE_COMMON:
                updateCommonData(v);
                break;
            case VILLAGE_UNITS:
                updateUnits(v);
                break;    
            case VILLAGE_BUILDINGS:
                updateBuildings(v);
                break;                 
        }
    }

    private void updateCommonData(Village v) {
        lblVillageName.setText(v.toString());
        
        lblWoodCount.setText(String.valueOf(v.getResources().getWood()));
        lblStoneCount.setText(String.valueOf(v.getResources().getStone()));
        lblIronCount.setText(String.valueOf(v.getResources().getIron()));
        lblStorageCount.setText(String.valueOf(v.getResources().getStorage()));
        lblPopulation.setText(v.getPopulation().toString());
        
        Collections.sort(v.getIncomingArmies());
        Collections.sort(v.getOutgoingArmies());
        lstIncoming.setListData(v.getIncomingArmies().toArray());
        lstOutgoing.setListData(v.getOutgoingArmies().toArray());
        
        if (v.IsAttacked()) // TODO: not working
            requestFocusInWindow();
    }

    private void updateUnits(Village v) {
        hideUnits();
        
        for (Unit u : v.getUnits()) {
            JLabel unitLabel = getUnitLabel(u.getType());
            if (u.getTotal() > 0) {
                unitLabel.setVisible(true);
                if (u.getRecruiting() <= 0)
                    unitLabel.setText(String.valueOf(u.getInVillage()));
                else
                    unitLabel.setText(String.format("%d +%d ()", u.getInVillage(), u.getRecruiting()));
            }                
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
