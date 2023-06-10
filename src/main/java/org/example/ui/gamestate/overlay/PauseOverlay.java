package org.example.ui.gamestate.overlay;

import org.example.interfaces.Updatable;
import org.example.ui.button.*;
import org.example.ui.button.Button;
import org.example.ui.gamestate.GameState;
import org.example.ui.gamestate.Playing;
import org.example.ui.gamestate.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static org.example.main.Game.GAME_WIDTH_CENTER;
import static org.example.main.Game.SCALE;
import static org.example.ui.button.SoundButton.SOUND_SIZE;
import static org.example.ui.button.UrmButton.URM_SIZE;
import static org.example.ui.gamestate.GameState.GAME_STATE;
import static org.example.ui.gamestate.GameState.OVERLAY;
import static org.example.utils.LoadSafe.getSpriteAtlas;
import static org.example.utils.constant.Image.PAUSE_BACKGROUND;
import static org.example.utils.constant.ItemInfo.SLIDER_I;
import static org.example.utils.constant.ItemInfo.VOLUME_I;

public class PauseOverlay extends State {
  private final Playing playing = (Playing) GameState.PLAYING.state;
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

    menuButton = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2, () -> {
      GAME_STATE = GameState.MENU;
      playing.unpauseGame();
    });
    replayButton = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1, () -> {
      playing.resetButtons();
      playing.unpauseGame();
    });
    unpauseButton = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0, playing::unpauseGame);

    buttons.add(menuButton);
    buttons.add(replayButton);
    buttons.add(unpauseButton);
  }

  private void createSoundButtons() {
    int soundX = (int) (450 * SCALE);
    int musicY = (int) (140 * SCALE);
    int sfxY = (int) (186 * SCALE);
    musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE, () -> musicButton.setMuted(!musicButton.isMuted()));
    sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE, () -> sfxButton.setMuted(!sfxButton.isMuted()));

    buttons.add(musicButton);
    buttons.add(sfxButton);
  }

  private void loadBackground() {
    backgroundImg = getSpriteAtlas(PAUSE_BACKGROUND);
    bgW = (int) (backgroundImg.getWidth() * SCALE);
    bgH = (int) (backgroundImg.getHeight() * SCALE);
    bgX = GAME_WIDTH_CENTER - bgW / 2;
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


  @Override
  public void mouseDragged(MouseEvent e) {
    if (volumeButton.isMousePressed()) {
      volumeButton.changeX(e.getX());
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    buttons.stream().filter(b -> isIn(e, b)).findFirst().ifPresent(b -> b.setMousePressed(true));
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    super.mouseReleased(e);
    if (isIn(e, unpauseButton) || isIn(e, replayButton) || isIn(e, menuButton)) OVERLAY = null;
  }

}
