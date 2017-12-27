package creature;

import nju.java.Thing2D;

public class BadThing2D extends Thing2D{
    public  BadThing2D(int x, int y) {
        super(x,y);
    }
    @Override
    public int getV(){
        return -super.getV();
    }
}
