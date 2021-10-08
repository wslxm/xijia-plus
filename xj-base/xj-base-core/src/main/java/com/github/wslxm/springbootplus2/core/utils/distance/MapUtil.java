package com.github.wslxm.springbootplus2.core.utils.distance;


import java.util.Arrays;
import java.util.List;

/**
 * 地图相关
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/11/2 0002 15:28 
 * @version 1.0.0
 */
public class MapUtil {
    /**
     * 六大区域划分  hbList=华北地区 dbList=东北地区 hdList=华东地区 znList=中南地区 xnList=西南地区 xbList=西北地区
     */
    private static List<String> hbList = Arrays.asList("北京市", "天津市", "河北省", "山西省", "内蒙古自治区");
    private static List<String> dbList = Arrays.asList("辽宁省", "吉林省", "黑龙江省");
    private static List<String> hdList = Arrays.asList("上海市", "江苏省", "浙江省", "安徽省", "福建省", "江西省", "山东省", "台湾省");
    private static List<String> znList = Arrays.asList("河南省", "湖北省", "湖南省", "广东省", "广西壮族自治区", "海南省", "香港特别行政区", "澳门特别行政区");
    private static List<String> xnList = Arrays.asList("四川省", "贵州省", "云南省", "重庆市", "西藏自治区");
    private static List<String> xbList = Arrays.asList("陕西省", "甘肃省", "青海省", "宁夏回族自治区", "新疆维吾尔自治区", "内蒙古自治区西部");
    /**
     * 七大划分（中南 =   (hzList=华中)  +  (hnList=华南))
     */
    private static List<String> hzList = Arrays.asList("河南省", "湖北省", "湖南省");
    private static List<String> hnList = Arrays.asList("广东省", "广西壮族自治区", "海南省", "香港特别行政区", "澳门特别行政区");

    /**
     * 地图区域划分（六大地理分区）
     * @author wangsong
     * @date 2020/11/2 0002 14:02
     * @return void
     * @version 1.0.0
     */
    public String getArea6(String province) {
        if (hbList.contains(province)) {
            return "华北";
        } else if (dbList.contains(province)) {
            return "东北";
        } else if (hdList.contains(province)) {
            return "华东";
        } else if (znList.contains(province)) {
            return "中南";
        } else if (xnList.contains(province)) {
            return "西南";
        } else if (xbList.contains(province)) {
            return "西北";
        } else {
            return "其他";
        }
    }

    /**
     * 地图区域划分（七大地理分区） - 建议七大区划分
     * @author wangsong
     * @date 2020/11/2 0002 14:02
     * @return void
     * @version 1.0.0
     */
    public static String getArea7(String province) {
        if (hbList.contains(province)) {
            return "华北";
        } else if (dbList.contains(province)) {
            return "东北";
        } else if (hdList.contains(province)) {
            return "华东";
        } else if (hzList.contains(province)) {
            return "华中";
        } else if (hnList.contains(province)) {
            return "华南";
        } else if (xnList.contains(province)) {
            return "西南";
        } else if (xbList.contains(province)) {
            return "西北";
        } else {
            return "其他";
        }
    }
}
