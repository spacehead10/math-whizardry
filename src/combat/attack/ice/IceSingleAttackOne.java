package combat.attack.ice;

import combat.Elements;
import combat.attack.Attack;

public class IceSingleAttackOne extends Attack {
    public IceSingleAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.ICE;
        damage = userDamageMultiplier * (TIER_ONE_DAMAGE + userLevelBonus);
        name = "Snowball";
        isAreaAttack = false;
    }
}
