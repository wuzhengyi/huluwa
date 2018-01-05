import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        background = new JLabel();

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
            "[]"));

        //---- background ----
        background.setIcon(new ImageIcon("C:\\Users\\wzy\\Desktop\\huluwa04.png"));
        contentPane.add(background, "cell 0 0 19 14,dock center");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
//        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
//        background.setBounds(0,0,this.getWidth(), this.getHeight());
    }

    public static void main(String[] args){
        login t = new login();
        t.show();
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel background;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
