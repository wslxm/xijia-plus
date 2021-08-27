package com.ws.ldy.manage.gc.service.gcimpl;

import com.ws.ldy.core.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.manage.gc.config.GenerateConfig;
import com.ws.ldy.manage.gc.controller.XjGenerateController;
import com.ws.ldy.manage.gc.service.XjGenerationSevice;
import com.ws.ldy.manage.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationServiceImpl extends BaseIServiceImpl implements XjGenerationSevice {


    /**
     * 生成ServiceImpl
     *
     * @param data    数据
     * @param GenerateConfig 数据
     * @param path    生成代码路径
     * @return void
     * @date 2019/11/20 19:18
     */
    @Override
    public void run(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "ServiceImpl");
        this.generateParameters(data);
        // 开始生成文件并进行数据替换
        GenerateDataProcessing.replacBrBwWritee(brBwPath);
        // 文件url记录
        XjGenerateController.pathMap.put("serviceImpl", getBaseUrl(request) + "/" + brBwPath.get("path").toString());
    }


    private void generateParameters(List<Map<String, Object>> data) {
        // MybatisPlus搜索条件数据拼接
        StringBuffer findPageMybatisPlus = new StringBuffer("");
        // 处理参数
        for (Map<String, Object> fieldMap : data) {
            String fieldName = fieldMap.get("name").toString();
            String type = fieldMap.get("type").toString();
            String desc = fieldMap.get("desc").toString();
            Object search = fieldMap.get("search");
            if (search == null || !Boolean.parseBoolean(search.toString())) {
                continue;
            }
            // 字段映射成驼峰模式,在首字母大写
            fieldName = GenerateDataProcessing.getFieldName(fieldName);
            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            //字段  // !=null StringUtils.isNotBlank(account)
            findPageMybatisPlus.append("                ");
            if (type.equals("int") || type.equals("bigint") || type.equals("tinyint")) {
                // 整数 int/long/tinyint
                findPageMybatisPlus.append(".eq(query.get" + fieldName + "() != null," + GenerateConfig.TABLE_NAME_UP + "::get" + fieldName + ",query.get" + fieldName + "())");
            } else if (type.equals("double") || type.equals("float") || type.equals("decimal")) {
                // 单精度小数 Float / 双精度小数 Double / decimal等
                findPageMybatisPlus.append(".eq(query.get" + fieldName + "() != null," + GenerateConfig.TABLE_NAME_UP + "::get" + fieldName + ",query.get" + fieldName + "())");
            } else if (type.equals("varchar") || type.equals("char") || type.equals("text") || type.equals("longtext")) {
                // 字符串 / 大文本、超大文本
                findPageMybatisPlus.append(".eq(StringUtils.isNotBlank(query.get" + fieldName + "())," + GenerateConfig.TABLE_NAME_UP + "::get" + fieldName + ",query.get" + fieldName + "())");
            } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
                // 时间
                findPageMybatisPlus.append(".eq(query.get" + fieldName + "() != null," + GenerateConfig.TABLE_NAME_UP + "::get" + fieldName + ",query.get" + fieldName + "())");
            }
            findPageMybatisPlus.append("\r\n");
        }
        GenerateConfig.FIND_PAGE_MYBATIS_PLUS = findPageMybatisPlus.toString();
    }
}
