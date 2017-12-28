package creature;

import nju.java.Thing2D;

public class GoodThing2D extends Thing2D{
    private final int OFFSET = 30;
    private final int SPSACE = 50;
    private final int w = 10 * SPACE + OFFSET;

    public  GoodThing2D(int x, int y) {
        super(x,y);
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        nx = (nx>w)?w:nx;
        nx = (nx<OFFSET)?OFFSET:nx;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }

    @Override
    public int getVx(){
        return super.getVx();
    }
}
