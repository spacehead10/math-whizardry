package item.relic.storm;

import combat.attack.fire.FireSingleAttackOne;
import combat.attack.storm.StormSingleAttackOne;
import core.Media;
import item.relic.Relic;

public class StormRelicOne extends Relic {
    public StormRelicOne() {
        super();
        name = "Storm Relic I";
        attack = StormSingleAttackOne.class;
        description.add("This Relic of Cumulus Acropolis grants the");
        description.add("user some basic Storm powers.");
        image = Media.imgStormRelicOne.getScaledCopy(0.5f);
    }
}
