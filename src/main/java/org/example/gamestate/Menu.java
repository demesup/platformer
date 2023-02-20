package org.example.gamestate;

import org.example.button.Button;
import org.example.button.MenuButton;
import org.example.utils.constant.Image;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static org.example.gamestate.GameState.*;
import static org.example.main.Game.*;
import static org.example.main.Main.game;
import static org.example.utils.LoadSafe.getSpriteAtlas;

public class Menu extends State {
    private BufferedImage plateImage;
    private final BufferedImage backgroundImage;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu() {
        loadButtons();
        loadPlate();
        backgroundImage = getSpriteAtlas(Image.BACKGROUND_MENU);
    }

    private void loadPlate() {
        plateImage = getSpriteAtlas(Image.MENU_PLATE);
        menuWidth = (int) (plateImage.getWidth() * SCALE);
        menuHeight = (int) (plateImage.getHeight() * SCALE);
        menuX = GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (45 * SCALE);
    }

    private void loadButtons() {
        buttons = new MenuButton[3];
        buttons[0] = new MenuButton(GAME_WIDTH / 2, (int) (150 * SCALE), 0, PLAYING);
        buttons[1] = new MenuButton(GAME_WIDTH / 2, (int) (220 * SCALE), 1, OPTIONS);
        buttons[2] = new MenuButton(GAME_WIDTH / 2, (int) (290 * SCALE), 2, QUIT);
    }

    @Override
    public void update() {
        for (Button button : buttons) {
            button.update();
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(backgroundImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        graphics.drawImage(plateImage, menuX, menuY, menuWidth, menuHeight, null);
        for (Button button : buttons) {
            button.draw(graphics);
        }
    }

    

    @Override
    public void mousePressed(MouseEvent e) {
        for (Button button : buttons) {
            if (isIn(e, button)) {
                button.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (Button button : buttons) {
            if (isIn(e, button)) {
                if (button.isMousePressed()) {
                    button.applyGameState();
                    break;
                }
            }
        }
        resetButtons();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER -> game.setState(PLAYING);

            case KeyEvent.VK_ESCAPE -> System.exit(0);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (Button button : buttons) {
            button.setMouseOver(false);
        }

        for (Button button : buttons) {
            if (isIn(e, button)) {
                button.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
