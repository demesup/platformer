package org.example.entity;

import org.example.utils.constant.PlayerState;
import org.example.main.Game;
import org.example.utils.constant.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.example.utils.LoadSafe.getSpriteAtlas;
import static org.example.utils.constant.PlayerState.*;
import static org.example.utils.Utils.*;
import static org.example.main.Game.SCALE;

public class Player extends Entity {
    private BufferedImage[][] animations;
    private PlayerState playerState = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down, jump;
    private final float playerSpeed = SCALE;
    private int[][] levelData;
    private final float xDrawOffset = 21 * Game.SCALE;
    private final float yDrawOffset = 4 * Game.SCALE;

    private float airSpeed = 0f;
    private float gravity = 0.04f * SCALE;
    private float jumpSpeed = -2.25f * SCALE;
    private float failSpeedAfterCollision = 0.5f * SCALE;
    private boolean inAir = false;


    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitBox(x, y, (int) (20 * SCALE), (int) (27 * SCALE));
        animationSpeed = 25;
    }

    @Override
    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    @Override
    public void draw(Graphics g, int xLevelOffset) {
        g.drawImage(animations[playerState.ordinal()][animationIndex],
                (int) (hitBox.x - xDrawOffset) - xLevelOffset,
                (int) (hitBox.y - yDrawOffset),
                width, height, null);
//        drawHitBox(g);
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
            playerState = ATTACK_1;

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
            xSpeed -= playerSpeed;
        }
        if (right) {
            xSpeed += playerSpeed;
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
                    airSpeed = failSpeedAfterCollision;
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

    private void loadAnimations() {

        BufferedImage img = getSpriteAtlas(Image.PLAYER);

        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);

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

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}
