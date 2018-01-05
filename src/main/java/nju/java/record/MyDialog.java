/*
 * Created by JFormDesigner on Sat Jan 06 02:55:49 CST 2018
 */

package nju.java.record;

import nju.java.field.Ground;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class MyDialog extends JDialog {
    public MyDialog(Frame owner) {
        super(owner);
        initComponents();
    }

    public MyDialog(MyDialog owner) {
        super(owner);
        initComponents();
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        Ground ground = new Ground();
        ground.setVisible(true);
        ground.recordFight();
        this.hide();
    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        Ground ground = new Ground();
        ground.setVisible(true);
//        ground.recordFight();
        this.hide();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u662f\u5426\u8bb0\u5f55\u672c\u5c40\u5bf9\u6218\uff1f");
        contentPane.add(label1);
        label1.setBounds(75, 55, 205, 55);

        //---- button1 ----
        button1.setText("\u662f(Yes)");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(75, 120), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u5426(No)");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(190, 120), button2.getPreferredSize()));

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        setLocationRelativeTo(null);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
