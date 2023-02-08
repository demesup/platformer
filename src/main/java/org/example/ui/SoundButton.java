package org.example.ui;

import org.example.utils.Image;

import java.awt.image.BufferedImage;

import static org.example.utils.LoadSafe.getSpriteAtlas;

public class SoundButton extends PauseButton {
    private BufferedImage[][] soundImages;

    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);

        loadSoundImages();
    }

    private void loadSoundImages() {
        BufferedImage temp = getSpriteAtlas(Image.SOUND_BUTTON);
        soundImages = new BufferedImage[2][3];
        for (int i = 0; i < soundImages.length; i++) {
            for (int j = 0; j < soundImages[i].length; j++) {
                soundImages[i][j] = temp.getSubimage(j,j,i,j);
            }
        }
    }
}
