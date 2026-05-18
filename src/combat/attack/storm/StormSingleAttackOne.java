package combat.attack.storm;

import combat.Elements;
import combat.attack.Attack;
import core.Media;
import org.newdawn.slick.Graphics;

public class StormSingleAttackOne extends Attack {
    public StormSingleAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.STORM;
        damage = userDamageMultiplier * (TIER_ONE_DAMAGE + userLevelBonus);
        name = "Spark Shock";
        isAreaAttack = false;
        sheetLeft = Media.sheetSpellSparkShock;
        sheetRight = Media.sheetSpellSparkShock;
    }

    @Override public void render(Graphics g, boolean fromLeft, int tick, int moveTimer) {
        movingRender(g, fromLeft, tick, moveTimer);
    }
}
