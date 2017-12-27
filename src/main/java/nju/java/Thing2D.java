package nju.java;

import java.awt.Image;
import java.util.Random;

public class Thing2D {

    public final int SPACE = 50;

    private int x;
    private int y;
    private int vx;
    private int indexVx;
    private int vy;
    private int indexVy;


    private Image image;

    public Thing2D(int x, int y) {
        this.x = x;
        this.y = y;

        Random rand = new Random();
        setVx(rand.nextInt(4 ) + 1);
        setVy(500);
    }

    public int getVx(){
        if(++indexVx == vx){
            indexVx =0;
            return SPACE;
        }
        else
            return 0;

    }

    public int getVy(){
        if(++indexVy >= vy){
            indexVy =0;
            return SPACE;
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

    public void setVy(int vy) {this.vy = vy; indexVy = 0;}

    public void setVx(int vx) {this.vx = vx; indexVx = 0;}


} 