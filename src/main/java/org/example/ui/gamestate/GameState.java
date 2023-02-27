package org.example.ui.gamestate;


import org.example.ui.gamestate.overlay.GameOverOverlay;
import org.example.ui.gamestate.overlay.PauseOverlay;

public enum GameState {
    PLAYING(new Playing()),
    OPTIONS(new Options()),
    QUIT(new Quit()),
    MENU(new Menu()),
    PAUSE(new PauseOverlay()),
    GAME_OVER(new GameOverOverlay());
    private final State state;

   public static GameState overlayState;

    GameState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
