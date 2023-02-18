package org.example;

import java.awt.*;

public interface Drawable {
   default void draw(Graphics graphics){}
}
