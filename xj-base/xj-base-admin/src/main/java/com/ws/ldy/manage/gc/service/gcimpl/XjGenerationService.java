package com.ws.ldy.manage.gc.service.gcimpl;

import com.ws.ldy.core.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.manage.gc.controller.XjGenerateController;
import com.ws.ldy.manage.gc.service.XjGenerationSevice;
import com.ws.ldy.manage.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationService extends BaseIServiceImpl implements XjGenerationSevice {


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
    public void run(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "Service");
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // 文件url记录
        XjGenerateController.pathMap.put("service", brBwPath.get("path").toString());
    }
}
