package creature;

import nju.java.Field;
import nju.java.Thing2D;

import java.util.Random;


public class Creature extends Thing2D implements Runnable{
    public final int OFFSET = 30;
    public final int SPSACE = 50;
    private int delay;
    private int indexDelay;
    protected Field field;
    private final int w = 10 * SPACE + OFFSET;

    public Creature(int x, int y, Field field) {
        super(x,y);
        this.field = field;
        isDied = false;
        Random rand = new Random();
        setDelay(rand.nextInt(4 ) + 1);
    }

    public boolean CanMove(){
        if(++indexDelay == delay){
            indexDelay =0;
            return true;
        }
        else
            return false;
    }

    public void setDelay(int delay) {this.delay = delay; indexDelay = 0;}

    public int getDelay() {return this.delay;}

    public void run() {
        while (!Thread.interrupted()) {
            try {
                if(this.CanMove()){
//                    System.out.println("Creature move");
                    this.field.CreatureMove(this);
                }
                Thread.sleep(1000);
                notify();
//                this.field.repaint();
            } catch (Exception e) {
            }

        }
    }
}
