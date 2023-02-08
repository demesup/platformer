package org.example.gamestates;

import org.example.entity.Player;
import org.example.level.LevelHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static org.example.main.Game.SCALE;
import static org.example.main.Main.game;

public class Playing extends State {
    private LevelHandler levelHandler;
    private Player player;
    private boolean paused;
    private PauseOverlay pauseOverlay;

    public Playing() {
        initClasses();
    }

    private void initClasses() {
        levelHandler = new LevelHandler();
        player = new Player(200F, 200F, (int) (64 * SCALE), (int) (40 * SCALE));
        player.loadLevelData(levelHandler.getCurrentLevel().levelData());
        pauseOverlay = new PauseOverlay();
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    @Override
    public void update() {
        levelHandler.update();
        player.update();
    }

    @Override
    public void draw(Graphics graphics) {
        levelHandler.draw(graphics);
        player.render(graphics);

        pauseOverlay.draw(graphics);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
//            case KeyEvent.VK_W, KeyEvent.VK_UP -> player.setUp(true);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> player.setLeft(true);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> player.setDown(true);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> player.setRight(true);
            case KeyEvent.VK_SPACE, KeyEvent.VK_W, KeyEvent.VK_UP -> player.setJump(true);

            case KeyEvent.VK_BACK_SPACE -> game.setState(GameState.MENU);
            case KeyEvent.VK_ESCAPE -> System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
//            case KeyEvent.VK_W, KeyEvent.VK_UP -> player.setUp(false);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> player.setLeft(false);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> player.setDown(false);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> player.setRight(false);
            case KeyEvent.VK_SPACE, KeyEvent.VK_W, KeyEvent.VK_UP -> player.setJump(false);
        }
    }
}
