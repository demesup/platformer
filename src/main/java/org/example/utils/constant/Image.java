package org.example.utils.constant;

public enum Image {
  PLAYER("player_sprites.png"),
  CRABBY("crabby_sprite.png"),
  OUTSIDE("outside_sprites.png"),
  MENU_BUTTONS("button_atlas.png"),
  MENU_PLATE("menu_background.png"),
  BACKGROUND_MENU("background_menu.png"),
  PAUSE_BACKGROUND("pause_menu.png"),
  SOUND_BUTTON("sound_button.png"),
  URM_BUTTONS("urm_buttons.png"),
  VOLUME_BUTTONS("volume_buttons.png"),
  PLAYING_BACKGROUND("playing_bg_img.png"),
  BIG_CLOUDS("big_clouds.png"),
  SMALL_CLOUDS("small_clouds.png"),
  BAR("health_power_bar.png"),
  COMPLETED("completed.png"),
  LEVEL_ONE("levels/1.png"),
  LEVEL_TWO("levels/2.png"),
  LEVEL_THREE("levels/3.png");

  private final String path;

  Image(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}
