package org.example.level;

public record Level(int[][] levelData) {

    public int getSpriteIndex(int x, int y) {
        return levelData[y][x];
    }
}
