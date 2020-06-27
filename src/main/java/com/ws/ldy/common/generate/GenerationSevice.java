package com.ws.ldy.common.generate;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public interface GenerationSevice {

    /***
     * TODO 生成实体类
     *
     * @param data  数据
     * @param this.TABLE_NAME 数据库表名
     * @param path      生成代码路径
     * @date 2019/11/20 19:18
     * @return void
     */
    public void buildEntity(List<Map<String, Object>> data, String path);

    public void buildDTO(List<Map<String, Object>> data, String path);

    public void buildVO(List<Map<String, Object>> data, String path);


    /**
     * TODO 生成Controller层
     *
     * @param data            数据
     * @param this.TABLE_NAME 数据库表名
     * @param path            生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildController(List<Map<String, Object>> data, String path);


    /**
     * TODO 生成Service层
     *
     * @param data            数据
     * @param this.TABLE_NAME 数据库表名
     * @param path            生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildService(List<Map<String, Object>> data, String path);


    // {code1} 包名     {code2} 项目Base 文件名      Demo 实体类名

    /**
     * TODO 生成ServiceImpl
     *
     * @param data    数据
     * @param fieldCG 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildServiceImpl(List<Map<String, Object>> data, String path);


    /**
     * TODO 生成Dao
     *
     * @param data    数据
     * @param fieldCG 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildMapper(List<Map<String, Object>> data, String path);


    /**
     * TODO 生成Html-main 展页
     *
     * @param data    数据
     * @param fieldCG 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildMainHtml(List<Map<String, Object>> dataList, String path);


    /**
     * TODO 生成Html-Add 添加页
     *
     * @param data    数据
     * @param fieldCG 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildAddHtml(List<Map<String, Object>> dataList, String path);


    /**
     * TODO 生成Html-Upd 修改页
     *
     * @param data    数据
     * @param fieldCG 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    public void buildUpdHtml(List<Map<String, Object>> dataList, String path);

}
