package item.relic.earth;

import combat.attack.earth.EarthSingleAttackOne;
import combat.attack.fire.FireSingleAttackOne;
import core.Media;
import item.relic.Relic;

public class EarthRelicOne extends Relic {
    public EarthRelicOne() {
        super();
        name = "Earth Relic I";
        attack = EarthSingleAttackOne.class;
        description.add("This Relic of ??? grants the user");
        description.add("some basic Earth powers.");
        image = Media.imgEarthRelicOne.getScaledCopy(0.5f);
    }
}
