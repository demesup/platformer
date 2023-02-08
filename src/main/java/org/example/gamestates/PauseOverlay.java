package org.example.gamestates;

import org.example.utils.Image;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static org.example.main.Game.GAME_WIDTH;
import static org.example.main.Game.SCALE;
import static org.example.utils.LoadSafe.getSpriteAtlas;

public class PauseOverlay extends State {
    private BufferedImage background;
    private int bgX, bgY, bgWidth, bgHeight;

    public PauseOverlay() {
        loadBackground();
    }

    private void loadBackground() {
        background = getSpriteAtlas(Image.PAUSE_BACKGROUND);
        bgWidth = (int) (background.getWidth() * SCALE);
        bgHeight = (int) (background.getHeight() * SCALE);
        bgX = GAME_WIDTH / 2 - bgWidth / 2;
        bgY = (int) (25 * SCALE);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(background, bgX, bgY, bgWidth, bgHeight, null);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
    }
}
