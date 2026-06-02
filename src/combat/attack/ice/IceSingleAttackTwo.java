package combat.attack.ice;

import combat.Elements;
import combat.attack.Attack;
import core.Media;
import org.newdawn.slick.Graphics;

public class IceSingleAttackTwo extends Attack {
    public IceSingleAttackTwo(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.ICE;
        damage = userDamageMultiplier * (TIER_TWO_DAMAGE + userLevelBonus);
        name = "Frigid Impale";
        isAreaAttack = false;
        sheetLeft = Media.sheetSpellFrigidImpaleLeft;
        sheetRight = Media.sheetSpellFrigidImpaleRight;
    }

    @Override public void render(Graphics g, boolean fromLeft, int tick, int moveTimer) {
        movingRender(g, fromLeft, tick, moveTimer);
    }
}
