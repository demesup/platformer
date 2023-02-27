package org.example.ui.button;

import org.example.utils.constant.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.example.utils.LoadSafe.getSpriteAtlas;
import static org.example.utils.constant.ItemInfo.SLIDER_I;
import static org.example.utils.constant.ItemInfo.VOLUME_I;

public class VolumeButton extends PauseButton {
    private BufferedImage[] images;
    private BufferedImage slider;
    private int index = 0;
    private int buttonX;
    private final int minX;
    private final int maxX;


    public VolumeButton(int x, int y, int width, int height) {
        super(x + width / 2, y, VOLUME_I.width, height);
        bounds.x -= VOLUME_I.width / 2;
        buttonX = x + width / 2;
        this.x = x;
        this.width = width;
        loadImages();
        minX = x + VOLUME_I.width / 2;
        maxX = minX + width - VOLUME_I.width;
    }

    public void changeX(int x) {
        if (x < minX) {
            buttonX = minX;
        } else buttonX = Math.min(x, maxX);

        bounds.x = buttonX - VOLUME_I.width / 2;
    }

    @Override
    protected void loadImages() {
        BufferedImage temp = getSpriteAtlas(Image.VOLUME_BUTTONS);
        images = new BufferedImage[3];
        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(i * VOLUME_I.defaultWidth, 0, VOLUME_I.defaultWidth, VOLUME_I.defaultHeight);
        }

        slider = temp.getSubimage(3 * VOLUME_I.defaultWidth, 0, SLIDER_I.defaultWidth, VOLUME_I.defaultHeight);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(slider, x, y, width, height, null);
        graphics.drawImage(images[index], buttonX - VOLUME_I.width / 2, y, VOLUME_I.width, VOLUME_I.height, null);
    }

    @Override
    public void update() {
        index = 0;
        if (mouseOver) index = 1;
        if (mousePressed) index = 2;
    }
}
