package item.wand.earth;

import combat.attack.earth.EarthAreaAttackOne;
import combat.attack.water.WaterAreaAttackOne;
import item.wand.Wand;

public class EnchantedRose extends Wand {
    public EnchantedRose() {
        super();
        name = "Enchanted Rose";
        attack = EarthAreaAttackOne.class;
        wandDamageMultiplier = 1.05;
        description.add("A very pretty wand made from a rose from an");
        description.add("enchanted garden. Unfortunately, flowers are");
        description.add("too flimsy to carry stronger magic.");
        cost = 300;
    }
}
