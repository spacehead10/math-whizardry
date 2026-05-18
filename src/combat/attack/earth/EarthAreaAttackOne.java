package combat.attack.earth;

import combat.Elements;
import combat.attack.Attack;
import core.Media;

public class EarthAreaAttackOne extends Attack {
    public EarthAreaAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.EARTH;
        damage = userDamageMultiplier * ((TIER_ONE_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Pollen Cloud";
        isAreaAttack = true;
        sheetLeft = Media.sheetSpellPollenCloud;
        sheetRight = Media.sheetSpellPollenCloud;
    }
}
