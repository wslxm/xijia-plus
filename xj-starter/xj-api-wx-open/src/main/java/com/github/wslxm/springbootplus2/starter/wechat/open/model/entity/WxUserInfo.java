package com.github.wslxm.springbootplus2.starter.wechat.open.model.entity;

import java.util.List;


/**
  * 获取用户信息微信返回的完整数据
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2021/7/8 0008 15:31
  * @version 1.0.0
  */
public class WxUserInfo {

    private String openid;
    private String nickname;
    private int sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private List<String> privilege;
    private String unionid;

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSex() {
        return sex;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setPrivilege(List<String> privilege) {
        this.privilege = privilege;
    }

    public List<String> getPrivilege() {
        return privilege;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getUnionid() {
        return unionid;
    }
}
