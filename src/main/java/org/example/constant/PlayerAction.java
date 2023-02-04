package org.example.constant;

public enum PlayerAction {
    IDLE(5),
    RUN(6),
    JUMP(3),
    FALL(1),
    GROUND(2),
    HIT(4),
    ATTACK_1(3),
    ATTACK_JUMP_1(3),
    ATTACK_JUMP_2(3);

    final int spriteAmount;

    PlayerAction(int spriteAmount) {
        this.spriteAmount = spriteAmount;
    }

    public int getSpriteAmount() {
        return spriteAmount;
    }

}
