package org.example.ui.gamestate;

import org.example.ui.button.Button;
import org.example.ui.button.MenuButton;
import org.example.utils.constant.Image;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static org.example.main.Game.*;
import static org.example.ui.gamestate.GameState.*;
import static org.example.utils.LoadSafe.getSpriteAtlas;

public class Menu extends State {
  private BufferedImage plateImage;
  private final BufferedImage backgroundImage;
  private int menuX, menuY, menuWidth, menuHeight;

  public Menu() {
    loadButtons();
    loadPlate();
    backgroundImage = getSpriteAtlas(Image.BACKGROUND_MENU);
  }

  private void loadPlate() {
    plateImage = getSpriteAtlas(Image.MENU_PLATE);
    menuWidth = (int) (plateImage.getWidth() * SCALE);
    menuHeight = (int) (plateImage.getHeight() * SCALE);
    menuX = GAME_WIDTH_CENTER- menuWidth / 2;
    menuY = (int) (45 * SCALE);
  }

  private void loadButtons() {
    buttons.add(new MenuButton(GAME_WIDTH / 2, (int) (150 * SCALE), 0, PLAYING));
    buttons.add(new MenuButton(GAME_WIDTH / 2, (int) (220 * SCALE), 1, OPTIONS));
    buttons.add(new MenuButton(GAME_WIDTH / 2, (int) (290 * SCALE), 2, QUIT));
  }

  @Override
  public void update() {
    for (Button button : buttons) {
      button.update();
    }
  }

  @Override
  public void draw(Graphics graphics) {
    graphics.drawImage(backgroundImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
    graphics.drawImage(plateImage, menuX, menuY, menuWidth, menuHeight, null);
    for (Button button : buttons) {
      button.draw(graphics);
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_ENTER -> GAME_STATE = PLAYING;

      case KeyEvent.VK_ESCAPE -> System.exit(0);
    }
  }
}
