package org.example.entity;

import org.example.utils.constant.EnemyType;

import static org.example.utils.constant.ItemInfo.CRABBY_I;

public class Crabby extends Enemy {
    public Crabby(float x, float y) {
        super(x, y, CRABBY_I.width, CRABBY_I.height, EnemyType.CRABBY);
    }
}
