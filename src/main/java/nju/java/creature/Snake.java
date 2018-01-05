package nju.java.creature;

import nju.java.attribute.Camp;
import nju.java.field.Field;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Snake extends Creature {
    private Field field;

    @Override
    public void setDied(){
        super.setDied();
        URL loc = this.getClass().getClassLoader().getResource("snakeDied.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public Snake(int x, int y, Field field) {
        super(x, y, field);
        this.camp = Camp.Bad;
        this.field = field;

        URL loc = this.getClass().getClassLoader().getResource("snake.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

}
