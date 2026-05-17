package combat.attack.light;

import combat.Elements;
import combat.attack.Attack;

public class LightAreaAttackTwo extends Attack {
    public LightAreaAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.LIGHT;
        damage = userDamageMultiplier * ((TIER_TWO_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Plasma Pulse";
        isAreaAttack = true;
    }
}
