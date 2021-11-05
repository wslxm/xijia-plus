package io.github.wslxm.springbootplus2.manage.gc.service;

import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;

import java.util.List;

@SuppressWarnings("all")
public interface XjGcSevice {

    /***
     * 执行方法
     * @param data  数据
     * @param path  生成代码路径
     * @date 2019/11/20 19:18
     * @return void
     */
    public void run(List<DbFieldPO> data, String path);

}
