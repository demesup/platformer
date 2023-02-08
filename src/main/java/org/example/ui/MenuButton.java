package org.example.ui;

import org.example.gamestates.GameState;
import org.example.utils.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.example.main.Game.SCALE;
import static org.example.main.Main.game;
import static org.example.utils.LoadSafe.getSpriteAtlas;

public class MenuButton extends Button {
    public static final int B_WIDTH_DEFAULT = 140;
    public static final int B_HEIGHT_DEFAULT = 56;
    public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * SCALE);
    public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * SCALE);
    private GameState state;
    private int rowIndex;
    private static int xOffsetCenter = B_WIDTH / 2;
    private int index;
    private BufferedImage[] images;
    private boolean mouseOver, mousePressed;

    public MenuButton(int x, int y, int rowIndex, GameState state) {
        super(x, y, new Rectangle(x - xOffsetCenter, y, B_WIDTH, B_HEIGHT));
        this.rowIndex = rowIndex;
        this.state = state;
        loadImages();
    }

    public void applyGameState() {
        game.setState(state);
    }


    private void loadImages() {
        images = new BufferedImage[3];
        BufferedImage temp = getSpriteAtlas(Image.MENU_BUTTONS);
        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(images[index], x - xOffsetCenter, y, B_WIDTH, B_HEIGHT, null);
    }

    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
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

    public Rectangle getBounds() {
        return bounds;
    }


    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
