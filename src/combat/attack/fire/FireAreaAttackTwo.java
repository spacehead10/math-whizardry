package combat.attack.fire;

import combat.Elements;
import combat.attack.Attack;

public class FireAreaAttackTwo extends Attack {
    public FireAreaAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.FIRE;
        damage = userDamageMultiplier * ((TIER_TWO_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Flash Point";
        isAreaAttack = true;
    }
}
