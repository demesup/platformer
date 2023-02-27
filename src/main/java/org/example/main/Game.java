package org.example.main;

import org.example.ui.gamestate.GameState;

import java.awt.*;

import static org.example.utils.constant.ItemInfo.TILES_I;

public class Game {
    private final GamePanel gamePanel;
    private GameState state;
    public static final float SCALE = 1.5F;
      public static final int GAME_WIDTH = TILES_I.size * TILES_I.defaultWidth;
    public static final int GAME_HEIGHT = TILES_I.size * TILES_I.defaultHeight;

    public Game() {
        initClasses();

        gamePanel = new GamePanel();
        new GameWindow(gamePanel);
        gamePanel.requestFocus();
    }

    private void initClasses() {
        state = GameState.GAME_OVER;
    }

    public void startGameLoop() {
        new GameThread().start();
    }

    protected void update() {
        state.getState().update();
    }

    public void render(Graphics graphics) {
        state.getState().draw(graphics);
    }

    public void windowFocusLost() {
        state.getState().windowFocusLost();
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
