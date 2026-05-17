package item.wand.water;

import combat.attack.water.WaterAreaAttackOne;
import combat.attack.water.WaterAreaAttackThree;
import core.Media;
import item.wand.Wand;

public class Trident extends Wand {
    public Trident() {
        super();
        name = "Trident";
        attack = WaterAreaAttackThree.class;
        wandDamageMultiplier = 1.1;
        description.add("A legendary wizard wielding the strongest of");
        description.add("these wands is said to have created all of the");
        description.add("world's oceans.");
        image = Media.imgTrident.getScaledCopy(0.5f);
        cost = 600;
    }
}
