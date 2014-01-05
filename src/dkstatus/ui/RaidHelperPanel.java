
package dkstatus.ui;

import dkstatus.ui.model.ButtonColumn;
import dkstatus.ui.model.RaidTableModel;
import dkstatus.world.AttackData;
import dkstatus.world.AttackManager;
import dkstatus.world.Unit;
import dkstatus.world.UnitType;
import dkstatus.world.Village;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JTable;

/**
 *
 * @author Johny
 */
public class RaidHelperPanel extends javax.swing.JPanel {

    private final Village attacker;
    
    /**
     * Creates new form RaidHelperPanel
     * @param attacker
     */
    public RaidHelperPanel(Village attacker) {
        this.attacker = attacker;
        
        initComponents();
        
        tblVillages.setModel(new RaidTableModel(new ArrayList<Village>(), attacker));

        ButtonColumn attackButton = new ButtonColumn(tblVillages, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf(e.getActionCommand());
                Village v = (Village)((RaidTableModel)table.getModel()).getValueAt(modelRow, -1);
                prepareAttack(v);
            }
        });
        attackButton.setMnemonic(KeyEvent.VK_A);   
        
        tblVillages.getColumn("Akce").setCellRenderer(attackButton);
        tblVillages.getColumn("Akce").setCellEditor(attackButton);
        //tblVillages.getColumn("Akce").setPreferredWidth(30);
        tblVillages.addMouseListener(attackButton);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblVillages = new javax.swing.JTable();

        tblVillages.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblVillages);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVillages;
    // End of variables declaration//GEN-END:variables

    Comparator<Village> byDist = new Comparator<Village>() {
        @Override
        public int compare(Village o1, Village o2) {
            return attacker.getDistance(o1) < attacker.getDistance(o2) ? -1 : 1;
        }
    };
    
    void updateVillages(List<Village> villages) {
        Collections.sort(villages, byDist);
        ((RaidTableModel)tblVillages.getModel()).setVillages(villages);
    }

    private void prepareAttack(Village to) {
        AttackData attack = new AttackData(attacker, to);
        attack.addUnit(new Unit(UnitType.SPY, 5));
        AttackManager.executeAttack(attack);
    }
}
