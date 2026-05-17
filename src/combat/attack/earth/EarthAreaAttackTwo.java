package combat.attack.earth;

import combat.Elements;
import combat.attack.Attack;

public class EarthAreaAttackTwo extends Attack {
    public EarthAreaAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.EARTH;
        damage = userDamageMultiplier * ((TIER_TWO_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Thorn Swarm";
        isAreaAttack = true;
    }
}
