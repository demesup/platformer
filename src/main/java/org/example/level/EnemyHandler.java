package org.example.level;

import org.example.Drawable;
import org.example.Updatable;
import org.example.entity.Crabby;
import org.example.entity.Player;
import org.example.utils.LoadSafe;
import org.example.utils.constant.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.example.utils.LoadSafe.getCrabs;
import static org.example.utils.constant.ItemInfo.CRABBY_I;
import static org.example.utils.constant.SpriteDrawOffset.CRABBY_O;

public class EnemyHandler implements Updatable, Drawable {
    private BufferedImage[][] crabbyImages;
    private ArrayList<Crabby> crabbies = new ArrayList<>();

    public EnemyHandler() {
        loadEnemyImages();
        addEnemies();
    }

    private void addEnemies() {
        crabbies = getCrabs();
    }

    private void loadEnemyImages() {
        crabbyImages = new BufferedImage[5][9];
        BufferedImage temp = LoadSafe.getSpriteAtlas(Image.CRABBY);

        for (int i = 0; i < crabbyImages.length; i++) {
            for (int j = 0; j < crabbyImages[i].length; j++) {
                crabbyImages[i][j] = temp.getSubimage(j * CRABBY_I.defaultWidth, i * CRABBY_I.defaultHeight, CRABBY_I.defaultWidth, CRABBY_I.defaultHeight);
            }
        }
    }

    @Override
    public void update(int[][] levelData, Player player) {
        crabbies.forEach(c-> c.update(levelData, player));
    }

    @Override
    public void draw(Graphics graphics, int xLevelOffset) {
        drawCrabs(graphics, xLevelOffset);
    }

    private void drawCrabs(Graphics graphics, int xLevelOffset) {
        crabbies.forEach(c -> graphics.drawImage(
                crabbyImages[c.getState().ordinal()][c.getAnimationIndex()],
                (int) c.getHitBox().x - xLevelOffset,
                (int) c.getHitBox().y- CRABBY_O.yOffset,
                CRABBY_I.width, CRABBY_I.height,
                null
        ));
    }
}
