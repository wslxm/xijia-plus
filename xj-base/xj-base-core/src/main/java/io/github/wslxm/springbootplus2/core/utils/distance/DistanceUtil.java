package io.github.wslxm.springbootplus2.core.utils.distance;

import lombok.extern.slf4j.Slf4j;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;

/**
 * 距离计算工具类 (根据经纬度计算距离（百度地图距离）)
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/2/3 0003 10:36
 * @version 1.0.1
 */
@Slf4j
public class DistanceUtil {

    public static void main(String[] args) {
        log.debug("经纬度距离计算结果：{} 米", getDistance(104.087421, 30.542043, 104.147618, 30.635065));
    }


    /**
     * 获取距离
     * @param longitudeTo      当前定位经度 (比如用户位置)
     * @param latitudeTo       当前定位纬度 (比如用户位置)
     * @param longitudeFrom    指定位置经度 (比如商家位置)
     * @param latitudeFrom     指定位置纬度 (比如商家位置)
     * @return 返回距离(单位米) -->  返回数据除1000等于公里数
     */
    public static double getDistance(double longitudeTo, double latitudeTo, double longitudeFrom, double latitudeFrom) {
        GlobalCoordinates source = new GlobalCoordinates(latitudeFrom, longitudeFrom);
        GlobalCoordinates target = new GlobalCoordinates(latitudeTo, longitudeTo);
        return new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, source, target).getEllipsoidalDistance();
    }
}
