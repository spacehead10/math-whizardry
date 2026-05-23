package core.state;

import combat.Battle;
import combat.Team;
import core.Media;
import core.Values;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import popup.PopupManager;
import world.World;

public class BattleState extends BasicGameState implements Values {
    private int id;

    public BattleState(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    private StateBasedGame sbg;

    private Battle battle;
    private static boolean developerMode;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.sbg = sbg;
        gc.setShowFPS(false);
        developerMode = false;
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        battle.update();
        PopupManager.update();
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        battle.render(g, gc);
        PopupManager.render(g);
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        battle = new Battle(new Team(World.getPlayer()), new Team(WorldState.getLastEnemyEncountered()), sbg);
    }

    public void leave(GameContainer gc, StateBasedGame sbg) {
        PopupManager.clear();
    }

    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_SEMICOLON:
                developerMode = !developerMode;
                break;
            default:
                battle.keyPressed(key);
        }
    }

    public void mousePressed(int button, int x, int y) {
        battle.mousePressed(button, x, y);
    }

    //only allow state classes to access this
    static void toggleDeveloperMode() {
        developerMode = !developerMode;
    }

    public static boolean developerMode() {
        return developerMode;
    }
}
