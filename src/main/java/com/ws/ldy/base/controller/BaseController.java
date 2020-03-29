package com.ws.ldy.base.controller;

import com.ws.ldy.common.result.Result;
import com.ws.ldy.common.result.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("all")
public class BaseController {

    @Autowired
    protected HttpSession session;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected RestTemplate restTemplate;

    //TODO  返回成功,带数据+页数
    public <T> Result<T> success(T data, Integer count) {
        return new Result(ResultEnum.SYS_SUCCESS, data, count);
    }

    //TODO  返回成功,带数据-不带页数
    public <T> Result<T> success(T data) {
        return new Result(ResultEnum.SYS_SUCCESS, data, 0);
    }

    // TODO 返回成功，-不带数据 -不带页数
    public Result<Void> success() {
        return new Result(ResultEnum.SYS_SUCCESS, null, 0);
    }

    // TODO 返回失败（传入自定义枚举）
    public <T> Result<T> error(Integer code, String msg) {
        return new Result(code, msg, null, 0);
    }

    // TODO 返回失败（传入自定义枚举）
    public <T> Result<T> error(ResultEnum resultType) {
        return new Result(resultType, null, 0);
    }


    //TODO  获取token
    public String getToken() {
        String token = request.getHeader("token");
        return token;
    }


    // TODO 默认值
    public static Integer castToInt(Object value, Integer defaults) {
        if (value == null) {
            return defaults;
        } else {
            return Integer.parseInt(value.toString());
        }
    }


    // TODO 获取项目部署后的classpath目录
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
        if (str == null) {
            return defalut;
        } else {
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
        if (str == null) {
            return defalut;
        } else {
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
        if (str == null) {
            return defalut;
        } else {
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
        if (str == null) {
            return defalut;
        } else {
            try {
                return Boolean.valueOf(str);
            } catch (NumberFormatException e) {
                return defalut;
            }
        }
    }


    /**
     * TODO  获取当前项目的父级硬盘目录 --> 如当前：D:\workSpace\tool1\code\spring-boot-plus2
     *
     * @return java.lang.String
     * @date 2019/11/21 10:02
     */
    public String getPathFather(String entryName) {
        // 获取项目跟目录
        String path = "";
        try {
            //D:\workSpace\tool1\code\spring-boot-plus2\项目名\target\classes
            path = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String upPath = path.replace("/target/classes", "")
                .replace("/target/admin-console.jar!/BOOT-INF/admin-console", "")
                .replace("\\target\\admin-console.jar!\\BOOT-INF\\admin-console", "");
        System.out.println(upPath.length());
        int index = upPath.substring(0, upPath.length() - 1).lastIndexOf("/");
        String newUpPath = upPath.substring(0, index) + "/";
        return newUpPath;
    }
}
