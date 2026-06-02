package combat.attack.storm;

import combat.Elements;
import combat.attack.Attack;
import core.Media;

public class StormAreaAttackTwo extends Attack {
    public StormAreaAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.STORM;
        damage = userDamageMultiplier * ((TIER_TWO_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Whirling Vortex";
        isAreaAttack = true;
        sheetLeft = Media.sheetSpellWhirlingVortex;
        sheetRight = Media.sheetSpellWhirlingVortex;
    }
}
