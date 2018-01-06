package nju.java.field;

import nju.java.creature.Creature;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FieldTest {
    Thing2D item1,item2, item3;
    Field field;
    @Before
    public void setUp() throws Exception {
        field = new Field();
        item1 = new Creature(50,50, field);
        item2 = new Creature(100,50,field);
        item3 = new Creature(300,20,field);
    }
    @Test
    public void testGetDistance() {
        assertEquals(field.getDistance(item1,item2),2500);
        assertEquals(field.getDistance(item1,item3),12589);
        assertEquals(field.getDistance(item2,item3),10111);
    }
}