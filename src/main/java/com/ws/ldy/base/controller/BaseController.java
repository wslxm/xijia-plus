package com.ws.ldy.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.admin.model.entity.UserAdmin;
import com.ws.ldy.config.constant.BaseConstant;
import com.ws.ldy.common.utils.BeanDtoVoUtils;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.config.result.Result;
import com.ws.ldy.config.result.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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


    //================================================================
    //================================================================
    //=========================== 返回相关 ============================
    //================================================================
    //================================================================
    //TODO  通用返回成功
    public <T> Result<T> success(T data) {
        return new Result(ResultEnum.SYS_SUCCESS, data);
    }

    public Result<Void> success() {
        return new Result(ResultEnum.SYS_SUCCESS, null);
    }

    // TODO 查询成功
    public Result<Void> successFind() {
        return new Result(ResultEnum.SYS_SUCCESS_FIND, null);
    }

    public <T> Result<T> successFind(T data) {
        return new Result(ResultEnum.SYS_SUCCESS_FIND, data);
    }

    // TODO 添加成功
    public Result<Void> successInsert() {
        return new Result(ResultEnum.SYS_SUCCESS_INSERT, null);
    }

    public <T> Result<T> successInsert(T data) {
        return new Result(ResultEnum.SYS_SUCCESS_INSERT, data);
    }

    // TODO 编辑成功
    public Result<Void> successUpdate() {
        return new Result(ResultEnum.SYS_SUCCESS_UPDATE, null);
    }

    public <T> Result<T> successUpdate(T data) {
        return new Result(ResultEnum.SYS_SUCCESS_UPDATE, data);
    }

    // TODO 删除成功
    public Result<Void> successDelete() {
        return new Result(ResultEnum.SYS_SUCCESS_DELETE, null);
    }

    public <T> Result<T> successDelete(T data) {
        return new Result(ResultEnum.SYS_SUCCESS_DELETE, data);
    }

    // TODO 返回失败（传入自定义错误code+msg）+ （传入自定义枚举）
    public <T> Result<T> error(Integer code, String msg) {
        return new Result(code, msg, null);
    }

    public <T> Result<T> error(ResultEnum resultType) {
        return new Result(ResultEnum.SYS_OPERATE_ERROR, null);
    }


    //=========================================================================================
    //=========================================================================================
    //=========================== 获取系统用户相关 ==================================================
    //=========================================================================================
    //=========================================================================================
    //TODO  获取系统用户token
    public String getToken() {
        String token = request.getHeader(BaseConstant.SYS + "token");
        return token;
    }

    //TODO  获取系统用户完整数据
    public UserAdmin getUserAdmin() {
        String token = request.getHeader("token");
        UserAdmin userAdmin = (UserAdmin) session.getAttribute(BaseConstant.SYS + token);
        // 判断用户是否登录
        if (userAdmin == null) {
            throw new ErrorException(ResultEnum.ADMIN_IS_NO_LOGIN);
        }
        return userAdmin;
    }

    //TODO  获取系统用户Id
    public Integer getUserAdminId() {
        String token = request.getHeader("token");
        UserAdmin userAdmin = (UserAdmin) session.getAttribute(BaseConstant.SYS + token);
        // 判断用户是否登录
        if (userAdmin == null) {
            throw new ErrorException(ResultEnum.ADMIN_IS_NO_LOGIN);
        }
        return userAdmin.getId();
    }


    //================================================================
    //================================================================
    //=========================== 分页相关 ============================
    //================================================================
    //================================================================

    /**
     * TODO 获取分页对象   === mybatis-plus
     *
     * @return
     */
    protected <T> com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> getPage() {
        // 页数
        Integer cursor = this.castToInt(request.getParameter("current"), 1);
        // 分页大小
        Integer limit = this.castToInt(request.getParameter("size"), 20);
        return new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(cursor, limit);
    }

    /**
     * TODO  默认值
     *
     * @author ws
     * @mail 1720696548@qq.com
     */
    public Integer castToInt(Object value, Integer defaults) {
        if (value == null) {
            return defaults;
        } else {
            return Integer.parseInt(value.toString());
        }
    }

    //================================================================
    //================================================================
    //======================dot ,Do ,entity 相互转换 ==================
    //================================================================
    //================================================================
    /**
     * TODO  dot ,Do ,entity 相互转换， 采用 org.springframework.beans.BeanUtils 包
     * 同：BeanUtils.copyProperties(dtoEntity, newInstance);
     *
     * @param oldClass 原数据--Dto，Vo，entity
     * @param newClass 转换为--Dto，Vo，entity
     * @return
     */
    public <E> E convert(Object oldClass, Class<E> newClass) {
        return BeanDtoVoUtils.convert(oldClass, newClass);
    }


    //TODO  Page<Entity> 分页对象转 IPage<Vo>  (采用 page.convert 循环方式)
    public <T, V> IPage<V> pageVo(Page<T> page, Class<V> v) {
        return BeanDtoVoUtils.pageVo(page, v);
    }

    //TODO  list<Entity> 集合对象转list<Vo> ( 采用 list 循环方式)
    public <T, V> List<V> listVo(List<T> oldList, Class<V> v) {
        return BeanDtoVoUtils.listVo(oldList, v);
    }


    //TODO  list<Entity> 集合对象转list<Vo> （采用 Stream 方式）
    public <T, V> List<V> listVoStream(List<T> oldList, Class<V> v) {
        return BeanDtoVoUtils.listVoStream(oldList, v);
    }
}
