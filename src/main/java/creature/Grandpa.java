package creature;

import nju.java.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Grandpa extends Creature{
    private Field field;

    public Grandpa(int x, int y, Field field) {
        super(x, y, field);
        setV(1);
        this.field = field;

        URL loc = this.getClass().getClassLoader().getResource("grandpa.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
