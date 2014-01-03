
package dkstatus.world;

/**
 *
 * @author Johny
 */
public enum UnitType {
    SPEARMAN(1, "Kopiníci", "spear", "unit_spear.png"),
    SWORDSMAN(2, "Šermíři", "sword", "unit_sword.png"),
    AXEMAN(3, "Sekerníci", "axe", "unit_axe.png"),
    ARCHER(4, "Lučištníci", "archer", "unit_archer.png"),
    SPY(5, "Zvědi", "spy", "unit_spy.png"),
    LIGHT_CAVALRY(6, "Lehká kavalérie", "light", "unit_light.png"),
    MOUNTED_ARCHER(7, "Lučištníci na koni", "marcher", "unit_marcher.png"),
    HEAVY_CAVALRY(8, "Težká kavalérie", "heavy", "unit_heavy.png"),
    RAM(9, "Beranidla", "ram", "unit_ram.png"),
    CATAPULT(10, "Katapulty", "catapult", "unit_catapult.png"),
    PALADIN(11, "Paladin", "knight", "unit_knight.png"),
    NOBLE(12, "Šlechticové", "snob", "unit_snob.png"),
    INVALID(13, "Invalid", "", "unit_snob.png");
    
    private final int id;
    private final String name;
    private final String shortcut;
    private final String image;
    
    private UnitType(int id, String name, String shortcut, String image) {
        this.id = id;
        this.name = name;
        this.shortcut = shortcut;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getShortcut() {
        return shortcut;
    }

    public static UnitType calculateUnitType(String unitShortcut) {
        for (UnitType  t : UnitType.values()) {
            if (unitShortcut.equals(t.shortcut))
                return t;
        }
        return INVALID;
    }
}
