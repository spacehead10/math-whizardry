package item.relic.earth;

import combat.attack.earth.EarthSingleAttackOne;
import combat.attack.earth.EarthSingleAttackTwo;
import core.Media;
import item.relic.Relic;

public class EarthRelicTwo extends Relic {
    public EarthRelicTwo() {
        super();
        name = "Earth Relic II";
        attack = EarthSingleAttackTwo.class;
        description.add("This Relic of Overgrowth Gardens grants the");
        description.add("user some strong Earth powers.");
        image = Media.imgEarthRelicTwo.getScaledCopy(0.5f);
    }
}
