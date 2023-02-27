package org.example.utils.constant;

public enum EntityInfo {
    PLAYER_E_I(100,10),
    CRABBY_E_I(10, 15);
    public final int maxHealth;
    public final int damage;

    EntityInfo(int maxHealth) {
        this.maxHealth = maxHealth;
        this.damage = 0;
    }


    EntityInfo(int maxHealth, int damage) {
        this.maxHealth = maxHealth;
        this.damage = damage;
    }

}
