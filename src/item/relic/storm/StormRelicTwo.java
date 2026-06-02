package item.relic.storm;

import combat.attack.storm.StormSingleAttackOne;
import combat.attack.storm.StormSingleAttackTwo;
import core.Media;
import item.relic.Relic;

public class StormRelicTwo extends Relic {
    public StormRelicTwo() {
        super();
        name = "Storm Relic II";
        attack = StormSingleAttackTwo.class;
        description.add("This Relic of Cumulus Acropolis grants the");
        description.add("user some strong Storm powers.");
        image = Media.imgStormRelicTwo.getScaledCopy(0.5f);
    }
}
