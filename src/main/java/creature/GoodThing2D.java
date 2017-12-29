package creature;

import nju.java.Field;
import nju.java.Thing2D;

public class GoodThing2D extends Thing2D{
    private final int OFFSET = 30;
    private final int SPSACE = 50;
    private final int w = 10 * SPACE + OFFSET;
    private Field field;

    public  GoodThing2D(int x, int y, Field field) {
        super(x,y);
        this.field = field;
        isDied = false;

    }

}
