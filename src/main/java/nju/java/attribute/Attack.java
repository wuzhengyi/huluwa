package nju.java.attribute;

import nju.java.creature.Creature;

public interface Attack {
    boolean fightTheEnemy(Creature enemy);
    void moveToDirection(Direction direction);
    void moveToTarget(Creature item, Creature target);

}
