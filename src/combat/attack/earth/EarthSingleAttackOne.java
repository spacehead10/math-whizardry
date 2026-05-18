package combat.attack.earth;

import combat.Elements;
import combat.attack.Attack;
import core.Media;
import org.newdawn.slick.Graphics;

public class EarthSingleAttackOne extends Attack {
    public EarthSingleAttackOne(double userDamageMultiplier, double userLevelBonus) {
        element = Elements.EARTH;
        damage = userDamageMultiplier * (TIER_ONE_DAMAGE + userLevelBonus);
        name = "Thorn Prick";
        isAreaAttack = false;
        sheetLeft = Media.sheetSpellThornPrickLeft;
        sheetRight = Media.sheetSpellThornPrickRight;
    }

    @Override public void render(Graphics g, boolean fromLeft, int tick, int moveTimer) {
        movingRender(g, fromLeft, tick, moveTimer);
    }
}
