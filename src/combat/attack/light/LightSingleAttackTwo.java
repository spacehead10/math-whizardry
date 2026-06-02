package combat.attack.light;

import combat.Elements;
import combat.attack.Attack;
import core.Media;
import org.newdawn.slick.Graphics;

public class LightSingleAttackTwo extends Attack {
    public LightSingleAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.LIGHT;
        damage = userDamageMultiplier * (TIER_TWO_DAMAGE + userLevelBonus);
        name = "Radiant Blast";
        isAreaAttack = false;
        sheetLeft = Media.sheetSpellRadiantBlast;
        sheetRight = Media.sheetSpellRadiantBlast;
    }

    @Override public void render(Graphics g, boolean fromLeft, int tick, int moveTimer) {
        movingRender(g, fromLeft, tick, moveTimer);
    }
}
