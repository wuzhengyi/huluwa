package record;

import java.awt.*;
import java.awt.image.*;
//import com.sun.image.codec.jpeg.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;


public class WnetWScreenRecorder extends Thread{

    private Dimension screenSize;
    private Rectangle rectangle;
    private Robot robot;
    private long i = 0;
//    private JPEGImageEncoder encoder;

    public WnetWScreenRecorder(Rectangle rectangle) {
//        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        screenSize = Toolkit.
        this.rectangle = rectangle;//可以指定捕获屏幕区域
//        this.rectangle = new Rectangle(10,10,100,100);
        try{
            robot = new Robot();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        try{
            deletefile("C:\\records");
        }catch (Exception e){
            e.printStackTrace();
        }

        new File("C:\\records").mkdirs();
    }

//    public static void main(String[] args) {
//        new WnetWScreenRecorder().start();
//    }

    public void run(){
//        FileOutputStream fos = null;
        while (true){
            try{
                BufferedImage image = robot.createScreenCapture(rectangle);//捕获制定屏幕矩形区域
//                fos = new FileOutputStream("C:\\records\\" + i + ".png");
//                JPEGCodec.createJPEGEncoder(fos).encode(image);//图像编码成JPEG
                ImageIO.write(image,"png",new File("C:\\records\\" + i + ".png"));
//                fos.close();
                i = i + 1;
                Thread.sleep(40);//每秒25帧
            }catch(Exception e){
                e.printStackTrace();
                System.out.println(e);
            }
        }
    }

    public static boolean deletefile(String delpath) throws Exception {
        File file = new File(delpath);
        if (file.isDirectory()) {
            String[] filelist = file.list();
            for (String delFile : filelist) {
                File delfile = new File(delpath + File.separator + delFile);
                if (delfile.isDirectory()) {
                    deletefile(delpath + File.separator + delFile);
                } else
                    System.out.println("正在删除文件：" + delfile.getPath() + ",删除是否成功：" + delfile.delete());
            }
            System.out.println("正在删除空文件夹：" + file.getPath() + ",删除是否成功：" + file.delete());
        } else
            System.out.println("正在删除文件：" + file.getPath() + ",删除是否成功：" + file.delete());
        return true;
    }
}


