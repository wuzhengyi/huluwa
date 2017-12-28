package creature;

import nju.java.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Grandpa extends GoodThing2D implements Runnable {
    private Field field;

    public Grandpa(int x, int y, Field field) {
        super(x, y);
        setV(1);
        this.field = field;

        URL loc = this.getClass().getClassLoader().getResource("grandpa.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();

            super.move(getV(), 0);
            try {

                Thread.sleep(rand.nextInt(1000) + 1000);
                this.field.repaint();

            } catch (Exception e) {

            }
        }
    }
}
