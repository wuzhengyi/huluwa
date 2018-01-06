/*
 * Created by JFormDesigner on Sat Jan 06 16:40:23 CST 2018
 */

package nju.java;

import nju.java.record.MyDialog;
import nju.java.record.WnetWScreenRecordPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * @author Brainrain
 */
public class loginUI extends JFrame {
    public loginUI() {
        initComponents();
    }

    private void startGameMouseClicked(MouseEvent e) {
        // TODO add your code here
        MyDialog myDialog = new MyDialog(new Frame());
        myDialog.show();
        this.hide();
    }

    private void closeGameMouseClicked(MouseEvent e) {
        // TODO add your code here
        this.hide();
    }

    private void playRecordMouseClicked(MouseEvent e) {
        // TODO add your code here
        new WnetWScreenRecordPlayer();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        startGame = new JButton();
        closeGame = new JButton();
        playRecord = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- startGame ----
        startGame.setText("\u5f00\u59cb\u6e38\u620f");
        startGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startGameMouseClicked(e);
            }
        });
        contentPane.add(startGame);
        startGame.setBounds(30, 50, 197, 47);

        //---- closeGame ----
        closeGame.setText("\u7ed3\u675f\u6e38\u620f");
        closeGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeGameMouseClicked(e);
            }
        });
        contentPane.add(closeGame);
        closeGame.setBounds(30, 135, 197, closeGame.getPreferredSize().height);

        //---- playRecord ----
        playRecord.setText("\u56de\u653e\u5f55\u50cf");
        playRecord.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playRecordMouseClicked(e);
            }
        });
        contentPane.add(playRecord);
        playRecord.setBounds(30, 220, 197, playRecord.getPreferredSize().height);

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
        initBackground();
        setLocationRelativeTo(null);
    }

    public void initBackground(){
        URL loc = this.getClass().getClassLoader().getResource("loginBackground.png");
        ImageIcon img = new ImageIcon(loc);
//        Image img = iia.getImage();

        JLabel imgLabel = new JLabel(img);//将背景图放在标签里�??
        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里�??
        imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//设置背景标签的位�?
        Container cp=this.getContentPane();
//        cp.setLayout(new BorderLayout());
        startGame.setBounds(new Rectangle(30,30,100,30));
        playRecord.setBounds(new Rectangle(30,90,100,30));
        closeGame.setBounds(new Rectangle(30,150,100,30));
        cp.setLayout(null);

        ((JPanel)cp).setOpaque(false); //注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来�?
        this.setSize(img.getIconWidth(),img.getIconHeight());
        this.setVisible(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton startGame;
    private JButton closeGame;
    private JButton playRecord;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
