package org.example.ui.gamestate;

import org.example.ui.button.Button;
import org.example.utils.constant.Image;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static org.example.main.Game.GAME_HEIGHT;
import static org.example.main.Game.GAME_WIDTH;
import static org.example.ui.gamestate.GameState.GAME_STATE;
import static org.example.utils.LoadSafe.getSpriteAtlas;

public class Options extends State {
  private final BufferedImage backgroundImage;
  public Options() {
    backgroundImage = getSpriteAtlas(Image.BACKGROUND_MENU);
  }


  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_ESCAPE -> GAME_STATE = GameState.MENU;
    }
  }

  @Override
  public void draw(Graphics graphics) {
    graphics.drawImage(backgroundImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
    for (Button button : buttons) {
      button.draw(graphics);
    }
  }
}
