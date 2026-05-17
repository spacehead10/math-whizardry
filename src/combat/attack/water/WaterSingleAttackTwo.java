package combat.attack.water;

import combat.Elements;
import combat.attack.Attack;

public class WaterSingleAttackTwo extends Attack {
    public WaterSingleAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.WATER;
        damage = userDamageMultiplier * (TIER_TWO_DAMAGE + userLevelBonus);
        name = "Super Soak";
        isAreaAttack = false;
    }
}
