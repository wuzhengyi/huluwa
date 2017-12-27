package creature;

import nju.java.Thing2D;

public class GoodThing2D extends Thing2D{
    public  GoodThing2D(int x, int y) {
        super(x,y);
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        //nx = (nx>w)
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }

    @Override
    public int getVx(){
        return super.getVx();
    }
}
