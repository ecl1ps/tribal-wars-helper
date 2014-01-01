
package dkstatus.world;

/**
 *
 * @author Johny
 */
public enum UnitType {
    SPEARMAN("Kopiníci", "unit_spear.png"),
    SWORDSMAN("Šermíři", "unit_sword.png"),
    AXEMAN("Sekerníci", "unit_axe.png"),
    ARCHER("Lučištníci", "unit_archer.png"),
    SPY("Zvědi", "unit_spy.png"),
    LIGHT_CAVALRY("Lehká kavalerie", "unit_light.png"),
    HEAVY_CAVALRY("Težká kavalerie", "unit_heavy.png"),
    MOUNTED_ARCHER("Lučištníci na koni", "unit_marcher.png"),
    RAM("Beranidla", "unit_ram.png"),
    CATAPULT("Katapulty", "unit_catapult.png"),
    PALADIN("Paladin", "unit_knight.png"),
    NOBLE("Šlechticové", "unit_snob.png");
    
    private String name;
    private String image;
    private UnitType(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
