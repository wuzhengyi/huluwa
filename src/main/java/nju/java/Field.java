package nju.java;

import creature.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;

public class Field extends JPanel implements ActionListener {

    private final int OFFSET = 30;
    private final int SPACE = 50;
    private ExecutorService exec;

    private ArrayList tiles = new ArrayList();
    private ArrayList Badcreatures = new ArrayList();
    private ArrayList Goodcreatures = new ArrayList();
//    private Player player;
//    private Calabash calabash1;

    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    private String level =
            "..........\n" +
                    ".*......m.\n" +
                    ".*......m.\n" +
                    ".*.....s..\n" +
                    "g*....n...\n" +
                    ".*......m.\n" +
                    ".*......m.\n" +
                    ".*......m.\n" +
                    "..........\n";

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

    public final void initWorld() {
        new Timer(1000, this).start();

        int x = OFFSET;
        int y = OFFSET;

        for (int i = 0; i < level.length(); i++) {

            tiles.add(new Tile(x, y));

            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }
                x = OFFSET;
            } else if (item == '.') {
                x += SPACE;
            } else if (item == '@') {
//                player = new Player(x, y, this);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            } else if (item == 'c') {
                x += SPACE;
            } else if (item == '*') {
                Goodcreatures.add(new Calabash(x, y, this));
                x += SPACE;
            } else if (item == 'g') {
                Goodcreatures.add(new Grandpa(x, y, this));
                x += SPACE;
            } else if (item == 'n') {
                Badcreatures.add(new Snake(x, y, this));
                x += SPACE;
            } else if (item == 's') {
                Badcreatures.add(new Scorpion(x, y, this));
                x += SPACE;
            } else if (item == 'm') {
                Badcreatures.add(new Minion(x, y, this));
                x += SPACE;
            }

            h = y;
        }

//        player = new Player(0 + OFFSET, 0 + OFFSET, this);
//        calabash1 = new Calabash(SPACE + OFFSET,SPACE+OFFSET,this);
    }

    public void actionPerformed(ActionEvent e) {
        repaint();

    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth() + OFFSET, this.getHeight() + OFFSET);

        ArrayList world = new ArrayList();
        world.addAll(tiles);
        world.addAll(Badcreatures);
        world.addAll(Goodcreatures);

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

    private void Thing2dStart(Thing2D item,ExecutorService exec){
        if(item instanceof Calabash)
            exec.execute((Calabash) item);
        else if(item instanceof Grandpa)
            exec.execute((Grandpa) item);
        else if(item instanceof Snake)
            exec.execute((Snake) item);
        else if(item instanceof Scorpion)
            exec.execute((Scorpion) item);
        else if(item instanceof Minion)
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
                for (int i = 0; i < Badcreatures.size(); i++) {
                    Thing2dStart((Thing2D) Badcreatures.get(i), exec);
                }
                for (int i = 0; i < Goodcreatures.size(); i++) {
                    Thing2dStart((Thing2D) Goodcreatures.get(i), exec);
                }
//                TimeUnit.SECONDS.sleep(5); // Run for a while...
//                exec.shutdownNow(); // Interrupt all tasks
            }
            repaint();
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