package org.example.main;

import org.example.gamestates.GameState;

import java.awt.*;

public class Game {
    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private GameState state;
    public static final int TILES_DEFAULT_SIZE = 32;
    public static final float SCALE = 1.5F;
    public static final int TILES_IN_WIDTH = 26;
    public static final int TILES_IN_HEIGHT = 14;
    public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    public Game() {
        initClasses();

        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
    }

    private void initClasses() {
        state = GameState.MENU;
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
