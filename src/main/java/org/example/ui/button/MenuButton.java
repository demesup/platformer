package org.example.ui.button;

import org.example.ui.gamestate.GameState;
import org.example.utils.constant.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.example.ui.gamestate.GameState.GAME_STATE;
import static org.example.utils.LoadSafe.getSpriteAtlas;
import static org.example.utils.constant.ItemInfo.BUTTON_I;

public class MenuButton extends Button {
  private GameState state;
  private static final int xOffsetCenter = BUTTON_I.width / 2;
  private int index;
  private BufferedImage[] images;

  public MenuButton(int x, int y, int rowIndex, GameState state) {
    super(x, y, new Rectangle(x - xOffsetCenter, y, BUTTON_I.width, BUTTON_I.height));
    this.rowIndex = rowIndex;
    this.state = state;
    loadImages();
  }

  public void applyGameState() {
    GAME_STATE = state;
  }


  @Override
  protected void loadImages() {
    images = new BufferedImage[3];
    BufferedImage temp = getSpriteAtlas(Image.MENU_BUTTONS);
    for (int i = 0; i < images.length; i++) {
      images[i] = temp.getSubimage(i * BUTTON_I.defaultWidth, rowIndex * BUTTON_I.defaultHeight,
          BUTTON_I.defaultWidth, BUTTON_I.defaultHeight);
    }
  }

  @Override
  public void draw(Graphics graphics) {
    graphics.drawImage(images[index],
        x - xOffsetCenter, y,
        BUTTON_I.width, BUTTON_I.height, null);
  }

  @Override
  public void update() {
    index = 0;
    if (mouseOver) {
      index = 1;
    }
    if (mousePressed) {
      index = 2;
    }
  }

  public Rectangle getBounds() {
    return bounds;
  }
}
