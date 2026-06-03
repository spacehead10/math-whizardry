package item.wand.fire;

import combat.attack.earth.EarthAreaAttackTwo;
import combat.attack.fire.FireAreaAttackTwo;
import core.Media;
import item.wand.Wand;

public class Sparkler extends Wand {
    public Sparkler() {
        super();
        name = "Sparkler";
        attack = FireAreaAttackTwo.class;
        wandDamageMultiplier = 1.1;
        description.add("Make sure you aren't putting this near any");
        description.add("flammable objects. Especially ceilings.");
        image = Media.imgSparkler.getScaledCopy(0.5f);
        cost = 600;
    }
}
