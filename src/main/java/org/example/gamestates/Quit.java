package org.example.gamestates;


public class Quit extends State {
    public Quit() {
    }

    @Override
    public void update() {
        System.exit(0);
    }
}
