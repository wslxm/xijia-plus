package io.github.wslxm.springbootplus2.manage.gc.service.gc.gcimpl;

import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.service.gc.GcSevice;
import io.github.wslxm.springbootplus2.manage.gc.util.GcFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@SuppressWarnings("all")
@Component
@Slf4j
public class GcController extends BaseServiceImpl implements GcSevice {

    /**
     * 模板key
     */
    public static final String KEY_NAME = "X-Controller";
    /**
     * 生成Service层
     *
     * @param data            数据
     * @param this.TABLE_NAME 数据库表名
     * @param path            生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void run(GcConfig gcConfig){
          log.info("开始生成: {}", KEY_NAME);
        // 开始生成文件并进行数据替换
        GcFileUtil.replacBrBwWritee(gcConfig, GcFileUtil.getBrBwPath(gcConfig, KEY_NAME));
    }
}
