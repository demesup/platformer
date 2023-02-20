package org.example.entity;

import org.example.Drawable;
import org.example.Updatable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity implements Updatable, Drawable {
    protected float x, y;
    protected int animationTick, animationIndex, animationSpeed;
    protected int width, height;
    protected Rectangle2D.Float hitBox;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void drawHitBox(Graphics graphics) {
        graphics.setColor(Color.CYAN);
        graphics.drawRect((int) hitBox.x, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }

    protected void initHitBox(float x, float y, int width, int height) {
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }

//    protected void updateHitBox() {
//        hitBox.x = (int) x;
//        hitBox.y = (int) y;
//    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

     protected abstract void updateAnimationTick();

    public int getAnimationIndex() {
        return animationIndex;
    }
}
