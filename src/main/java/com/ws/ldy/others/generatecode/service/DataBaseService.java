package com.ws.ldy.others.generatecode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.ldy.others.generatecode.model.vo.TableFieldVO;
import com.ws.ldy.others.generatecode.model.vo.TableVO;

import java.util.List;

/**
 * TODO  数据库相关数据查询，代码生成，ecxel sql处理内
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 10:40
 */
public interface DataBaseService  extends IService{

    /**
     * 查询数据库的所有表
     * @date 2019/11/20 10:41
     * @return java.util.List<java.lang.String>
     */
    public List<TableVO> findTable();

    /**
     * 查询数据库下指定表的数据-字段名/类型/备注
     *
     * @return java.util.List<java.lang.String>
     * @date 2019/11/20 10:41
     */
    public List<TableFieldVO> findTableField(String table);
}
