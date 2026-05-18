package combat.attack.light;

import combat.Elements;
import combat.attack.Attack;
import core.Media;
import org.newdawn.slick.Graphics;

public class LightSingleAttackOne extends Attack {
    public LightSingleAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.LIGHT;
        damage = userDamageMultiplier * (TIER_ONE_DAMAGE + userLevelBonus);
        name = "Orb";
        isAreaAttack = false;
        sheetLeft = Media.sheetSpellOrb;
        sheetRight = Media.sheetSpellOrb;
    }

    @Override public void render(Graphics g, boolean fromLeft, int tick, int moveTimer) {
        movingRender(g, fromLeft, tick, moveTimer);
    }
}
