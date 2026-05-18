package combat.attack.ice;

import combat.Elements;
import combat.attack.Attack;
import core.Media;

public class IceAreaAttackOne extends Attack {
    public IceAreaAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.ICE;
        damage = userDamageMultiplier * ((TIER_ONE_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Snowstorm";
        isAreaAttack = true;
        sheetLeft = Media.sheetSpellSnowstorm;
        sheetRight = Media.sheetSpellSnowstorm;
    }
}
