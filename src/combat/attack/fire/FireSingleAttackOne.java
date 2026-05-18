package combat.attack.fire;

import combat.Elements;
import combat.attack.Attack;
import core.Media;
import org.newdawn.slick.Graphics;

public class FireSingleAttackOne extends Attack {
    public FireSingleAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.FIRE;
        damage = userDamageMultiplier * (TIER_ONE_DAMAGE + userLevelBonus);
        name = "Fireball";
        isAreaAttack = false;
        sheetLeft = Media.sheetSpellFireball;
        sheetRight = Media.sheetSpellFireball;
    }

    @Override public void render(Graphics g, boolean fromLeft, int tick, int moveTimer) {
        movingRender(g, fromLeft, tick, moveTimer);
    }
}
