
package io.github.wslxm.springbootplus2.starter.qq.model.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author wangsong
 * Created by Administrator on 2018/10/30/030.
 */
@Data
@ToString
public class QqUserInfo {

    private Integer ret;
    private String msg;
    private Integer isLost;
    private String nickname;
    private String gender;
    private String province;
    private String city;
    private String year;
    private String constellation;
    private String figureurl;
    private String figureurl1;
    private String figureurl2;
    private String figureurlQq;
    private String figureurlQq1;
    private String figureurlQq2;
    private String isYellowVip;
    private String vip;
    private String yellowVipLevel;
    private String level;
    private String isYellowYearVip;


}