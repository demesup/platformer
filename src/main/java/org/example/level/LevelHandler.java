package org.example.level;

import org.example.interfaces.Drawable;
import org.example.utils.LoadSafe;
import org.example.utils.constant.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.example.utils.constant.ItemInfo.TILES_I;

public class LevelHandler implements Drawable {
    private BufferedImage[] levelSprite;
    private Level levelOne;

    public LevelHandler() {
        importOutsideSprites();
        levelOne = new Level(LoadSafe.getLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage image = LoadSafe.getSpriteAtlas(Image.OUTSIDE);

        levelSprite = new BufferedImage[48];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                levelSprite[i * 12 + j] = image.getSubimage(j * 32, i * 32, 32, 32);
            }
        }
    }

    @Override
    public void draw(Graphics graphics, int xLevelOffset) {
        for (int j = 0; j < TILES_I.defaultHeight; j++)
            for (int i = 0; i < levelOne.levelData()[0].length; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                graphics.drawImage(levelSprite[index], TILES_I.size * i - xLevelOffset, TILES_I.size * j, TILES_I.size, TILES_I.size, null);
            }
    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return levelOne;
    }
}
