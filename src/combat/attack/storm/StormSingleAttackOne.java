package combat.attack.storm;

import combat.Elements;
import combat.attack.Attack;

public class StormSingleAttackOne extends Attack {
    public StormSingleAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.STORM;
        damage = userDamageMultiplier * (TIER_ONE_DAMAGE + userLevelBonus);
        name = "Spark Shock";
        isAreaAttack = false;
    }
}
