package item.currency;

import core.Media;

public class Gold extends Currency {
    public Gold() {
        super();
        name = "Gold";
        description.add("Gold can be spent in the shop.");
        image = Media.imgGold.getScaledCopy(0.3f);
    }
}
