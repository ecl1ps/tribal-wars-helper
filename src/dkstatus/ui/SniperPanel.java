
package dkstatus.ui;

import dkstatus.world.ArmyType;
import dkstatus.world.MapPosition;
import dkstatus.world.Village;
import java.awt.Color;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 *
 * @author Johny
 */
public class SniperPanel extends javax.swing.JPanel {

    private final Village v;
    private final Village target = new Village();
    private boolean coordsValid = false;
    private final PeriodFormatter periodFormatter;
    
    /**
     * Creates new form SniperPanel
     * @param village
     */
    public SniperPanel(Village village) {
        v = village;
        
        periodFormatter = new PeriodFormatterBuilder()
                .appendHours()
                .appendSeparator(":")
                .appendMinutes()
                .appendSeparator(":")
                .appendSeconds()
                .appendSeparator(":")
                .appendMillis3Digit()
                .toFormatter();        
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
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
        pAttack = new javax.swing.JPanel();
        tfCoords = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        spAttackTime = new javax.swing.JSpinner();

        rbImpactTime = new javax.swing.JRadioButton();
        rbAttackTime = new javax.swing.JRadioButton();
        btnAttack = new javax.swing.JButton();
        rbAttack = new javax.swing.JRadioButton();
        rbSupport = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pTimes = new javax.swing.JPanel();
        tfSummary = new javax.swing.JTextField();

        lblSpear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_spear.png"))); // NOI18N

        tfSpear.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfSpear.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                test(evt);
            }
        });

        lblSpearCount.setText("(1000)");
        lblSpearCount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblSword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_sword.png"))); // NOI18N

        tfSword.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfSword.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                unitCountChanged(evt);
            }
        });

        lblSwordCount.setText("(1000)");
        lblSwordCount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblAxe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_axe.png"))); // NOI18N

        tfAxe.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfAxe.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                unitCountChanged(evt);
            }
        });

        lblAxeCount.setText("(1000)");
        lblAxeCount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblArcher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_archer.png"))); // NOI18N

        tfArcher.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfArcher.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                unitCountChanged(evt);
            }
        });

        lblArcherCount.setText("(1000)");
        lblArcherCount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblSpy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_spy.png"))); // NOI18N

        tfSpy.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfSpy.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                unitCountChanged(evt);
            }
        });

        lblSpyCount.setText("(1000)");
        lblSpyCount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblLight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_light.png"))); // NOI18N

        tfLight.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfLight.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                unitCountChanged(evt);
            }
        });

        lblLightCount.setText("(1000)");
        lblLightCount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblMarcher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_marcher.png"))); // NOI18N

        tfMarcher.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfMarcher.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                unitCountChanged(evt);
            }
        });

        lblMarcherCount.setText("(1000)");
        lblMarcherCount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblHeavy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_heavy.png"))); // NOI18N

        tfHeavy.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfHeavy.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                unitCountChanged(evt);
            }
        });

        lblHeavyCount.setText("(1000)");
        lblHeavyCount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblRam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_ram.png"))); // NOI18N

        tfRam.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfRam.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                unitCountChanged(evt);
            }
        });

        lblRamCount.setText("(1000)");
        lblRamCount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblCatapult.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_catapult.png"))); // NOI18N

        tfCatapult.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfCatapult.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                unitCountChanged(evt);
            }
        });

        lblCatapultCount.setText("(1000)");
        lblCatapultCount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblKnight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_knight.png"))); // NOI18N

        tfKnight.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfKnight.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                unitCountChanged(evt);
            }
        });

        lblKnightCount.setText("(1)");
        lblKnightCount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblSnob.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/unit_snob.png"))); // NOI18N

        tfSnob.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfSnob.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                unitCountChanged(evt);
            }
        });

        lblSnobCount.setText("(10)");
        lblSnobCount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

        pAttack.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tfCoords.setDocument(new JTextFieldLimit(7));
        tfCoords.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCoordsFocusLost(evt);
            }
        });

        jLabel1.setText("Cíl:");

        jLabel2.setText("X|Y");

        jLabel3.setText("Čas:");

        spAttackTime.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.SECOND));
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spAttackTime, "dd.MM.yy HH:mm:ss:SSS");
        spAttackTime.setEditor(timeEditor);
        spAttackTime.setValue(new Date());

        buttonGroup1.add(rbImpactTime);
        rbImpactTime.setSelected(true);
        rbImpactTime.setText("čas dopadu");

        buttonGroup1.add(rbAttackTime);
        rbAttackTime.setText("čas útoku");

        btnAttack.setText("Poslat");
        btnAttack.setEnabled(false);

        buttonGroup2.add(rbAttack);
        rbAttack.setSelected(true);
        rbAttack.setText("útok");

        buttonGroup2.add(rbSupport);
        rbSupport.setText("podpora");

        javax.swing.GroupLayout pAttackLayout = new javax.swing.GroupLayout(pAttack);
        pAttack.setLayout(pAttackLayout);
        pAttackLayout.setHorizontalGroup(
            pAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAttackLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAttack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pAttackLayout.createSequentialGroup()
                        .addGroup(pAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pAttackLayout.createSequentialGroup()
                                .addComponent(tfCoords, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(spAttackTime)))
                    .addGroup(pAttackLayout.createSequentialGroup()
                        .addGroup(pAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbImpactTime)
                            .addComponent(rbAttack))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbSupport)
                            .addComponent(rbAttackTime))))
                .addContainerGap())
        );
        pAttackLayout.setVerticalGroup(
            pAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAttackLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbImpactTime)
                    .addComponent(rbAttackTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbAttack)
                    .addComponent(rbSupport))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(spAttackTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfCoords, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAttack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jTable1);

        tfSummary.setEditable(false);
        tfSummary.setBorder(null);

        javax.swing.GroupLayout pTimesLayout = new javax.swing.GroupLayout(pTimes);
        pTimes.setLayout(pTimesLayout);
        pTimesLayout.setHorizontalGroup(
            pTimesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTimesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfSummary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pTimesLayout.setVerticalGroup(
            pTimesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTimesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfSummary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pAttack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(pUnits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(pTimes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pAttack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pUnits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(pTimes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfCoordsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCoordsFocusLost
        String text = tfCoords.getText();
        if (text.length() != 7) {
            coordsValid = false;
            tfCoords.setForeground(Color.red);
            return;
        }
            
        try {
            int x = Integer.parseInt(text.substring(0, 3));
            int y = Integer.parseInt(text.substring(4, 7));
            target.setPosition(new MapPosition(x, y));
            coordsValid = true;
            tfCoords.setForeground(Color.black);
        } catch (NumberFormatException e) {
            coordsValid = false;
            tfCoords.setForeground(Color.red);
        }
        
        recalculateAttackTimes();
    }//GEN-LAST:event_tfCoordsFocusLost

    private void unitCountChanged(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_unitCountChanged
        recalculateAttackTimes();
    }//GEN-LAST:event_unitCountChanged

    private void test(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_test
        recalculateAttackTimes();
    }//GEN-LAST:event_test


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAttack;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
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
    private javax.swing.JPanel pAttack;
    private javax.swing.JPanel pTimes;
    private javax.swing.JPanel pUnits;
    private javax.swing.JRadioButton rbAttack;
    private javax.swing.JRadioButton rbAttackTime;
    private javax.swing.JRadioButton rbImpactTime;
    private javax.swing.JRadioButton rbSupport;
    private javax.swing.JSpinner spAttackTime;
    private javax.swing.JTextField tfArcher;
    private javax.swing.JTextField tfAxe;
    private javax.swing.JTextField tfCatapult;
    private javax.swing.JTextField tfCoords;
    private javax.swing.JTextField tfHeavy;
    private javax.swing.JTextField tfKnight;
    private javax.swing.JTextField tfLight;
    private javax.swing.JTextField tfMarcher;
    private javax.swing.JTextField tfRam;
    private javax.swing.JTextField tfSnob;
    private javax.swing.JTextField tfSpear;
    private javax.swing.JTextField tfSpy;
    private javax.swing.JTextField tfSummary;
    private javax.swing.JTextField tfSword;
    // End of variables declaration//GEN-END:variables

    private void recalculateAttackTimes() {
        ArmyType type = getSlowestUnit();
        
        if (!coordsValid || type == ArmyType.INVALID) {
            tfSummary.setText("");
            return;
        }
            
        if (rbSupport.isSelected() && type.isSlowerThan(ArmyType.LIGHT_CAVALLERY))
            type = ArmyType.LIGHT_CAVALLERY;
        
        DateTime start, impact;
        Period travelTime = type.getTimeToTravell(v.getPosition(), target.getPosition());
        if (rbAttackTime.isSelected()) {
            start = new DateTime(((Date)spAttackTime.getValue()).getTime());
            impact = start.plus(travelTime);
        } else {
            impact = new DateTime(((Date)spAttackTime.getValue()).getTime());
            start = impact.minus(travelTime);
        }
        
        tfSummary.setText(String.format("Útok na %d|%d, nejpomalejší: %s, přesun: %s, útok: %s, dopad: %s", 
                target.getPosition().getPosition().x, target.getPosition().getPosition().y, 
                type.getName(), travelTime.toString(periodFormatter), 
                start.toString("E HH:mm:ss:SSS"), impact.toString("E HH:mm:ss:SSS")));
    }

    private ArmyType getSlowestUnit() {
        if (isSelected(tfSnob))
            return ArmyType.NOBLE;
        else if (isSelected(tfCatapult) || isSelected(tfRam))
            return ArmyType.RAM_CATAPULT;
        else if (isSelected(tfSword))
            return ArmyType.SWORDSMAN;
        else if (isSelected(tfSpear) || isSelected(tfAxe) || isSelected(tfArcher))
            return ArmyType.PIKE_AXE;
        else if (isSelected(tfHeavy))
            return ArmyType.HEAVY_CAVALLERY;
        else if (isSelected(tfLight) || isSelected(tfMarcher) || isSelected(tfKnight))
            return ArmyType.LIGHT_CAVALLERY;
        else if (isSelected(tfSpy))
            return ArmyType.SCOUT; 
        
        return ArmyType.INVALID;
    }

    private boolean isSelected(JTextField tf) {
        if (tf.getText().equals("") || tf.getText().equals("0"))
            return false;
        
        try {
            int c = Integer.parseInt(tf.getText());
            if (c > 0)
                return true;
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }
}
