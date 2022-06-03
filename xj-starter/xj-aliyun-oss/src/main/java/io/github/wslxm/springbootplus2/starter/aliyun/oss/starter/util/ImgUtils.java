package io.github.wslxm.springbootplus2.starter.aliyun.oss.starter.util;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈图片压缩工具类〉
 *
 * @author yc
 * @create 2020/10/18 22:43
 * @since 1.0.1
 */
@Slf4j
public class ImgUtils {

    /**
     *
     * 传递byte数据，返回压缩后的 byte 数组
     * @param bytes  压缩前的 byte 数组
     * @return qality  压缩率
     */
    public static byte[] compressPic(byte[] bytes, float qality) {
        BufferedImage bufferedImage = null;
        try {
            //将b作为输入流；
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            bufferedImage = ImageIO.read(in);
            bytes = compressPic(bufferedImage, qality);
            return bytes;
        } catch (IOException e) {
            log.error(e.toString());
        }
        return new byte[]{};
    }


    public static byte[] compressPic(BufferedImage targetImage, float qality) throws IOException {

        // 指定压缩后图片格式为png(jpg 会失色)
        ImageWriter imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageWriteParam imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);

        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

        // 这里指定压缩的程度，参数qality是取值0~1范围内，数字越小压缩率越大
        imgWriteParams.setCompressionQuality((float) qality);
        imgWriteParams.setProgressiveMode(ImageWriteParam.MODE_DISABLED);

        // 防止图片变红(设为白底), 创建输出流
        BufferedImage newBufferedImage = new BufferedImage(targetImage.getWidth(), targetImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        newBufferedImage.createGraphics().drawImage(targetImage, 0, 0, Color.WHITE, null);
        IIOImage ioImage = new IIOImage(newBufferedImage, null, null);

        // 开始压缩，重新构造输入流 OutputStream，调用write方法, 向输入流写入图片
        ByteArrayOutputStream outArray = new ByteArrayOutputStream();
        imgWrier.reset();
        imgWrier.setOutput(ImageIO.createImageOutputStream(outArray));
        imgWrier.write(null, ioImage, imgWriteParams);

        // 返回
        return outArray.toByteArray();
    }


    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }


    /**
     * @Author ws
     * @Description inputstream 转 byte[]
     * @Date 23:53 2020/10/18
     * @Param [stream]
     * @return byte[]
     **/
    public static byte[] inputstreamToByte(InputStream stream) {
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = 0;
        try {
            while ((len = stream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, len);
            }
            byteArrayOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            log.error(e.toString());
        }
        return bytes;
    }

    public static InputStream byteToInputStream(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

}

