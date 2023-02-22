package org.example.utils;

import org.example.main.Game;

import java.awt.geom.Rectangle2D;
import java.util.stream.IntStream;

import static org.example.utils.constant.ItemInfo.TILES_I;

public class Utils {
    public static boolean canMoveHere(float x, float y, float width, float height, int[][] levelData) {
        if (isSolid(x, y, levelData)) return false;
        if (isSolid(x + width, y + height, levelData)) return false;
        if (isSolid(x + width, y, levelData)) return false;
        return !isSolid(x, y + height, levelData);
    }

    public static boolean canMoveHere(Rectangle2D.Float hitBox, int[][] levelData) {
        return canMoveHere(hitBox.x, hitBox.y, hitBox.width, hitBox.height, levelData);
    }

    private static boolean isSolid(float x, float y, int[][] levelData) {
        if (isInGameWindow(x, y, levelData)) return true;

        float xIndex = x / TILES_I.size;
        float yIndex = y / TILES_I.size;

        return isTileSolid((int) xIndex, (int) yIndex, levelData);
    }

    private static boolean isInGameWindow(float x, float y, int[][] levelData) {
        int maxWidth = levelData[0].length * TILES_I.size;
        return x < 0 || x >= maxWidth || y < 0 || y >= Game.GAME_HEIGHT;
    }

    private static boolean isTileSolid(int x, int y, int[][] levelData) {
        int value = levelData[y][x];
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

    public static boolean isFloor(Rectangle2D.Float hitBox, float xSpeed, int[][] levelData) {
        return isSolid(hitBox.x + xSpeed, hitBox.y + hitBox.height + 1, levelData);
    }

    public static boolean isSightClear(Rectangle2D.Float hitBox1, Rectangle2D.Float hitBox2, int tileY, int[][] levelData) {
        int firstXTile = (int) (hitBox1.x / TILES_I.size);
        int secondXTile = (int) (hitBox2.x / TILES_I.size);
        IntStream stream = (firstXTile > secondXTile) ? IntStream.range(secondXTile, firstXTile) :
                IntStream.range(firstXTile, secondXTile);
        return allTilesWalkable(stream, tileY, levelData);
    }

    public static boolean allTilesWalkable(IntStream stream, int y, int[][] levelData) {
        return !stream.anyMatch(x -> isSolid(x, y, levelData) && !isTileSolid(x, y + 1, levelData));
    }
}
