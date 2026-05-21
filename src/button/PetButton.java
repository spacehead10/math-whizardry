package button;

import core.Media;
import entities.creature.Creature;
import entities.player.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class PetButton extends Button {
    private static PetButton selectedButton;
    private Creature pet;
    private int index;

    public PetButton(Creature pet) {
        super(0, 0, PET_BUTTON_WIDTH, PET_BUTTON_HEIGHT);
        this.pet = pet;
        if (pet.getSheetLeft() != null) {
            icon = pet.getSheetLeft().getSprite(0, 0).getScaledCopy(0.6f);
        }
        else if (pet.getSheetRight() != null) {
            icon = pet.getSheetRight().getSprite(0, 0).getScaledCopy(0.6f);
        }
    }

    @Override public void render(Graphics g, GameContainer gc) {
        if (index >= 0 && index < 9) {
            int spacing = PET_SELECTOR_SPACING;
            int indent = PET_SELECTOR_SPACING;
            x = indent + (w + spacing) * (index % PETS_PER_SELECTOR_ROW);
            y = indent + (h + spacing) * (index / PETS_PER_SELECTOR_ROW);

            super.render(g, gc);
            g.setColor(Color.white);
            Media.drawAlignedString(pet.getName(), x + w / 2, y + 20, Media.CENTER, Media.TOP, Media.defaultFontMedium, g);
            if (isSelected()) {
                g.setColor(new Color(255, 255, 255, 64));
                g.fillRect(x, y, w, h);
            }
            if (pet == Player.getPetSelector().getPetInTeam(0) || pet == Player.getPetSelector().getPetInTeam(1)) {
                g.setColor(Color.green);
                g.drawRect(x - 1, y - 1, w + 2, h + 2);
                g.fill(new Circle(x + 40, y + h - 40, 20));
            }
            g.setColor(Color.white);
            Media.drawAlignedString("Level " + pet.getLevel(), x + w - 20, y + h - 20, Media.RIGHT, Media.BOTTOM, Media.defaultFontMedium, g);
        }
    }

    @Override public void onClick() {
        if (index >= 0 && index < 9) {
            if (isSelected()) {
                clearSelection();
            }
            else {
                select();
            }
        }
    }

    public static void clearSelection() {
        selectedButton = null;
    }

    public void select() {
        selectedButton = this;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static PetButton getSelectedButton() {
        return selectedButton;
    }

    public Creature getPet() {
        return pet;
    }

    public boolean isSelected() {
        return selectedButton == this;
    }

    public static boolean hasSelectedButton() {
        return selectedButton != null;
    }
}
