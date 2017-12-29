package creature;

import nju.java.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Snake extends Creature {
    private Field field;

    public Snake(int x, int y, Field field) {
        super(x, y, field);

        this.field = field;

        URL loc = this.getClass().getClassLoader().getResource("snake.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

}
