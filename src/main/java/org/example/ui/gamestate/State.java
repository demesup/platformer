package org.example.ui.gamestate;

import org.example.interfaces.Drawable;
import org.example.interfaces.Updatable;
import org.example.interfaces.KeyEventResponse;
import org.example.interfaces.MouseEventResponse;
import org.example.ui.button.Button;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public abstract class State implements MouseEventResponse, KeyEventResponse, Drawable, Updatable {
    protected Button[] buttons;

    public State() {
    }

    public boolean isIn(MouseEvent e, Button button) {
        return button.getBounds().contains(e.getPoint());
    }

    protected void resetButtons() {
        for (Button button : buttons) {
            button.resetBooleans();
        }
    }

    public void windowFocusLost() {
    }

    public void resetAll() {
    }

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
    }
}
