package combat.attack.fire;

import combat.Elements;
import combat.attack.Attack;
import core.Media;

public class FireAreaAttackOne extends Attack {
    public FireAreaAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.FIRE;
        damage = userDamageMultiplier * ((TIER_ONE_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Heatwave";
        isAreaAttack = true;
        sheetLeft = Media.sheetSpellHeatwave;
        sheetRight = Media.sheetSpellHeatwave;
    }
}
