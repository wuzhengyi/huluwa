package nju.java.creature;

import nju.java.attribute.Camp;
import nju.java.field.Field;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class Calabash extends Creature {

    private static int index = 1;
    private int number;

    @Override
    public void setDied(){
        super.setDied();
        URL loc = this.getClass().getClassLoader().getResource((number) + "Died" + ".png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public Calabash(int x, int y, Field field) {
        super(x, y,field);
        number = index;
        if (++index > 7)
            index = 1;
        this.camp = Camp.Good;
        URL loc = this.getClass().getClassLoader().getResource((number) + ".png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }


}
