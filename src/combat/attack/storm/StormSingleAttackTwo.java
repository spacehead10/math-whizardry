package combat.attack.storm;

import combat.Elements;
import combat.attack.Attack;

public class StormSingleAttackTwo extends Attack {
    public StormSingleAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.STORM;
        damage = userDamageMultiplier * (TIER_TWO_DAMAGE + userLevelBonus);
        name = "Charge Cannon";
        isAreaAttack = false;
    }
}
