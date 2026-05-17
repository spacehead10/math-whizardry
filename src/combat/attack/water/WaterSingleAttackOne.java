package combat.attack.water;

import combat.Elements;
import combat.attack.Attack;

public class WaterSingleAttackOne extends Attack {
    public WaterSingleAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.WATER;
        damage = userDamageMultiplier * (TIER_ONE_DAMAGE + userLevelBonus);
        name = "Bubble Burst";
        isAreaAttack = false;
    }
}
