package button;

import entities.player.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import shop.Shop;

public class ShopBuyButton extends Button {
    private Shop shop;

    public ShopBuyButton(Shop shop) {
        super(0, 0, EQUIP_BUTTON_WIDTH, EQUIP_BUTTON_HEIGHT);
        this.shop = shop;
        text = "Buy";
    }

    @Override public void render(Graphics g, GameContainer gc) {
        if (shop.getSelectedItem() != null) {
            if (Player.getInventory().countGold() >= shop.getSelectedItem().getCost()) {
                text = "Buy";
            }
            else {
                text = "Cannot afford";
            }
        }
        else if (shop.getSelectedPet() != null) {
            if (Player.getInventory().countGold() >= shop.getSelectedPet().getCost()) {
                text = "Buy";
            }
            else {
                text = "Cannot afford";
            }
        }
        super.render(g, gc);
    }

    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override public void onClick() {
        shop.buy();
    }
}
