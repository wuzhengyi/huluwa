
package creature;

import nju.java.*;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Scorpion extends Creature{
    private Field field;

    public Scorpion(int x, int y, Field field) {
        super(x, y, field);
        this.camp = Camp.Bad;
        this.field = field;

        URL loc = this.getClass().getClassLoader().getResource("scorpion.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

}
