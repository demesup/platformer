package org.example.interfaces;

import org.example.entity.Player;

public interface Updatable {
    default void update() {
    }

    default void update(int[][] levelData) {
    }

    default void update(int[][] levelData, Player player){}
}
