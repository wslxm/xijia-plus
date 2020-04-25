package com.ws.ldy.base.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * TODO  通用 service，自定义通用方法 (mybatis-plus)
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/10/31 21:12
 */
//@Repository
public class BaseIServiceAndMapper<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements com.baomidou.mybatisplus.extension.service.IService<T> {

    /**
     * TODO
     *
     * @param m Mapper
     * @param t 实体类
     * @return
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/4/16 0016 10:40
     */
    public BaseIServiceAndMapper(M m, Class<T> t) {
        this.baseMapper = m;
        this.entityClass = t;
    }
}
