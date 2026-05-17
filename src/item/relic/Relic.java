package item.relic;

import combat.attack.Attack;
import item.Item;

public abstract class Relic extends Item {
    protected Class<? extends Attack> attack;

    public Relic() {
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
}
