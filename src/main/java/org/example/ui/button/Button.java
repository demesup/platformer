package org.example.ui.button;

import org.example.interfaces.Drawable;
import org.example.interfaces.Updatable;

import java.awt.*;

public abstract class Button implements Updatable, Drawable {
    protected int x, y;
    protected Rectangle bounds;
    protected boolean mouseOver, mousePressed;
    protected int rowIndex;

    public Button(int x, int y, Rectangle bounds) {
        this.x = x;
        this.y = y;
        this.bounds = bounds;
    }

    public Button(int x, int y, int rowIndex, Rectangle bounds) {
        this.x = x;
        this.y = y;
        this.rowIndex = rowIndex;
        this.bounds = bounds;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
    }

    public void applyGameState() {
    }

    protected abstract void loadImages();
}
