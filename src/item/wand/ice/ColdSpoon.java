package item.wand.ice;

import combat.attack.ice.IceAreaAttackOne;
import core.Media;
import item.wand.Wand;

public class ColdSpoon extends Wand {
    public ColdSpoon() {
        super();
        name = "Cold Spoon";
        attack = IceAreaAttackOne.class;
        wandDamageMultiplier = 1.05;
        description.add("Many young wizards believed that putting one");
        description.add("of these under their pillows would manifest");
        description.add("a snow day.");
        image = Media.imgColdSpoon.getScaledCopy(0.5f);
        cost = 300;
    }
}
