package org.example.gamestates;

import org.example.ui.MenuButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class State {

    public State() {
    }

    public boolean isIn(MouseEvent e, MenuButton button) {
        return button.getBounds().contains(e.getPoint());
    }

    public abstract void update();

    public void draw(Graphics graphics) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void windowFocusLost() {
    }

    public void mouseDragged(MouseEvent e){

    }
}
