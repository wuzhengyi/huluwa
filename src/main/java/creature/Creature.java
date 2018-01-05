package creature;

import attribute.Action;
import attribute.Camp;
import attribute.Direction;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.FieldAccessor_Double;
import nju.java.Field;
import nju.java.Thing2D;

import java.util.Random;

public class Creature extends Thing2D implements Runnable, Action {
    private int delay;
    private int indexDelay;
    protected Field field;
    protected Camp camp;
    private boolean isDied;
    public boolean isFighting ;

    public Creature(int x, int y, Field field) {
        super(x,y);
        this.field = field;
        isFighting = false;
        setNotDied();
        Random rand = new Random();
        setDelay(rand.nextInt(4 ) + 1);
        camp = Camp.Neutrality;
    }

    public void setDied(){
        isDied = true;
    }

    public void setNotDied(){
        isDied = false;
    }

    public  boolean isDied(){
        return isDied;
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

    public boolean fightTheEnemy(Creature enemy){
        if(this.camp == enemy.camp || this.camp == Camp.Neutrality || enemy.camp == Camp.Neutrality || this.isDied() || enemy.isDied() || this.isFighting || enemy.isFighting)
            return false;
        this.isFighting = true;
        enemy.isFighting = true;
        field.addFight(this, enemy);
        return true;
    }

    public int getDelay() {return this.delay;}

    public void moveToDirection(Direction direction) {
        switch (direction){
            case North:setY(field.getPointY(field.getIndexY(y())-1));break;
            case South:setY(field.getPointY(field.getIndexY(y())+1));break;
            case East:setX(field.getPointX(field.getIndexX(x())+1));break;
            case West:setX(field.getPointX(field.getIndexX(x())-1));break;
            case NoAction: break;
            default:break;
        }
    }

    private void moveToTarget(Creature item, Creature target){
        Direction direction = field.lookAround(item,target);
        moveToDirection(direction);
    }

    public void run() {
        while (!Thread.interrupted()) {
            try {
                synchronized (field){
                    field.initFieldArray();
                    if(this.CanMove()){
                        if(isDied() || isFighting)
                            continue;
                        System.out.println("Find Target");
                        Creature target = field.observeFieldAndGetTarget(this);
                        if(target == null)
                            continue;
                        System.out.println("Move To Target");
                        moveToTarget(this,target);
                        if(field.EnemyInRange(this,target)) {
                            fightTheEnemy(target);
                            System.out.println("Begin Fight");
                        }
                    }
                }
                Thread.sleep(1000);
                notify();
//                this.field.repaint();
            } catch (Exception e) {
            }

        }
    }
}
