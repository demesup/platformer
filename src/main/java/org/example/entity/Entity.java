package org.example.entity;

import org.example.interfaces.Drawable;
import org.example.interfaces.Updatable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static org.example.main.Game.SCALE;

public abstract class Entity implements Updatable, Drawable {
    protected float x, y;
    protected int animationTick, animationIndex, animationSpeed;
    protected int width, height;
    protected int currentHealth;
    protected Rectangle2D.Float hitBox;
    protected Rectangle2D.Float attackBox;
    protected int attackBoxOffsetX;
    protected boolean inAir = false;
    protected float gravity = 0.04f * SCALE;
    protected float fallSpeed = 0.5f * SCALE;
    protected final float speed;
    protected boolean attackChecked;

    public Entity(float x, float y, int width, int height,
                  float speed, int attackBoxOffsetX) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.attackBoxOffsetX = (int) (attackBoxOffsetX * SCALE);
    }

    protected void drawHitBox(Graphics graphics, int xLevelOffset) {
        graphics.setColor(Color.CYAN);
        graphics.drawRect((int) hitBox.x - xLevelOffset, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }

    protected void initHitBox(float x, float y, int width, int height) {
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }

    protected void initAttackBox(float x, float y, int width, int height) {
        attackBox = new Rectangle2D.Float(x, y, width, height);
    }

    protected abstract void updateAttackBox();

    protected void updateHitBox() {
        hitBox.x = (int) x;
        hitBox.y = (int) y;
    }

    protected void drawAttackBox(Graphics graphics, int xLevelOffset) {
        graphics.setColor(Color.RED);
        graphics.drawRect(
                (int) (attackBox.x - xLevelOffset),
                (int) attackBox.y,
                (int) attackBox.width,
                (int) attackBox.height
        );
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

    protected abstract void updateAnimationTick();

    public int getAnimationIndex() {
        return animationIndex;
    }
}
