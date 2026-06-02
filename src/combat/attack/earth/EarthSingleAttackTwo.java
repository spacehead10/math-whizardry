package combat.attack.earth;

import combat.Elements;
import combat.attack.Attack;
import core.Media;
import org.newdawn.slick.Graphics;

public class EarthSingleAttackTwo extends Attack {
    public EarthSingleAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.EARTH;
        damage = userDamageMultiplier * (TIER_TWO_DAMAGE + userLevelBonus);
        name = "Leaf Blade";
        isAreaAttack = false;
        sheetLeft = Media.sheetSpellLeafBladeLeft;
        sheetRight = Media.sheetSpellLeafBladeRight;
    }

    @Override public void render(Graphics g, boolean fromLeft, int tick, int moveTimer) {
        movingRender(g, fromLeft, tick, moveTimer);
    }
}
