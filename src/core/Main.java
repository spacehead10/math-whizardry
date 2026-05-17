package core;

import core.state.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
    public final static int FRAMES_PER_SECOND = 60;
    private static AppGameContainer appgc;

    public static final int WORLD_ID = 0;
    public static final int BATTLE_ID = 1;

    private BasicGameState world;
    private BasicGameState battle;

    public Main(String name) {
        super(name);

        world = new WorldState(WORLD_ID);
        battle = new BattleState(BATTLE_ID);
    }

    public static int getScreenWidth() {
        return appgc.getScreenWidth();
    }

    public static int getScreenHeight() {
        return appgc.getScreenHeight();
    }

    public void initStatesList(GameContainer gc) throws SlickException {
        addState(world);
        addState(battle);
    }

    public static void main(String[] args) {
        try {
            appgc = new AppGameContainer(new Main("Math Whizardry"));
            System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

            appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), false);
            appgc.setTargetFrameRate(FRAMES_PER_SECOND);
            appgc.setVSync(true);
            appgc.start();

        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}