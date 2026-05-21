package button;

import core.Media;
import entities.player.Player;
import item.Item;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class ItemButton extends Button {
    private static ItemButton selectedButton;
    private Item item;
    private int index;

    public ItemButton(Item item) {
        super(0, 0, ITEM_BUTTON_WIDTH, ITEM_BUTTON_HEIGHT);
        this.item = item;
        icon = item.getImage();
    }

    @Override public void render(Graphics g, GameContainer gc) {
        if (index >= 0 && index < 9) {
            super.render(g, gc);
            int spacing = INVENTORY_SPACING;
            int indent = INVENTORY_SPACING;
            x = indent + (w + spacing) * (index % ITEMS_PER_INVENTORY_ROW);
            y = indent + (h + spacing) * (index / ITEMS_PER_INVENTORY_ROW);
            g.setColor(Color.white);
            Media.drawAlignedString(item.getName(), x + w / 2, y + 20, Media.CENTER, Media.TOP, Media.defaultFontMedium, g);
            if (isSelected()) {
                g.setColor(new Color(255, 255, 255, 64));
                g.fillRect(x, y, w, h);
            }
            if (item == Player.getInventory().getEquippedRelic() || item == Player.getInventory().getEquippedWand()) {
                g.setColor(Color.green);
                g.drawRect(x - 1, y - 1, w + 2, h + 2);
                g.fill(new Circle(x + 40, y + h - 40, 20));
            }
            if (item.getCurSize() > 1) {
                g.setColor(Color.white);
                Media.drawAlignedString("" + item.getCurSize(), x + w - 20, y + h - 20, Media.RIGHT, Media.BOTTOM, Media.defaultFontMedium, g);
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

    public static ItemButton getSelectedButton() {
        return selectedButton;
    }

    public Item getItem() {
        return item;
    }

    public boolean isSelected() {
        return (selectedButton == this);
    }

    public static boolean hasSelectedButton() {
        return (selectedButton != null);
    }
}
