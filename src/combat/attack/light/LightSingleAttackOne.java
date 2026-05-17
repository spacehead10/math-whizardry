package combat.attack.light;

import combat.Elements;
import combat.attack.Attack;

public class LightSingleAttackOne extends Attack {
    public LightSingleAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.LIGHT;
        damage = userDamageMultiplier * (TIER_ONE_DAMAGE + userLevelBonus);
        name = "Orb";
        isAreaAttack = false;
    }
}
