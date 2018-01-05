package nju.java.attribute;

import nju.java.creature.Creature;
import nju.java.field.Thing2D;

public interface ObserveField {
    boolean EnemyInRange(Creature item, Creature target);
    Direction lookAround(Creature item, Creature target);
    Creature observeFieldAndGetTarget(Creature item);
    int getDistance(Thing2D item1, Thing2D item2);
}
