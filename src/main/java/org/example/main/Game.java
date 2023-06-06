package org.example.main;

import java.awt.*;

import static org.example.ui.gamestate.GameState.GAME_STATE;
import static org.example.ui.gamestate.GameState.OVERLAY;
import static org.example.utils.constant.ItemInfo.TILES_I;

public class Game {
  private final GamePanel gamePanel;
  public static final float SCALE = 1.5F;
  public static final int GAME_WIDTH = TILES_I.size * TILES_I.defaultWidth;
  public static final int GAME_WIDTH_CENTER = GAME_WIDTH / 2;
  public static final int GAME_HEIGHT = TILES_I.size * TILES_I.defaultHeight;

  public Game() {
    gamePanel = new GamePanel();
    new GameWindow(gamePanel);
    gamePanel.setFocusable(true);
    gamePanel.requestFocus();
  }

  public void startGameLoop() {
    new GameThread().start();
  }

  protected void update() {
    GAME_STATE.state.update();
    if (OVERLAY != null) OVERLAY.state.update();
  }

  public void render(Graphics graphics) {
    GAME_STATE.state.draw(graphics);
    if (OVERLAY != null) OVERLAY.state.draw(graphics);
  }

  public void windowFocusLost() {
    GAME_STATE.state.windowFocusLost();
  }

  public GamePanel getGamePanel() {
    return gamePanel;
  }
}
