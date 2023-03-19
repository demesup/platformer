package org.example.entity;

import org.example.utils.constant.Direction;
import org.example.utils.constant.EnemyState;
import org.example.utils.constant.EnemyType;

import java.awt.*;

import static org.example.level.EnemyHandler.crabbyImages;
import static org.example.main.Game.SCALE;
import static org.example.utils.constant.EnemyState.*;
import static org.example.utils.constant.EntityInfo.CRABBY_E_I;
import static org.example.utils.constant.ItemInfo.CRABBY_I;
import static org.example.utils.constant.SpriteDrawOffset.CRABBY_O;

public class Crabby extends Enemy {

  public Crabby(float x, float y) {
    super(x, y, CRABBY_I.width, CRABBY_I.height, 30, EnemyType.CRABBY);
    initHitBox(x, y, (int) (22 * SCALE), (int) (19 * SCALE));
    initAttackBox(x, y, (int) (82 * SCALE), (int) (19 * SCALE));
    enemyState = EnemyState.IDLE;
  }

  @Override
  public void updateBehavior(int[][] levelData, Player player) {
    isFirstUpdate(levelData);

    if (inAir) {
      updateInAir(levelData);
    } else {
      switch (enemyState) {
        case IDLE -> enemyState = RUN;
        case RUN -> {
          if (canSeePlayer(levelData, player)) {
            turnToPlayer(player);
            if (isPlayerInAttackRange(player)) {
              setState(ATTACK);
            }
          }
          move(levelData);
        }
        case ATTACK -> {
          if (animationIndex == 0) attackChecked = false;
          if (animationIndex == 3 && !attackChecked) {
            checkAttack(attackBox, player);
          }
        }
        case HIT -> {
        }
      }
    }
  }

  public int flipX() {
    return direction == Direction.RIGHT ? width : 0;
  }

  public int flipW() {
    return direction == Direction.RIGHT ? -1 : 1;
  }

  @Override
  public void draw(Graphics graphics, int xLevelOffset) {
    graphics.drawImage(
        crabbyImages[enemyState.ordinal()][animationIndex],
        (int) hitBox.x - xLevelOffset - CRABBY_O.xOffset + flipX(),
        (int) hitBox.y - CRABBY_O.yOffset,
        CRABBY_I.width * flipW(), CRABBY_I.height,
        null
    );
    drawAttackBox(graphics, xLevelOffset);
    drawHitBox(graphics, xLevelOffset);
  }

  @Override
  protected void updateAttackBox() {
    attackBox.x = hitBox.x - attackBoxOffsetX - 1;
    attackBox.y = hitBox.y;
  }

  @Override
  public void resetAll() {
    hitBox.x = x;
    hitBox.y = y;
    firstUpdate = true;
    currentHealth = CRABBY_E_I.maxHealth;
    enemyState = IDLE;
    active = true;
    fallSpeed = 0;
  }
}
