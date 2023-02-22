package org.example.entity;

import org.example.bar.Bar;
import org.example.bar.HealthBar;
import org.example.bar.button.StatusBar;
import org.example.utils.constant.PlayerState;
import org.example.utils.constant.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.example.utils.LoadSafe.getSpriteAtlas;
import static org.example.utils.constant.PlayerState.*;
import static org.example.utils.Utils.*;
import static org.example.main.Game.SCALE;
import static org.example.utils.constant.SpriteDrawOffset.PLAYER_O;

public class Player extends Entity {
    private BufferedImage[][] animations;
    private PlayerState playerState = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down, jump;
    private int[][] levelData;

    private float airSpeed = 0f;
    private final float jumpSpeed = -2.25f * SCALE;
    private HealthBar healthBar;
    private StatusBar statusBar;
    private final int maxHealth;
    private int currentHealth;
    private int healthWidth;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height, SCALE);
        loadAnimations();
        initHitBox(x, y, (int) (20 * SCALE), (int) (27 * SCALE));
        initAttackBox(x, y, (int) (20 * SCALE), (int) (20 * SCALE));
        animationSpeed = 25;
        healthBar = new HealthBar();
        statusBar = new StatusBar();
        maxHealth = 100;
        currentHealth = maxHealth;
        healthWidth = healthBar.width;
    }

    @Override
    public void update() {
        updateHealthBar();
        updateAttackBox();
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    @Override
    protected void updateAttackBox() {
        if (right) {
            attackBox.x = hitBox.x + hitBox.width + (int) (SCALE * 10);
        } else if (left) {
            attackBox.x = hitBox.x - hitBox.width - (int) (SCALE * 10);
        }
        attackBox.y = hitBox.y + (int) (SCALE * 10);
    }

    private void updateHealthBar() {
        healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBar.width);
    }

    @Override
    public void draw(Graphics graphics, int xLevelOffset) {
        graphics.drawImage(animations[playerState.ordinal()][animationIndex],
                (int) (hitBox.x - PLAYER_O.xOffset) - xLevelOffset,
                (int) (hitBox.y - PLAYER_O.yOffset),
                width, height, null);
//        drawHitBox(graphics);
        drawBar(graphics);
        drawAttackBox(graphics, xLevelOffset);
    }

    private void drawAttackBox(Graphics graphics, int xLevelOffset) {
        graphics.setColor(Color.RED);
        graphics.drawRect(
                (int) attackBox.x - xLevelOffset,
                (int) attackBox.y,
                (int) attackBox.width,
                (int) attackBox.height
        );
    }

    private void drawBar(Graphics graphics) {
        graphics.drawImage(Bar.image, statusBar.x, statusBar.y, statusBar.width, statusBar.height, null);
        graphics.setColor(Color.RED);
        graphics.fillRect(
                healthBar.x + statusBar.x,
                healthBar.y + statusBar.y,
                healthBar.width,
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
            }

        }

    }

    private void setAnimation() {
        PlayerState startAni = playerState;

        if (moving)
            playerState = RUN;
        else
            playerState = IDLE;

        if (inAir) {
            if (airSpeed < 0) {
                playerState = JUMP;
            } else playerState = FALL;
        }

        if (attacking)
            playerState = ATTACK;

        if (startAni != playerState)
            resetAniTick();
    }

    private void resetAniTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    private void updatePos() {
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
        }
        if (right) {
            xSpeed += speed;
        }
        if (!inAir) {
            if (!isEntityOnFloor(hitBox, levelData)) {
                inAir = true;
            }
        }

        if (inAir) {
            if (canMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, levelData)) {
                hitBox.y += airSpeed;
                airSpeed += gravity;
                updateXPosition(xSpeed);
            } else {
                hitBox.y = getEntityYPositionUnderRoofOrAboveFloor(hitBox, airSpeed);
                if (airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = fallSpeed;
                }
                updateXPosition(xSpeed);
            }
        } else {
            updateXPosition(xSpeed);
        }
        moving = true;
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

    public void changeX(int value) {
        currentHealth += value;
        if (currentHealth <= 0) {
            currentHealth = 0;
            //gameOver();
        } else if (currentHealth >= maxHealth) {
            currentHealth = maxHealth;
        }
    }

    private void loadAnimations() {
        BufferedImage img = getSpriteAtlas(Image.PLAYER);

        animations = new BufferedImage[7][8];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
            }
        }
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
}
