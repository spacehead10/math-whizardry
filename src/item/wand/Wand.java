package item.wand;

import combat.attack.Attack;
import item.Item;

public abstract class Wand extends Item {
    protected Class<? extends Attack> attack;
    protected double wandDamageMultiplier;

    public Wand() {
        super();
    }

    public Class<? extends Attack> getAttack() {
        return attack;
    }

    public String getAttackDescription() {
        String attackDescription = "";
        try {
            Attack a = attack.getDeclaredConstructor(double.class, double.class).newInstance(0, 0);
            attackDescription = a.getName() + " (" + a.getNameOfElement() + ")";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return attackDescription;
    }

    public double getWandDamageMultiplier() {
        return wandDamageMultiplier;
    }
}
