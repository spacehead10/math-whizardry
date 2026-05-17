package combat.attack.fire;

import combat.Elements;
import combat.attack.Attack;

public class FireAreaAttackOne extends Attack {
    public FireAreaAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.FIRE;
        damage = userDamageMultiplier * ((TIER_ONE_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Heatwave";
        isAreaAttack = true;
    }
}
