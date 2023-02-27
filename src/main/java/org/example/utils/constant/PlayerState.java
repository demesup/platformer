package org.example.utils.constant;

import java.awt.image.BufferedImage;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.example.utils.LoadSafe.getSpriteAtlas;

public enum PlayerState {
    IDLE(5),
    RUN(6),
    JUMP(3),
    FALL(1),
    ATTACK(3),
    HIT(4),
    DEAD(8);

    final int spriteAmount;
     BufferedImage[] animation;

    PlayerState(int spriteAmount) {
        this.spriteAmount = spriteAmount;
    }

    public int getSpriteAmount() {
        return spriteAmount;
    }

    public void loadAnimation() {
         BufferedImage img = getSpriteAtlas(Image.PLAYER);
        BufferedImage[] animations = new BufferedImage[spriteAmount];
        for (int i = 0; i < animations.length; i++) {
            animations[i] = img.getSubimage(i * 64, ordinal() * 40, 64, 40);
        }
        this.animation =  animations;
    }

    public BufferedImage[] getAnimation(){
        return animation;
    }
}
