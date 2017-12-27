package nju.java;

import java.awt.Image;
import java.util.Random;

public class Thing2D {

    private final int SPACE = 20;

    private int x;
    private int y;
    private int v;
    private int indexV;


    private Image image;

    public Thing2D(int x, int y) {
        this.x = x;
        this.y = y;

        Random rand = new Random();
        v =rand.nextInt(4 ) + 1;
        indexV=0;
    }

    public int getV(){
        if(++indexV == v){
            indexV=0;
            return 50;
        }
        else
            return 0;

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


} 