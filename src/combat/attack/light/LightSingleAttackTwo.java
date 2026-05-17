package combat.attack.light;

import combat.Elements;
import combat.attack.Attack;

public class LightSingleAttackTwo extends Attack {
    public LightSingleAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.LIGHT;
        damage = userDamageMultiplier * (TIER_TWO_DAMAGE + userLevelBonus);
        name = "Radiant Blast";
        isAreaAttack = false;
    }
}
