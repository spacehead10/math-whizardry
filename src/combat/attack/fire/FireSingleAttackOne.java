package combat.attack.fire;

import combat.Elements;
import combat.attack.Attack;

public class FireSingleAttackOne extends Attack {
    public FireSingleAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.FIRE;
        damage = userDamageMultiplier * (TIER_ONE_DAMAGE + userLevelBonus);
        name = "Fireball";
        isAreaAttack = false;
    }
}
