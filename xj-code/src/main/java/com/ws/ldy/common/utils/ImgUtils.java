package com.ws.ldy.common.utils;

import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
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
 * @since 1.0.0
 */
public class ImgUtils {

    /**
     *
     *   传递byte数据，返回压缩后的 byte 数组
     * @param bytes  压缩前的 byte 数组
     * @return byte  压缩后的 byte 数组
     */
    public static byte[] compressPic(byte[] bytes) {
        BufferedImage bufferedImage = null;
        try {
            //将b作为输入流；
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            bufferedImage = ImageIO.read(in);
            bytes = compressPic(bufferedImage);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] compressPic(BufferedImage image) throws IOException {
        ByteArrayOutputStream outArray = new ByteArrayOutputStream();
        // 指定写图片的方式为 jpg
        ImageWriter imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageWriteParam imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(
                null);
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        // 这里指定压缩的程度，参数qality是取值0~1范围内，数字越小压缩率越大
        imgWriteParams.setCompressionQuality((float) 0.1);
        imgWriteParams.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
        // ColorModel.getRGBdefault();
        ColorModel colorModel = image.getColorModel();
        imgWriteParams.setDestinationType(new ImageTypeSpecifier(
                colorModel, colorModel.createCompatibleSampleModel(16, 16)));
        try {
            imgWrier.reset();
            // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
            // OutputStream构造
            imgWrier.setOutput(ImageIO.createImageOutputStream(outArray));
            // 调用write方法，就可以向输入流写图片
            imgWrier.write(null, new IIOImage(image, null, null),
                    imgWriteParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outArray.toByteArray();
    }

    /**
     * @Author yc
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
            e.printStackTrace();
        }
        return bytes;
    }

    public static InputStream byteToInputStream(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }


//    public static void main(String[] args) {
//        byte[] bytes = new byte[1024];
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        String old_img_path = "C:\\Users\\yc\\Pictures\\Camera Roll\\11111111.jpg";
//        String new_img_path = "C:\\Users\\yc\\Pictures\\a.jpg";
//        try {
//            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(new File(old_img_path)));
//            int len = 0;
//            while ((len = buffer.read(bytes))!=-1){
//                byteArrayOutputStream.write(bytes,0,len);
//            }
//            byteArrayOutputStream.flush();
//            bytes = byteArrayOutputStream.toByteArray();
//            byte[] picByte = compressPic(bytes);
//
//            FileOutputStream fileOutputStream = new FileOutputStream(new_img_path);
//            fileOutputStream.write(picByte);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

