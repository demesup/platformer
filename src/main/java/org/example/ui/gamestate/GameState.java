package org.example.ui.gamestate;


import org.example.ui.gamestate.overlay.CompletedOverlay;
import org.example.ui.gamestate.overlay.GameOverOverlay;
import org.example.ui.gamestate.overlay.PauseOverlay;

public enum GameState {
  PLAYING(new Playing()),
  OPTIONS(new Options()),
  QUIT(new Quit()),
  MENU(new Menu()),
  PAUSE(new PauseOverlay()),
  GAME_OVER(new GameOverOverlay()),
  COMPLETED(new CompletedOverlay());
  public final State state;

  GameState(State state) {
    this.state = state;
  }

  public static GameState GAME_STATE = GameState.MENU;

  public static void returnToMenu() {
    GAME_STATE = MENU;
    OVERLAY = null;
    PLAYING.state.resetButtons();
  }

  public static GameState OVERLAY = null;

}
