
package dkstatus.ui.model;

import dkstatus.world.Village;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Johny
 */
public class RaidTableModel extends AbstractTableModel {
    private static final String[] COLUMN_NAMES = new String[] {"Vesnice", "Vzdálenost", "Body", "Akce"};
    private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {String.class, String.class, String.class,  Village.class};

    private List<Village> villages = new ArrayList<>();
    private Village attacker = new Village();

    @Override
    public int getRowCount() {
        return  villages.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case -1:
                return villages.get(rowIndex);
            case 0:
                return villages.get(rowIndex).toStringSimple();
            case 1:
                return String.format("%.2f", villages.get(rowIndex).getDistance(attacker));
            case 2:
                return villages.get(rowIndex).getPoints();
            case 3:
                return "Zaútočit";
            default:
                return "";                
        }
    }

    public RaidTableModel() {
    }

    public RaidTableModel(List<Village> villages, Village attacker) {
        this.villages = villages;
        this.attacker = attacker;
    }

    @Override 
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }

    @Override 
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_TYPES[columnIndex];
    }

    public void setVillages(List<Village> villages) {
        this.villages = villages;
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 3)
            return true;
        
        return false;
    }
    
}
