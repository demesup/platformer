package org.example.input;

import org.example.main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static org.example.ui.gamestate.GameState.*;

public class

MouseInput implements MouseListener, MouseMotionListener {
  private final GamePanel gamePanel;

  public MouseInput(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    GAME_STATE.state.mouseClicked(e);
  }

  @Override
  public void mousePressed(MouseEvent e) {
    GAME_STATE.state.mousePressed(e);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    GAME_STATE.state.mouseReleased(e);

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (OVERLAY != null && OVERLAY.equals(PAUSE)) {
      PAUSE.state.mouseDragged(e);
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    GAME_STATE.state.mouseMoved(e);
  }
}
