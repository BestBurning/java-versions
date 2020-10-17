package com.di1shuai.base.awt;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

/**
 * @author: Bruce
 * @date: 2020-03-03
 * @description:
 */
public class Screenshot {

    /**
     * 截图
     *
     * @param filePath 截图保存文件夹路径
     * @param fileName 截图文件名称
     * @throws Exception
     */
    public static void captureScreen(String filePath, String fileName) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        // 截图保存的路径
        File screenFile = new File(filePath + fileName);
        // 如果文件夹路径不存在，则创建
        if (!screenFile.getParentFile().exists()) {
            screenFile.getParentFile().mkdirs();
        }

        // 指定屏幕区域，参数为截图左上角坐标(100,100)+右下角坐标(500,500)
        BufferedImage subimage = image.getSubimage(100, 100, 500, 500);
        ImageIO.write(subimage, "png", screenFile);

    }

    public static void main(String[] args) throws Exception {
        Date now = new Date();
        SimpleDateFormat sdfPath = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdfName = new SimpleDateFormat("yyyyMMddHHmmss");
        String path = sdfPath.format(now);
        String name = sdfName.format(now);
        captureScreen("C:\\Users\\SF\\Desktop" + File.separator + path + File.separator, name + ".png");
    }
}