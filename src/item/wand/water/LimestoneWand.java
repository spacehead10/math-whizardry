package item.wand.water;

import combat.attack.water.WaterAreaAttackOne;
import core.Media;
import item.wand.Wand;

public class LimestoneWand extends Wand {
    public LimestoneWand() {
        super();
        name = "Limestone Wand";
        attack = WaterAreaAttackOne.class;
        wandDamageMultiplier = 1.05;
        description.add("This simple limestone wand is powered by an");
        description.add("enchanted seashell.");
        image = Media.imgLimestoneWand.getScaledCopy(0.5f);
        cost = 300;
    }
}
