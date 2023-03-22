package org.example.utils.constant;

import static org.example.main.Game.SCALE;

public enum SpriteDrawOffset {
  CRABBY_O(26, 9),
  PLAYER_O(21, 4);
  public final int xOffset;
  public final int yOffset;

  SpriteDrawOffset(int xOffset, int yOffset) {
    this.xOffset = (int) (xOffset * SCALE);
    this.yOffset = (int) (yOffset * SCALE);
  }
}
