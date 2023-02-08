package org.example.gamestates;

public class Quit extends State {
    public Quit() {
        System.out.println("Quit");
    }

    @Override
    public void update() {
        System.exit(0);
    }
}
