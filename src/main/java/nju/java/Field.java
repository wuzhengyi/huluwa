package nju.java;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import creature.*;
import record.WnetWScreenRecordPlayer;
import record.WnetWScreenRecorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;

public class Field extends JPanel implements ActionListener {
    public static final int INF = 1000000;
    private static int OFFSET = 30;
    private static int SPACE = 50;
    private int row, column = 0;
    private ExecutorService exec;
    private int fieldArray[][];
    private WnetWScreenRecorder screenRecorder;

    private ArrayList<Tile> tiles = new ArrayList<Tile>();
    private ArrayList<Fight> fight = new ArrayList<Fight>();
    private ArrayList<Creature> badCreatures = new ArrayList<Creature>();
    private ArrayList<Creature> goodCreatures = new ArrayList<Creature>();
    private Timer timer = new Timer(100, new TimerListener());

    //    private Player player;
    //    private Calabash calabash1;

    private int w = 0;
    private int h = 0;
    private boolean isStart = false;
    private boolean completed = false;

    private String level =
            ".........\n" +
            "*......m.\n" +
            "*.....m..\n" +
            "*....s...\n" +
            "*....n...\n" +
            "*.....m..\n" +
            "*......m.\n" +
            "*.......m\n" +
            "g........\n";

    public Field() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public void setScreenRecorder(WnetWScreenRecorder recorder){
        this.screenRecorder = recorder;
    }

    synchronized public void CreatureMove(Creature item){
//        PrintFieldArray();
        if(item.isDied || item.isFighting)
            return;
        Creature target = getTarget(item);
        if(target == null)
            return;
        creatureMoveToTarget(item,target);
        if(getDistance(item,target) == SPACE){
//            item.isFighting = true;

            item.fightTheEnemy(target);
        }
//        System.out.println(getDistance(item,target));
        repaint();
    }

    synchronized private void creatureMoveToTarget(Creature item, Creature target){
        int x = getIndexX(item.x());
        int y = getIndexY(item.y());
        int dx = target.x() - item.x();
        int dy = target.y() - item.y();
        if(dx < 0 && x > 0 && fieldArray[x - 1][y] == 0){
            fieldArray[x-1][y] = fieldArray[x][y];
            fieldArray[x][y] = 0;
            item.setX(getPointX(x-1));
        }
        else if(dx > 0 && x < column - 1 && fieldArray[x + 1][y] == 0){
            fieldArray[x+1][y] = fieldArray[x][y];
            fieldArray[x][y] = 0;
            item.setX(getPointX(x+1));
        }
        else if(dy < 0 && y > 0 && fieldArray[x][y - 1] == 0){
            fieldArray[x][y-1] = fieldArray[x][y];
            fieldArray[x][y] = 0;
            item.setY(getPointY(y-1));
        }
        else if(dy > 0 && x < row - 1 && fieldArray[x][y + 1] == 0){
            fieldArray[x][y+1] = fieldArray[x][y];
            fieldArray[x][y] = 0;
            item.setY(getPointY(y+1));
        }
    }

    public boolean creatureIsGood(Creature item){
        int x = getIndexX(item.x());
        int y = getIndexY(item.y());
        if(fieldArray[x][y] == 1)
            return true;
        return false;
    }

    synchronized private Creature getTarget(Creature item){
        Creature target = null;
        int distance = INF;
        int x = getIndexX(item.x());
        int y = getIndexY(item.y());
        if(creatureIsGood(item)){
            for(int i = 0;i<badCreatures.size();i++){
                Creature temp = (Creature)badCreatures.get(i);
                if(temp.isFighting || temp.isDied)
                    continue;
                if(getDistance(item,temp)<distance){
                    target = temp;
                    distance = getDistance(item,target);
                }
            }
        }
        else if(!creatureIsGood(item)){
            for(int i = 0;i<goodCreatures.size();i++){
                Creature temp = (Creature)goodCreatures.get(i);
                if(temp.isFighting || temp.isDied)
                    continue;
                if(getDistance(item,temp)<distance){
                    target = temp;
                    distance = getDistance(item,target);
                }
            }
        }
//        System.out.println("进攻：[" + getIndexX(item.x())+"][" + getIndexY(item.y()) + "] --> [" + getIndexX(target.x())+"][" + getIndexY(target.y()) + "]");
        return target;
    }

    public int getDistance(Thing2D item1, Thing2D item2){
        return (int)Math.sqrt((item1.x()-item2.x())*(item1.x()-item2.x()) + (item1.y()-item2.y())*(item1.y()-item2.y()));
    }

    public static int getPointX(int x){return x * SPACE + OFFSET;}

    public static int getIndexX(int x){return (x - OFFSET)/ SPACE;}

    public static int getIndexY(int y){return (y - OFFSET)/ SPACE;}

    public static int getPointY(int y){return y * SPACE + OFFSET;}

    public final void initWorld() {


        goodCreatures.clear();
        badCreatures.clear();
        fight.clear();
        isStart = false;
        completed = false;

        int x = 0;
        int y = 0;

        for (int i = 0; i < level.length(); i++) {
            //铺上草地


            char item = level.charAt(i);
            if (item != '\n')
                tiles.add(new Tile(x, y));
            if (item == '\n') {
                y++;
                if (this.w < getPointX(x)) {
                    this.w = getPointX(x);
                    column = x;
                }
                x = 0;
            } else if (item == '.') {
                x++;
            } else if (item == 'c') {
                x++;
            } else if (item == '*') {
                goodCreatures.add(new Calabash(x, y, this));
                x++;
            } else if (item == 'g') {
                goodCreatures.add(new Grandpa(x, y, this));
                x++;
            } else if (item == 'n') {
                badCreatures.add(new Snake(x, y, this));
                x++;
            } else if (item == 's') {
                badCreatures.add(new Scorpion(x, y, this));
                x++;
            } else if (item == 'm') {
                badCreatures.add(new Minion(x, y, this));
                x++;
            }

            h = getPointY(y);
        }
        row = y;
        timer.start();
        initFieldArray();

    }

    private void initFieldArray(){

        fieldArray = new int[column][row];

        for(int i=0;i<row;i++)
            for(int j=0;j<column;j++)
                fieldArray[i][j]=0;

        for (int j = 0; j < goodCreatures.size(); j++) {
            Creature item2 = (Creature) goodCreatures.get(j);
            if(item2.isDied)
                continue;
            int x = getIndexX(item2.x());
            int y = getIndexY(item2.y());
            fieldArray[x][y] = 1;
        }

        for (int i = 0; i < badCreatures.size(); i++) {
            Creature item1 = (Creature) badCreatures.get(i);
            if(item1.isDied)
                continue;
            int x = getIndexX(item1.x());
            int y = getIndexY(item1.y());
            fieldArray[x][y] = -1;
        }

    }

    public void actionPerformed(ActionEvent e) {
        repaint();

    }

    public void addFight(Creature item1, Creature item2){
        fight.add(new Fight(item1, item2));
//        System.out.println("fight at " + getIndexX(x) + "," + getIndexY(y));
    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth() + OFFSET, this.getHeight() + OFFSET);

        ArrayList world = new ArrayList();

        world.addAll(tiles);
        world.addAll(badCreatures);
        world.addAll(goodCreatures);
        world.addAll(fight);

        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Creature) {
                if(!item.isDied)
                    g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else if(item instanceof Fight){
                if(!((Fight) item).fightIsEnd())
                    g.drawImage(item.getImage(), item.x(), item.y(), this);
            }else
            {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
                screenRecorder.stop();
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_R) {
                restartLevel();}
            else if (e.getKeyCode() == KeyEvent.VK_L) {
                System.out.println("play record.");
                new WnetWScreenRecordPlayer();
            }
            if (completed) {
                return;
            }
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_R) {
                restartLevel();
            } else if (key == KeyEvent.VK_SPACE) {
                System.out.println("摁下空格");
                if(isStart)
                    return;
                isStart = true;
                System.out.println("线程开始");
                exec = Executors.newCachedThreadPool();
                for (int i = 0; i < badCreatures.size(); i++) {
                    exec.execute((Creature) badCreatures.get(i));
//                    Thing2dStart((BadThing2D) badCreatures.get(i), exec);
                }
                for (int i = 0; i < goodCreatures.size(); i++) {
//                    Thing2dStart((GoodThing2D) goodCreatures.get(i), exec);
                    exec.execute((Creature)goodCreatures.get(i));
                }
                //                TimeUnit.SECONDS.sleep(5); // Run for a while...
                //                exec.shutdownNow(); // Interrupt all tasks
            }
            repaint();
        }
    }

    private boolean collisionDetection(Thing2D item1, Thing2D item2) {
        if (Math.abs(item1.x() - item2.x()) < SPACE && Math.abs(item1.y() - item2.y()) < SPACE)
            return true;
        return false;
    }

   /* synchronized private boolean collisionAllDetection(){
        boolean allStop = true;
            for (int i = 0; i < badCreatures.size(); i++) {
                for (int j = 0; j < goodCreatures.size(); j++) {

                    Creature item2 = (Creature) goodCreatures.get(j);
                    Creature item1 = (Creature) badCreatures.get(i);
                    if ((item1.x() != getPointX(0) && item1.x() != getPointX(column)) || (item2.x() != getPointX(0) && item2.x() != getPointX(column)))
                        allStop = false;
                    if (collisionDetection(item1, item2)) {
                        if (Math.abs(item1.getDelay()) < Math.abs(item2.getDelay())) {
                            //goodCreatures.remove(j);
                            item2.isDied = true;
                        } else if (Math.abs(item1.getDelay()) == Math.abs(item2.getDelay())) {
                            item1.isDied = true;
                            item2.isDied = true;
                        } else if (Math.abs(item1.getDelay()) > Math.abs(item2.getDelay())) {
                            item1.isDied = true;
                        }
                    }
                }
            }

        return allStop;
    }*/

    /*private void sortCreatures(ArrayList Creatures){
        for (int i = 0; i < Creatures.size(); i++) {
            ((Thing2D) Creatures.get(i)).setY(getPointY(i));
            ((Thing2D) Creatures.get(i)).setReverse();
        }
    }*/

    boolean isCompleted(){
        boolean goodAllDied = true;
        for(Creature c:goodCreatures){
            if(!c.isDied)
                goodAllDied = false;
        }
        boolean badAllDied = true;
        for(Creature c:badCreatures){
            if(!c.isDied)
                badAllDied = false;
        }
        return goodAllDied || badAllDied;
    }

    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for(Fight f:fight){
                f.fightIsEnd();
            }
            initFieldArray();
            repaint();
            if(isCompleted())
                completed = true;

//            /*碰撞检测*/
//            boolean allStop = collisionAllDetection();
//
//            //反复进攻
//            if (allStop) {
//                if(goodCreatures.size()==0 || badCreatures.size()==0){
//                    completed = true;
//                    timer.stop();
//                    repaint();
//                    return;
//                }
//
//                sortCreatures(goodCreatures);
//                sortCreatures(badCreatures);
//
//            }
        }
    }

    public void restartLevel() {
        tiles.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }
}