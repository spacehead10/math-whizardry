package combat.attack.fire;

import combat.Elements;
import combat.attack.Attack;

public class FireSingleAttackTwo extends Attack {
    public FireSingleAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.FIRE;
        damage = userDamageMultiplier * (TIER_TWO_DAMAGE + userLevelBonus);
        name = "Flamethrower";
        isAreaAttack = false;
    }
}
