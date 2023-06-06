package org.example.ui.button;

import org.example.utils.constant.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.example.main.Game.SCALE;
import static org.example.utils.LoadSafe.getSpriteAtlas;

public class UrmButton extends PauseButton {
  public static final int URM_DEFAULT_SIZE = 56;
  public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * SCALE);
  private int index;
  private BufferedImage[] images;

  public UrmButton(int x, int y, int width, int height, int rowIndex, Runnable whenPressed) {
    super(x, y, width, height, rowIndex, whenPressed);
    loadImages();
  }

  @Override
  protected void loadImages() {
    images = new BufferedImage[3];
    BufferedImage temp = getSpriteAtlas(Image.URM_BUTTONS);
    for (int i = 0; i < images.length; i++) {
      images[i] = temp.getSubimage(i * URM_DEFAULT_SIZE, rowIndex * URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);
    }
  }

  @Override
  public void draw(Graphics graphics) {
    graphics.drawImage(images[index], x, y, URM_SIZE, URM_SIZE, null);
  }

  @Override
  public void update() {
    index = 0;
    if (mouseOver) index = 1;
    if (mousePressed) index = 2;
  }
}
