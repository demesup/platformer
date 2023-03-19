package org.example.ui.gamestate.overlay;

import org.example.ui.gamestate.State;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import static org.example.main.Game.GAME_HEIGHT;
import static org.example.main.Game.GAME_WIDTH;
import static org.example.ui.gamestate.GameState.returnToMenu;

public class GameOverOverlay extends State {
  enum StringToWrite {
    MAIN("Game over!", 50f, GAME_HEIGHT / 3),
    ESCAPE_INFO("Press esc to enter main menu", 20f, GAME_HEIGHT / 2);
    final String text;
    final float fontSize;
    final int y;
    final Font font;

    StringToWrite(String text, float fontSize, int y) {
      this.text = text;
      this.fontSize = fontSize;
      this.font = new Font("Times New Roman", Font.BOLD, (int) fontSize);
      // Determine the X coordinate for the text
      // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
      this.y = y;
    }

    public void drawString(Graphics graphics) {
      graphics.setFont(font);
      FontMetrics metrics = graphics.getFontMetrics(font);
      int width = metrics.stringWidth(text);
      int x = (GAME_WIDTH - width) / 2;
      graphics.setColor(Color.MAGENTA);
      graphics.drawString(text, x, y);
    }
  }

  @Override
  public void draw(Graphics graphics) {
    graphics.setColor(new Color(0, 0, 0, 200));
    graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
    graphics.setColor(Color.WHITE);
    Arrays.stream(StringToWrite.values()).forEach(s -> s.drawString(graphics));
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      returnToMenu();
    }
  }
}
