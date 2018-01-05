package creature;

import attribute.Camp;
import nju.java.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Grandpa extends Creature{
    private Field field;

    @Override
    public void setDied(){
        super.setDied();
        URL loc = this.getClass().getClassLoader().getResource("grandpaDied.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public Grandpa(int x, int y, Field field) {
        super(x, y, field);
        this.camp = Camp.Good;
        setDelay(1);
        this.field = field;

        URL loc = this.getClass().getClassLoader().getResource("grandpa.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
