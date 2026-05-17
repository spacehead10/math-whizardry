package combat.attack.water;

import combat.Elements;
import combat.attack.Attack;

public class WaterAreaAttackThree extends Attack {
    public WaterAreaAttackThree(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.WATER;
        damage = userDamageMultiplier * ((TIER_THREE_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Tidal Surge";
        isAreaAttack = true;
    }
}
