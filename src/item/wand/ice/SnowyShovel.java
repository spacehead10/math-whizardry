package item.wand.ice;

import combat.attack.ice.IceAreaAttackTwo;
import core.Media;
import item.wand.Wand;

public class SnowyShovel extends Wand {
    public SnowyShovel() {
        super();
        name = "Snowy Shovel";
        attack = IceAreaAttackTwo.class;
        wandDamageMultiplier = 1.1;
        description.add("No matter how much you shake it or clean it,");
        description.add("there's always some snow on it.");
        image = Media.imgSnowyShovel.getScaledCopy(0.5f);
        cost = 600;
    }
}
