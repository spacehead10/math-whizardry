package combat.attack.light;

import combat.Elements;
import combat.attack.Attack;
import core.Media;

public class LightAreaAttackOne extends Attack {
    public LightAreaAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.LIGHT;
        damage = userDamageMultiplier * ((TIER_ONE_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Dazzle";
        isAreaAttack = true;
        sheetLeft = Media.sheetSpellDazzle;
        sheetRight = Media.sheetSpellDazzle;
    }
}
