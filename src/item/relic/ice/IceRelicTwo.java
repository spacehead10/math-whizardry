package item.relic.ice;

import combat.attack.ice.IceSingleAttackOne;
import combat.attack.ice.IceSingleAttackTwo;
import core.Media;
import item.relic.Relic;

public class IceRelicTwo extends Relic {
    public IceRelicTwo() {
        super();
        name = "Ice Relic II";
        attack = IceSingleAttackTwo.class;
        description.add("This Relic of Permafrost Glaciers grants the");
        description.add("user some strong Ice powers.");
        image = Media.imgIceRelicTwo.getScaledCopy(0.5f);
    }
}
