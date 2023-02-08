package org.example.gamestates;

public enum GameState {
    PLAYING(new Playing()),
    OPTIONS(new Options()),
    QUIT(new Quit()),
    MENU(new Menu());
    private final State state;

    GameState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
