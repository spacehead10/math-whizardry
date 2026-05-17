package combat.attack;

import combat.Elements;
import combat.Team;
import core.Values;

public abstract class Attack implements Values {
    protected Elements element;
    protected double damage;
    protected String name;
    protected boolean isAreaAttack;

    public void use(Team t, boolean missed) {
        t.takeDamage(this, isAreaAttack, missed);
    }

    public Elements getElement() {
        return element;
    }

    public String getName() {
        return name;
    }

    public String getNameOfElement() {
        return switch (element) {
            case LIGHT -> "Light";
            case FIRE -> "Fire";
            case WATER -> "Water";
            case STORM -> "Storm";
            case ICE -> "Ice";
            case EARTH -> "Earth";
        };
    }

    public double getDamage() {
        return damage;
    }
}
