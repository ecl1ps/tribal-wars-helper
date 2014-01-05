
package dkstatus.ui;

import dkstatus.ui.model.ButtonColumn;
import dkstatus.ui.model.RaidTableModel;
import dkstatus.world.AttackData;
import dkstatus.world.AttackManager;
import dkstatus.world.Unit;
import dkstatus.world.UnitType;
import dkstatus.world.Village;
import dkstatus.world.World;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Johny
 */
public class RaidHelperPanel extends javax.swing.JPanel {

    private enum VillageSelecton {
        BARBARIC,
        PLAYER,
        ALL
    }
    
    private List<Village> worldVillages;
    private final Village attacker;
    private VillageSelecton villageSelectionMode = VillageSelecton.BARBARIC;
    private float maxDist = 10;
    
    /**
     * Creates new form RaidHelperPanel
     * @param attacker
     */
    public RaidHelperPanel(Village attacker) {
        this.attacker = attacker;
        
        initComponents();
        
        updateAvailableUnits();
        
        RaidTableModel model = new RaidTableModel(new ArrayList<Village>(), attacker);
        tblVillages.setModel(model);
        TableRowSorter<RaidTableModel> sorter = new TableRowSorter<>(model);
        sorter.setComparator(1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Float f1 = Float.parseFloat(o1);
                Float f2 = Float.parseFloat(o2);
                return f1.compareTo(f2);
            }
        });
        sorter.setComparator(2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });        
        sorter.setComparator(3, new Comparator<ImageIcon>() {
            @Override
            public int compare(ImageIcon o1, ImageIcon o2) {
                if (o1 == null && o2 == null)
                    return 0;
                
                return o1 == null ? -1 : 1;
            }
        });
        sorter.setSortable(4, false);
        tblVillages.setRowSorter(sorter);

        ButtonColumn attackButton = new ButtonColumn(tblVillages, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = table.convertRowIndexToView(Integer.valueOf(e.getActionCommand()));
                Village v = (Village)((RaidTableModel)table.getModel()).getValueAt(modelRow, -1);
                
                if (prepareAndSendAttack(v))
                    table.setValueAt(new ImageIcon(getClass().getResource("/resources/images/attack.png")), modelRow, 3);
            }
        });
        attackButton.setMnemonic(KeyEvent.VK_A);   
        
        tblVillages.getTableHeader().setReorderingAllowed(false);
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tblVillages.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        
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

        btngrpSelectedVillages = new javax.swing.ButtonGroup();
        spVillages = new javax.swing.JScrollPane();
        tblVillages = new javax.swing.JTable();
        pHeader = new javax.swing.JPanel();
        pUnits = new javax.swing.JPanel();
        lblSpear = new javax.swing.JLabel();
        tfSpear = new javax.swing.JTextField();
        lblSpearCount = new javax.swing.JLabel();
        lblSword = new javax.swing.JLabel();
        tfSword = new javax.swing.JTextField();
        lblSwordCount = new javax.swing.JLabel();
        lblAxe = new javax.swing.JLabel();
        tfAxe = new javax.swing.JTextField();
        lblAxeCount = new javax.swing.JLabel();
        lblArcher = new javax.swing.JLabel();
        tfArcher = new javax.swing.JTextField();
        lblArcherCount = new javax.swing.JLabel();
        lblSpy = new javax.swing.JLabel();
        tfSpy = new javax.swing.JTextField();
        lblSpyCount = new javax.swing.JLabel();
        lblLight = new javax.swing.JLabel();
        tfLight = new javax.swing.JTextField();
        lblLightCount = new javax.swing.JLabel();
        lblMarcher = new javax.swing.JLabel();
        tfMarcher = new javax.swing.JTextField();
        lblMarcherCount = new javax.swing.JLabel();
        lblHeavy = new javax.swing.JLabel();
        tfHeavy = new javax.swing.JTextField();
        lblHeavyCount = new javax.swing.JLabel();
        lblRam = new javax.swing.JLabel();
        tfRam = new javax.swing.JTextField();
        lblRamCount = new javax.swing.JLabel();
        lblCatapult = new javax.swing.JLabel();
        tfCatapult = new javax.swing.JTextField();
        lblCatapultCount = new javax.swing.JLabel();
        lblKnight = new javax.swing.JLabel();
        tfKnight = new javax.swing.JTextField();
        lblKnightCount = new javax.swing.JLabel();
        lblSnob = new javax.swing.JLabel();
        tfSnob = new javax.swing.JTextField();
        lblSnobCount = new javax.swing.JLabel();
        pFilter = new javax.swing.JPanel();
        lblMaxDist = new javax.swing.JLabel();
        tfMaxDist = new javax.swing.JTextField();
        rbFree = new javax.swing.JRadioButton();
        rbOwned = new javax.swing.JRadioButton();
        rbAll = new javax.swing.JRadioButton();
        btnClearAttacked = new javax.swing.JButton();

        tblVillages.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        spVillages.setViewportView(tblVillages);

        lblSpear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_spear.png"))); // NOI18N

        tfSpear.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        lblSpearCount.setText("(1000)");

        lblSword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_sword.png"))); // NOI18N

        tfSword.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        lblSwordCount.setText("(1000)");

        lblAxe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_axe.png"))); // NOI18N

        tfAxe.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        lblAxeCount.setText("(1000)");

        lblArcher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_archer.png"))); // NOI18N

        tfArcher.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        lblArcherCount.setText("(1000)");

        lblSpy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_spy.png"))); // NOI18N

        tfSpy.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        lblSpyCount.setText("(1000)");

        lblLight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_light.png"))); // NOI18N

        tfLight.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        lblLightCount.setText("(1000)");

        lblMarcher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_marcher.png"))); // NOI18N

        tfMarcher.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        lblMarcherCount.setText("(1000)");

        lblHeavy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_heavy.png"))); // NOI18N

        tfHeavy.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        lblHeavyCount.setText("(1000)");

        lblRam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_ram.png"))); // NOI18N

        tfRam.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        lblRamCount.setText("(1000)");

        lblCatapult.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_catapult.png"))); // NOI18N

        tfCatapult.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        lblCatapultCount.setText("(1000)");

        lblKnight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_knight.png"))); // NOI18N

        tfKnight.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        lblKnightCount.setText("(1)");

        lblSnob.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_snob.png"))); // NOI18N

        tfSnob.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        lblSnobCount.setText("(10)");

        javax.swing.GroupLayout pUnitsLayout = new javax.swing.GroupLayout(pUnits);
        pUnits.setLayout(pUnitsLayout);
        pUnitsLayout.setHorizontalGroup(
            pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pUnitsLayout.createSequentialGroup()
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pUnitsLayout.createSequentialGroup()
                        .addComponent(lblSpear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSpear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSpearCount))
                    .addGroup(pUnitsLayout.createSequentialGroup()
                        .addComponent(lblSword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSwordCount))
                    .addGroup(pUnitsLayout.createSequentialGroup()
                        .addComponent(lblAxe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAxe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAxeCount))
                    .addGroup(pUnitsLayout.createSequentialGroup()
                        .addComponent(lblArcher)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfArcher, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblArcherCount)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pUnitsLayout.createSequentialGroup()
                        .addComponent(lblSpy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSpy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSpyCount))
                    .addGroup(pUnitsLayout.createSequentialGroup()
                        .addComponent(lblLight)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfLight, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLightCount))
                    .addGroup(pUnitsLayout.createSequentialGroup()
                        .addComponent(lblMarcher)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfMarcher, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMarcherCount))
                    .addGroup(pUnitsLayout.createSequentialGroup()
                        .addComponent(lblHeavy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfHeavy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHeavyCount)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pUnitsLayout.createSequentialGroup()
                        .addComponent(lblRam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfRam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRamCount))
                    .addGroup(pUnitsLayout.createSequentialGroup()
                        .addComponent(lblCatapult)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCatapult, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCatapultCount))
                    .addGroup(pUnitsLayout.createSequentialGroup()
                        .addComponent(lblKnight)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfKnight, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblKnightCount))
                    .addGroup(pUnitsLayout.createSequentialGroup()
                        .addComponent(lblSnob)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSnob, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSnobCount)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pUnitsLayout.setVerticalGroup(
            pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pUnitsLayout.createSequentialGroup()
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSpear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSpear)
                    .addComponent(lblSpearCount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSword)
                    .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfSword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSwordCount)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAxe)
                    .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfAxe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAxeCount)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblArcher)
                    .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfArcher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblArcherCount))))
            .addGroup(pUnitsLayout.createSequentialGroup()
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSpy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSpy)
                    .addComponent(lblSpyCount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLight)
                    .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLightCount)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMarcher)
                    .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfMarcher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMarcherCount)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblHeavy)
                    .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfHeavy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblHeavyCount))))
            .addGroup(pUnitsLayout.createSequentialGroup()
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRam)
                    .addComponent(lblRamCount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCatapult)
                    .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfCatapult, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCatapultCount)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblKnight)
                    .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfKnight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblKnightCount)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSnob)
                    .addGroup(pUnitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfSnob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSnobCount))))
        );

        pFilter.setToolTipText("Filtr");
        pFilter.setName("Filtr"); // NOI18N

        lblMaxDist.setText("Max. vzdálenost");

        tfMaxDist.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfMaxDist.setText("10");
        tfMaxDist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMaxDistActionPerformed(evt);
            }
        });

        btngrpSelectedVillages.add(rbFree);
        rbFree.setSelected(true);
        rbFree.setText("barbarské");
        rbFree.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                villageSelectionItemStateChanged(evt);
            }
        });

        btngrpSelectedVillages.add(rbOwned);
        rbOwned.setText("hráčské");
        rbOwned.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                villageSelectionItemStateChanged(evt);
            }
        });

        btngrpSelectedVillages.add(rbAll);
        rbAll.setText("všechny");
        rbAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                villageSelectionItemStateChanged(evt);
            }
        });

        btnClearAttacked.setText("Vyčistit útoky");
        btnClearAttacked.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearAttackedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pFilterLayout = new javax.swing.GroupLayout(pFilter);
        pFilter.setLayout(pFilterLayout);
        pFilterLayout.setHorizontalGroup(
            pFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pFilterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pFilterLayout.createSequentialGroup()
                        .addComponent(rbFree)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbOwned)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbAll))
                    .addGroup(pFilterLayout.createSequentialGroup()
                        .addComponent(lblMaxDist)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfMaxDist, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnClearAttacked))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pFilterLayout.setVerticalGroup(
            pFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pFilterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbFree)
                    .addComponent(rbOwned)
                    .addComponent(rbAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaxDist)
                    .addComponent(tfMaxDist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClearAttacked))
        );

        javax.swing.GroupLayout pHeaderLayout = new javax.swing.GroupLayout(pHeader);
        pHeader.setLayout(pHeaderLayout);
        pHeaderLayout.setHorizontalGroup(
            pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pHeaderLayout.createSequentialGroup()
                .addComponent(pFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pUnits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pHeaderLayout.setVerticalGroup(
            pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pHeaderLayout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(pUnits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(spVillages, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spVillages))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfMaxDistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMaxDistActionPerformed
        try {
            maxDist = Float.parseFloat(tfMaxDist.getText());
        } catch (NumberFormatException e) {
            tfMaxDist.setText("10");
        }
        transformAndShowVillages();
    }//GEN-LAST:event_tfMaxDistActionPerformed

    private void villageSelectionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_villageSelectionItemStateChanged
        if (evt.getStateChange() != ItemEvent.SELECTED)
            return;

        if (evt.getSource() == rbFree)
            villageSelectionMode = VillageSelecton.BARBARIC;
        else if (evt.getSource() == rbOwned)
            villageSelectionMode = VillageSelecton.PLAYER;
        else if (evt.getSource() == rbAll)
            villageSelectionMode = VillageSelecton.ALL;

        transformAndShowVillages();
    }//GEN-LAST:event_villageSelectionItemStateChanged

    private void btnClearAttackedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearAttackedActionPerformed
        ((RaidTableModel)tblVillages.getModel()).clearAttacked();
    }//GEN-LAST:event_btnClearAttackedActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearAttacked;
    private javax.swing.ButtonGroup btngrpSelectedVillages;
    private javax.swing.JLabel lblArcher;
    private javax.swing.JLabel lblArcherCount;
    private javax.swing.JLabel lblAxe;
    private javax.swing.JLabel lblAxeCount;
    private javax.swing.JLabel lblCatapult;
    private javax.swing.JLabel lblCatapultCount;
    private javax.swing.JLabel lblHeavy;
    private javax.swing.JLabel lblHeavyCount;
    private javax.swing.JLabel lblKnight;
    private javax.swing.JLabel lblKnightCount;
    private javax.swing.JLabel lblLight;
    private javax.swing.JLabel lblLightCount;
    private javax.swing.JLabel lblMarcher;
    private javax.swing.JLabel lblMarcherCount;
    private javax.swing.JLabel lblMaxDist;
    private javax.swing.JLabel lblRam;
    private javax.swing.JLabel lblRamCount;
    private javax.swing.JLabel lblSnob;
    private javax.swing.JLabel lblSnobCount;
    private javax.swing.JLabel lblSpear;
    private javax.swing.JLabel lblSpearCount;
    private javax.swing.JLabel lblSpy;
    private javax.swing.JLabel lblSpyCount;
    private javax.swing.JLabel lblSword;
    private javax.swing.JLabel lblSwordCount;
    private javax.swing.JPanel pFilter;
    private javax.swing.JPanel pHeader;
    private javax.swing.JPanel pUnits;
    private javax.swing.JRadioButton rbAll;
    private javax.swing.JRadioButton rbFree;
    private javax.swing.JRadioButton rbOwned;
    private javax.swing.JScrollPane spVillages;
    private javax.swing.JTable tblVillages;
    private javax.swing.JTextField tfArcher;
    private javax.swing.JTextField tfAxe;
    private javax.swing.JTextField tfCatapult;
    private javax.swing.JTextField tfHeavy;
    private javax.swing.JTextField tfKnight;
    private javax.swing.JTextField tfLight;
    private javax.swing.JTextField tfMarcher;
    private javax.swing.JTextField tfMaxDist;
    private javax.swing.JTextField tfRam;
    private javax.swing.JTextField tfSnob;
    private javax.swing.JTextField tfSpear;
    private javax.swing.JTextField tfSpy;
    private javax.swing.JTextField tfSword;
    // End of variables declaration//GEN-END:variables

    Comparator<Village> byDist = new Comparator<Village>() {
        @Override
        public int compare(Village o1, Village o2) {
            return attacker.getDistance(o1) < attacker.getDistance(o2) ? -1 : 1;
        }
    };
    
    void updateVillages(List<Village> villages) {
        worldVillages = villages;
        transformAndShowVillages();
    }

    private boolean prepareAndSendAttack(Village to) {
        AttackData attack = new AttackData(attacker, to);
        addUnits(attack);
        
        for (Unit u : attack.getAttackingUnits())
            if (u.getInVillage() <= 0)
                return false; // empty attack
        
        for (Unit u : attack.getAttackingUnits())
            if (attacker.getAvailableUnitCount(u.getType()) < u.getInVillage())
                return false; // not enough units for required attack
        
        AttackManager.executeAttack(attack);
        
        for (Unit u : attack.getAttackingUnits())
            attacker.reduceAvailableUnitCount(u.getType(), u.getInVillage());
        
        updateAvailableUnits();
                
        return true;
    }

    private void addUnits(AttackData attack) {
        addUnit(attack, tfArcher, UnitType.ARCHER);
        addUnit(attack, tfAxe, UnitType.AXEMAN);
        addUnit(attack, tfCatapult, UnitType.CATAPULT);
        addUnit(attack, tfHeavy, UnitType.HEAVY_CAVALRY);
        addUnit(attack, tfKnight, UnitType.PALADIN);
        addUnit(attack, tfLight, UnitType.LIGHT_CAVALRY);
        addUnit(attack, tfMarcher, UnitType.MOUNTED_ARCHER);
        addUnit(attack, tfRam, UnitType.RAM);
        addUnit(attack, tfSnob, UnitType.NOBLE);
        addUnit(attack, tfSpear, UnitType.SPEARMAN);
        addUnit(attack, tfSpy, UnitType.SPY);
        addUnit(attack, tfSword, UnitType.SWORDSMAN);
    }

    private void addUnit(AttackData attack, JTextField countField, UnitType unitType) {
        if (countField.getText().equals(""))
            return;
        
        int count;
        try {
            count = Integer.parseInt(countField.getText());
        } catch (NumberFormatException e) {
            return;
        }
        
        attack.addUnit(new Unit(unitType, count));
    }

    private void transformAndShowVillages() {
        if (worldVillages == null)
            return;
        
        List<Village> transformed;
        if (villageSelectionMode == VillageSelecton.ALL && maxDist == 0) {
            transformed = new ArrayList<>(worldVillages);
        } else {
            transformed = new ArrayList<>(worldVillages.size() / 2);
            for (Village v : worldVillages)
                if ((villageSelectionMode == VillageSelecton.BARBARIC && !v.hasOwnerPlayer() && v.getPoints() > 0) ||
                       (villageSelectionMode == VillageSelecton.PLAYER && v.hasOwnerPlayer()) ||
                        villageSelectionMode == VillageSelecton.ALL) {
                    if (attacker.getDistance(v) <= maxDist)
                        transformed.add(v);
                }            
        }
        
        Collections.sort(transformed, byDist);
        ((RaidTableModel)tblVillages.getModel()).setVillages(transformed);
    }
    
    void updateWorld(World world) {
        updateAvailableUnits();
    }
    
    private void updateAvailableUnits() {
        resetAvailableUnits();
        
        for (Unit u : attacker.getUnits()) {
            switch (u.getType()) {
                case ARCHER:
                    lblArcherCount.setText(String.format("(%d)", u.getInVillage()));
                    break;
                case AXEMAN:
                    lblAxeCount.setText(String.format("(%d)", u.getInVillage()));
                    break;
                case CATAPULT:
                    lblCatapultCount.setText(String.format("(%d)", u.getInVillage()));
                    break;
                case HEAVY_CAVALRY:
                    lblHeavyCount.setText(String.format("(%d)", u.getInVillage()));
                    break;
                case LIGHT_CAVALRY:
                    lblLightCount.setText(String.format("(%d)", u.getInVillage()));
                    break;
                case MOUNTED_ARCHER:
                    lblMarcherCount.setText(String.format("(%d)", u.getInVillage()));
                    break;
                case NOBLE:
                    lblSnobCount.setText(String.format("(%d)", u.getInVillage()));
                    break;
                case PALADIN:
                    lblKnightCount.setText(String.format("(%d)", u.getInVillage()));
                    break;
                case RAM:
                    lblRamCount.setText(String.format("(%d)", u.getInVillage()));
                    break;
                case SPEARMAN:
                    lblSpearCount.setText(String.format("(%d)", u.getInVillage()));
                    break;
                case SPY:
                    lblSpyCount.setText(String.format("(%d)", u.getInVillage()));
                    break;
                case SWORDSMAN:
                    lblSwordCount.setText(String.format("(%d)", u.getInVillage()));
                    break;                    
            }
        }
        
    }    
    
    private void resetAvailableUnits() {
        lblArcherCount.setText("(0)");
        lblAxeCount.setText("(0)");
        lblCatapultCount.setText("(0)");
        lblHeavyCount.setText("(0)");
        lblKnightCount.setText("(0)");
        lblLightCount.setText("(0)");
        lblMarcherCount.setText("(0)");
        lblRamCount.setText("(0)");
        lblSnobCount.setText("(0)");
        lblSpearCount.setText("(0)");
        lblSpyCount.setText("(0)");
        lblSwordCount.setText("(0)");        
    }    
}
