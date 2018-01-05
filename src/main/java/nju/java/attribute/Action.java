package nju.java.attribute;

public interface Action {
    int delay = 0;
    int indexDelay = 0;
    boolean CanMove();
    void setDelay(int delay);
    int getDelay();
    void moveToDirection(Direction direction);
}
