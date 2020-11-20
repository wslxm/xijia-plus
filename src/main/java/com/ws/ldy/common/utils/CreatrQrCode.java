package com.ws.ldy.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * 二维码生成
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/11/12 0012 16:35
 * @version 1.0.0
 */
@SuppressWarnings("ALL")
public class CreatrQrCode {

    // 生成后的二维码高宽
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    // 生成二维码的文件路径(单纯的二维码，带logo的二维码，带logo+文字的二维码)
    public static final String IMG = "File/img/qrCode.jpg";
    public static final String IMG_LOGO = "File/img/qrCode_logo.jpg";
    public static final String IMG_LOGO_TEXT = "File/img/qrCode_logo_text.jpg";
    // 生成的二维码文件格式 FILE_TYPE
    public static final String FILE_TYPE = "jpeg";
    // 带logo的二维码属性配置
    public static final Color DEFAULT_BORDERCOLOR = Color.WHITE; // logo 默认边框颜色
    public static final int DEFAULT_BORDER = 2;                  // logo 默认边框宽度
    public static final int DEFAULT_LOGOPART = 4;                // logo 大小默认为照片的1/4
    private static final int border = DEFAULT_BORDER;
    private static final Color borderColor = DEFAULT_BORDERCOLOR;
    private static final int logoPart = DEFAULT_LOGOPART;


    /**
     * 测试
     * @author wangsong
     * @param args
     * @date 2020/11/12 0012 16:36
     * @return void
     * @version 1.0.0
     */
    public static void main(String args[]) throws IOException {
        // 1、生成普通的二维码
        File file1 = generateQRCode("http://www.baidu.com");

        // 2、生成代logo的二维码
        File file2 = generateQRCode("http://www.baidu.com", ImageIO.read(new File("D:/ceshi/3.jpg")));

        // 3、生成代logo+文字的二维码-> 二维码中的文字(左下)
        File file3 = generateQRCode("http://www.baidu.com", ImageIO.read(new File("D:/ceshi/3.jpg")), "id:156465514");

        System.out.println("生成成功");

        // 保存到本地文件夹查看
        addFile(file1, "E:/test/file1.jpg");
        addFile(file2, "E:/test/file2.jpg");
        addFile(file3, "E:/test/file3.jpg");
    }


    // 外部方法1： 生成 普通的二维码
    public  static File generateQRCode(String content) {
        return generateQRCode(content, null, null, 1);
    }

    // 外部方法2： 生成带 logo的二维码
    public  static File generateQRCode(String content, BufferedImage logoImg) {
        return generateQRCode(content, logoImg, null, 2);
    }

    // 外部方法3： 生成带 logo+文字的二维码
    public  static File generateQRCode(String content, BufferedImage logoImg, String text) {
        return generateQRCode(content, logoImg, text, 3);
    }


    /**
     * 生成二维码
     *
     * @author wangsong
     * @param content 二维码中的内容
     * @param logoImg 二维码中的logo
     * @param text 二维码中的文字
     * @param type 1=生成平台的二维码  2=生成带logo的二维码  3=生成带logo+文字的二维码
     *
     * @date 2020/11/12 0012 10:11
     * @return void
     * @version 1.0.0
     */
    public synchronized static File generateQRCode(String content, BufferedImage logoImg, String text, int type) {
        // 生成 普通的二维码
        File qrPic = qrCode(content);
        if (type == 1) {
            return qrPic;
        }
        // 生成带 logo的二维码
        File qrLogoFile = qrCodeLogo(qrPic, logoImg);
        if (type == 2) {
            return qrLogoFile;
        }
        // 生成带 logo + 文字的二维码
        File qrLogoText = qrCodeLogoText(text);
        if (type == 3) {
            return qrLogoText;
        }
        return null;
    }


    /**
     *  生成普通的二维码
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2020/11/12 0012 14:53
     * @version 1.0.0
     * @return File 二维码的文件
     */
    public static File qrCode(String content) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map hints = new HashMap();
        // 设置UTF-8， 防止中文乱码
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 设置二维码四周白色区域的大小
        hints.put(EncodeHintType.MARGIN, 0);
        // 设置二维码的容错性
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 画二维码，记得调用 multiFormatWriter.encode()时最后要带上hints参数，不然上面设置无效， 因为要在二维码下方附上文字，所以把图片设置为长方形（高大于宽）
        BitMatrix bitMatrix = null;
        // 存放生成的二维码图片路径
        File qrcFile = new File(IMG);
        existsFile(qrcFile);
        try {
            // 生成二维码
            bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            // 开始画二维码
            MatrixToImageWriter.writeToFile(bitMatrix, FILE_TYPE, qrcFile);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return qrcFile;
    }


    /**
     * 给二维码图片添加Logo
     * @author wangsong
     * @param qrPic
     * @param logo
     * @date 2020/11/12 0012 16:36
     * @return java.io.File
     * @version 1.0.0
     */
    public static File qrCodeLogo(File qrPic, BufferedImage logo) {
        File file = new File(IMG_LOGO);
        try {
            CreatrQrCode creatrQrCode = new CreatrQrCode();
            // 读取二维码图片，并构建绘图对象
            BufferedImage image = ImageIO.read(qrPic);
            Graphics2D g = image.createGraphics();
            // 计算宽高
            int widthLogo = image.getWidth() / logoPart;
            // int  heightLogo = image.getHeight()/logoConfig.getLogoPart();
            int heightLogo = image.getWidth() / logoPart; //保持二维码是正方形的
            // logo放置位置
            int x = (image.getWidth() - widthLogo) / 2;
            int y = (image.getHeight() - heightLogo) / 2;

            //开始绘制图片
            g.drawImage(logo, x, y, widthLogo, heightLogo, null);
            g.drawRoundRect(x, y, widthLogo, heightLogo, 10, 10);
            g.setStroke(new BasicStroke(border));
            g.setColor(borderColor);
            g.drawRect(x, y, widthLogo, heightLogo);
            ImageIO.write(image, FILE_TYPE, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    /**
     * 为图片添加文字
     * @author wangsong
     * @param pressText 文字
     * @date 2020/11/12 0012 16:36
     * @return java.io.File
     * @version 1.0.0
     */
    private static File qrCodeLogoText(String pressText) {
        // 计算文字开始的位置
        Color color = Color.RED;
        // 字体大小
        int fontSize = 20;
        // 字体样式
        int fontStyle = 4;
        // x开始的位置：（图片宽度-字体大小*字的个数）/2
        //int startX = (WIDTH - (fontSize * pressText.length())) / 300;
        // y开始的位置：图片高度-（图片高度-图片宽度）/2
        // int startY = HEIGHT - (HEIGHT - WIDTH) / 10;
        int startX = 12;
        int startY = HEIGHT - 12;
        // 获取带二维码的文件
        File file = new File(IMG_LOGO);
        File outfile = new File(IMG_LOGO_TEXT);
        try {
            Image src = ImageIO.read(file);
            int imageW = src.getWidth(null);
            int imageH = src.getHeight(null);
            BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, imageW, imageH, null);
            g.setColor(color);
            g.setFont(new Font(null, fontStyle, fontSize));
            g.drawString(pressText, startX, startY);
            g.dispose();

            // 生成带logo + text的文件
            FileOutputStream out = new FileOutputStream(outfile);
            ImageIO.write(image, "JPEG", out);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
            System.out.println("image press success");
        } catch (Exception e) {
            System.out.println(e);
        }
        return outfile;
    }


    /**
     * 判断文件是否存在，不存在创建
     */
    private static void existsFile(File file) {
        // 判断文件路径是否存在,不存在新建
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 输出到文件夹
     * @param file
     * @param filePath
     */
    private static void addFile(File file, String filePath) {
        try {
            existsFile(file);
            InputStream is = new FileInputStream(file);
            FileOutputStream os = new FileOutputStream(new File(filePath));
            byte[] car = new byte[10000];
            int len = 0;
            while (-1 != (len = is.read(car))) {
                os.write(car, 0, len);
            }
            is.close();
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}