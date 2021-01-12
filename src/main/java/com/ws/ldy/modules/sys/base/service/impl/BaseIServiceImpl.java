package com.ws.ldy.modules.sys.base.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 通用 IService + baseMapper  ===>  所有service 继承 BaseIService
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/10/31 21:12
 */
@SuppressWarnings("all")
@Slf4j
public class BaseIServiceImpl<M extends BaseMapper<T>,  T> extends ServiceImpl<M, T> {

    /**
     * 当前继承this类传递的Mapper 类 (提供Api操作，及Mapper.xml自定义sql)
     */
    @Autowired
    protected M baseMapper;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;

    /**
     * rpc工具,可以直接发起http请求
     */
    @Autowired
    protected RestTemplate restTemplate;
    /**
     * 数据库操作
     * execute：可以执行所有SQL语句，没有返回值, 一般用于执行DDL语句。
     * update：用于执行INSERT、UPDATE、DELETE等DML语句。
     * queryXxx：用于DQL数据查询语句。
     */
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    /**
     * this = 当前继承BaseIServiceImpl+T 类的 Iservice类 （对 baseMapper 进行增强, 提供批量操作方法）
     */
    //protected this;
}



