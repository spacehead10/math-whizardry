package item.relic.fire;

import combat.attack.fire.FireSingleAttackOne;
import core.Media;
import item.relic.Relic;

public class FireRelicOne extends Relic {
    public FireRelicOne() {
        super();
        name = "Fire Relic I";
        attack = FireSingleAttackOne.class;
        description.add("This Relic of Caldera Castle grants the user");
        description.add("some basic Fire powers.");
        image = Media.imgFireRelicOne.getScaledCopy(0.5f);
    }
}
