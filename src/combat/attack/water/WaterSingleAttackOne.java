package combat.attack.water;

import combat.Elements;
import combat.attack.Attack;
import core.Media;
import org.newdawn.slick.Graphics;

public class WaterSingleAttackOne extends Attack {
    public WaterSingleAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.WATER;
        damage = userDamageMultiplier * (TIER_ONE_DAMAGE + userLevelBonus);
        name = "Bubble Burst";
        isAreaAttack = false;
        sheetLeft = Media.sheetSpellBubbleBurst;
        sheetRight = Media.sheetSpellBubbleBurst;
    }

    @Override public void render(Graphics g, boolean fromLeft, int tick, int moveTimer) {
        movingRender(g, fromLeft, tick, moveTimer);
    }
}
