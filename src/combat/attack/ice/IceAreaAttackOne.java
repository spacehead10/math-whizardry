package combat.attack.ice;

import combat.Elements;
import combat.attack.Attack;

public class IceAreaAttackOne extends Attack {
    public IceAreaAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.ICE;
        damage = userDamageMultiplier * ((TIER_ONE_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Snowstorm";
        isAreaAttack = true;
    }
}
