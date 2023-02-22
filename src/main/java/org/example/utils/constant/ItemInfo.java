package org.example.utils.constant;

import static org.example.main.Game.SCALE;

public enum ItemInfo {
    TILES_I(26, 14, 32),
    BUTTON_I(140, 56),
    VOLUME_I(28, 44),
    SLIDER_I(215),
    BIG_CLOUDS_I(448, 101),
    SMALL_CLOUDS_I(75, 24),
    CRABBY_I(72, 32);
    public final int defaultWidth;
    public final int defaultHeight;
    public final int defaultSize;
    public final int width;
    public final int height;
    public final int size;

    ItemInfo(int defaultWidth) {
        this.defaultWidth = defaultWidth;
        this.defaultHeight = 0;
        this.defaultSize = 0;
        this.width = (int) (defaultWidth * SCALE);
        this.height = 0;
        this.size = 0;

    }

    ItemInfo(int defaultWidth, int defaultHeight) {
        this.defaultWidth = defaultWidth;
        this.defaultHeight = defaultHeight;
        this.defaultSize = 0;
        this.width = (int) (defaultWidth * SCALE);
        this.height = (int) (defaultHeight * SCALE);
        this.size = 0;
    }

    ItemInfo(int defaultWidth, int defaultHeight, int defaultSize) {
        this.defaultWidth = defaultWidth;
        this.defaultHeight = defaultHeight;
        this.defaultSize = defaultSize;
        this.width = (int) (defaultWidth * SCALE);
        this.height = (int) (defaultHeight * SCALE);
        this.size = (int) (defaultSize * SCALE);
    }
}
