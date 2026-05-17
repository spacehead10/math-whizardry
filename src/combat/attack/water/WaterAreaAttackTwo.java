package combat.attack.water;

import combat.Elements;
import combat.attack.Attack;

public class WaterAreaAttackTwo extends Attack {
    public WaterAreaAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.WATER;
        damage = userDamageMultiplier * ((TIER_TWO_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Splash Zone";
        isAreaAttack = true;
    }
}
