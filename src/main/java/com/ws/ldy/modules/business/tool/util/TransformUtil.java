package com.ws.ldy.modules.business.tool.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 文字符号生成器
 * @author wangsong
 * @date 2020/12/11 0011 13:43
 * @return
 * @version 1.0.0
 */
public class TransformUtil {

    // public static final String FILL = "#";
    // public static final int NARROW = 3 ;	    // 缩小倍数
    public static final String EMPTY = " ";
    public static final String FONT_TYPE = "楷体";
    public static final int IMG_HEIGHT = 80;
    public static final int FONT_WIDTH = 100;    // 每个文字在图片中的宽度
    public static final int FONT_SIZE = 100;     // 文字大小
    public static final int FONT_X = 0;
    public static final int FONT_Y = 70;
    public static final int COLOR_ALLOWANCE = 2000;    //颜色容差

    public static void main(String[] args) {
        String result = transform("兮家小二", 5, "#");
        System.out.println(result);
    }

    /**
     * 汉字生成 字符
     * @author wangsong
     * @param input 内容
     * @param narrow 倍数
     * @param fill 使用符号
     * @date 2020/12/11 0011 13:45
     * @return java.lang.String
     * @version 1.0.0
     */
    public static String transform(String input, Integer narrow, String fill) {

        StringBuilder sb = new StringBuilder();

        /* 根据文字长度，创建指定长度的Image，然后将文字写入到Image中 */
        BufferedImage bi = new BufferedImage(input.length() * FONT_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        Font font = new Font(FONT_TYPE, Font.BOLD, FONT_SIZE);
        g.setFont(font);
        g.drawString(input, FONT_X, FONT_Y);

        int width = bi.getWidth();
        int height = bi.getHeight();

        /* 根据图像的长宽，逐个遍历图像的点 */
        for (int i = 0; i < height; i += narrow) {
            for (int j = 0; j < width; j += narrow) {
                Color color = new Color(bi.getRGB(j, i));
                int rc = (int) (Math.pow((Color.WHITE.getRed() - color.getRed()), 2) +
                        Math.pow((Color.WHITE.getGreen() - color.getGreen()), 2) +
                        Math.pow((Color.WHITE.getBlue() - color.getBlue()), 2));
                /* 根据容差值填充字符串 */
                String tmp = rc < COLOR_ALLOWANCE ? fill : EMPTY;
                sb.append(tmp);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
