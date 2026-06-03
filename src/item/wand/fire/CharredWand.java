package item.wand.fire;

import combat.attack.earth.EarthAreaAttackTwo;
import combat.attack.fire.FireAreaAttackOne;
import core.Media;
import item.wand.Wand;

public class CharredWand extends Wand {
    public CharredWand() {
        super();
        name = "Charred Wand";
        attack = FireAreaAttackOne.class;
        wandDamageMultiplier = 1.05;
        description.add("This wooden wand may seem damaged, but its");
        description.add("charring is part of where it gets its fire");
        description.add("powers.");
        image = Media.imgCharredWand.getScaledCopy(0.5f);
        cost = 300;
    }
}
