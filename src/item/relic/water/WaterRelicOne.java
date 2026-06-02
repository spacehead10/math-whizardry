package item.relic.water;

import combat.attack.fire.FireSingleAttackOne;
import combat.attack.storm.StormSingleAttackOne;
import combat.attack.water.WaterSingleAttackOne;
import core.Media;
import item.relic.Relic;

public class WaterRelicOne extends Relic {
    public WaterRelicOne() {
        super();
        name = "Water Relic I";
        attack = WaterSingleAttackOne.class;
        description.add("This Relic of Pearlescent Reef grants the");
        description.add("user some basic Water powers.");
        image = Media.imgWaterRelicOne.getScaledCopy(0.5f);
    }
}
