package io.github.wslxm.springbootplus2.manage.gc.service.gc.gcimpl;

import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.manage.gc.config.GcConfig;
import io.github.wslxm.springbootplus2.manage.gc.constant.BracketConstant;
import io.github.wslxm.springbootplus2.manage.gc.constant.FieldTypeConstant;
import io.github.wslxm.springbootplus2.manage.gc.model.po.DbFieldPO;
import io.github.wslxm.springbootplus2.manage.gc.template.VueAddUpdSlotTemplate;
import io.github.wslxm.springbootplus2.manage.gc.template.VueAddUpdTemplate;
import io.github.wslxm.springbootplus2.manage.gc.template.VueMainSlotTemplate;
import io.github.wslxm.springbootplus2.manage.gc.util.GcDataUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * 通用信息处理
 *
 * @author wangsong
 * @version 1.0.1
 * @mail 1720696548@qq.com
 * @date 2021/8/27 0027 17:28
 */
public class BaseGcImpl extends BaseServiceImpl {


    /**
     * 数据库类型对应字段生成
     * <p>
     * 用于 entity+dto+vo=query
     * </P>
     *
     * @param fieldName 1
     * @param type 1
     * @param isTimeStr 时间是否使用字符串,只处理 datetime 字段类型（特殊处理, 满足query范围查询的代码自动生成）
     */
    protected String jxModel(GcConfig gcConfig, String fieldName, String type, Boolean isTimeStr) {
        isTimeStr = isTimeStr == null ? false : isTimeStr;
        // 转驼峰模式
        fieldName = GcDataUtil.getFieldName(gcConfig, fieldName);
        String field = "";
        // 字段
        if (type.equals(FieldTypeConstant.INT)) {
            // 整数int
            field = "private Integer " + fieldName + ";";
        } else if (type.equals(FieldTypeConstant.BIGINT)) {
            // 整数Long
            field = "private Long " + fieldName + ";";
        } else if (type.equals(FieldTypeConstant.VARCHAR) || type.equals(FieldTypeConstant.CHAR)) {
            // 字符串
            field = "private String " + fieldName + ";";
        } else if (type.equals(FieldTypeConstant.TEXT) || type.equals(FieldTypeConstant.LONG_TEXT)) {
            // 大文本、超大文本
            field = "private String " + fieldName + ";";
        } else if (type.equals(FieldTypeConstant.DATETIME) || type.equals(FieldTypeConstant.TIME)
                || type.equals(FieldTypeConstant.TIMESTAMP)) {
            // 时间
            if (type.equals(FieldTypeConstant.DATETIME) && isTimeStr) {
                field = "private String " + fieldName + ";";
            } else {
                field = "private LocalDateTime " + fieldName + ";";
            }
        } else if (type.equals(FieldTypeConstant.DOUBLE)) {
            // 双精度小数 Double
            field = "private Double " + fieldName + ";";
        } else if (type.equals(FieldTypeConstant.FLOAT)) {
            // 单精度小数 Float
            field = "private Float " + fieldName + ";";
        } else if (type.equals(FieldTypeConstant.DECIMAL)) {
            // 小数 decimal
            field = "private BigDecimal " + fieldName + ";";
        } else if (type.equals(FieldTypeConstant.TINYINT)) {
            // 布尔 tinyint
            field = "private Boolean " + fieldName + ";";
        }
        return field;
    }


    /**
     * 必填字段获取 jsr303 验证注解，  NO 代表必填,YES 非必填
     * <p>
     * [\r\n    ]
     * </P>
     *
     * @param type       字段类型
     * @param typeDetail 字段类型长度  int(11)
     * @param desc       字段备注
     */
    protected String jsrModel(String type, String typeDetail, String desc) {
        // 获取数据库注释,去除括号后的内容
        desc = desc.contains(BracketConstant.LEFT_BRACKET) ? desc.substring(0, desc.indexOf(BracketConstant.LEFT_BRACKET)) : desc;
        desc = desc.contains(BracketConstant.LEFT_BRACKET_TWO) ? desc.substring(0, desc.indexOf(BracketConstant.LEFT_BRACKET_TWO)) : desc;
        String jsr = null;
        //字段
        if (type.equals(FieldTypeConstant.INT) || type.equals(FieldTypeConstant.BIGINT)) {
            // 整数
            // int(11), 判断是否有长度,存在长度获取指定长度的最大值, 转为long添加到注解中
            if (typeDetail.contains(BracketConstant.LEFT_BRACKET)) {
                int len = Integer.parseInt(typeDetail.substring(typeDetail.indexOf("(") + 1, typeDetail.indexOf(")")));
                double max = (Math.pow(10, len) - 1);
                jsr = "@Range(min=0, max={MAX}L,message = \"{DESC} 必须>=0 和 <={MAX}\")";
                if (type.equals(FieldTypeConstant.INT)) {
                    jsr = jsr.replaceAll("\\{MAX}", new BigDecimal(max + "").intValue() + "").replace("{DESC}", desc);
                } else {
                    jsr = jsr.replaceAll("\\{MAX}", new BigDecimal(max + "").longValue() + "").replace("{DESC}", desc);
                }
            }
        } else if (type.equals(FieldTypeConstant.DOUBLE) || type.equals(FieldTypeConstant.FLOAT)
                || type.equals(FieldTypeConstant.DECIMAL)) {
            //  小数
            //  判断是否有长度,存在长度获取指定长度的最大值, 转为long添加到注解中 decimal(10,2)，取10, 2不处理
            if (typeDetail.contains(BracketConstant.LEFT_BRACKET)) {
                String typeDetailStr = typeDetail.substring(typeDetail.indexOf(BracketConstant.LEFT_BRACKET) + 1,
                        typeDetail.indexOf(BracketConstant.RIGHT_BRACKET));
                int len = Integer.parseInt(typeDetailStr.split(",")[0]);
                double max = (Math.pow(10, len) - 1);
                jsr = "@Range(min=0, max={MAX}L,message = \"{DESC} 必须>=0 和 <={MAX}\")";
                jsr = jsr.replaceAll("\\{MAX}", new BigDecimal(max + "").longValue() + "")
                        .replace("{DESC}", desc);
            }
        } else if (type.equals(FieldTypeConstant.VARCHAR) || type.equals(FieldTypeConstant.CHAR)
                || type.equals(FieldTypeConstant.TEXT) || type.equals(FieldTypeConstant.LONG_TEXT)) {
            // 字符串
            if (typeDetail.contains(BracketConstant.LEFT_BRACKET)) {
                jsr = "@Length(min=0, max={MAX},message = \"{DESC} 必须>=0 和 <={MAX}位\")";
                String max = typeDetail.substring(typeDetail.indexOf(BracketConstant.LEFT_BRACKET) + 1,
                        typeDetail.indexOf(BracketConstant.RIGHT_BRACKET));
                jsr = jsr.replaceAll("\\{MAX}", max).replace("{DESC}", desc);
            }
        } else if (type.equals(FieldTypeConstant.DATETIME) || type.equals(FieldTypeConstant.TIME)
                || type.equals(FieldTypeConstant.TIMESTAMP)) {
            /// 时间暂无
            // fields.append("\r\n" + "    @NotNull(message = \"" + desc + " 不能为空\")");
        }
        return jsr;
    }


    /**
     * 判断当前字段是否勾选
     *
     * @param fieldMap
     * @return boolean true 表示勾选，false 表示为勾选
     * @author wangsong
     * @date 2021/11/4 0004 7:04
     * @version 1.0.0
     */
    protected boolean isChecked(DbFieldPO fieldMap) {
        // 兼容layui
        boolean checked = false;
        if (fieldMap.getChecked() != null) {
            checked = fieldMap.getChecked();
        }
        // 兼容vue
        boolean isChecked = false;
        if (fieldMap.getIsChecked() != null) {
            isChecked = fieldMap.getIsChecked();
        }
        return checked || isChecked;
    }

    /**
     * 获取 desc 字段描叙 去掉 () 内后的数据
     *
     * @param desc
     * @return String
     */
    protected String getDesc(String desc) {
        if (desc != null) {
            if (desc.contains(BracketConstant.LEFT_BRACKET)) {
                desc = desc.substring(0, desc.indexOf(BracketConstant.LEFT_BRACKET));
            }
            if (desc.contains(BracketConstant.LEFT_BRACKET)) {
                desc = desc.substring(0, desc.indexOf(BracketConstant.LEFT_BRACKET_TWO));
            }
        }
        return desc;
    }


    /**
     * 获取vue 添加或编辑页的 表单数据
     *
     * @param gcConfig      代码生成配置信息
     * @param name          字段名
     * @param type          字段类型
     * @param typeDetail    类型+长度
     * @param newDesc       字段中文描述
     * @param vueFieldType  vue表单字段类型
     * @return
     */
    protected String jxVueColumns(GcConfig gcConfig, String name, String type, String typeDetail, String newDesc, Integer vueFieldType, List<String> dictCode) {
        // 生成表单时获取数据库的字段的长度来控制输入
        long maxlength = 0L;
        if (type.equals(FieldTypeConstant.INT) || type.equals(FieldTypeConstant.BIGINT)) {
            // int(11), 判断是否有长度,存在长度获取指定长度的最大值, 转为long添加到注解中
            if (typeDetail.contains(BracketConstant.LEFT_BRACKET)) {
                int len = Integer.parseInt(typeDetail.substring(typeDetail.indexOf(BracketConstant.LEFT_BRACKET) + 1, typeDetail.indexOf(BracketConstant.RIGHT_BRACKET)));
                double max = (Math.pow(10, len) - 1);
                maxlength = new BigDecimal(max + "").longValue();
            }
        } else if (type.equals(FieldTypeConstant.DOUBLE) || type.equals(FieldTypeConstant.FLOAT) || type.equals(FieldTypeConstant.DECIMAL)) {
            //  小数
            //  判断是否有长度,存在长度获取指定长度的最大值, 转为long添加到注解中 decimal(10,2)，取10, 2不处理
            if (typeDetail.contains(BracketConstant.LEFT_BRACKET)) {
                String typeDetailStr = typeDetail.substring(typeDetail.indexOf(BracketConstant.LEFT_BRACKET) + 1, typeDetail.indexOf(BracketConstant.RIGHT_BRACKET));
                int len = Integer.parseInt(typeDetailStr.split(",")[0]);
                double max = (Math.pow(10, len) - 1);
                maxlength = new BigDecimal(max + "").longValue();
            }
        } else if (type.equals(FieldTypeConstant.VARCHAR) || type.equals(FieldTypeConstant.CHAR)
                || type.equals(FieldTypeConstant.TEXT) || type.equals(FieldTypeConstant.LONG_TEXT)) {
            // 字符串
            if (typeDetail.contains(BracketConstant.LEFT_BRACKET)) {
                String max = typeDetail.substring(typeDetail.indexOf(BracketConstant.LEFT_BRACKET) + 1, typeDetail.indexOf(BracketConstant.RIGHT_BRACKET));
                maxlength = Long.parseLong(max);
            }
        } else if (type.equals(FieldTypeConstant.DATETIME) || type.equals(FieldTypeConstant.TIME) || type.equals(FieldTypeConstant.TIMESTAMP)) {
        }
        // 处理字段
        String columnStr = "";
        name = GcDataUtil.getFieldName(gcConfig, name);
        if (Base.VueFieldType.V1.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.INPUT.replaceAll("\\{label}", newDesc).replace("{prop}", name).replace("{maxlength}", maxlength + "");
        } else if (Base.VueFieldType.V2.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.NUMBER.replaceAll("\\{label}", newDesc).replace("{prop}", name);
        } else if (Base.VueFieldType.V3.getValue().equals(vueFieldType)) {
        } else if (Base.VueFieldType.V4.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.RADIO.replaceAll("\\{label}", newDesc).replace("{prop}", name).replace("{dictCode}", getDictCode(dictCode));
        } else if (Base.VueFieldType.V5.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.CHECKBOX.replaceAll("\\{label}", newDesc).replace("{prop}", name).replace("{dictCode}", getDictCode(dictCode));
        } else if (Base.VueFieldType.V6.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.SELECT.replaceAll("\\{label}", newDesc).replace("{prop}", name).replace("{dictCode}", getDictCode(dictCode));
        } else if (Base.VueFieldType.V7.getValue().equals(vueFieldType)) {
        } else if (Base.VueFieldType.V8.getValue().equals(vueFieldType)) {
        } else if (Base.VueFieldType.V9.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.SWITCH.replaceAll("\\{label}", newDesc).replace("{prop}", name).replace("{dictCode}", getDictCode(dictCode));
        } else if (Base.VueFieldType.V10.getValue().equals(vueFieldType)) {
        } else if (Base.VueFieldType.V11.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.DATETIME.replaceAll("\\{label}", newDesc).replace("{prop}", name);
        } else if (Base.VueFieldType.V12.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.TIME.replaceAll("\\{label}", newDesc).replace("{prop}", name);
        } else if (Base.VueFieldType.V13.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.UPLOAD
                    .replaceAll("\\{label}", newDesc).replace("{prop}", name).replace("{listType}", "picture-img")
                    .replace("{limit}", "1").replace("{fileType}", "img")
                    .replace("{accept}", "'image/png, image/jpeg, image/jpg, image/gif'").replace("{tip}", "只能上传 jpg/png/gif 格式的图片");
        } else if (Base.VueFieldType.V14.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.UPLOAD
                    .replaceAll("\\{label}", newDesc).replace("{prop}", name).replace("{listType}", "picture-card")
                    .replace("{limit}", "10").replace("{fileType}", "img")
                    .replace("{accept}", "'image/png, image/jpeg, image/jpg, image/gif'").replace("{tip}", "只能上传10张 jpg/png/gif 格式的图片");
        } else if (Base.VueFieldType.V15.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.UPLOAD
                    .replaceAll("\\{label}", newDesc).replace("{prop}", name).replace("{listType}", "picture-img")
                    .replace("{limit}", "1").replace("{fileType}", "video")
                    .replace("{accept}", "'video/mp4'").replace("{tip}", "只能上传mp4格式的视频");
        } else if (Base.VueFieldType.V16.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.UPLOAD
                    .replaceAll("\\{label}", newDesc).replace("{prop}", name).replace("{listType}", "")
                    .replace("{limit}", "10").replace("{fileType}", "all")
                    .replace("{accept}", "null").replace("{tip}", "");
        } else if (Base.VueFieldType.V17.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.TEXTAREA.replaceAll("\\{label}", newDesc).replace("{prop}", name).replace("{maxlength}", maxlength + "");
        } else if (Base.VueFieldType.V18.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.INPUT.replaceAll("\\{label}", newDesc).replace("{prop}", name).replace("{maxlength}", maxlength + "");
        } else if (Base.VueFieldType.V19.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.INPUT.replaceAll("\\{label}", newDesc).replace("{prop}", name).replace("{maxlength}", maxlength + "");
        } else if (Base.VueFieldType.V20.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.CASCADER.replaceAll("\\{label}", newDesc).replace("{prop}", name);
        } else if (Base.VueFieldType.V21.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.ARRAY.replaceAll("\\{label}", newDesc).replace("{prop}", name);
        } else if (Base.VueFieldType.V22.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.ICON.replaceAll("\\{label}", newDesc).replaceAll("\\{prop}", name);
        } else if (Base.VueFieldType.V23.getValue().equals(vueFieldType)) {
            columnStr = VueAddUpdTemplate.COLOR.replaceAll("\\{label}", newDesc).replaceAll("\\{prop}", name);
        } else {
            // 没有默认 input
            columnStr = VueAddUpdTemplate.INPUT.replaceAll("\\{label}", newDesc).replace("{prop}", name).replace("{maxlength}", maxlength + "");
        }
        return columnStr;
    }


    /**
     * vue 字段需要定义为插槽的处理 (添加和编辑页)
     *
     * @param vueFieldType  vue表单字段类型
     * @param name          字段名
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2022/5/14 0014 22:41 
     * @version 1.0.0
     */
    protected String jxVueColumnsSlot(Integer vueFieldType, String name) {
        String vueAddUpdSlot = "";
        // name = GcDataUtil.getFieldName(gcConfig, name);
        if (Base.VueFieldType.V18.getValue().equals(vueFieldType)) {
            // 追加定义富文本插槽
            vueAddUpdSlot = VueAddUpdSlotTemplate.TINYMCE_EDITOR.replace("{prop}", name);
        }
        if (Base.VueFieldType.V19.getValue().equals(vueFieldType)) {
            // 追加定义md编辑器插槽
            vueAddUpdSlot = VueAddUpdSlotTemplate.MD_EDITOR.replace("{prop}", name);
        }
        return vueAddUpdSlot;
    }


    /**
     * vue 字段需要定义为插槽的处理 (列表页，搜索插槽 或 特殊字段展示的插槽)
     *
     * @param vueFieldType  vue表单字段类型
     * @param name          字段名
     * @param isSearch      是否搜索
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2022/5/14 0014 22:41
     * @version 1.0.0
     */
    protected String jxVueInfoColumnsSlot(Integer vueFieldType, String name, boolean isSearch) {
        String vueAddUpdSlot = "";
        // name = GcDataUtil.getFieldName(gcConfig, name);
        if (Base.VueFieldType.V11.getValue().equals(vueFieldType)) {
            // 时间范围搜索插槽
            vueAddUpdSlot = VueMainSlotTemplate.DATE_PICKER.replace("{prop}", name);
        }
        return vueAddUpdSlot;
    }


    /**
     * 获取字典替换值
     *
     * @param dictCode
     * @return
     */
    protected String getDictCode(List<String> dictCode) {
        String dictCodeStr = "this.website.Dict.Base.Default";
        if (dictCode != null && dictCode.size() > 0) {
            dictCodeStr = dictCode.get(dictCode.size() - 1);
            dictCodeStr = "'" + dictCodeStr + "'";
        }
        return dictCodeStr;
    }
}
