package org.example.level;

import org.example.entity.Enemy;
import org.example.interfaces.Drawable;
import org.example.interfaces.Resettable;
import org.example.interfaces.Updatable;
import org.example.entity.Crabby;
import org.example.entity.Player;
import org.example.utils.LoadSafe;
import org.example.utils.constant.Image;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.example.utils.LoadSafe.getCrabs;
import static org.example.utils.constant.ItemInfo.CRABBY_I;

public class EnemyHandler implements Updatable, Drawable, Resettable {
  public static BufferedImage[][] crabbyImages;
  private ArrayList<Crabby> crabbies = new ArrayList<>();

  public EnemyHandler() {
    loadEnemyImages();
    addEnemies();
  }

  private void addEnemies() {
    crabbies = getCrabs();
  }

  private void loadEnemyImages() {
    crabbyImages = new BufferedImage[5][9];
    BufferedImage temp = LoadSafe.getSpriteAtlas(Image.CRABBY);

    for (int i = 0; i < crabbyImages.length; i++) {
      for (int j = 0; j < crabbyImages[i].length; j++) {
        crabbyImages[i][j] = temp.getSubimage(j * CRABBY_I.defaultWidth, i * CRABBY_I.defaultHeight, CRABBY_I.defaultWidth, CRABBY_I.defaultHeight);
      }
    }
  }

  @Override
  public void update(int[][] levelData, Player player) {
    crabbies.stream().filter(Enemy::isActive).forEach(c -> c.update(levelData, player));
  }

  @Override
  public void draw(Graphics graphics, int xLevelOffset) {
    drawCrabs(graphics, xLevelOffset);
  }

  private void drawCrabs(Graphics graphics, int xLevelOffset) {
    crabbies.stream().filter(Enemy::isActive).forEach(c -> c.draw(graphics, xLevelOffset));
  }

  public void checkEnemyHit(Rectangle2D.Float attackBox) {
    crabbies.forEach(c -> {
      if (attackBox.intersects(c.getHitBox())) {
        c.receivedDamage(10);
      }
    });
  }

  @Override
  public void resetAll() {
    crabbies.forEach(Crabby::resetAll);
  }
}
