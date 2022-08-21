package io.github.wslxm.springbootplus2.manage.gc.service.gc;

import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;

/**
  * 代码生成执行器
  * @author wangsong
  * @mail  1720696548@qq.com
  * @date  2022/8/21 0021 9:15
  * @version 1.0.0
  */
@SuppressWarnings("all")
public interface GcSevice {

    /***
     * 执行方法
     * @param data  数据
     * @param path  生成代码路径 模板位置
     * @param path  生成代码路径
     * @date 2019/11/20 19:18
     * @return void
     */
    public void run(GcConfig gcConfig);

}
