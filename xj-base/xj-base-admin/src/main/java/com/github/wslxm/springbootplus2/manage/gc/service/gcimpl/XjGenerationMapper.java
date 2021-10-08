package com.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import com.github.wslxm.springbootplus2.manage.gc.controller.XjGenerateController;
import com.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import com.github.wslxm.springbootplus2.manage.gc.service.XjGenerationSevice;
import com.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationMapper extends BaseIServiceImpl implements XjGenerationSevice {


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
    public void run(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "Mapper");
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // 文件url记录
        XjGenerateController.pathMap.put("mapper", getBaseUrl(request) +"/" +brBwPath.get("path").toString());
    }

}
