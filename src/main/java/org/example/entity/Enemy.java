package org.example.entity;

import org.example.utils.constant.EnemyState;
import org.example.utils.constant.EnemyType;

public abstract class Enemy extends Entity {
    protected EnemyState enemyState;
    private final EnemyType enemyType;

    public Enemy(float x, float y, int width, int height, EnemyType enemyType) {
        super(x, y, width, height);
        animationSpeed = 25;
        this.enemyType = enemyType;
        initHitBox(x, y, width, height);
    }

    @Override
    protected void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= enemyType.getSpriteAmount(enemyState)) {
                animationIndex = 0;
            }
        }
    }

    @Override
    public void update() {
        updateAnimationTick();
    }

    public EnemyState getEnemyState() {
        return enemyState;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }
}
