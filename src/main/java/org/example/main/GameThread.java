package org.example.main;

import static org.example.main.Main.game;

public class GameThread extends Thread {

    @Override
    public void run() {
        int FPS_SET = 120;
        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        int UPS_SET = 200;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;

        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                game.update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                game.getGamePanel().repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;

            }
        }
    }
}
