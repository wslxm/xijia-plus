//package com.ws.ldy.common.utils;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//
//import javax.imageio.ImageIO;
//import javax.imageio.stream.ImageOutputStream;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//@SuppressWarnings("ALL")
//public class CreatrQrCode2 {
//
//    // logo 默认边框颜色
//    public static final Color DEFAULT_BORDERCOLOR = Color.WHITE;
//    // logo 默认边框宽度
//    public static final int DEFAULT_BORDER = 2;
//    // logo 大小默认为照片的1/4
//    public static final int DEFAULT_LOGOPART = 4;
//    // 生成后的二维码高宽
//    public static final int WIDTH = 400;
//    public static final int HEIGHT = 450;
//
//
//    private final int border = DEFAULT_BORDER;
//    private final Color borderColor;
//    private final int logoPart;
//
//    /**
//     * Creates a default config with on color {@link #BLACK} and off color
//     * {@link #WHITE}, generating normal black-on-white barcodes.
//     * 在颜色{@ link #黑}和颜色上创建一个默认配置
//     *  {@link # WHITE}，生成正常的黑白条码。
//     */
//    public CreatrQrCode2() {
//        this(DEFAULT_BORDERCOLOR, DEFAULT_LOGOPART);
//    }
//
//    public CreatrQrCode2(Color borderColor, int logoPart) {
//        this.borderColor = borderColor;
//        this.logoPart = logoPart;
//    }
//
//    public Color getBorderColor() {
//        return borderColor;
//    }
//
//    public int getBorder() {
//        return border;
//    }
//
//    public int getLogoPart() {
//        return logoPart;
//    }
//
//    /**
//     * 给二维码图片添加Logo
//     *
//     * @param image  二维码图片对象
//     * @param imageLogo log 图片对象
//     * @param creatrQrCode
//     */
//    private static void addLogo_QRCode(BufferedImage image) {
//        try {
//            // image =  ImageIO.read(new File("D:/ceshi/ewm.jpg"));
//            BufferedImage imageLogo = ImageIO.read(new File("D:/ceshi/3.jpg"));
//            //在二维码中加入图片
//            //LogoConfig中设置Logo的属性
//            CreatrQrCode2 creatrQrCode = new CreatrQrCode2();
//            // 读取二维码图片，并构建绘图对象
//            Graphics2D g = image.createGraphics();
//            //
//            int widthLogo = image.getWidth() / creatrQrCode.getLogoPart();
//            //  int  heightLogo = image.getHeight()/logoConfig.getLogoPart();
//            int heightLogo = image.getWidth() / creatrQrCode.getLogoPart(); //保持二维码是正方形的
//
//            // 计算图片放置位置
//            int x = (image.getWidth() - widthLogo) / 2;
//            int y = (image.getHeight() - heightLogo) / 2;
//
//            //开始绘制图片
//            g.drawImage(imageLogo, x, y, widthLogo, heightLogo, null);
//            g.drawRoundRect(x, y, widthLogo, heightLogo, 10, 10);
//            g.setStroke(new BasicStroke(creatrQrCode.getBorder()));
//            g.setColor(creatrQrCode.getBorderColor());
//            g.drawRect(x, y, widthLogo, heightLogo);
//
//            //
//            // ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(image);
//            ImageIO.write(image, "jpg", new File("D:/ceshi/ewm-loge.jpg"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * @param pressText 文字
//     * @param newImg    带文字的图片
//     * @param targetImg 需要添加文字的图片
//     * @param fontStyle
//     * @param color
//     * @param fontSize
//     * @param width
//     * @param height
//     * @为图片添加文字
//     */
//    private static void pressText(String pressText, String newImg, String targetImg, int fontStyle, Color color, int fontSize, int width, int height) {
//
//        //计算文字开始的位置
//        //x开始的位置：（图片宽度-字体大小*字的个数）/2
//        int startX = (width - (fontSize * pressText.length())) / 300;
//        //y开始的位置：图片高度-（图片高度-图片宽度）/2
//        int startY = height - (height - width) / 10;
//
//        try {
//            File file = new File(targetImg);
//            Image src = ImageIO.read(file);
//            int imageW = src.getWidth(null);
//            int imageH = src.getHeight(null);
//            BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
//            Graphics g = image.createGraphics();
//            g.drawImage(src, 0, 0, imageW, imageH, null);
//            g.setColor(color);
//            g.setFont(new Font(null, fontStyle, fontSize));
//            g.drawString(pressText, startX, startY);
//            g.dispose();
//
//            FileOutputStream out = new FileOutputStream(newImg);
//            ImageIO.write(image, "JPEG", out);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(image);
//            out.close();
//            System.out.println("image press success");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//
//    /**
//     * 生成二维码
//     * @author wangsong
//     * @param content 二维码中的内容
//     *
//     * @date 2020/11/12 0012 10:11
//     * @return void
//     * @version 1.0.0
//     */
//    public static void generateQRCode(String content) {
//        try {
//            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//            Map hints = new HashMap();
//            // 设置UTF-8， 防止中文乱码
//            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//            // 设置二维码四周白色区域的大小
//            hints.put(EncodeHintType.MARGIN, 0);
//            // 设置二维码的容错性
//            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
//
//            //画二维码，记得调用multiFormatWriter.encode()时最后要带上hints参数，不然上面设置无效， 因为要在二维码下方附上文字，所以把图片设置为长方形（高大于宽）
//            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
//
//            // 开始画二维码,返回二维码的流
//            // MatrixToImageWriter.writeToFile(bitMatrix, "jpg", qrcFile);
//            BufferedImage img = MatrixToImageWriter.toBufferedImage(bitMatrix);
//            ImageIO.write(img, "jpg", new File("D:/ceshi/ewm.jpg"));
//
//
//            //logoFile准备放在二维码中的图片(path:图片路径,图片名称)
//            addLogo_QRCode(img);
//            //CreatrQrCode.addLogo_QRCode(new File("D:/ceshi/ewm.jpg"), new File("D:/ceshi/3.png"),new CreatrQrCode());
//
//
//            int font = 20; //字体大小
//            int fontStyle = 4; //字体风格
//
//            //用来存放的带有logo+文字的二维码图片
//            String newImageWithText = "D:/ceshi/456.jpg";
//            //带有logo二维码图片
//            String targetImage = "D:/ceshi/789.png";
//            //附加在图片上的文字信息
//            String text = "my name is Shi Linwei";
//
//            //在二维码下方添加文字（文字居中）
//            pressText(text, newImageWithText, targetImage, fontStyle, Color.red, font, WIDTH, HEIGHT);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     *
//     * @param args
//     */
//    public static void main(String args[]) {
//        generateQRCode("www.baidu.com");
//    }
//}