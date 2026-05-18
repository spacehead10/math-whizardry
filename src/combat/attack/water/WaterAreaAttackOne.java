package combat.attack.water;

import combat.Elements;
import combat.attack.Attack;
import core.Media;

public class WaterAreaAttackOne extends Attack {
    public WaterAreaAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.WATER;
        damage = userDamageMultiplier * ((TIER_ONE_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Drizzle";
        isAreaAttack = true;
        sheetLeft = Media.sheetSpellDrizzle;
        sheetRight = Media.sheetSpellDrizzle;
    }
}
