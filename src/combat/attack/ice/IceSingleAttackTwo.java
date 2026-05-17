package combat.attack.ice;

import combat.Elements;
import combat.attack.Attack;

public class IceSingleAttackTwo extends Attack {
    public IceSingleAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.ICE;
        damage = userDamageMultiplier * (TIER_TWO_DAMAGE + userLevelBonus);
        name = "Frigid Impale";
        isAreaAttack = false;
    }
}
