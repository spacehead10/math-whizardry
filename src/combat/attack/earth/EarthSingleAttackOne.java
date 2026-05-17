package combat.attack.earth;

import combat.Elements;
import combat.attack.Attack;

public class EarthSingleAttackOne extends Attack {
    public EarthSingleAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.EARTH;
        damage = userDamageMultiplier * (TIER_ONE_DAMAGE + userLevelBonus);
        name = "Thorn Prick";
        isAreaAttack = false;
    }
}
