package creature;

import nju.java.Thing2D;

public class BadThing2D extends Thing2D{
    public  BadThing2D(int x, int y) {
        super(x,y);
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }

    @Override
    public int getVx(){
        return -super.getVx();
    }
}
