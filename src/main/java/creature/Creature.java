package creature;

import nju.java.Field;
import nju.java.Thing2D;

import java.util.Random;

enum Camp{Good, Bad, Neutrality}

public class Creature extends Thing2D implements Runnable{
    private int delay;
    private int indexDelay;
    protected Field field;
    protected Camp camp = Camp.Neutrality;

    public Creature(int x, int y, Field field) {
        super(x,y);
        this.field = field;
        isDied = false;
        Random rand = new Random();
        setDelay(rand.nextInt(4 ) + 1);
        camp = Camp.Neutrality;
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
        if(this.camp == enemy.camp || this.camp == Camp.Neutrality || enemy.camp == Camp.Neutrality || this.isDied || enemy.isDied || this.isFighting || enemy.isFighting)
            return false;
        this.isFighting = true;
        enemy.isFighting = true;
        field.addFight(this, enemy);
        return true;
    }

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
