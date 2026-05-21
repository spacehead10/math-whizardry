package button;

import core.Media;
import entities.creature.Creature;
import entities.player.Player;
import item.Item;
import item.wand.Wand;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import shop.Shop;

public class ShopButton extends Button {
    private Item item;
    private Creature pet;
    private static ShopButton selectedButton;
    private int index;

    public ShopButton(Item item) {
        super(0, 0, ITEM_BUTTON_WIDTH, ITEM_BUTTON_HEIGHT);
        this.item = item;
        icon = item.getImage();
    }

    public ShopButton(Creature pet) {
        super(0, 0, ITEM_BUTTON_WIDTH, ITEM_BUTTON_HEIGHT);
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
            super.render(g, gc);
            int spacing = INVENTORY_SPACING;
            int indent = INVENTORY_SPACING;
            x = indent + (w + spacing) * (index % ITEMS_PER_INVENTORY_ROW);
            y = indent + (h + spacing) * (index / ITEMS_PER_INVENTORY_ROW);
            g.setColor(Color.white);
            if (item != null) {
                Media.drawAlignedString(item.getName(), x + w / 2, y + 20, Media.CENTER, Media.TOP, Media.defaultFontMedium, g);
            }
            else if (pet != null) {
                Media.drawAlignedString(pet.getName(), x + w / 2, y + 20, Media.CENTER, Media.TOP, Media.defaultFontMedium, g);
            }
            if (isSelected()) {
                g.setColor(new Color(255, 255, 255, 64));
                g.fillRect(x, y, w, h);
            }
            g.setColor(Color.white);
            if (item != null) {
                Media.drawAlignedString("Cost: " + item.getCost(), x + w - 20, y + h - 20, Media.RIGHT, Media.BOTTOM, Media.defaultFontMedium, g);
            }
            else if (pet != null) {
                Media.drawAlignedString("Cost: " + pet.getCost(), x + w - 20, y + h - 20, Media.RIGHT, Media.BOTTOM, Media.defaultFontMedium, g);
            }
            if (item instanceof Wand && Player.getInventory().hasItem(item)) {
                g.setColor(new Color(0, 0, 0, 127));
                g.fillRect(x, y, w, h);
            }
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

    public void updatePetLevel() {
        if (pet != null) {
            if (pet.getLevel() < Creature.getDefaultLevel()) {
                Class<? extends Creature> petClass = pet.getClass();
                try {
                    pet = petClass.getDeclaredConstructor().newInstance();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("\u001b[31mError in shop button for a pet attempting to update its pet's level.\u001b[0m");
                }
            }
        }
    }

    public static ShopButton getSelectedButton() {
        return selectedButton;
    }

    public Item getItem() {
        return item;
    }

    public Creature getPet() {
        return pet;
    }

    public boolean isSelected() {
        return (selectedButton == this);
    }

    public static boolean hasSelectedButton() {
        return (selectedButton != null);
    }
}
