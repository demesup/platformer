package org.example.utils.constant;

public enum PlayerState {
    IDLE(5),
    RUN(6),
    JUMP(3),
    FALL(1),
    HIT(4),
    ATTACK(3),
    DEAD(8);

    final int spriteAmount;

    PlayerState(int spriteAmount) {
        this.spriteAmount = spriteAmount;
    }

    public int getSpriteAmount() {
        return spriteAmount;
    }

}
