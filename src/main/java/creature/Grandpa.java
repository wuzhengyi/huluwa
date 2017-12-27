package creature;

import nju.java.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Grandpa extends Thing2D implements Runnable {
    private Field field;

    public Grandpa(int x, int y, Field field) {
        super(x, y);

        this.field = field;

        URL loc = this.getClass().getClassLoader().getResource("8.png");
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

            this.move(rand.nextInt(10), rand.nextInt(10));
            try {

                Thread.sleep(rand.nextInt(1000) + 1000);
                this.field.repaint();

            } catch (Exception e) {

            }
        }
    }
}
