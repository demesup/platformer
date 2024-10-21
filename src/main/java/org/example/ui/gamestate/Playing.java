package org.example.ui.gamestate;

import org.example.entity.Player;
import org.example.level.EnemyHandler;
import org.example.level.LevelHandler;
import org.example.utils.LoadSafe;
import org.example.utils.constant.Image;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import static org.example.main.Game.*;
import static org.example.ui.gamestate.GameState.*;
import static org.example.utils.constant.ItemInfo.*;

public class Playing extends State {
  private LevelHandler levelHandler;
  private EnemyHandler enemyHandler;
  private Player player;
  private boolean paused = false;
  private boolean gameOver = false;
  private boolean levelCompleted = false;

  private int xLvlOffset;
  private final int leftBorder = (int) (0.2 * GAME_WIDTH);
  private final int rightBorder = (int) (0.8 * GAME_WIDTH);
  private final int lvlTilesWide = LoadSafe.getLevelData()[0].length;
  private final int maxTilesOffset = lvlTilesWide - TILES_I.defaultWidth;
  private final int maxLvlOffsetX = maxTilesOffset * TILES_I.size;

  private final BufferedImage background;
  private BufferedImage bigClouds;
  private BufferedImage smallClouds;
  private int[] smallCloudsY;
  private final Random random = new Random();

  public Playing() {
    initClasses();

    background = LoadSafe.getSpriteAtlas(Image.PLAYING_BACKGROUND);
    loadClouds();
  }

  private void loadClouds() {
    smallClouds = LoadSafe.getSpriteAtlas(Image.SMALL_CLOUDS);
    bigClouds = LoadSafe.getSpriteAtlas(Image.BIG_CLOUDS);
    smallCloudsY = new int[8];
    for (int i = 0; i < smallCloudsY.length; i++) {
      smallCloudsY[i] = (int) (random.nextInt(95, 190) * SCALE);
    }
  }

  private void initClasses() {
    levelHandler = new LevelHandler();
    enemyHandler = new EnemyHandler();
    player = new Player(200F, 200F, (int) (64 * SCALE), (int) (40 * SCALE));
    player.loadLevelData(levelHandler.getCurrentLevel().levelData());
  }

  public Player getPlayer() {
    return player;
  }

  @Override
  public void windowFocusLost() {
    player.resetDirBooleans();
  }

  @Override
  public void update() {
    if (paused) {
      GameState.PAUSE.state.update();
    } else if (levelCompleted) {
      COMPLETED.state.update();
    } else if (!gameOver) {
      levelHandler.update();
      player.update();
      enemyHandler.update(levelHandler.getCurrentLevel().levelData(), player);
      checkCloseToBorder();
    }
  }

  @Override
  public void checkEnemyHit(Rectangle2D.Float attackBox) {
    enemyHandler.checkEnemyHit(attackBox);
  }

  private void checkCloseToBorder() {
    int playerX = (int) player.getHitBox().x;
    int diff = playerX - xLvlOffset;

    if (diff > rightBorder)
      xLvlOffset += diff - rightBorder;
    else if (diff < leftBorder)
      xLvlOffset += diff - leftBorder;

    if (xLvlOffset > maxLvlOffsetX)
      xLvlOffset = maxLvlOffsetX;
    else if (xLvlOffset < 0)
      xLvlOffset = 0;
  }

  @Override
  public void draw(Graphics graphics) {
    graphics.drawImage(background, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
    drawClouds(graphics);

    levelHandler.draw(graphics, xLvlOffset);
    player.draw(graphics, xLvlOffset);
    enemyHandler.draw(graphics, xLvlOffset);

    if (paused) {
      graphics.setColor(new Color(0, 0, 0, 150));
      graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
      GameState.PAUSE.state.draw(graphics);
    } else if (gameOver) {
      GAME_OVER.state.draw(graphics);
    } else if (levelCompleted) {
      COMPLETED.state.draw(graphics);
    }
  }

  private void drawClouds(Graphics graphics) {
    for (int i = 0; i < 3; i++) {
      graphics.drawImage(bigClouds,
          i * BIG_CLOUDS_I.width - (int) (xLvlOffset * 0.3),
          (int) (204 * SCALE), BIG_CLOUDS_I.width, BIG_CLOUDS_I.height, null);
    }

    for (int i = 0; i < smallCloudsY.length; i++) {
      graphics.drawImage(smallClouds,
          SMALL_CLOUDS_I.width * 4 * i - (int) (xLvlOffset * 0.7),
          smallCloudsY[i], SMALL_CLOUDS_I.width, SMALL_CLOUDS_I.height, null);

    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (gameOver) {
      return;
    }
    if (e.getButton() == MouseEvent.BUTTON1) {
      player.setAttacking(true);
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (gameOver) {
      return;
    }
    if (paused) {
      GameState.PAUSE.state.mousePressed(e);
    } else if (levelCompleted) {
      COMPLETED.state.mousePressed(e);
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (gameOver) {
      return;
    }
    if (paused) {
      GameState.PAUSE.state.mouseReleased(e);
    } else if (levelCompleted) {
      COMPLETED.state.mouseReleased(e);
    }
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (gameOver) {
      return;
    }
    if (paused) {
      GameState.PAUSE.state.mouseDragged(e);
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if (gameOver) {
      return;
    }
    if (paused) {
      GameState.PAUSE.state.mouseMoved(e);
    } else if (levelCompleted) {
      COMPLETED.state.mouseMoved(e);
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (gameOver) {
      GAME_OVER.state.keyPressed(e);
      return;
    }
    switch (e.getKeyCode()) {
      case KeyEvent.VK_A, KeyEvent.VK_LEFT -> player.setLeft(true);
      case KeyEvent.VK_S, KeyEvent.VK_DOWN -> player.setDown(true);
      case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> player.setRight(true);
      case KeyEvent.VK_SPACE, KeyEvent.VK_W, KeyEvent.VK_UP -> player.setJump(true);

      case KeyEvent.VK_BACK_SPACE -> GAME_STATE = GameState.MENU;
      case KeyEvent.VK_ESCAPE -> {
        paused = !paused;
        OVERLAY = GameState.PAUSE;
      }
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (gameOver) {
      return;
    }
    switch (e.getKeyCode()) {
      case KeyEvent.VK_A, KeyEvent.VK_LEFT -> player.setLeft(false);
      case KeyEvent.VK_S, KeyEvent.VK_DOWN -> player.setDown(false);
      case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> player.setRight(false);
      case KeyEvent.VK_SPACE, KeyEvent.VK_W, KeyEvent.VK_UP -> player.setJump(false);
    }
  }

  @Override
  public void unpauseGame() {
    paused = false;
  }

  @Override
  public void resetButtons() {
    player.resetAll();
    enemyHandler.resetAll();
    paused = false;
    setGameOver(false);
  }

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }
}
