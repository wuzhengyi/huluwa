import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
import net.miginfocom.swing.*;
import nju.java.field.Ground;
import nju.java.record.WnetWScreenRecordPlayer;
/*
 * Created by JFormDesigner on Fri Jan 05 13:38:16 CST 2018
 */



/**
 * @author unknown
 */
public class login extends JFrame {
    public login() {
        initComponents();
    }

    private void startGameMouseClicked(MouseEvent e) {
        // TODO add your code here
        Ground ground = new Ground();
        ground.setVisible(true);
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
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- startGame ----
        startGame.setText("\u5f00\u59cb\u6e38\u620f");
        startGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startGameMouseClicked(e);
            }
        });
        contentPane.add(startGame, "cell 1 1 7 2,dock center");

        //---- closeGame ----
        closeGame.setText("\u7ed3\u675f\u6e38\u620f");
        closeGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeGameMouseClicked(e);
            }
        });
        contentPane.add(closeGame, "cell 1 4 7 2");

        //---- playRecord ----
        playRecord.setText("\u56de\u653e\u5f55\u50cf");
        playRecord.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playRecordMouseClicked(e);
            }
        });
        contentPane.add(playRecord, "cell 1 7 7 2");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        initBackground();
    }

    public void initBackground(){
        URL loc = this.getClass().getClassLoader().getResource("loginBackground.png");
        ImageIcon img = new ImageIcon(loc);
//        Image img = iia.getImage();

        JLabel imgLabel = new JLabel(img);//将背景图放在标签里。
        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
        imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//设置背景标签的位置
        Container cp=this.getContentPane();
//        cp.setLayout(new BorderLayout());
        startGame.setBounds(new Rectangle(30,30,100,30));
        playRecord.setBounds(new Rectangle(30,90,100,30));
        closeGame.setBounds(new Rectangle(30,150,100,30));
        cp.setLayout(null);

        ((JPanel)cp).setOpaque(false); //注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。
        this.setSize(img.getIconWidth(),img.getIconHeight());
        this.setVisible(true);
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton startGame;
    private JButton closeGame;
    private JButton playRecord;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
