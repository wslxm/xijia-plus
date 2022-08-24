package io.github.wslxm.springbootplus2.manage.gc.service.gc.gcimpl;

import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.service.gc.GcSevice;
import io.github.wslxm.springbootplus2.manage.gc.util.GcFileUtil;
import org.springframework.stereotype.Component;

@SuppressWarnings("all")
@Component
public class GcMapper extends BaseServiceImpl implements GcSevice {

    /**
     * 模板key
     */
    public static final String KEY_NAME = "X-Mapper";
    /**
     * 生成Dao
     *
     * @param data    数据
     * @param GenerateConfig 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void run(GcConfig gcConfig){
        // 开始生成文件并进行数据替换
        GcFileUtil.replacBrBwWritee(gcConfig, GcFileUtil.getBrBwPath(gcConfig, KEY_NAME));
    }
}
