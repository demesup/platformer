package org.example.ui.gamestate.overlay;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.interfaces.Updatable;
import org.example.ui.button.UrmButton;
import org.example.ui.gamestate.GameState;
import org.example.ui.gamestate.State;
import org.example.utils.LoadSafe;
import org.example.utils.constant.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.example.main.Game.GAME_WIDTH_CENTER;
import static org.example.main.Game.SCALE;
import static org.example.ui.button.UrmButton.URM_SIZE;
import static org.example.ui.gamestate.GameState.GAME_STATE;
import static org.example.ui.gamestate.GameState.OVERLAY;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompletedOverlay extends State {
  BufferedImage image;
  int x;
  int y;
  int width;
  int height;

  public CompletedOverlay() {
    initImage();
    initButtons();
  }

  private void initButtons() {
    int menuX = (int) (330 * SCALE);
    int nextX = (int) (445 * SCALE);
    int y = (int) (195 * SCALE);

    UrmButton menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2, () -> {
      GAME_STATE = GameState.MENU;
//      OVERLAY = null;
    });
    UrmButton next = new UrmButton(nextX, y, URM_SIZE, URM_SIZE, 0 ,() -> {
      System.out.println("Helllo");
    });

    buttons.add(menu);
    buttons.add(next);
  }

  private void initImage() {
    image = LoadSafe.getSpriteAtlas(Image.COMPLETED);
    width = (int) (image.getWidth() * SCALE);
    height = (int) (image.getHeight() * SCALE);
    x = GAME_WIDTH_CENTER - width / 2;
    y = (int) (75 * SCALE);
  }

  @Override
  public void draw(Graphics graphics) {
    graphics.drawImage(image, x, y, width, height, null);
    buttons.forEach(b -> b.draw(graphics));
  }

  @Override
  public void update() {
    buttons.forEach(Updatable::update);
  }


}
