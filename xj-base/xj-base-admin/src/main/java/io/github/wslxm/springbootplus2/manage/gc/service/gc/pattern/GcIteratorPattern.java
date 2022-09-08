package io.github.wslxm.springbootplus2.manage.gc.service.gc.pattern;

import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.service.gc.GcSevice;
import io.github.wslxm.springbootplus2.manage.gc.service.gc.gcimpl.*;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 执行代码生成，并依次生成文件
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2022/8/9 14:53
 */
@Service
public class GcIteratorPattern {

    /**
     * 每个模板的代码生成实现 bean
     */
    Map<String, GcSevice> beans = null;

    /**
     * 初始化代码生成实现类到容器中
     *
     * @return void
     * @author wangsong
     * @date 2022/8/9 15:32
     */
    public void init() {
        if (beans == null) {
            // beans = SpringContextUtil.getApplicationContext().getBeansOfType(GcSevice.class);
            beans = new LinkedHashMap<>(16);
            beans.put(GcEntity.KEY_NAME, new GcEntity());
            beans.put(GcDTO.KEY_NAME, new GcDTO());
            beans.put(GcVO.KEY_NAME, new GcVO());
            beans.put(GcQuery.KEY_NAME, new GcQuery());
            beans.put(GcController.KEY_NAME, new GcController());
            beans.put(GcIService.KEY_NAME, new GcIService());
            beans.put(GcIServiceImpl.KEY_NAME, new GcIServiceImpl());
            beans.put(GcMapper.KEY_NAME, new GcMapper());
            beans.put(GcMapperXml.KEY_NAME, new GcMapperXml());
            beans.put(GcVueMain.KEY_NAME, new GcVueMain());
            beans.put(GcVueAdd.KEY_NAME, new GcVueAdd());
            beans.put(GcVueUpd.KEY_NAME, new GcVueUpd());
        }

    }


    /**
     * 依次执行代码生成
     *
     * @param gcConfig
     * @return void
     * @author wangsong
     * @date 2022/8/9 15:02
     */
    public void run(GcConfig gcConfig) {
        this.init();
        for (GcSevice xjGcSevice : beans.values()) {
            xjGcSevice.run(gcConfig);
        }
    }
}
