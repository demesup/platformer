package org.example.gamestate;

import org.example.Updatable;
import org.example.button.*;
import org.example.button.Button;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.example.button.SoundButton.SOUND_SIZE;
import static org.example.button.UrmButton.URM_SIZE;
import static org.example.main.Game.GAME_WIDTH;
import static org.example.main.Game.SCALE;
import static org.example.main.Main.game;
import static org.example.utils.constant.Image.PAUSE_BACKGROUND;
import static org.example.utils.LoadSafe.getSpriteAtlas;
import static org.example.utils.constant.ItemInfo.SLIDER_I;
import static org.example.utils.constant.ItemInfo.VOLUME_I;

public class PauseOverlay extends State {
    private final ArrayList<PauseButton> buttons = new ArrayList<>();
    private final Playing playing = (Playing) GameState.PLAYING.getState();
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;
    private SoundButton musicButton, sfxButton;
    private UrmButton menuButton, replayButton, unpauseButton;
    private VolumeButton volumeButton;

    public PauseOverlay() {
        loadBackground();
        createSoundButtons();
        createUrmButtons();
        createVolumeButton();
    }

    private void createVolumeButton() {
        int vX = (int) (309 * SCALE);
        int vY = (int) (278 * SCALE);
        volumeButton = new VolumeButton(vX, vY, SLIDER_I.width, VOLUME_I.width);

        buttons.add(volumeButton);
    }

    private void createUrmButtons() {
        int menuX = (int) (313 * SCALE);
        int replayX = (int) (387 * SCALE);
        int unpauseX = (int) (462 * SCALE);
        int bY = (int) (325 * SCALE);

        menuButton = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        replayButton = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
        unpauseButton = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);

        buttons.add(menuButton);
        buttons.add(replayButton);
        buttons.add(unpauseButton);
    }

    private void createSoundButtons() {
        int soundX = (int) (450 * SCALE);
        int musicY = (int) (140 * SCALE);
        int sfxY = (int) (186 * SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);

        buttons.add(musicButton);
        buttons.add(sfxButton);
    }

    private void loadBackground() {
        backgroundImg = getSpriteAtlas(PAUSE_BACKGROUND);
        bgW = (int) (backgroundImg.getWidth() * SCALE);
        bgH = (int) (backgroundImg.getHeight() * SCALE);
        bgX = GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (25 * SCALE);

    }

    public void update() {
        buttons.forEach(Updatable::update);
    }

    public void draw(Graphics graphics) {
        // Background
        graphics.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);

        buttons.forEach(b -> b.draw(graphics));
    }


    public void mouseDragged(MouseEvent e) {
        if (volumeButton.isMousePressed()) {
            volumeButton.changeX(e.getX());
        }
    }

    public void mousePressed(MouseEvent e) {
        buttons.stream().filter(b -> isIn(e, b)).findFirst().ifPresent(b -> b.setMousePressed(true));
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e, musicButton)) {
            if (musicButton.isMousePressed())
                musicButton.setMuted(!musicButton.isMuted());

        } else if (isIn(e, sfxButton)) {
            if (sfxButton.isMousePressed())
                sfxButton.setMuted(!sfxButton.isMuted());
        } else if (isIn(e, menuButton)) {
            if (menuButton.isMousePressed()) {
                game.setState(GameState.MENU);
                playing.unpauseGame();
            }
        } else if (isIn(e, replayButton)) {
            if (replayButton.isMousePressed())
                System.out.println("replay lvl!");
        } else if (isIn(e, unpauseButton)) {
            if (unpauseButton.isMousePressed())
                playing.unpauseGame();
        }
        buttons.forEach(Button::resetBooleans);
    }

    public void mouseMoved(MouseEvent e) {
        buttons.forEach(b -> b.setMouseOver(false));
        buttons.stream().filter(b -> isIn(e, b)).findFirst().ifPresent(b -> b.setMouseOver(true));
    }
}
