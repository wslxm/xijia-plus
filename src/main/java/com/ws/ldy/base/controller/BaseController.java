package com.ws.ldy.base.controller;

import com.ws.ldy.admin.entity.UserAdmin;
import com.ws.ldy.base.constant.BaseConstant;
import com.ws.ldy.base.entity.BeanDtoVoUtils;
import com.ws.ldy.base.query.IPage;
import com.ws.ldy.common.error.ErrorException;
import com.ws.ldy.common.result.Result;
import com.ws.ldy.common.result.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    //TODO  返回成功,带数据
    public <T> Result<T> success(T data) {
        return new Result(ResultEnum.SYS_SUCCESS, data);
    }

    // TODO 返回成功，-不带数据
    public Result<Void> success() {
        return new Result(ResultEnum.SYS_SUCCESS, null);
    }

    // TODO 返回失败（传入自定义错误code+msg）
    public <T> Result<T> error(Integer code, String msg) {
        return new Result(code, msg, null);
    }

    // TODO 返回失败（传入自定义枚举）
    public <T> Result<T> error(ResultEnum resultType) {
        return new Result(resultType, null);
    }


    //================================================================
    //================================================================
    //=========================== 系统用户相关 ========================
    //================================================================
    //================================================================
    //TODO  获取系统用户token
    public String getToken() {
        String token = request.getHeader(BaseConstant.SYS + "token");
        return token;
    }

    //TODO  获取系统用户完整数据
    public UserAdmin getUserAdmin() {
        String token = request.getHeader("token");
        UserAdmin userAdmin = (UserAdmin) session.getAttribute(BaseConstant.SYS + token);
        this.logOutOfDate(userAdmin);
        return userAdmin;
    }

    //TODO  获取系统用户Id
    public Integer getUserAdminId() {
        String token = request.getHeader("token");
        UserAdmin userAdmin = (UserAdmin) session.getAttribute(BaseConstant.SYS + token);
        this.logOutOfDate(userAdmin);
        return userAdmin.getId();
    }

    //TODO  获取系统用户Id
    public void logOutOfDate(UserAdmin userAdmin) {
        if (userAdmin == null) {
            throw new ErrorException(ResultEnum.ADMIN_IS_NO_LOGIN);
        }
    }
    //================================================================
    //================================================================
    //=========================== 分页相关 ============================
    //================================================================
    //================================================================

    //TODO  获取分页对象
    public IPage getPage() {
        int page = 1;
        int limit = 20;
        Object pageObj = request.getParameter("page");
        Object limitObj = request.getParameter("limit");
        if (pageObj != null) {
            page = Integer.parseInt(pageObj.toString());
        }
        if (limitObj != null) {
            limit = Integer.parseInt(limitObj.toString());
        }
        return new IPage(page, limit);
    }


    //================================================================
    //================================================================
    //======================dot ,Do ,entity 相互转换 ==================
    //================================================================
    //================================================================

    /**
     * TODO  dot ,Do ,entity 相互转换
     * 同：BeanUtils.copyProperties(dtoEntity, newInstance);
     *
     * @param oldClass 原数据--Dto，Vo，entity
     * @param newClass 转换为--Dto，Vo，entity
     * @return
     */
    public <E> E convert(Object oldClass, Class<E> newClass) {
        return BeanDtoVoUtils.convert(oldClass, newClass);
    }


    //TODO  Page<Entity> 分页对象转 Page<Vo>  ( list 循环)
    public <T, V> Page<V> pageVo(Page<T> page, Class<V> v) {
        return BeanDtoVoUtils.pageVo(page, v);
    }


    //TODO  Page<Entity> 分页对象转 Page<Vo> （Stream 方式）
    public <T, V> Page<V> pageVoStream(Page<T> page, Class<V> v) {
        return BeanDtoVoUtils.pageVoStream(page, v);
    }


    //TODO  list<Entity> 集合对象转list<Vo> ( list 循环)
    public <T, V> List<V> listVo(List<T> oldList, Class<V> v) {
        return BeanDtoVoUtils.listVo(oldList, v);
    }


    //TODO  list<Entity> 集合对象转list<Vo> （Stream 方式）
    public <T, V> List<V> listVoStream(List<T> oldList, Class<V> v) {
        return BeanDtoVoUtils.listVoStream(oldList, v);
    }
}
