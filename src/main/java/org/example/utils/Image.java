package org.example.utils;

public enum Image {
    PLAYER("player_sprites.png"),
    OUTSIDE("outside_sprites.png"),
    LEVEL_ONE_DATA("level_one_data.png"),
    MENU_BUTTONS("button_atlas.png"),
    MENU_BACKGROUND("menu_background.png"),
    PAUSE_BACKGROUND("pause_menu.png"),
    SOUND_BUTTON("sound_button.png"),
    URM_BUTTONS("urm_buttons.png"),
    VOLUME_BUTTONS("volume_buttons.png");
    private final String path;

    Image(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
