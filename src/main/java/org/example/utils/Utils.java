package org.example.utils;

import org.example.main.Game;

import java.awt.geom.Rectangle2D;

import static org.example.utils.constant.ItemInfo.TILES_I;

public class Utils {
    public static boolean canMoveHere(float x, float y, float width, float height, int[][] levelData) {
        if (isSolid(x, y, levelData)) return false;
        if (isSolid(x + width, y + height, levelData)) return false;
        if (isSolid(x + width, y, levelData)) return false;
        return !isSolid(x, y + height, levelData);
    }

    private static boolean isSolid(float x, float y, int[][] levelData) {
        int maxWidth = levelData[0].length * TILES_I.size;
        if (x < 0 || x >= maxWidth)
            return true;
        if (y < 0 || y >= Game.GAME_HEIGHT)
            return true;

        float xIndex = x / TILES_I.size;
        float yIndex = y / TILES_I.size;

        int value = levelData[(int) yIndex][(int) xIndex];

        return value != 11;
    }

    public static float getEntityXPositionNextToWall(Rectangle2D.Float hitBox, float xSpeed) {
        int currentTile = (int) (hitBox.x / TILES_I.size);
        if (xSpeed > 0) {
            int tileXPosition = currentTile * TILES_I.size;
            int xOffset = (int) (TILES_I.size - hitBox.width);
            return tileXPosition + xOffset - 1;
        } else {
            return currentTile * TILES_I.size;
        }
    }

    public static float getEntityYPositionUnderRoofOrAboveFloor(Rectangle2D.Float hitBox, float airSpeed) {
        int currentTile = (int) (hitBox.y / TILES_I.size);
        if (airSpeed > 0) {
            int tileYPosition = currentTile * TILES_I.size;
            int yOffset = (int) (TILES_I.size - hitBox.height);
            return tileYPosition + yOffset - 1;
        } else {
            return currentTile * TILES_I.size;
        }
    }

    public static boolean isEntityOnFloor(Rectangle2D.Float hitBox, int[][] levelData) {
        if (!isSolid(hitBox.x, hitBox.y + hitBox.height + 1, levelData)) {
            return isSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, levelData);
        }
        return true;
    }
}
