package item.wand.earth;

import combat.attack.earth.EarthAreaAttackOne;
import combat.attack.earth.EarthAreaAttackTwo;
import combat.attack.water.WaterAreaAttackOne;
import core.Media;
import item.wand.Wand;

public class EnchantedRose extends Wand {
    public EnchantedRose() {
        super();
        name = "Enchanted Rose";
        attack = EarthAreaAttackTwo.class;
        wandDamageMultiplier = 1.1;
        description.add("A very pretty wand made from a rose from an");
        description.add("enchanted garden. Roses are notoriously");
        description.add("thorny, and this wand is no exception.");
        image = Media.imgEnchantedRose.getScaledCopy(0.5f);
        cost = 600;
    }
}
