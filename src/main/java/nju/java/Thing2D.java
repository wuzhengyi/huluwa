package nju.java;

import creature.BadThing2D;
import creature.GoodThing2D;

import java.awt.Image;
import java.util.Random;

public class Thing2D {

    public final int SPACE = 50;

    private int x;
    private int y;
    public boolean isDied = true;
    public boolean isFighting = false;
    private boolean reverse = false;


    private Image image;

    public Thing2D(int x, int y) {
//        this.x = x;
//        this.y = y;
        this.x = Field.getPointX(x);
        this.y = Field.getPointY(y);
        Random rand = new Random();
        setV(rand.nextInt(4 ) + 1);
        reverse = false;
        isDied = true;
        isFighting = false;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setV(int v) {this.v = v; indexV = 0;}

    public int vx() {return this.v;}

    public void setReverse(){reverse = !reverse;}
} 