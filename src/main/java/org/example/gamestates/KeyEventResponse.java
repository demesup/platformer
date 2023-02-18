package org.example.gamestates;

import java.awt.event.KeyEvent;

public interface KeyEventResponse {

    default void keyPressed(KeyEvent e) {
    }

    default void keyReleased(KeyEvent e) {
    }
}
