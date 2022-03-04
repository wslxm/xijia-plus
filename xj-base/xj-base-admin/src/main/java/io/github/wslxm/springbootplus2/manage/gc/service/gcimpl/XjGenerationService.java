package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjGcSevice;
import io.github.wslxm.springbootplus2.manage.gc.service.impl.XjGenerationSeviceImpl;
import io.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;
import io.github.wslxm.springbootplus2.manage.gc.util.TemplateParamsReplace;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationService extends BaseIServiceImpl implements XjGcSevice {


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
    public void run(List<DbFieldPO> data,String templatesPath, String path,String suffix) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(templatesPath,path,suffix);

        // 开始生成文件并进行数据替换
        TemplateParamsReplace.replacBrBwWritee(brBwPath);
        // 文件url记录
        XjGenerationSeviceImpl.pathMap.put("service", brBwPath.get("path").toString());
    }
}
