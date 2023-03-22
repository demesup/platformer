package org.example.main;

import org.example.input.KeyboardInput;
import org.example.input.MouseInput;

import javax.swing.*;
import java.awt.*;

import static org.example.main.Game.GAME_HEIGHT;
import static org.example.main.Game.GAME_WIDTH;
import static org.example.main.Main.game;


public class GamePanel extends JPanel {

  public GamePanel() {
    MouseInput mouseInputs = new MouseInput(this);

    setPanelSize();
    addKeyListener(new KeyboardInput(this));
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
  }


  private void setPanelSize() {
    Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    setPreferredSize(size);
  }

  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    game.render(graphics);
  }

  public void updateGame() {
  }

  public Game getGame() {
    return game;
  }
}