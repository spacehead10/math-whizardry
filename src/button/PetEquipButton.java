package button;

import pet.PetSelector;

public class PetEquipButton extends Button {
    private PetSelector petSelector;
    private int index;

    public PetEquipButton(PetSelector petSelector, int index) {
        super(0, 0, EQUIP_BUTTON_WIDTH, EQUIP_BUTTON_HEIGHT);
        this.petSelector = petSelector;
        this.index = index;
        text = "Add pet to position " + (index + 1);
    }

    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override public void onClick() {
        petSelector.equip(index);
    }
}
