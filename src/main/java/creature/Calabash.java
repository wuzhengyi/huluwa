package creature;

import nju.java.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Calabash extends Thing2D implements Runnable {
    private Field field;
    private static int index = 1;
    public Calabash(int x, int y, Field field) {
        super(x, y);

        this.field = field;

        URL loc = this.getClass().getClassLoader().getResource((index++) + ".png");
        if(index > 7)
            index = 1;
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }

    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();
            this.move(getV(), 0);
            try {

                Thread.sleep( 1000);
                notify();
                this.field.repaint();

            } catch (Exception e) {

            }


        }
    }
}
