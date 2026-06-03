package item.wand.storm;

import combat.attack.storm.StormAreaAttackOne;
import combat.attack.storm.StormAreaAttackTwo;
import combat.attack.water.WaterAreaAttackOne;
import core.Media;
import item.wand.Wand;

public class MysteriousFan extends Wand {
    public MysteriousFan() {
        super();
        name = "Mysterious Fan";
        attack = StormAreaAttackTwo.class;
        wandDamageMultiplier = 1.1;
        description.add("This fan creates a breeze without rotating...");
        description.add("Strange.");
        image = Media.imgMysteriousFan.getScaledCopy(0.5f);
        cost = 600;
    }
}
