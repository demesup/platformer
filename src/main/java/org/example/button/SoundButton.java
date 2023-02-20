package org.example.button;

import org.example.utils.constant.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.example.main.Game.SCALE;
import static org.example.utils.LoadSafe.getSpriteAtlas;

public class SoundButton extends PauseButton {
    public static final int SOUND_SIZE_DEFAULT = 42;
    public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * SCALE);
    private BufferedImage[][] soundImages;
    private boolean muted;
    private int columnIndex = 0;

    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);

        loadImages();
    }

    @Override
    protected void loadImages() {
        loadSoundImages();
    }

    private void loadSoundImages() {
        BufferedImage temp = getSpriteAtlas(Image.SOUND_BUTTON);
        soundImages = new BufferedImage[2][3];
        for (int i = 0; i < soundImages.length; i++) {
            for (int j = 0; j < soundImages[i].length; j++) {
                soundImages[i][j] = temp.getSubimage(
                        j * SOUND_SIZE_DEFAULT,
                        i * SOUND_SIZE_DEFAULT,
                        SOUND_SIZE_DEFAULT,
                        SOUND_SIZE_DEFAULT);
            }
        }
    }

    @Override
    public void update() {
        if (muted) {
            rowIndex = 1;
        } else rowIndex = 0;

        columnIndex = 0;
        if (mouseOver) columnIndex = 1;
        if (mousePressed) columnIndex = 2;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(soundImages[rowIndex][columnIndex], x, y, width, height, null);
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

}
