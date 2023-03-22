package org.example.main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import static org.example.main.Main.game;


public class GameWindow extends JFrame {
  GamePanel panel;

  public GameWindow(GamePanel gamePanel) {
    this.panel = gamePanel;
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(gamePanel);
    setResizable(false);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    addWindowFocusListener(new WindowFocusListener() {

      @Override
      public void windowLostFocus(WindowEvent e) {
        game.windowFocusLost();
      }

      @Override
      public void windowGainedFocus(WindowEvent e) {

      }
    });


  }
}
