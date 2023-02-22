package org.example.entity;

import org.example.utils.constant.EnemyState;
import org.example.utils.constant.EnemyType;

import static org.example.main.Game.SCALE;
import static org.example.utils.constant.EnemyState.ATTACK;
import static org.example.utils.constant.EnemyState.RUN;
import static org.example.utils.constant.ItemInfo.CRABBY_I;

public class Crabby extends Enemy {
    public Crabby(float x, float y) {
        super(x, y, CRABBY_I.width, CRABBY_I.height, EnemyType.CRABBY);
        initHitBox(x, y, (int) (22 * SCALE), (int) (19 * SCALE));
        enemyState = EnemyState.IDLE;
    }


    @Override
    public void updateMove(int[][] levelData, Player player) {
        isFirstUpdate(levelData);

        if (inAir) {
            updateInAir(levelData);
        } else {
            switch (enemyState) {
                case IDLE -> enemyState = RUN;
                case RUN -> {
                    if (canSeePlayer(levelData, player)) {
                        turnToPlayer(player);
                        if (isPlayerInAttackRange(player)) {
                            setState(ATTACK);
                        }
                    }
                    move(levelData);
                }
            }
        }

    }

}
