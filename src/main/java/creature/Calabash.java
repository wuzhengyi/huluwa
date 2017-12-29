package creature;

import nju.java.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class Calabash extends Creature {

    private static int index = 1;

    public Calabash(int x, int y, Field field) {
        super(x, y,field);
        URL loc = this.getClass().getClassLoader().getResource((index++) + ".png");
        if (index > 7)
            index = 1;
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }


}
