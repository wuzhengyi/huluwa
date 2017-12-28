package nju.java;

import java.awt.Image;
import java.util.Random;

public class Thing2D {

    public final int SPACE = 50;

    private int x;
    private int y;
    private int v;
    private int indexV;
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
    }

    public int getV(){
        if(++indexV == v){
            indexV =0;
            return SPACE * (reverse ? -1 : 1);
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

    public void setV(int v) {this.v = v; indexV = 0;}

    public int vx() {return this.v;}

    public void setReverse(){reverse = !reverse;}
} 