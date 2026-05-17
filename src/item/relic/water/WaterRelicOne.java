package item.relic.water;

import combat.attack.fire.FireSingleAttackOne;
import combat.attack.storm.StormSingleAttackOne;
import combat.attack.water.WaterSingleAttackOne;
import item.relic.Relic;

public class WaterRelicOne extends Relic {
    public WaterRelicOne() {
        super();
        name = "Water Relic I";
        attack = WaterSingleAttackOne.class;
        description.add("This Relic of ??? grants the user");
        description.add("some basic Water powers.");
    }
}
