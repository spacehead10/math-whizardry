package combat.attack.light;

import combat.Elements;
import combat.attack.Attack;
import core.Media;
import org.newdawn.slick.Graphics;

public class LightSingleAttackThree extends Attack {
    public LightSingleAttackThree(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.LIGHT;
        damage = userDamageMultiplier * (TIER_THREE_DAMAGE + userLevelBonus);
        name = "Stellar Beam";
        isAreaAttack = false;
        sheetLeft = Media.sheetSpellStellarBeamLeft;
        sheetRight = Media.sheetSpellStellarBeamRight;
    }

    @Override public void render(Graphics g, boolean fromLeft, int tick, int moveTimer) {
        movingRender(g, fromLeft, tick, moveTimer);
    }
}
