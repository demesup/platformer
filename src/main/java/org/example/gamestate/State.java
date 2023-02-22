package org.example.gamestate;

import org.example.Drawable;
import org.example.Updatable;
import org.example.bar.button.Button;

import java.awt.event.MouseEvent;

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
}
