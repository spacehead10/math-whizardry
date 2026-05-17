package item.relic.earth;

import combat.attack.earth.EarthSingleAttackOne;
import combat.attack.fire.FireSingleAttackOne;
import item.relic.Relic;

public class EarthRelicOne extends Relic {
    public EarthRelicOne() {
        super();
        name = "Earth Relic I";
        attack = EarthSingleAttackOne.class;
        description.add("This Relic of ??? grants the user");
        description.add("some basic Earth powers.");
    }
}
