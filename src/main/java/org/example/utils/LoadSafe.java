package org.example.utils;

import org.example.entity.Crabby;
import org.example.utils.constant.EnemyType;
import org.example.utils.constant.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.example.utils.constant.ItemInfo.TILES_I;


public class LoadSafe {
  public static BufferedImage getSpriteAtlas(org.example.utils.constant.Image image) {
    try (InputStream is = LoadSafe.class.getClassLoader().getResourceAsStream(image.getPath())) {
      return ImageIO.read(Objects.requireNonNull(is));
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
      throw new RuntimeException(e);
    }
  }

  public static int[][] getLevelData() {
    BufferedImage img = getSpriteAtlas(Image.LEVEL_ONE);
    int[][] levelData = new int[img.getHeight()][img.getWidth()];

    for (int j = 0; j < img.getHeight(); j++)
      for (int i = 0; i < img.getWidth(); i++) {
        Color color = new Color(img.getRGB(i, j));
        int value = color.getRed();
        if (value >= 48)
          value = 0;
        levelData[j][i] = value;
      }
    return levelData;
  }

  public static ArrayList<Crabby> getCrabs() {
    BufferedImage img = getSpriteAtlas(Image.LEVEL_ONE);
    ArrayList<Crabby> crabbies = new ArrayList<>();
    for (int j = 0; j < img.getHeight(); j++) {
      for (int i = 0; i < img.getWidth(); i++) {
        Color color = new Color(img.getRGB(i, j));
        int value = color.getGreen();
        if (value == EnemyType.CRABBY.ordinal()) {
          crabbies.add(new Crabby(i * TILES_I.size, j * TILES_I.size));
        }
      }
    }
    return crabbies;
  }

  public static List<BufferedImage> getAllLevels() throws IOException {
    URL url = LoadSafe.class.getResource("levels");
    File file = null;
    try {
      file = new File(url.toURI());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    File[] files = file.listFiles();
    List<BufferedImage> list = new ArrayList<>(files.length);
    for (int i = 0; i < files.length; i++) {
      list.add(i - 1, ImageIO.read(files[i]));
    }
    return list;
  }
}
