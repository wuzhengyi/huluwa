package nju.java.record;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class WnetWScreenRecordPlayer extends JFrame {
    BorderLayout borderLayout1 = new BorderLayout();
    Dimension screenSize;
    String path;

    public WnetWScreenRecordPlayer() {
        super();
        path = ".\\records";
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//只能选择目录
        jf.setCurrentDirectory(new File(".\\records\\"));
//        jf.setSelectedFile(new File(".\\records\\"));
        int value = jf.showOpenDialog(null);
        jf.setFileHidingEnabled(false);
        if (value == JFileChooser.APPROVE_OPTION) {
            File getPath = jf.getSelectedFile();
            // TODO
            path = getPath.getPath();
        } else {
            // TODO
            System.out.println("open default records");
            path = ".\\records\\2018-01-06_15.12.40";
        }

        ImageIcon icon = new ImageIcon(path + "\\1.png");
        this.setSize(icon.getIconWidth(), icon.getIconHeight());

        Screen p = new Screen(path);
        Container c = this.getContentPane();
        c.setLayout(borderLayout1);
        c.add(p, "Center");
        new Thread(p).start();
        this.show();
    }

    public static void main(String[] args) {
        new WnetWScreenRecordPlayer();
    }

}

class Screen extends JPanel implements Runnable {
    private BorderLayout borderLayout1 = new BorderLayout();
    private Image cimage;
    private String path;

    public void run() {
        int i = 0;
        while (true) {
            try {
                cimage = loadImage(i + ".png");
                if(judeFileExists(new File(path + "\\" + (i+1) + ".png")))
                    i = i + 1;
                repaint();
                Thread.sleep(40);//与录像时每秒帧数一致

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
    }

    public Image loadImage(String name) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image image = null;
        image = tk.getImage(path + "\\" + name);
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(image, 0);
        try {
            mt.waitForID(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return image;
    }

    public Screen(String path) {
        super();
        this.setLayout(null);
        this.path = path;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(cimage, 0, 0, null);
    }

    // 判断文件是否存在
    public static boolean judeFileExists(File file) {
        if (file.exists()) {
            return true;
        } else {
           return false;
        }

    }

}
