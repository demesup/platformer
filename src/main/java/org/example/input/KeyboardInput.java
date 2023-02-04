package org.example.input;

import org.example.constant.Direction;
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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> gamePanel.setDirection(Direction.UP);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> gamePanel.setDirection(Direction.LEFT);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> gamePanel.setDirection(Direction.DOWN);
            case KeyEvent.VK_D,  KeyEvent.VK_RIGHT -> gamePanel.setDirection(Direction.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP ,
                    KeyEvent.VK_A, KeyEvent.VK_LEFT,
                    KeyEvent.VK_S, KeyEvent.VK_DOWN ,
                    KeyEvent.VK_D,  KeyEvent.VK_RIGHT -> gamePanel.setMoving(false);
        }
    }
}
