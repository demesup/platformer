package org.example.input;

import org.example.main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static org.example.ui.gamestate.GameState.PAUSE;
import static org.example.ui.gamestate.GameState.overlayState;

public class

MouseInput implements MouseListener, MouseMotionListener {
    private final GamePanel gamePanel;

    public MouseInput(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gamePanel.getGame().getState().getState().mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gamePanel.getGame().getState().getState().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gamePanel.getGame().getState().getState().mouseReleased(e);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (overlayState.equals(PAUSE)) {
            PAUSE.getState().mouseDragged(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gamePanel.getGame().getState().getState().mouseMoved(e);
    }
}
