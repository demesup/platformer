package org.example.interfaces;

import java.awt.*;

public interface Drawable {
  default void draw(Graphics graphics) {
  }

  default void draw(Graphics graphics, int xLevelOffset) {
  }
}
