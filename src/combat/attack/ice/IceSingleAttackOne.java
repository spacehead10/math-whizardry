package combat.attack.ice;

import combat.Elements;
import combat.attack.Attack;
import core.Media;
import org.newdawn.slick.Graphics;

public class IceSingleAttackOne extends Attack {
    public IceSingleAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.ICE;
        damage = userDamageMultiplier * (TIER_ONE_DAMAGE + userLevelBonus);
        name = "Snowball";
        isAreaAttack = false;
        sheetLeft = Media.sheetSpellSnowball;
        sheetRight = Media.sheetSpellSnowball;
    }

    @Override public void render(Graphics g, boolean fromLeft, int tick, int moveTimer) {
        movingRender(g, fromLeft, tick, moveTimer);
    }
}
