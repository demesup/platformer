package org.example.entity;

import org.example.Drawable;
import org.example.Updatable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static org.example.main.Game.SCALE;

public abstract class Entity implements Updatable, Drawable {
    protected float x, y;
    protected int animationTick, animationIndex, animationSpeed;
    protected int width, height;
    protected Rectangle2D.Float hitBox;
    protected Rectangle2D.Float attackBox;
    protected boolean inAir = false;
    protected float gravity = 0.04f * SCALE;
    protected float fallSpeed = 0.5f * SCALE;
    protected final float speed;


    public Entity(float x, float y, int width, int height, float speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    protected void drawHitBox(Graphics graphics, int xLevelOffset) {
        graphics.setColor(Color.CYAN);
        graphics.drawRect((int) hitBox.x-xLevelOffset, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }

    protected void initHitBox(float x, float y, int width, int height) {
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }
    protected void initAttackBox(float x, float y, int width, int height) {
        attackBox = new Rectangle2D.Float(x, y, width, height);
    }

    // TODO: abstract method
    protected void updateAttackBox(){}
    protected void updateHitBox() {
        hitBox.x = (int) x;
        hitBox.y = (int) y;
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

     protected abstract void updateAnimationTick();

    public int getAnimationIndex() {
        return animationIndex;
    }
}
