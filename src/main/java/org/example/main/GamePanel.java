package org.example.main;

import org.example.constant.Direction;
import org.example.input.KeyboardInput;
import org.example.input.MouseInput;
import org.example.constant.PlayerAction;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class GamePanel extends JPanel {

    private float xDelta = 100, yDelta = 100;
    private BufferedImage image;
    private BufferedImage[][] animations;
    private final int idleWidth = 64;
    private final int idleHeight = 40;
    private int aniTick;
    private int aniIndex;
    private final int aniSpeed = 15;
    private PlayerAction playerAction = PlayerAction.IDLE;
    private Direction playerDirection;
    private boolean isMoving = false;

    public GamePanel() {
        MouseInput mouseInputs = new MouseInput(this);
        importImage();
        loadAnimations();

        setPanelSize();
        addKeyListener(new KeyboardInput(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void loadAnimations() {
        animations = new BufferedImage[9][6];

        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = image.getSubimage(j * idleWidth, i * idleHeight, idleWidth, idleHeight);
            }
        }
    }

    private void importImage() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("player_sprites.png")) {
            image = ImageIO.read(Objects.requireNonNull(is));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }

    public void setDirection(Direction direction) {
        this.playerDirection = direction;
        isMoving = true;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawImage(animations[playerAction.ordinal()][aniIndex], (int) xDelta, (int) yDelta, idleWidth * 4, idleHeight * 4, null);
    }

    private void setAnimation() {
        if (isMoving) {
            playerAction = PlayerAction.RUN;
        } else playerAction = PlayerAction.IDLE;
    }

    private void updatePosition() {
        if (isMoving) {
            switch (playerDirection) {
                case LEFT -> xDelta -= 5;
                case UP -> yDelta -= 5;
                case DOWN -> yDelta += 5;
                case RIGHT -> xDelta += 5;
            }
        }
    }

    private void updateAnimationTick(PlayerAction playerAction) {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= playerAction.getSpriteAmount()) aniIndex = 0;
        }
    }

    public void updateGame() {
        updateAnimationTick(playerAction);
        setAnimation();
        updatePosition();
    }
}