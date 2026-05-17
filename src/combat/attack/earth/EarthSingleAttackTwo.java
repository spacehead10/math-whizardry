package combat.attack.earth;

import combat.Elements;
import combat.attack.Attack;

public class EarthSingleAttackTwo extends Attack {
    public EarthSingleAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.EARTH;
        damage = userDamageMultiplier * (TIER_TWO_DAMAGE + userLevelBonus);
        name = "Leaf Blade";
        isAreaAttack = false;
    }
}
