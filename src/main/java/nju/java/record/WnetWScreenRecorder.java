package nju.java.record;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

//import com.sun.image.codec.jpeg.*;


public class WnetWScreenRecorder extends Thread{
    private Rectangle rectangle;
    private Robot robot;
    private long i = 0;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");//设置日期格式
    Date date;
//    private JPEGImageEncoder encoder;

    public WnetWScreenRecorder(Rectangle rectangle) {
//        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        screenSize = Toolkit.
        this.rectangle = new Rectangle(rectangle.x,rectangle.y+30,rectangle.width - 10,rectangle.height);//可以指定捕获屏幕区域
//        this.rectangle = new Rectangle(10,10,100,100);
        try{
            robot = new Robot();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        date = new Date();
        new File(".\\records\\"+df.format(date)).mkdirs();
    }

    public void run(){
//        FileOutputStream fos = null;
        while (true){
            try{
                BufferedImage image = robot.createScreenCapture(rectangle);//捕获制定屏幕矩形区域
//                fos = new FileOutputStream("C:\\records\\" + i + ".png");
//                JPEGCodec.createJPEGEncoder(fos).encode(image);//图像编码成JPEG
                ImageIO.write(image,"png",new File(".\\records\\"+df.format(date) + "\\" + i + ".png"));
//                fos.close();
                i = i + 1;
                Thread.sleep(40);//每秒25帧
            }catch(Exception e){
                e.printStackTrace();
                System.out.println(e);
            }
        }
    }
}


