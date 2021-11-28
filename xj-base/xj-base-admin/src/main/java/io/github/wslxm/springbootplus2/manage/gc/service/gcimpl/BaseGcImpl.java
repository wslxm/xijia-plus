package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.template.VueAddUpdTemplate;
import io.github.wslxm.springbootplus2.manage.gc.util.GenerateDataProcessing;

import java.math.BigDecimal;

/**
 * 通用信息处理
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2021/8/27 0027 17:28
 * @version 1.0.1
 */
public class BaseGcImpl extends BaseIServiceImpl {


    /**
     * 数据库类型对应字段生成
     * <P>
     *   用于 entity+dto+vo=query
     * </P>
     * @param fieldName
     * @param type
     */
    @SuppressWarnings("all")
    protected String JXModel(String fieldName, String type) {
        // 转驼峰模式
        fieldName = GenerateDataProcessing.getFieldName(fieldName);
        String field = "";
        //字段
        if (type.equals("int")) {
            //整数int
            field = "private Integer " + fieldName + ";";
        } else if (type.equals("bigint")) {
            //整数Long
            field = "private Long " + fieldName + ";";
        } else if (type.equals("varchar") || type.equals("char")) {
            //字符串
            field = "private String " + fieldName + ";";
        } else if (type.equals("text") || type.equals("longtext")) {
            //大文本、超大文本
            field = "private String " + fieldName + ";";
        } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
            //时间
            field = "private LocalDateTime " + fieldName + ";";
        } else if (type.equals("double")) {
            //双精度小数 Double
            field = "private Double " + fieldName + ";";
        } else if (type.equals("float")) {
            //单精度小数 Float
            field = "private Float " + fieldName + ";";
        } else if (type.equals("decimal")) {
            //小数 decimal
            field = "private BigDecimal " + fieldName + ";";
        } else if (type.equals("tinyint")) {
            //布尔 tinyint
            field = "private Boolean " + fieldName + ";";
        }
        return field;
    }


    /**
     * 必填字段获取 jsr303 验证注解，  NO 代表必填,YES 非必填
     * <P>
     *     [\r\n    ]
     * </P>
     * @param type 字段类型
     * @param typeDetail  字段类型长度  int(11)
     * @param desc 字段备注
     */
    @SuppressWarnings("all")
    protected String JsrModel(String type, String typeDetail, String desc) {
        // 获取数据库注释,去除括号后的内容
        desc = (desc.indexOf("(") == -1) ? desc : desc.substring(0, desc.indexOf("("));
        desc = (desc.indexOf("（") == -1) ? desc : desc.substring(0, desc.indexOf("（"));
        String jsr = null;
        //字段
        if (type.equals("int") || type.equals("bigint")) {
            // 整数
            // int(11), 判断是否有长度,存在长度获取指定长度的最大值, 转为long添加到注解中
            if (typeDetail.indexOf("(") != -1) {
                int len = Integer.parseInt(typeDetail.substring(typeDetail.indexOf("(") + 1, typeDetail.indexOf(")")));
                Double max = (Math.pow(10, len) - 1);
                jsr = "@Range(min=0, max={MAX}L,message = \"{DESC} 必须>=0 和 <={MAX}\")";
                if (type.equals("int")) {
                    jsr = jsr.replaceAll("\\{MAX}", new BigDecimal(max.toString()).intValue() + "").replace("{DESC}", desc);
                    ;
                } else {
                    jsr = jsr.replaceAll("\\{MAX}", new BigDecimal(max.toString()).longValue() + "").replace("{DESC}", desc);
                    ;
                }
            }
        } else if (type.equals("double") || type.equals("float") || type.equals("decimal") || type.equals("float")) {
            //  小数
            //  判断是否有长度,存在长度获取指定长度的最大值, 转为long添加到注解中 decimal(10,2)，取10, 2不处理
            if (typeDetail.indexOf("(") != -1) {
                String typeDetailStr = typeDetail.substring(typeDetail.indexOf("(") + 1, typeDetail.indexOf(")"));
                int len = Integer.parseInt(typeDetailStr.split(",")[0]);
                Double max = (Math.pow(10, len) - 1);
                jsr = "@Range(min=0, max={MAX}L,message = \"{DESC} 必须>=0 和 <={MAX}\")";
                if (type.equals("int")) {
                    jsr = jsr.replaceAll("\\{MAX}", new BigDecimal(max.toString()).intValue() + "").replace("{DESC}", desc);
                    ;
                } else {
                    jsr = jsr.replaceAll("\\{MAX}", new BigDecimal(max.toString()).longValue() + "").replace("{DESC}", desc);
                    ;
                }
            }
        } else if (type.equals("varchar") || type.equals("char") || type.equals("text") || type.equals("longtext")) {
            // 字符串
            if (typeDetail.indexOf("(") != -1) {
                jsr = "@Length(min=0, max={MAX},message = \"{DESC} 必须>=0 和 <={MAX}位\")";
                String max = typeDetail.substring(typeDetail.indexOf("(") + 1, typeDetail.indexOf(")"));
                jsr = jsr.replaceAll("\\{MAX}", max).replace("{DESC}", desc);
            }
        } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
            // 时间暂无
            // fields.append("\r\n" + "    @NotNull(message = \"" + desc + " 不能为空\")");
        }
        return jsr;
    }


    /**
     * 判断当前字段是否勾选
     * @author wangsong
     * @param fieldMap
     * @date 2021/11/4 0004 7:04
     * @return boolean
     * @version 1.0.0
     */
    protected boolean isChecked(DbFieldPO fieldMap) {
        Boolean checked = fieldMap.getChecked();      // 兼容layui
        Boolean isChecked = fieldMap.getIsChecked();  // 兼容vue
        if (checked != null && !Boolean.parseBoolean(checked.toString())) {
            return true;
        }
        if (isChecked != null && !Boolean.parseBoolean(isChecked.toString())) {
            return true;
        }
        return false;
    }

    /**
     * 获取 desc 字段描叙 去掉 () 内后的数据
     * @param fieldMap
     * @return
     */
    protected String getDesc(String desc) {
        if (desc != null) {
            if (desc.indexOf("(") != -1) {
                desc = desc.substring(0, desc.indexOf("("));
            }
            if (desc.indexOf("（") != -1) {
                desc = desc.substring(0, desc.indexOf("（"));
            }
        }
        return desc;
    }


    /**
     * 获取vue 添加或编辑页的 表单数据
     * @param fieldMap
     * @param fieldMap
     * @param fieldMap
     * @param type
     * @return
     */
    protected String JXVueColumns(String name, String type,  String typeDetail, String newDesc, Integer vueFieldType) {
        // 生成表单时获取数据库的字段的长度来控制输入
        Long maxlength = 0L;
        if (type.equals("int") || type.equals("bigint")) {
            // int(11), 判断是否有长度,存在长度获取指定长度的最大值, 转为long添加到注解中
            if (typeDetail.indexOf("(") != -1) {
                int len = Integer.parseInt(typeDetail.substring(typeDetail.indexOf("(") + 1, typeDetail.indexOf(")")));
                Double max = (Math.pow(10, len) - 1);
                maxlength = new BigDecimal(max.toString()).longValue();
            }
        } else if (type.equals("double") || type.equals("float") || type.equals("decimal") || type.equals("float")) {
            //  小数
            //  判断是否有长度,存在长度获取指定长度的最大值, 转为long添加到注解中 decimal(10,2)，取10, 2不处理
            if (typeDetail.indexOf("(") != -1) {
                String typeDetailStr = typeDetail.substring(typeDetail.indexOf("(") + 1, typeDetail.indexOf(")"));
                int len = Integer.parseInt(typeDetailStr.split(",")[0]);
                Double max = (Math.pow(10, len) - 1);
                maxlength = new BigDecimal(max.toString()).longValue();
            }
        } else if (type.equals("varchar") || type.equals("char") || type.equals("text") || type.equals("longtext")) {
            // 字符串
            if (typeDetail.indexOf("(") != -1) {
                String max = typeDetail.substring(typeDetail.indexOf("(") + 1, typeDetail.indexOf(")"));
                maxlength = Long.parseLong(max);
            }
        } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
            //
        }
        // 处理字段
        String columnStr = null;
        name = GenerateDataProcessing.getFieldName(name);
        if (Base.VueFieldType.V1.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.INPUT
                    .replaceAll("\\{label}", newDesc)
                    .replace("{prop}", name)
                    .replace("{maxlength}", maxlength + "");
        } else if (Base.VueFieldType.V2.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.NUMBER.replaceAll("\\{label}", newDesc).replace("{prop}", name);
        } else if (Base.VueFieldType.V3.getValue().equals(vueFieldType)) {
            //
        } else if (Base.VueFieldType.V4.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.RADIO.replaceAll("\\{label}", newDesc).replace("{prop}", name);
        } else if (Base.VueFieldType.V5.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.CHECKBOX.replaceAll("\\{label}", newDesc).replace("{prop}", name);
        } else if (Base.VueFieldType.V6.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.SELECT.replaceAll("\\{label}", newDesc).replace("{prop}", name);
        } else if (Base.VueFieldType.V7.getValue().equals(vueFieldType)) {
            //
        } else if (Base.VueFieldType.V8.getValue().equals(vueFieldType)) {
            //
        } else if (Base.VueFieldType.V9.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.SWITCH.replaceAll("\\{label}", newDesc).replace("{prop}", name);
        } else if (Base.VueFieldType.V10.getValue().equals(vueFieldType)) {
            //
        } else if (Base.VueFieldType.V11.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.DATETIME.replaceAll("\\{label}", newDesc).replace("{prop}", name);
        } else if (Base.VueFieldType.V12.getValue().equals(vueFieldType)) {
            //
        } else if (Base.VueFieldType.V13.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.UPLOAD.replaceAll("\\{label}", newDesc).replace("{prop}", name);
        } else if (Base.VueFieldType.V14.getValue().equals(vueFieldType)) {
            //
        } else if (Base.VueFieldType.V15.getValue().equals(vueFieldType)) {
            //
        } else if (Base.VueFieldType.V16.getValue().equals(vueFieldType)) {
            //
        } else if (Base.VueFieldType.V17.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.TEXTAREA
                    .replaceAll("\\{label}", newDesc)
                    .replace("{prop}", name)
                    .replace("{maxlength}", maxlength + "");
        }
        // 没有默认 input
        if (columnStr == null) {
            columnStr = VueAddUpdTemplate.INPUT
                    .replaceAll("\\{label}", newDesc)
                    .replace("{prop}", name)
                    .replace("{maxlength}", name);
        }
        return columnStr;
    }

}
