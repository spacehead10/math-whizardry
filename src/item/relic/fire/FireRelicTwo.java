package item.relic.fire;

import combat.attack.fire.FireSingleAttackOne;
import combat.attack.fire.FireSingleAttackTwo;
import core.Media;
import item.relic.Relic;

public class FireRelicTwo extends Relic {
    public FireRelicTwo() {
        super();
        name = "Fire Relic II";
        attack = FireSingleAttackTwo.class;
        description.add("This Relic of Caldera Castle grants the user");
        description.add("some strong Fire powers.");
        image = Media.imgFireRelicTwo.getScaledCopy(0.5f);
    }
}
