package org.example.button;

import org.example.utils.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.example.main.Game.SCALE;
import static org.example.utils.LoadSafe.getSpriteAtlas;

public class VolumeButton extends PauseButton {
    static final int VOLUME_DEFAULT_WIDTH = 28;
    static final int VOLUME_DEFAULT_HEIGHT = 44;
    static final int SLIDER_DEFAULT_WIDTH = 215;

    static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * SCALE);
    public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * SCALE);
    public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * SCALE);

    private BufferedImage[] images;
    private BufferedImage slider;
    private int index = 0;
    private int buttonX;
    private final int minX;
    private final int maxX;


    public VolumeButton(int x, int y, int width, int height) {
        super(x + width / 2, y, VOLUME_WIDTH, height);
        bounds.x -= VOLUME_WIDTH / 2;
        buttonX = x + width / 2;
        this.x = x;
        this.width = width;
        loadImages();
        minX = x + VOLUME_WIDTH / 2;
        maxX = minX + width - VOLUME_WIDTH;
    }

    public void changeX(int x) {
        if (x < minX) {
            buttonX = minX;
        } else buttonX = Math.min(x, maxX);

        bounds.x = buttonX - VOLUME_WIDTH / 2;
    }

    @Override
    protected void loadImages() {
        BufferedImage temp = getSpriteAtlas(Image.VOLUME_BUTTONS);
        images = new BufferedImage[3];
        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(i * VOLUME_DEFAULT_WIDTH, 0, VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
        }

        slider = temp.getSubimage(3 * VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(slider, x, y, width, height, null);
        graphics.drawImage(images[index], buttonX - VOLUME_WIDTH / 2, y, VOLUME_WIDTH, VOLUME_HEIGHT, null);
    }

    @Override
    public void update() {
        index = 0;
        if (mouseOver) index = 1;
        if (mousePressed) index = 2;
    }
}
