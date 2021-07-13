package com.ws.ldy.modules.sys.gc.service.gcimpl;

import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.modules.sys.gc.config.GenerateConfig;
import com.ws.ldy.modules.sys.gc.controller.XjGenerateController;
import com.ws.ldy.modules.sys.gc.service.XjGenerationSevice;
import com.ws.ldy.modules.sys.gc.util.GenerateDataProcessing;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Component
public class XjGenerationDTO extends BaseIServiceImpl implements XjGenerationSevice {


    @Override
    public void run(List<Map<String, Object>> data, String path) {
        Map<String, Object> brBwPath = GenerateDataProcessing.getBrBwPath(path, "DTO");
        //数据拼接(所有字段)
        StringBuffer fields = new StringBuffer();
        int position = 0;
        for (Map<String, Object> fieldMap : data) {
            if (!Boolean.parseBoolean(fieldMap.get("checked").toString())) {
                continue;
            }
            String fieldName = GenerateDataProcessing.getFieldName(fieldMap.get("name").toString());
            String type = fieldMap.get("type").toString();
            fields.append("\r\n    @ApiModelProperty(notes = \"" + fieldMap.get("desc") + "\" ,position = " + position++ + ")");
            String isNull = fieldMap.get("isNull").toString();
            //
            String typeDetail = fieldMap.get("typeDetail").toString();
            /**
             *   必填字段添加 jsr303验证 -->   NO 代表必填,YES 非必填
             */
            if (("NO").equals(isNull)) {
                //字段
                if (type.equals("int")
                        || type.equals("bigint")) {
                    /**
                     * 整数
                     */
                    // 获取数据库注释
                    String desc = "";
                    if (fieldMap.get("desc").toString().indexOf("(") != -1) {
                        desc = fieldMap.get("desc").toString().substring(0, fieldMap.get("desc").toString().indexOf("("));
                    } else {
                        desc = fieldMap.get("desc").toString();
                    }
                    // 2 int(11)
                    if (typeDetail.indexOf("(") != -1) {
                        int length = Integer.parseInt(typeDetail.substring(typeDetail.indexOf("(") + 1, typeDetail.indexOf(")")));
                        Double max = (Math.pow(10, length) - 1);
                        if (type.equals("int")) {
                            fields.append("\r\n" + "    @Range(min=0, max=" + new BigDecimal(max.toString()).intValue() + "L"
                                    + ",message = \"" + desc + " 必须>=0 和 <=" + new BigDecimal(max.toString()).intValue()
                                    + "\")");
                        } else {
                            fields.append("\r\n" + "    @Range(min=0, max=" + new BigDecimal(max.toString()).longValue() + "L"
                                    + ",message = \"" + desc + " 必须>=0 和 <=" + new BigDecimal(max.toString()).longValue()
                                    + "\")");

                        }
                    }
                } else if (type.equals("double")
                        || type.equals("float")
                        || type.equals("decimal")
                        || type.equals("float")) {//小数 decimal){
                    /**
                     * 小数
                     */
                    //1
                    String desc = "";
                    if (fieldMap.get("desc").toString().indexOf("(") != -1) {
                        desc = fieldMap.get("desc").toString().substring(0, fieldMap.get("desc").toString().indexOf("("));
                    } else {
                        desc = fieldMap.get("desc").toString();
                    }
                    //   fields.append("\r\n" + "    @NotNull(message = \"" + desc + " 不能为空 \")");
                    //2 decimal(10,2)
                    if (typeDetail.indexOf("(") != -1) {
                        int length = Integer.parseInt(typeDetail.substring(typeDetail.indexOf("(") + 1, typeDetail.indexOf(",")));
                        Double max = Math.pow(10, length) - 1;
                        fields.append("\r\n" + "    @DecimalMin(value = \"0\""
                                + ",message = \"" + desc + " 必须>=0"
                                + "\")");
                        fields.append("\r\n" + "    @DecimalMax(value = \"" + new BigDecimal(max.toString()).longValue() + "\""
                                + ",message = \"" + desc + " 必须<=" + new BigDecimal(max.toString()).longValue()
                                + "\")");
                    }
                } else if (type.equals("varchar")
                        || type.equals("char")
                        || type.equals("text")
                        || type.equals("longtext")) {
                    /**
                     * 字符串
                     */
                    //1 varchar(32)
                    String desc = "";
                    if (fieldMap.get("desc").toString().indexOf("(") != -1) {
                        desc = fieldMap.get("desc").toString().substring(0, fieldMap.get("desc").toString().indexOf("("));
                    } else {
                        desc = fieldMap.get("desc").toString();
                    }
                    //   fields.append("\r\n" + "    @NotBlank(message = \"" + desc + " 不能为空\")");
                    //2
                    if (typeDetail.indexOf("(") != -1) {
                        String max = typeDetail.substring(typeDetail.indexOf("(") + 1, typeDetail.indexOf(")"));
                        fields.append("\r\n" + "    @Length(min=1, max=" + max
                                + ",message = \"" + desc + " 必须>=0 和 <=" + new BigDecimal(max.toString()).intValue()
                                + "位\")");
                    }
                } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
                    /**
                     * 时间
                     */
                    //1
                    String desc = "";
                    if (fieldMap.get("desc").toString().indexOf("(") != -1) {
                        desc = fieldMap.get("desc").toString().substring(0, fieldMap.get("desc").toString().indexOf("("));
                    } else {
                        desc = fieldMap.get("desc").toString();
                    }
                    // 暂无
                    //   fields.append("\r\n" + "    @NotNull(message = \"" + desc + " 不能为空\")");
                }
            }
            //生成字段
            JXModel(fields, fieldName, type);
        }
        // 数据保存到替换对象类,使模板中可以读取
        GenerateConfig.FIELD_ENTITYS = fields.toString();
        GenerateDataProcessing.replacBrBwWritee(brBwPath);    // 开始生成文件并进行数据替换
        XjGenerateController.pathMap.put("DTO", brBwPath.get("path").toString());
    }


    //每一个字段(entity+dto+vo)
    private void JXModel(StringBuffer fields, String fieldName, String type) {
        //字段
        if (type.equals("int")) {
            //整数int
            fields.append("\r\n" + "    private Integer " + fieldName + ";");
        } else if (type.equals("bigint")) {
            //整数Long
            fields.append("\r\n" + "    private Long " + fieldName + ";");
        } else if (type.equals("varchar") || type.equals("char")) {
            //字符串
            fields.append("\r\n" + "    private String " + fieldName + ";");
        } else if (type.equals("text") || type.equals("longtext")) {
            //大文本、超大文本
            fields.append("\r\n" + "    private String " + fieldName + ";");
        } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
            //时间
            fields.append("\r\n" + "    private LocalDateTime " + fieldName + ";");
        } else if (type.equals("double")) {
            //双精度小数 Double
            fields.append("\r\n" + "    private Double " + fieldName + ";");
        } else if (type.equals("float")) {
            //单精度小数 Float
            fields.append("\r\n" + "    private Float " + fieldName + ";");
        } else if (type.equals("decimal")) {
            //小数 decimal
            fields.append("\r\n" + "    private BigDecimal " + fieldName + ";");
        } else if (type.equals("tinyint")) {
            //小数 decimal
            fields.append("\r\n" + "    private Boolean " + fieldName + ";");
        }
        //每生成一次换一次行
        fields.append("\r\n");
    }

}
