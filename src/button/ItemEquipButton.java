package button;

import item.Inventory;

public class ItemEquipButton extends Button {
    private Inventory inventory;

    public ItemEquipButton(Inventory inventory) {
        super(0, 0, EQUIP_BUTTON_WIDTH, EQUIP_BUTTON_HEIGHT);
        this.inventory = inventory;
        text = "Equip item";
    }

    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override public void onClick() {
        inventory.equip();
    }
}
