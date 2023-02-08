package org.example.ui;

import java.awt.*;

public class PauseButton extends Button {
    private int width, height;

    public PauseButton(int x, int y, int width, int height) {
        super(x, y, new Rectangle(x, y, width, height));
        this.width = width;
        this.height = height;
        createBounds();
    }

    private void createBounds() {
        bounds = new Rectangle(x, y, width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
