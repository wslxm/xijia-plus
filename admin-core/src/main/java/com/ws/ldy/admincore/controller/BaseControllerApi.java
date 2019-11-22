package com.ws.ldy.admincore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseControllerApi {

    @Autowired
    protected HttpSession session;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;

    /**
     * 获取页面字符串
     *
     * @param name
     * @param defalut
     * @return String
     */
    public String getString(String name, String defalut) {
        String str = request.getParameter(name);
        if (str == null) {
            return defalut;
        } else {
            return str;
        }
    }

    /**
     * 获取整数对象
     *
     * @param name
     * @param defalut
     * @return Integer
     */
    public Integer getInt(String name, Integer defalut) {
        String str = request.getParameter(name);
        if (str == null)
            return defalut;
        else {
            try {
                return Integer.valueOf(str);
            } catch (NumberFormatException e) {
                return defalut;
            }
        }
    }

    /**
     * 获取整数对象
     *
     * @param name
     * @param defalut
     * @return Long
     */
    public Long getLong(String name, Long defalut) {
        String str = request.getParameter(name);
        if (str == null)
            return defalut;
        else {
            try {
                return Long.valueOf(str);
            } catch (NumberFormatException e) {
                return defalut;
            }
        }
    }

    /**
     * 获取duoble对象
     *
     * @param name
     * @param defalut
     * @return Double
     */
    public Double getDoule(String name, Double defalut) {
        String str = request.getParameter(name);
        if (str == null)
            return defalut;
        else {
            try {
                return Double.valueOf(str);
            } catch (NumberFormatException e) {
                return defalut;
            }
        }
    }

    /**
     * 获取时间对象
     *
     * @param name
     * @param defalut
     * @param format
     * @return Date
     */
    public Date getDate(String name, Date defalut, String format) {
        String str = request.getParameter(name);
        if (str == null) {
            return defalut;
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return defalut;
            }
        }
    }

    /**
     * 获取boolean对象
     *
     * @param name
     * @param defalut
     * @return boolean
     */
    public boolean getBoolean(String name, boolean defalut) {
        String str = request.getParameter(name);
        if (str == null)
            return defalut;
        else {
            try {
                return Boolean.valueOf(str);
            } catch (NumberFormatException e) {
                return defalut;
            }
        }
    }


    /**
     * TODO  获取项目部署后的classpath目录
     * @date  2019/11/21 10:01
     * @return java.lang.String
     */
    public String getPath() {
        // 获取项目跟目录
        String path = "";
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return path;
    }


    /**
     * TODO  获取当前项目的父级硬盘目录 --> 如当前：D:\workSpace\tool1\code\spring-boot-plus2
     * @date  2019/11/21 10:02
     * @return java.lang.String
     */
    public String getPathFather(String entryName) {
        // 获取项目跟目录
        String path = "";
        try {
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String upPath = path.replace("/"+entryName+"/target/classes", "");
        return upPath;
    }
}
