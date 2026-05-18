package item.relic.ice;

import combat.attack.fire.FireSingleAttackOne;
import combat.attack.ice.IceSingleAttackOne;
import core.Media;
import item.relic.Relic;

public class IceRelicOne extends Relic {
    public IceRelicOne() {
        super();
        name = "Ice Relic I";
        attack = IceSingleAttackOne.class;
        description.add("This Relic of Permafrost Glaciers grants the");
        description.add("user some basic Ice powers.");
        image = Media.imgIceRelicOne.getScaledCopy(0.5f);
    }
}
