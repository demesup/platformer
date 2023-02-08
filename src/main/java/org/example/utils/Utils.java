package org.example.utils;

import org.example.main.Game;

import java.awt.geom.Rectangle2D;

import static org.example.main.Game.*;

public class Utils {
    public static boolean canMoveHere(float x, float y, float width, float height, int[][] levelData) {
        if (isSolid(x, y, levelData)) return false;
        if (isSolid(x + width, y + height, levelData)) return false;
        if (isSolid(x + width, y, levelData)) return false;
        return !isSolid(x, y + height, levelData);
    }

    private static boolean isSolid(float x, float y, int[][] levelData) {
        if (x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if (y < 0 || y >= Game.GAME_HEIGHT)
            return true;

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = levelData[(int) yIndex][(int) xIndex];

        return value != 11;
    }

    public static float getEntityXPositionNextToWall(Rectangle2D.Float hitBox, float xSpeed) {
        int currentTile = (int) (hitBox.x / TILES_SIZE);
        if (xSpeed > 0) {
            int tileXPosition = currentTile * TILES_SIZE;
            int xOffset = (int) (TILES_SIZE - hitBox.width);
            return tileXPosition + xOffset - 1;
        } else {
            return currentTile * TILES_SIZE;
        }
    }

    public static float getEntityYPositionUnderRoofOrAboveFloor(Rectangle2D.Float hitBox, float airSpeed) {
        int currentTile = (int) (hitBox.y / TILES_SIZE);
        if (airSpeed > 0) {
            int tileYPosition = currentTile * TILES_SIZE;
            int yOffset = (int) (TILES_SIZE - hitBox.height);
            return tileYPosition + yOffset - 1;
        } else {
            return currentTile * TILES_SIZE;
        }
    }

    public static boolean isEntityOnFloor(Rectangle2D.Float hitBox, int[][] levelData) {
        if (!isSolid(hitBox.x, hitBox.y + hitBox.height + 1, levelData)) {
            if (!isSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, levelData)) {
                return false;
            }
        }
        return true;
    }
}
