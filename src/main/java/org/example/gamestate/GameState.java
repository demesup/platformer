package org.example.gamestate;


public enum GameState {
    PLAYING(new Playing()),
    OPTIONS(new Options()),
    QUIT(new Quit()),
    MENU(new Menu()),
    PAUSE(new PauseOverlay());
    private final State state;

   public static GameState overlayState;

    GameState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
