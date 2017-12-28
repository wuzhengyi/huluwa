package nju.java;

import creature.*;

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

    private static int OFFSET = 30;
    private static int SPACE = 50;
    private ExecutorService exec;

    private ArrayList tiles = new ArrayList();
    private ArrayList badCreatures = new ArrayList();
    private ArrayList goodCreatures = new ArrayList();
    private boolean locked = false;
    private Timer timer = new Timer(100, new TimerListener());

    //    private Player player;
    //    private Calabash calabash1;

    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    private String level =
            "..........\n" +
            ".*......m.\n" +
            ".*.....m..\n" +
            ".*....s...\n" +
            ".*....n...\n" +
            ".*.....m..\n" +
            ".*......m.\n" +
            ".*.......m\n" +
            ".g........\n";

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

//    public static Point getPoint(int x, int y){
//        return new Point(OFFSET + x * SPACE,OFFSET + y * SPACE);
//    }

    public static int getPointX(int x){return x * SPACE + OFFSET;}

    public static int getPointY(int y){return y * SPACE + OFFSET;}

    public final void initWorld() {
        //new Timer(1000, this).start();
        timer.start();
        goodCreatures.clear();
        badCreatures.clear();
//        if(!exec.isShutdown())
//            exec.shutdownNow();
        int x = 0;
        int y = 0;

        for (int i = 0; i < level.length(); i++) {
            //铺上草地
            tiles.add(new Tile(x, y));

            char item = level.charAt(i);

            if (item == '\n') {
                y++;
                if (this.w < getPointX(x)) {
                    this.w = getPointX(x);
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

    }

    public void actionPerformed(ActionEvent e) {
        repaint();

    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth() + OFFSET, this.getHeight() + OFFSET);

        ArrayList world = new ArrayList();
        world.addAll(tiles);
        world.addAll(badCreatures);
        world.addAll(goodCreatures);

        //        world.add(player);
        //        world.add(calabash1);

        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof GoodThing2D || item instanceof BadThing2D) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    private void Thing2dStart(Thing2D item, ExecutorService exec) {
        if (item instanceof Calabash)
            exec.execute((Calabash) item);
        else if (item instanceof Grandpa)
            exec.execute((Grandpa) item);
        else if (item instanceof Snake)
            exec.execute((Snake) item);
        else if (item instanceof Scorpion)
            exec.execute((Scorpion) item);
        else if (item instanceof Minion)
            exec.execute((Minion) item);
    }

    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (completed) {
                return;
            }
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                //                player.move(-SPACE, 0);
            } else if (key == KeyEvent.VK_RIGHT) {
                //                player.move(SPACE, 0);
            } else if (key == KeyEvent.VK_UP) {
                //                player.move(0, -SPACE);
            } else if (key == KeyEvent.VK_DOWN) {
                //                player.move(0, SPACE);
            } else if (key == KeyEvent.VK_S) {
                //                new Thread(player).start();
            } else if (key == KeyEvent.VK_R) {
                restartLevel();
            } else if (key == KeyEvent.VK_SPACE) {
                exec = Executors.newCachedThreadPool();
                for (int i = 0; i < badCreatures.size(); i++) {
                    Thing2dStart((Thing2D) badCreatures.get(i), exec);
                }
                for (int i = 0; i < goodCreatures.size(); i++) {
                    Thing2dStart((Thing2D) goodCreatures.get(i), exec);
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

    private boolean collisionAllDetection(){
        boolean allStop = true;
        for (int i = 0; i < badCreatures.size(); i++) {
            for (int j = 0; j < goodCreatures.size(); j++) {
                Thing2D item2 = (Thing2D) goodCreatures.get(j);
                Thing2D item1 = (Thing2D) badCreatures.get(i);
                if ((item1.x() != getPointX(0) && item1.x() != getPointX(10)) || (item2.x() != getPointX(0) && item2.x() != getPointX(10)))
                    allStop = false;
                if (collisionDetection(item1, item2)) {
                    if (Math.abs(item1.vx()) < Math.abs(item2.vx())) {
                        goodCreatures.remove(j);
                    } else if (Math.abs(item1.vx()) == Math.abs(item2.vx())) {
                        badCreatures.remove(i);
                        goodCreatures.remove(j);
                    } else if (Math.abs(item1.vx()) > Math.abs(item2.vx())) {
                        badCreatures.remove(i);
                    }
                }
            }
        }
        return allStop;
    }

    private void sortCreatures(ArrayList Creatures){
        for (int i = 0; i < Creatures.size(); i++) {
            ((Thing2D) Creatures.get(i)).setY(getPointY(i));
            ((Thing2D) Creatures.get(i)).setReverse();
        }
    }

    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            while (locked);
            locked = true;

            /*碰撞检测*/
            boolean allStop = collisionAllDetection();

            //反复进攻
            if (allStop) {
                if(goodCreatures.size()==0 || badCreatures.size()==0){
                    completed = true;
                    repaint();
                    locked = false;
                    return;
                }

                sortCreatures(goodCreatures);
                sortCreatures(badCreatures);

            }
            locked = false;

        }

    }

    public void restartLevel() {
        tiles.clear();
        initWorld();
        timer.stop();
        if (completed) {
            completed = false;
        }
    }
}