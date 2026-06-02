package combat.attack.storm;

import combat.Elements;
import combat.attack.Attack;
import core.Media;
import org.newdawn.slick.Graphics;

public class StormSingleAttackTwo extends Attack {
    public StormSingleAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.STORM;
        damage = userDamageMultiplier * (TIER_TWO_DAMAGE + userLevelBonus);
        name = "Charge Cannon";
        isAreaAttack = false;
        sheetLeft = Media.sheetSpellChargeCannon;
        sheetRight = Media.sheetSpellChargeCannon;
    }

    @Override public void render(Graphics g, boolean fromLeft, int tick, int moveTimer) {
        movingRender(g, fromLeft, tick, moveTimer);
    }
}
