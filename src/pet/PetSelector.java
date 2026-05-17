package pet;

import button.PetButton;
import button.PetEquipButton;
import core.Media;
import core.Values;
import entities.Entity;
import entities.creature.Creature;
import entities.player.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import world.World;

import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;

import static core.Main.getScreenWidth;
import static core.Main.getScreenHeight;

public class PetSelector implements Values {
    private List<PetButton> petButtons;
    private PetEquipButton equipButtonOne;
    private PetEquipButton equipButtonTwo;
    private Creature[] petsInTeam;

    public PetSelector() {
        petButtons = new ArrayList<>();
        equipButtonOne = new PetEquipButton(this, 0);
        equipButtonTwo = new PetEquipButton(this, 1);
        petsInTeam = new Creature[2];
        clearSelection();
    }

    public void render(Graphics g, GameContainer gc) {
        g.setColor(Color.white);
        g.fillRect(10, 10, getScreenWidth() - 20, getScreenHeight() - 20);
        for (int i = 0; i < petButtons.size(); i++) {
            petButtons.get(i).render(g, gc);
        }

        float x = getScreenWidth() * 0.6f;
        float y = 50;
        float spacing = Media.defaultFontMedium.getHeight();
        int position = 1;
        g.setColor(Color.black);
        Media.drawAlignedString("Team Selector", x, y - 0.5f * spacing, Media.LEFT, Media.TOP, Media.defaultFontLarge, g);
        Media.drawAlignedString("Your team:", x, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
        position++;
        Media.drawAlignedString(World.getPlayer().getName() + " (Level " + World.getPlayer().getLevel() + ")", x, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
        position++;
        if (petsInTeam[0] != null) {
            Media.drawAlignedString(petsInTeam[0].getName() + " (Level " + petsInTeam[0].getLevel() + ")", x, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            position++;
        }
        if (petsInTeam[1] != null) {
            Media.drawAlignedString(petsInTeam[1].getName() + " (Level " + petsInTeam[1].getLevel() + ")", x, y + position * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
        }

        DecimalFormat df = new DecimalFormat("0.###");
        if (hasSelectedPet()) {
            y = y + 6 * spacing;
            g.drawRect(x, y, getScreenWidth() - 40 - x, getScreenHeight() - 40 - y);
            Media.drawAlignedString("Selected pet info", x + (getScreenWidth() - 40 - x) / 2, y, Media.CENTER, Media.TOP, Media.defaultFontLarge, g);
            x += 20;
            y += Media.defaultFontLarge.getHeight();
            Creature p = getSelectedPet();
            Media.drawAlignedString(p.getName(), x, y, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("Element: " + p.getNameOfElement(), x, y + spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("Level " + p.getLevel(), x, y + 2 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("XP: " + p.getXPRatio(), x, y + 3 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("Health: " + df.format(p.getMaxHealth()), x, y + 4 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("Power: " + df.format(p.getAttack(0).getDamage()), x, y + 5 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);

            Media.drawAlignedString("Spells: ", x, y + 6 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            float xIndent = 30;
            for (int i = 0; i < 4; i++) {
                if (p.getAttack(i) == null) {
                    if (i == 2) {
                        Media.drawAlignedString("[Unlocks at level " + CREATURE_ALT_SINGLE_UNLOCK + "]", x + xIndent, y + (7 + i) * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                    }
                    else if (i == 3) {
                        Media.drawAlignedString("[Unlocks at level " + CREATURE_ALT_AREA_UNLOCK + "]", x + xIndent, y + (7 + i) * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                    }
                }
                else {
                    Media.drawAlignedString(p.getAttack(i).getName() + " (" + p.getAttack(i).getNameOfElement() + ")", x + xIndent, y + (7 + i) * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                }
            }

            if (p == getPetInTeam(0) || p == getPetInTeam(1)) {
                Media.drawAlignedString("Currently on your team", x, y + 12 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            }
            else {
                equipButtonOne.updatePosition((int) x, (int) (y + 12 * spacing));
                equipButtonOne.render(g, gc);
                equipButtonTwo.updatePosition((int) x, (int) (y + 12 * spacing + EQUIP_BUTTON_HEIGHT + 10));
                equipButtonTwo.render(g, gc);
            }
        }
        else {
            y = y + 6 * spacing;
            g.drawRect(x, y, getScreenWidth() - 40 - x, getScreenHeight() - 40 - y);
            Media.drawAlignedString("Wizard info", x + (getScreenWidth() - 40 - x) / 2, y, Media.CENTER, Media.TOP, Media.defaultFontLarge, g);
            x += 20;
            y += Media.defaultFontLarge.getHeight();
            Player p = World.getPlayer();
            Media.drawAlignedString(p.getName(), x, y, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("Element: " + p.getNameOfElement(), x, y + spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("Level " + p.getLevel(), x, y + 2 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("XP: " + p.getXPRatio(), x, y + 3 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("Health: " + df.format(p.getMaxHealth()), x, y + 4 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString("Power: " + df.format(p.getAttack(0).getDamage()), x, y + 5 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);

            Media.drawAlignedString("Spells: ", x, y + 6 * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
            float xIndent = 30;
            for (int i = 0; i < 4; i++) {
                if (p.getAttack(i) == null) {
                    if (i == 2) {
                        Media.drawAlignedString("[Equip a Relic to unlock]", x + xIndent, y + (7 + i) * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                    }
                    else if (i == 3) {
                        Media.drawAlignedString("[Equip a Wand to unlock]", x + xIndent, y + (7 + i) * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                    }
                }
                else {
                    Media.drawAlignedString(p.getAttack(i).getName() + " (" + p.getAttack(i).getNameOfElement() + ")", x + xIndent, y + (7 + i) * spacing, Media.LEFT, Media.TOP, Media.defaultFontMedium, g);
                }
            }
        }
    }

    public void mousePressed(int button, int x, int y) {
        for (PetButton p : petButtons) {
            p.mousePressed(x, y);
        }
        if (hasSelectedUnequippedPet()) {
            equipButtonOne.mousePressed(x, y);
            equipButtonTwo.mousePressed(x, y);
        }
    }

    public void cleanup() {
        for (int i = 0; i < petButtons.size(); i++) {
            petButtons.get(i).setIndex(i);
        }
    }

    public void clearSelection() {
        PetButton.clearSelection();
    }

    public void addPet(Entity e) {
        try {
            Creature pet = (Creature) e;
            petButtons.add(new PetButton(pet));
            if (petsInTeam[0] == null) {
                setPetInTeam(pet, 0);
            }
            else if (petsInTeam[1] == null) {
                setPetInTeam(pet, 1);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearPets() {
        petButtons.clear();
        setPetInTeam(null, 0);
        setPetInTeam(null, 1);
    }

    public void setPetInTeam(Creature pet, int index) {
        if (!(index == 0 || index == 1)) {
            return;
        }
        petsInTeam[index] = pet;
    }

    public void equip(int index) {
        if (hasSelectedUnequippedPet()) {
            setPetInTeam(getSelectedPet(), index);
        }
    }

    public List<Creature> getPets() {
        List<Creature> currentPets = new ArrayList<>();
        for (PetButton b : petButtons) {
            currentPets.add(b.getPet());
        }
        return currentPets;
    }

    public Creature getPetInTeam(int index) {
        if (!(index == 0 || index == 1)) {
            return null;
        }
        return petsInTeam[index];
    }

    public Creature getSelectedPet() {
        if (!hasSelectedPet()) {
            return null;
        }
        return PetButton.getSelectedButton().getPet();
    }

    public boolean hasSelectedPet() {
        return PetButton.hasSelectedButton();
    }

    public boolean inBounds(int index) {
        return (index >= 0 && index < petButtons.size());
    }

    public boolean hasSelectedUnequippedPet() {
        return hasSelectedPet() && !(getSelectedPet() == getPetInTeam(0) || getSelectedPet() == getPetInTeam(1));
    }
}
