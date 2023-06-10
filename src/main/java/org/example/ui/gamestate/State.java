package org.example.ui.gamestate;

import org.example.interfaces.Drawable;
import org.example.interfaces.KeyEventResponse;
import org.example.interfaces.MouseEventResponse;
import org.example.interfaces.Updatable;
import org.example.ui.button.Button;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

public abstract class State implements MouseEventResponse, KeyEventResponse, Drawable, Updatable {
  protected List<Button> buttons = new LinkedList<>();


  public State() {
  }

  public boolean isIn(MouseEvent e, Button button) {
    return button.getBounds().contains(e.getPoint());
  }

  protected void resetButtons() {
    buttons.forEach(Button::resetBooleans);
  }

  public void windowFocusLost() {
  }

  public void checkEnemyHit(Rectangle2D.Float attackBox) {
  }


  @Override
  public void mouseMoved(MouseEvent e) {
    buttons.forEach(button -> button.setMouseOver(false));

    buttons.stream().filter(button -> isIn(e, button)).findFirst().ifPresent(button -> button.setMouseOver(true));
  }

  @Override
  public void mousePressed(MouseEvent e) {
    buttons.stream().filter(b -> isIn(e, b)).findFirst().ifPresent(b -> b.setMousePressed(true));
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    buttons.stream().filter(button -> isIn(e, button))
        .findFirst().filter(Button::isMousePressed)
        .ifPresent(Button::runOnPressed);
    resetButtons();
  }

  public void unpauseGame(){

  }
}
