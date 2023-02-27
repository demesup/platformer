package org.example.entity;

import org.example.utils.constant.Direction;
import org.example.utils.constant.EnemyState;
import org.example.utils.constant.EnemyType;

import java.awt.geom.Rectangle2D;

import static org.example.main.Game.SCALE;
import static org.example.utils.Utils.*;
import static org.example.utils.constant.Direction.LEFT;
import static org.example.utils.constant.Direction.RIGHT;
import static org.example.utils.constant.EntityInfo.CRABBY_E_I;
import static org.example.utils.constant.ItemInfo.TILES_I;

public abstract class Enemy extends Entity {
    protected EnemyState enemyState;
    protected final EnemyType enemyType;
    protected boolean firstUpdate = true;
    protected float fallSpeed;
    protected Direction direction;
    protected int tileY;
    protected float attackDistance = TILES_I.size;
    protected float sightDistance = attackDistance * 5;
    protected boolean active = true;

    public Enemy(float x, float y, int width, int height,
                 int attackBoxOffsetX, EnemyType enemyType) {
        super(x, y, width, height, 0.3f * SCALE, attackBoxOffsetX);
        this.enemyType = enemyType;
        this.animationSpeed = 25;
        this.direction = RIGHT;
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
                switch (enemyState) {
                    case ATTACK, HIT -> enemyState = EnemyState.IDLE;
                    case DEAD -> active = false;
                }
            }
        }
    }

    @Override
    public void update(int[][] levelData, Player player) {
        updateBehavior(levelData, player);
        updateAnimationTick();
        updateAttackBox();
    }

    public abstract void updateBehavior(int[][] levelData, Player player);

    protected void changeDirection() {
        direction = (direction == LEFT) ? RIGHT : LEFT;
    }

    protected void isFirstUpdate(int[][] levelData) {
        if (firstUpdate) {
            if (!isEntityOnFloor(hitBox, levelData)) {
                inAir = true;
            }
            firstUpdate = false;
        }
    }

    protected void updateInAir(int[][] levelData) {
        if (canMoveHere(hitBox.x, hitBox.y + fallSpeed, hitBox.width, hitBox.height, levelData)) {
            hitBox.y += fallSpeed;
            fallSpeed += gravity;
        } else {
            inAir = false;
            hitBox.y = getEntityYPositionUnderRoofOrAboveFloor(hitBox, fallSpeed);
            tileY = (int) (hitBox.y / TILES_I.size);
        }
    }

    protected void move(int[][] levelData) {
        float xSpeed = direction == LEFT ? -speed : speed;
        if (canMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, levelData))
            if (isFloor(hitBox, xSpeed, levelData)) {
                hitBox.x += xSpeed;
                return;
            }

        changeDirection();
    }

    protected boolean canSeePlayer(int[][] levelData, Player player) {
        return checkY(player) && isPlayerInSightRange(player) && isSightClear(hitBox, player.hitBox, tileY, levelData);
    }

    private boolean checkY(Player player) {
        int playerTileY = (int) (player.getHitBox().y / TILES_I.size);
        return playerTileY == tileY;
    }

    public void receivedDamage(int value) {
        currentHealth -= value;
        setState(currentHealth <= 0 ? EnemyState.DEAD : EnemyState.HIT);
    }

    protected void turnToPlayer(Player player) {
        direction = player.hitBox.x > hitBox.x ? RIGHT : LEFT;
    }

    protected boolean isPlayerInSightRange(Player player) {
        if (!checkY(player)) return false;
        int absValue = (int) Math.abs(player.hitBox.x - this.hitBox.x);
        return absValue <= sightDistance;
    }

    protected boolean isPlayerInAttackRange(Player player) {
        int absValue = (int) Math.abs(player.hitBox.x - this.hitBox.x);
        return absValue <= attackDistance;
    }


    protected void checkAttack(Rectangle2D.Float attackBox, Player player) {
        if (attackBox.intersects(player.hitBox))
            player.changeHealth(-CRABBY_E_I.damage);
        attackChecked = true;
    }

    public void setState(EnemyState enemyState) {
        this.enemyState = enemyState;
        animationIndex = 0;
        animationTick = 0;
    }

    public EnemyState getState() {
        return enemyState;
    }

    public EnemyType getType() {
        return enemyType;
    }

    public boolean isActive() {
        return active;
    }
}
