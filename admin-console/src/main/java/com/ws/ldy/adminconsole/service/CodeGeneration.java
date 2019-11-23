package com.ws.ldy.adminconsole.service;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.ws.ldy.adminconsole.controller.vo.FieldCG;
import com.ws.ldy.adminconsole.service.base.BaseAdminConsoleService;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * TODO  代码生成
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/20 16:28
 */
public interface CodeGeneration extends BaseAdminConsoleService<T, ID> {

    /**
     * 解析数据前台成自己方便操作的数据
     */
    public List<Map<String, Object>> getDataAnalysis(String data);

    /**
     * 生成实体类代码
     */
    public void buildEntity(List<Map<String, Object>> data, FieldCG fieldCG, String path) throws Exception;

    /**
     * 生成Controller 层代码
     */
    public void buildController(List<Map<String, Object>> data, FieldCG fieldCG, String path) throws IOException;


    /**
     * 生成 service 层代码
     */
    public void buildService(List<Map<String, Object>> data, FieldCG fieldCG, String path) throws IOException;

    /**
     * 生成 serviceImpl 代码
     */
    public void buildServiceImpl(List<Map<String, Object>> data, FieldCG fieldCG, String path) throws IOException;

    /**
     * 生成 dao 层代码
     */
    public void buildDao(FieldCG fieldCG, String path) throws IOException;

    /**
     * 追加dao依赖注如信息
     */
    public void buildDaoFactory(FieldCG fieldCG, String path) throws IOException;

    /**
     * 追加service依赖注如信息
     */
    public void buildServiceFactory(FieldCG fieldCG, String path) throws IOException;

    /**
     * 生成前台展示页代码
     */
    public void buildMainHtml(List<Map<String, Object>> dataList, FieldCG fieldCG,String path) throws IOException;

    /**
     * 生成前台添加页代码
     */
    public void buildAddHtml(List<Map<String, Object>> dataList, FieldCG fieldCG, String path) throws IOException;

    /**
     * 生成前台编辑页代码
     */
    public void buildUpdHtml(List<Map<String, Object>> dataList, FieldCG fieldCG, String path) throws IOException;
}
