package combat.attack.water;

import combat.Elements;
import combat.attack.Attack;
import core.Media;
import org.newdawn.slick.Graphics;

public class WaterSingleAttackTwo extends Attack {
    public WaterSingleAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.WATER;
        damage = userDamageMultiplier * (TIER_TWO_DAMAGE + userLevelBonus);
        name = "Super Soak";
        isAreaAttack = false;
        sheetLeft = Media.sheetSpellSuperSoakLeft;
        sheetRight = Media.sheetSpellSuperSoakRight;
    }

    @Override public void render(Graphics g, boolean fromLeft, int tick, int moveTimer) {
        movingRender(g, fromLeft, tick, moveTimer);
    }
}
