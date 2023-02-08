package org.example.input;

import org.example.main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardInput implements KeyListener {
    private final GamePanel gamePanel;

    public KeyboardInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.getGame().getState().getState().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gamePanel.getGame().getState().getState().keyReleased(e);

    }
}
