package creature;

import nju.java.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class Snake extends BadThing2D implements Runnable {
    private Field field;

    public Snake(int x, int y, Field field) {
        super(x, y);

        this.field = field;

        URL loc = this.getClass().getClassLoader().getResource("snake.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();

            super.move(getVx(), getVy());

            try {

                Thread.sleep(rand.nextInt(1000) + 1000);
                this.field.repaint();

            } catch (Exception e) {

            }
        }
    }
}
