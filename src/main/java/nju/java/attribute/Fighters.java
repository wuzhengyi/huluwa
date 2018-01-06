package nju.java.attribute;

import nju.java.creature.Creature;
import nju.java.field.Fight;

import java.util.ArrayList;

public interface Fighters {
    ArrayList<Fight> fight = new ArrayList<Fight>();
    ArrayList<Creature> badCreatures = new ArrayList<Creature>();
    ArrayList<Creature> goodCreatures = new ArrayList<Creature>();

    boolean creatureIsGood(Creature item);
    void initFieldArray();
    void addFight(Creature item1, Creature item2);
    boolean fightIsCompleted();
}
