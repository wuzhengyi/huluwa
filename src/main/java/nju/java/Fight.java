package nju.java;

import creature.Creature;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Fight extends Thing2D {
    public int delay;
    Creature item1;
    Creature item2;
    public Fight(Creature item1,Creature item2) {
        super(0, 0);
        this.item1 = item1;
        this.item2 = item2;
        int midX = (item1.x() + item2.x())/2;
        int midY = (item1.y() + item2.y())/2;
        setX(midX);
        setY(midY);
        delay = 50;
        URL loc = this.getClass().getClassLoader().getResource("fight.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    boolean fightIsEnd(){
        if(delay == 0){
            item1.isFighting = false;
            item2.isFighting = false;
            item1.setDied();
            item2.setNotDied();
        }
        return (delay--)<0;
    }


}
