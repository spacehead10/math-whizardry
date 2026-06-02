package combat.attack.fire;

import combat.Elements;
import combat.attack.Attack;
import core.Media;
import org.newdawn.slick.Graphics;

public class FireSingleAttackTwo extends Attack {
    public FireSingleAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.FIRE;
        damage = userDamageMultiplier * (TIER_TWO_DAMAGE + userLevelBonus);
        name = "Flamethrower";
        isAreaAttack = false;
        sheetLeft = Media.sheetSpellFlamethrower;
        sheetRight = Media.sheetSpellFlamethrower;
    }

    @Override public void render(Graphics g, boolean fromLeft, int tick, int moveTimer) {
        movingRender(g, fromLeft, tick, moveTimer);
    }
}
