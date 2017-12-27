package nju.java;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import creature.Calabash;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class Field extends JPanel implements ActionListener{

    private final int OFFSET = 30;
    private final int SPACE = 50;
    private ExecutorService exec;

    private ArrayList tiles = new ArrayList();
    private ArrayList creatures = new ArrayList();
    private Player player;
//    private Calabash calabash1;

    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    private String level =
            "..........\n" +
            ".*........\n" +
            ".*........\n" +
            ".*........\n" +
            ".*........\n" +
            ".*........\n" +
            ".*........\n" +
            ".*........\n" +
            "..........\n" ;

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

        Tile a;


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
                player = new Player(x, y, this);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            } else if (item == 'c') {
                x += SPACE;
            } else if (item == '*'){
                creatures.add(new Calabash(x, y, this));
                x += SPACE;
            }

            h = y;
        }

        player = new Player(0+ OFFSET,0+OFFSET, this);
//        calabash1 = new Calabash(SPACE + OFFSET,SPACE+OFFSET,this);
    }

    public void actionPerformed(ActionEvent e) {
        repaint();

    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(tiles);
        world.addAll(creatures);


        world.add(player);
//        world.add(calabash1);


        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player || item instanceof Calabash) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            }
            else {
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

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (completed) {
                return;
            }


            int key = e.getKeyCode();


            if (key == KeyEvent.VK_LEFT) {


                player.move(-SPACE, 0);

            } else if (key == KeyEvent.VK_RIGHT) {


                player.move(SPACE, 0);

            } else if (key == KeyEvent.VK_UP) {


                player.move(0, -SPACE);

            } else if (key == KeyEvent.VK_DOWN) {


                player.move(0, SPACE);

            } else if (key == KeyEvent.VK_S) {

                new Thread(player).start();

            } else if (key == KeyEvent.VK_R) {
                restartLevel();
            } else if (key == KeyEvent.VK_SPACE) {
                exec = Executors.newCachedThreadPool();


                for ( int i=0; i<creatures.size(); i++){
                    exec.execute((Calabash) creatures.get(i));
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