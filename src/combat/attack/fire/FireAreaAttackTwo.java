package combat.attack.fire;

import combat.Elements;
import combat.attack.Attack;
import core.Media;

public class FireAreaAttackTwo extends Attack {
    public FireAreaAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.FIRE;
        damage = userDamageMultiplier * ((TIER_TWO_DAMAGE * AREA_ATTACK_DAMAGE_MULTIPLIER) + userLevelBonus);
        name = "Flashover";
        isAreaAttack = true;
        sheetLeft = Media.sheetSpellFlashover;
        sheetRight = Media.sheetSpellFlashover;
    }
}
