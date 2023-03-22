package org.example.ui.bar;

import org.example.interfaces.Updatable;
import org.example.utils.LoadSafe;
import org.example.utils.constant.Image;

import java.awt.image.BufferedImage;

import static org.example.main.Game.SCALE;

public abstract class Bar implements Updatable {
  public static BufferedImage image = LoadSafe.getSpriteAtlas(Image.BAR);
  public final int width;
  public final int height;
  public final int x;
  public final int y;

  protected Bar(int width, int height, int x, int y) {
    this.width = (int) (width * SCALE);
    this.height = (int) (height * SCALE);
    this.x = (int) (x * SCALE);
    this.y = (int) (y * SCALE);
  }


}
