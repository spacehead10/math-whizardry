package item.relic.storm;

import combat.attack.fire.FireSingleAttackOne;
import combat.attack.storm.StormSingleAttackOne;
import item.relic.Relic;

public class StormRelicOne extends Relic {
    public StormRelicOne() {
        super();
        name = "Storm Relic I";
        attack = StormSingleAttackOne.class;
        description.add("This Relic of ??? grants the user");
        description.add("some basic Storm powers.");
    }
}
