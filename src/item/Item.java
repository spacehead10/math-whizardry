package item;

import core.Values;
import org.newdawn.slick.Image;
import java.util.List;
import java.util.ArrayList;

public class Item implements Values {
    protected Image image;
    protected int stackSize;
    protected String name;
    protected List<String> description;
    protected int cost;

    public Item() {
        description = new ArrayList<>();
    }

    public void addItem() {
        stackSize++;
    }

    public void spendItem() {
        stackSize--;
    }

    public void spendItem(int amount) {
        stackSize -= amount;
    }

    public boolean isFull() {
        return stackSize >= MAX_STACK_SIZE;
    }

    public boolean isEmpty() {
        return stackSize <= 0;
    }

    public Image getImage() {
        return image;
    }

    public int getCurSize() {
        return stackSize;
    }

    public String getName() {
        return name;
    }

    public List<String> getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }
}
