package nju.java.field;

import java.awt.*;

public abstract class Thing2D {
    private int x;
    private int y;

    private Image image;



    public Thing2D(int x, int y) {
//        this.x = x;
//        this.y = y;
        this.x = Field.getPointX(x);
        this.y = Field.getPointY(y);

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