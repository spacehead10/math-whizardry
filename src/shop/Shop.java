package shop;

import button.ShopButton;
import button.ShopBuyButton;
import core.Media;
import core.Values;
import entities.creature.Creature;
import entities.creature.earth.GardenLutin;
import entities.creature.fire.FlameLutin;
import entities.creature.fire.Terrapyre;
import entities.creature.ice.FrostLutin;
import entities.creature.ice.Frostbite;
import entities.creature.storm.CloudLutin;
import entities.creature.storm.Voltiger;
import entities.creature.water.RiverLutin;
import entities.player.Player;
import item.Item;
import item.relic.Relic;
import item.wand.Wand;
import item.wand.storm.MysteriousFan;
import item.wand.water.LimestoneWand;
import item.wand.water.Trident;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;

import static core.Main.getScreenHeight;
import static core.Main.getScreenWidth;

public class Shop implements Values {
    private List<ShopButton> shopButtons;
    private ShopBuyButton buyButton;
    private int scrollOffset;

    public Shop() {
        shopButtons = new ArrayList<>();
        clearSelection();
        shopButtons.add(new ShopButton(new MysteriousFan()));
        shopButtons.add(new ShopButton(new LimestoneWand()));
        shopButtons.add(new ShopButton(new Trident()));
        shopButtons.add(new ShopButton(new Terrapyre()));
        shopButtons.add(new ShopButton(new Frostbite()));
        shopButtons.add(new ShopButton(new Voltiger()));
        shopButtons.add(new ShopButton(new FlameLutin()));
        shopButtons.add(new ShopButton(new FrostLutin()));
        shopButtons.add(new ShopButton(new CloudLutin()));
        shopButtons.add(new ShopButton(new RiverLutin()));
        shopButtons.add(new ShopButton(new GardenLutin()));
        buyButton = new ShopBuyButton(this);
        scrollOffset = 0;
    }

    public void render(Graphics g, GameContainer gc) {
        g.setColor(Color.white);
        g.fillRect(10, 10, getScreenWidth() - 20, getScreenHeight() - 20);
        for (ShopButton s : shopButtons) {
            s.render(g, gc);
        }

        float x = getScreenWidth() * 0.6f;
        float y = 50;
        float spacing = Media.defaultFontMedium.getHeight();
        g.setColor(Color.black);
        Media.drawAlignedString("Shop", x, y - 0.5f * spacing, Media.LEFT, Media.TOP, Media.defaultFontLarge, g);
        int countGold = Player.getInventory().countGold();
        Media.drawAlignedString("Gold: " + countGold, x, y + spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);

        y = y + 6 * spacing;
        g.drawRect(x, y, getScreenWidth() - 40 - x, getScreenHeight() - 40 - y);
        Media.drawAlignedString("Selection info", x + (getScreenWidth() - 40 - x) / 2, y, Media.CENTER, Media.TOP, Media.defaultFontLarge, g);
        if (getSelectedItem() != null) { //not hasSelected() because this is specifically for items, not pets
            x += 20;
            y += Media.defaultFontLarge.getHeight();
            Item selectedItem = getSelectedItem();
            Media.drawAlignedString(selectedItem.getName(), x, y, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("Cost: " + selectedItem.getCost(), x, y + spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);

            int position = 2;
            float xIndent = 30;
            if (selectedItem.getDescription() != null && !selectedItem.getDescription().isEmpty()) {
                Media.drawAlignedString("Description: ", x, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                position++;
                for (String d : selectedItem.getDescription()) {
                    Media.drawAlignedString(d, x + xIndent, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                    position++;
                }
            }

            if (selectedItem instanceof Relic) {
                Media.drawAlignedString("Spell: " + ((Relic) selectedItem).getAttackDescription(), x, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                position++;
            }
            else if (selectedItem instanceof Wand) {
                Media.drawAlignedString("Damage bonus: " + Math.round((((Wand) selectedItem).getWandDamageMultiplier() - 1) * 100) + "%", x, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                position++;
                Media.drawAlignedString("Spell: " + ((Wand) selectedItem).getAttackDescription(), x, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                position++;
            }

            int ownedQuantity = 0;
            for (Item itemInInventory : Player.getInventory().getItems()) {
                if (selectedItem.getClass().equals(itemInInventory.getClass())) {
                    ownedQuantity += itemInInventory.getCurSize();
                }
            }
            if (selectedItem instanceof Wand) {
                if (ownedQuantity > 0) {
                    position++;
                    Media.drawAlignedString("Already owned!", x, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                }
                else {
                    position++;
                    buyButton.updatePosition((int) x, (int) (y + position * spacing));
                    buyButton.render(g, gc);
                }
            }
            else {
                position++;
                Media.drawAlignedString("You own: " + ownedQuantity, x, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                position++;
                buyButton.updatePosition((int) x, (int) (y + position * spacing));
                buyButton.render(g, gc);
            }
        }
        else if (getSelectedPet() != null) {
            DecimalFormat df = new DecimalFormat("0.###");
            x += 20;
            y += Media.defaultFontLarge.getHeight();
            Creature selectedPet = getSelectedPet();
            Media.drawAlignedString(selectedPet.getName(), x, y, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("Element: " + selectedPet.getNameOfElement(), x, y + spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("Level " + selectedPet.getLevel(), x, y + 2 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("Health: " + df.format(selectedPet.getMaxHealth()), x, y + 3 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("Power: " + df.format(selectedPet.getAttack(0).getDamage()), x, y + 4 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);

            Media.drawAlignedString("Spells: ", x, y + 5 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            float xIndent = 30;
            for (int i = 0; i < 4; i++) {
                if (selectedPet.getAttack(i) == null) {
                    if (i == 2) {
                        Media.drawAlignedString("[Unlocks at level " + CREATURE_ALT_SINGLE_UNLOCK + "]", x + xIndent, y + (6 + i) * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                    }
                    else if (i == 3) {
                        Media.drawAlignedString("[Unlocks at level " + CREATURE_ALT_AREA_UNLOCK + "]", x + xIndent, y + (6 + i) * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                    }
                }
                else {
                    Media.drawAlignedString(selectedPet.getAttack(i).getName() + " (" + selectedPet.getAttack(i).getNameOfElement() + ")", x + xIndent, y + (6 + i) * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                }
            }

            int ownedQuantity = 0;
            for (Creature ownedPet : Player.getPetSelector().getPets()) {
                if (selectedPet.getClass().equals(ownedPet.getClass())) {
                    ownedQuantity++;
                }
            }
            Media.drawAlignedString("You own: " + ownedQuantity, x, y + 11 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            buyButton.updatePosition((int) x, (int) (y + 12 * spacing));
            buyButton.render(g, gc);
        }

        if (shopButtons.size() > 9) {
            g.setColor(Color.black);
            Media.drawAlignedString("Use scroll wheel to view all.", 30, 9, Media.LEFT, Media.TOP, Media.defaultFontSmall, g);
        }
    }

    public void mousePressed(int button, int x, int y) {
        for (ShopButton s : shopButtons) {
            s.mousePressed(x, y);
        }
        int ownedQuantity = 0;
        if (getSelectedItem() != null) {
            for (Item itemInInventory : Player.getInventory().getItems()) {
                if (getSelectedItem().getClass().equals(itemInInventory.getClass())) {
                    ownedQuantity += itemInInventory.getCurSize();
                }
            }
        }
        if (hasSelected() && !(getSelectedItem() instanceof Wand && ownedQuantity > 0)) {
            buyButton.mousePressed(x, y);
        }
    }

    public void mouseWheelMoved(int change) {
        if (shopButtons.size() > 9) {
            if (change < 0) { //scroll down
                if (scrollOffset > -((shopButtons.size() - 9) / 3 + 1)) {
                    scrollOffset--;
                }
            }
            else { //scroll up
                if (scrollOffset < 0) {
                    scrollOffset++;
                }
            }
        }
    }

    public void cleanup() {
        for (ShopButton s : shopButtons) {
            s.updatePetLevel();
        }
        for (int i = 0; i < shopButtons.size(); i++) {
            shopButtons.get(i).setIndex(i + 3 * scrollOffset);
        }
        Player.getInventory().cleanup();
    }

    public void buyItem(Item item) {
        int countGold = Player.getInventory().countGold();

        if (countGold >= item.getCost()) {
            if (item instanceof Wand && Player.getInventory().hasItem(item)) {
                return;
            }

            Player.getInventory().addItem(item.getClass(), 1);
            Player.getInventory().spendGold(item.getCost());
            Media.sfxBuy.play();
        }
        else {
            Media.sfxInvalidAction.play();
        }
    }

    public void buyPet(Creature pet) {
        int countGold = Player.getInventory().countGold();

        if (countGold >= pet.getCost()) {
            try {
                Creature copyOfPet; //this is necessary because otherwise, updates to the shop's stored "pet" (i.e. level updates) will also affect the actual pet you've bought
                Class<? extends Creature> petClass = pet.getClass();
                copyOfPet = petClass.getDeclaredConstructor().newInstance();
                Player.getPetSelector().addPet(copyOfPet);
                Player.getInventory().spendGold(pet.getCost());
                Media.sfxBuy.play();
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("\u001b[31mError in attempting to buy a pet.\u001b[0m");
            }
        }
        else {
            Media.sfxInvalidAction.play();
        }
    }

    public void buy() {
        if (hasSelected()) {
            if (getSelectedItem() != null) {
                buyItem(getSelectedItem());
            }
            if (getSelectedPet() != null) {
                buyPet(getSelectedPet());
            }
        }
    }

    public void clearSelection() {
        ShopButton.clearSelection();
    }

    public Item getSelectedItem() {
        if (!hasSelected()) {
            return null;
        }
        return ShopButton.getSelectedButton().getItem();
    }

    public Creature getSelectedPet() {
        if (!hasSelected()) {
            return null;
        }
        return ShopButton.getSelectedButton().getPet();
    }

    public boolean hasSelected() {
        return ShopButton.hasSelectedButton();
    }
}
