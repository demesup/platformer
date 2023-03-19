package org.example.input;

import org.example.main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.example.ui.gamestate.GameState.GAME_STATE;
import static org.example.ui.gamestate.GameState.OVERLAY;


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
        if (OVERLAY!= null) {
          OVERLAY.state.keyPressed(e);
        } else {
          GAME_STATE.state.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      if (OVERLAY!= null) {
        OVERLAY.state.keyReleased(e);
      } else {
        GAME_STATE.state.keyReleased(e);
      }
    }
}
