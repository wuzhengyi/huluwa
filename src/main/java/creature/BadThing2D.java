package creature;

import nju.java.Thing2D;

public class BadThing2D extends Thing2D{
//    private final int w = 11 * 50;
    private final int OFFSET = 30;
    private final int SPSACE = 50;
    private final int w = 10 * SPACE + OFFSET;

    public  BadThing2D(int x, int y) {
        super(x,y);
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        nx = (nx>w)?w:nx;
        nx = (nx<OFFSET)?OFFSET:nx;
        this.setX(nx);
        this.setY(ny);
    }

    @Override
    public int getV(){
        return -super.getV();
    }
}
