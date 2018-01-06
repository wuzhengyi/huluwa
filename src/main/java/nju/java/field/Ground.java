package nju.java.field;


import nju.java.record.WnetWScreenRecorder;

import javax.swing.*;


public final class Ground extends JFrame {

    private final int OFFSET = 30;
    private WnetWScreenRecorder screenRecorder;
    private Field field;

    public Ground() {
        InitUI();
    }

    public void InitUI() {
        field = new Field();
        add(field);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth() + 2 * OFFSET,
                field.getBoardHeight() + 2 * OFFSET);
        setLocationRelativeTo(null);
        System.out.println(this.getX());
        setTitle("Ground");

    }

    public void recordFight(){
        screenRecorder = new WnetWScreenRecorder(this.getBounds());
        field.setScreenRecorder(screenRecorder);
        screenRecorder.start();
    }
}