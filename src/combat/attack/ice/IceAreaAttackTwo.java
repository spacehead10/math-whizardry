package combat.attack.ice;

import combat.Elements;
import combat.attack.Attack;

public class IceAreaAttackTwo extends Attack {
    public IceAreaAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.ICE;
        damage = userDamageMultiplier * ((TIER_TWO_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Hailstorm";
        isAreaAttack = true;
    }
}
