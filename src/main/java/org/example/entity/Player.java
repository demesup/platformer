package org.example.entity;

import org.example.ui.bar.Bar;
import org.example.ui.bar.HealthBar;
import org.example.ui.bar.StatusBar;
import org.example.ui.gamestate.GameState;
import org.example.ui.gamestate.Playing;
import org.example.utils.constant.PlayerState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static org.example.main.Game.SCALE;
import static org.example.utils.Utils.*;
import static org.example.utils.constant.EntityInfo.PLAYER_E_I;
import static org.example.utils.constant.PlayerState.*;
import static org.example.utils.constant.SpriteDrawOffset.PLAYER_O;

public class Player extends Entity  {
  private BufferedImage[][] animations;
  private PlayerState playerState = IDLE;
  private boolean moving = false, attacking = false;
  private boolean left, up, right, down, jump;
  private int[][] levelData;

  private float airSpeed = 0f;
  private final float jumpSpeed = -2.25f * SCALE;
  private HealthBar healthBar;
  private StatusBar statusBar;
  private int healthWidth;
  private int flipX = 0;
  private int flipW = 1;


  public Player(float x, float y, int width, int height) {
    super(x, y, width, height, SCALE, 10);
    loadAnimations();
    initHitBox(x, y, (int) (20 * SCALE), (int) (27 * SCALE));
    initAttackBox(x, y, (int) (20 * SCALE), (int) (20 * SCALE));
    animationSpeed = 25;
    healthBar = new HealthBar();
    statusBar = new StatusBar();
    currentHealth = PLAYER_E_I.maxHealth;
    healthWidth = healthBar.width;
  }

  @Override
  public void update() {
    updateHealthBar();
    if (currentHealth <= 0) {
      Playing playing = (Playing) GameState.PLAYING.state;
      playing.setGameOver(true);
      return;
    }
    updateAttackBox();
    updatePosition();
    if (attacking) checkAttack();
    updateAnimationTick();
    setAnimation();
  }

  private void checkAttack() {
    if (attackChecked || animationIndex != 1) attackChecked = true;
    GameState.PLAYING.state.checkEnemyHit(attackBox);
  }

  @Override
  protected void updateAttackBox() {
    if (right) {
      attackBox.x = hitBox.x + hitBox.width + attackBoxOffsetX;
    } else if (left) {
      attackBox.x = hitBox.x - hitBox.width - attackBoxOffsetX;
    }
    attackBox.y = hitBox.y + attackBoxOffsetX;
  }

  private void updateHealthBar() {
    healthWidth = (int) ((currentHealth / (float) PLAYER_E_I.maxHealth) * healthBar.width);
  }

  @Override
  public void draw(Graphics graphics, int xLevelOffset) {
    graphics.drawImage(playerState.getAnimation()[animationIndex],
        (int) (hitBox.x - PLAYER_O.xOffset) - xLevelOffset + flipX,
        (int) (hitBox.y - PLAYER_O.yOffset),
        width * flipW, height, null);
    drawHitBox(graphics, xLevelOffset);
    drawAttackBox(graphics, xLevelOffset);
    drawBar(graphics);
  }

  private void drawBar(Graphics graphics) {
    graphics.drawImage(Bar.image,
        statusBar.x,
        statusBar.y,
        statusBar.width,
        statusBar.height,
        null);
    graphics.setColor(Color.RED);
    graphics.fillRect(
        healthBar.x + statusBar.x,
        healthBar.y + statusBar.y,
        healthWidth,
        healthBar.height
    );
  }

  @Override
  protected void updateAnimationTick() {
    animationTick++;
    if (animationTick >= animationSpeed) {
      animationTick = 0;
      animationIndex++;
      if (animationIndex >= playerState.getSpriteAmount()) {
        animationIndex = 0;
        attacking = false;
        attackChecked = false;
      }
    }
  }

  private void setAnimation() {
    PlayerState startAni = playerState;
    playerState = moving ? RUN : IDLE;
    if (inAir) {
      playerState = airSpeed < 0 ? JUMP : FALL;
    }
    if (attacking) {
      playerState = ATTACK;
      if (startAni != ATTACK) {
        animationIndex = 1;
        animationTick = 0;
        return;
      }
    }
    if (startAni != playerState) resetAniTick();
  }

  private void resetAniTick() {
    animationTick = 0;
    animationIndex = 0;
  }

  private void updatePosition() {
    moving = false;
    if (jump) {
      jump();
    }

    if (!inAir) {
      if ((!left && !right) || (right && left)) {
        return;
      }
    }

    float xSpeed = 0;

    if (left) {
      xSpeed -= speed;
      flipX = width;
      flipW = -1;
    }
    if (right) {
      xSpeed += speed;
      flipX = 0;
      flipW = 1;
    }
    if (!inAir) {
      if (!isEntityOnFloor(hitBox, levelData)) {
        inAir = true;
      }
    }

    if (inAir) {
      updateInAirPosition(xSpeed);
    } else {
      updateXPosition(xSpeed);
    }
    moving = true;
  }

  private void updateInAirPosition(float xSpeed) {
    if (canMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, levelData)) {
      hitBox.y += airSpeed;
      airSpeed += gravity;
    } else {
      hitBox.y = getEntityYPositionUnderRoofOrAboveFloor(hitBox, airSpeed);
      if (airSpeed > 0) {
        resetInAir();
      } else {
        airSpeed = fallSpeed;
      }
    }
    updateXPosition(xSpeed);
  }

  private void jump() {
    if (inAir) return;
    inAir = true;
    airSpeed = jumpSpeed;
  }

  private void resetInAir() {
    inAir = false;
    airSpeed = 0;
  }

  private void updateXPosition(float xSpeed) {
    if (canMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, levelData)) {
      hitBox.x += xSpeed;
    } else {
      hitBox.x = getEntityXPositionNextToWall(hitBox, xSpeed);
    }
  }

  public void changeHealth(int value) {
    currentHealth += value;

    if (currentHealth <= 0)
      currentHealth = 0;
    else if (currentHealth >= PLAYER_E_I.maxHealth)
      currentHealth = PLAYER_E_I.maxHealth;
  }

  private void loadAnimations() {
    Arrays.stream(values()).forEach(PlayerState::loadAnimation);
  }

  public void loadLevelData(int[][] levelData) {
    this.levelData = levelData;
    if (!isEntityOnFloor(hitBox, levelData)) {
      inAir = true;
    }
  }

  public void resetDirBooleans() {
    left = false;
    right = false;
    up = false;
    down = false;
  }

  public void setAttacking(boolean attacking) {
    this.attacking = attacking;
  }


  public void setLeft(boolean left) {
    this.left = left;
  }

  public void setUp(boolean up) {
    this.up = up;
  }


  public void setRight(boolean right) {
    this.right = right;
  }

  public void setDown(boolean down) {
    this.down = down;
  }

  public void setJump(boolean jump) {
    this.jump = jump;
  }

  @Override
  public void resetAll() {
    resetDirBooleans();
    inAir = false;
    attacking = false;
    moving = false;
    currentHealth = PLAYER_E_I.maxHealth;
    playerState = IDLE;

    hitBox.x = x;
    hitBox.y = y;

    if (!isEntityOnFloor(hitBox, levelData)) {
      inAir = true;
    }
  }
}
