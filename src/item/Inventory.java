package item;

import button.ItemButton;
import button.ItemEquipButton;
import core.Media;
import entities.player.Player;
import item.currency.Gold;
import item.relic.Relic;
import item.wand.Wand;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import world.World;

import java.util.List;
import java.util.ArrayList;
import static core.Main.getScreenWidth;
import static core.Main.getScreenHeight;

public class Inventory {
    private List<ItemButton> itemButtons;
    private ItemEquipButton equipButton;
    private Relic equippedRelic;
    private Wand equippedWand;

    public Inventory() {
        itemButtons = new ArrayList<>();
        equipButton = new ItemEquipButton(this);
        clearSelection();
    }

    public void render(Graphics g, GameContainer gc) {
        g.setColor(Color.white);
        g.fillRect(10, 10, getScreenWidth() - 20, getScreenHeight() - 20);
        for (ItemButton i : itemButtons) {
            i.render(g, gc);
        }
        if (hasSelectedGear()) {
        }

        float x = getScreenWidth() * 0.6f;
        float y = 50;
        float spacing = Media.defaultFontMedium.getHeight();
        g.setColor(Color.black);
        Media.drawAlignedString("Inventory", x, y - 0.5f * spacing, Media.LEFT, Media.TOP, Media.defaultFontLarge, g);
        Media.drawAlignedString("Your gear:", x, y + spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
        if (equippedRelic != null) {
            Media.drawAlignedString(equippedRelic.getName(), x, y + 2 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
        }
        else {
            Media.drawAlignedString("[No Relic equipped]", x, y + 2 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
        }
        if (equippedWand != null) {
            Media.drawAlignedString(equippedWand.getName(), x, y + 3 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
        }
        else {
            Media.drawAlignedString("[No Wand equipped]", x, y + 3 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
        }

        y = y + 6 * spacing;
        g.drawRect(x, y, getScreenWidth() - 40 - x, getScreenHeight() - 40 - y);
        Media.drawAlignedString("Selected item info", x + (getScreenWidth() - 40 - x) / 2, y, Media.CENTER, Media.TOP, Media.defaultFontLarge, g);
        if (hasSelectedItem()) {
            x += 20;
            y += Media.defaultFontLarge.getHeight();
            Item it = getSelectedItem();
            Media.drawAlignedString(it.getName(), x, y, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("Amount you own: " + it.getCurSize(), x, y + spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);

            int position = 2;
            float xIndent = 30;
            if (it.getDescription() != null && !it.getDescription().isEmpty()) {
                Media.drawAlignedString("Description: ", x, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                position++;
                for (String d : it.getDescription()) {
                    Media.drawAlignedString(d, x + xIndent, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                    position++;
                }
            }

            if (it instanceof Relic) {
                Media.drawAlignedString("Spell: " + ((Relic) it).getAttackDescription(), x, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                position++;
            }
            else if (it instanceof Wand) {
                Media.drawAlignedString("Damage bonus: " + Math.round((((Wand) it).getWandDamageMultiplier() - 1) * 100) + "%", x, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                position++;
                Media.drawAlignedString("Spell: " + ((Wand) it).getAttackDescription(), x, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                position++;
            }

            if (it == equippedRelic || it == equippedWand) {
                position++;
                Media.drawAlignedString("Currently equipped", x, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            }
            else if (hasSelectedGear()) {
                position++;
                equipButton.updatePosition((int) x, (int) (y + position * spacing));
                equipButton.render(g, gc);
            }
        }
    }

    public void mousePressed(int button, int x, int y) {
        for (ItemButton i : itemButtons) {
            i.mousePressed(x, y);
        }
        if (hasSelectedUnequippedGear()) {
            equipButton.mousePressed(x, y);
        }
    }

    public void cleanup() {
        for (int i = 0; i < itemButtons.size(); i++) {
            itemButtons.get(i).setIndex(i);
        }
        for (int i = 0; i < itemButtons.size(); i++) {
            if (itemButtons.get(i).getItem().isEmpty()) {
                if (itemButtons.get(i).isSelected()) {
                    clearSelection();
                }
                itemButtons.remove(i);
                i--;
            }
        }
    }

    public void clearSelection() {
        ItemButton.clearSelection();
    }

    public void addItem(Class<? extends Item> c, int quantity) {
        forQuantity: for (int i = 0; i < quantity; i++) {
            for (ItemButton b : itemButtons) {
                Item item = b.getItem();
                if (!item.isFull() && c.isInstance(item)) {
                    item.addItem();
                    continue forQuantity;
                }
            }

            try {
                Item item = c.getDeclaredConstructor().newInstance();
                item.addItem();
                itemButtons.add(new ItemButton(item));
                if (item instanceof Relic && equippedRelic == null) {
                    setRelic((Relic) item);
                }
                if (item instanceof Wand && equippedWand == null) {
                    setWand((Wand) item);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void clearItems() {
        itemButtons.clear();
        setRelic(null);
        setWand(null);
    }

    public void setRelic(Relic relic) {
        equippedRelic = relic;
        World.getPlayer().assignAttacks();
    }

    public void setWand(Wand wand) {
        equippedWand = wand;
        World.getPlayer().assignAttacks();
    }

    public void spendGold(int cost) {
        for (Item i : getItems()) {
            if (i instanceof Gold) {
                i.spendItem(cost);
                break;
            }
        }
    }

    public void equip() {
        if (getSelectedItem() instanceof Relic && getSelectedItem() != equippedRelic) {
            setRelic((Relic) getSelectedItem());
        }
        else if (getSelectedItem() instanceof Wand && getSelectedItem() != equippedWand) {
            setWand((Wand) getSelectedItem());
        }
    }

    public List<Item> getItems() {
        List<Item> currentItems = new ArrayList<>();
        for (ItemButton b : itemButtons) {
            currentItems.add(b.getItem());
        }
        return currentItems;
    }

    public Item getSelectedItem() {
        if (!hasSelectedItem()) {
            return null;
        }
        return ItemButton.getSelectedButton().getItem();
    }

    public Relic getEquippedRelic() {
        return equippedRelic;
    }

    public Wand getEquippedWand() {
        return equippedWand;
    }

    public boolean hasSelectedItem() {
        return ItemButton.hasSelectedButton();
    }

    public boolean inBounds(int index) {
        return (index >= 0 && index < itemButtons.size());
    }

    public boolean hasSelectedGear() {
        return hasSelectedItem() && (getSelectedItem() instanceof Wand || getSelectedItem() instanceof Relic);
    }

    public boolean hasSelectedUnequippedGear() {
        return hasSelectedGear() && !(getSelectedItem() == equippedRelic || getSelectedItem() == equippedWand);
    }

    public boolean hasItem(Item item) {
        for (Item i : getItems()) {
            if (item.getClass().equals(i.getClass())) {
                return true;
            }
        }
        return false;
    }

    public int countGold() {
        int count = 0;
        for (Item i : getItems()) {
            if (i instanceof Gold) {
                count += i.getCurSize();
            }
        }
        return count;
    }
}
