package org.example.utils.constant;

public enum EnemyType {
  CRABBY {
    @Override
    public int getSpriteAmount(EnemyState state) {
      switch (state) {
        case IDLE -> {
          return 9;
        }
        case RUN -> {
          return 6;
        }
        case ATTACK -> {
          return 7;
        }
        case HIT -> {
          return 4;
        }
        case DEAD -> {
          return 5;
        }
      }
      return 0;
    }
  };

  public abstract int getSpriteAmount(EnemyState state);
}
