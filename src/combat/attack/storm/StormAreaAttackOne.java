package combat.attack.storm;

import combat.Elements;
import combat.attack.Attack;
import core.Media;

public class StormAreaAttackOne extends Attack {
    public StormAreaAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.STORM;
        damage = userDamageMultiplier * ((TIER_ONE_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Wind Slash";
        isAreaAttack = true;
        sheetLeft = Media.sheetSpellWindSlash;
        sheetRight = Media.sheetSpellWindSlash;
    }
}
