package item.wand.water;

import combat.attack.water.WaterAreaAttackTwo;
import core.Media;
import item.wand.Wand;

public class Trident extends Wand {
    public Trident() {
        super();
        name = "Trident";
        attack = WaterAreaAttackTwo.class;
        wandDamageMultiplier = 1.1;
        description.add("This trident holds only a fraction of the power");
        description.add("of the ancient trident that created all of the");
        description.add("world's oceans.");
        image = Media.imgTrident.getScaledCopy(0.5f);
        cost = 600;
    }
}
