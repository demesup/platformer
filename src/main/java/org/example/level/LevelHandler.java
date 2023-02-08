package org.example.level;

import org.example.utils.LoadSafe;
import org.example.utils.Image;
import org.example.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelHandler {
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

    public void draw(Graphics graphics) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
            for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                graphics.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return levelOne;
    }
}
