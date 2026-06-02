package item.relic.water;

import combat.attack.water.WaterSingleAttackOne;
import combat.attack.water.WaterSingleAttackTwo;
import core.Media;
import item.relic.Relic;

public class WaterRelicTwo extends Relic {
    public WaterRelicTwo() {
        super();
        name = "Water Relic II";
        attack = WaterSingleAttackTwo.class;
        description.add("This Relic of Pearlescent Reef grants the");
        description.add("user some strong Water powers.");
        image = Media.imgWaterRelicTwo.getScaledCopy(0.5f);
    }
}
