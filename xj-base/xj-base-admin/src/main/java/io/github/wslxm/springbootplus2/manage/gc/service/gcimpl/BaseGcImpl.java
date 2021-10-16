package io.github.wslxm.springbootplus2.manage.gc.service.gcimpl;

import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
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
        fieldName =  GenerateDataProcessing.getFieldName(fieldName);
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
                    jsr = jsr.replaceAll("\\{MAX}", new BigDecimal(max.toString()).intValue() + "").replace("{DESC}",desc);;
                } else {
                    jsr = jsr.replaceAll("\\{MAX}", new BigDecimal(max.toString()).longValue() + "").replace("{DESC}",desc);;
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
                    jsr = jsr.replaceAll("\\{MAX}", new BigDecimal(max.toString()).intValue() + "").replace("{DESC}",desc);;
                } else {
                    jsr = jsr.replaceAll("\\{MAX}", new BigDecimal(max.toString()).longValue() + "").replace("{DESC}",desc);;
                }
            }
        } else if (type.equals("varchar") || type.equals("char") || type.equals("text") || type.equals("longtext")) {
            // 字符串
            if (typeDetail.indexOf("(") != -1) {
                jsr = "@Length(min=0, max={MAX},message = \"{DESC} 必须>=0 和 <={MAX}位\")";
                String max = typeDetail.substring(typeDetail.indexOf("(") + 1, typeDetail.indexOf(")"));
                jsr = jsr.replaceAll("\\{MAX}", max).replace("{DESC}",desc);
            }
        } else if (type.equals("datetime") || type.equals("time") || type.equals("timestamp")) {
            // 时间暂无
            // fields.append("\r\n" + "    @NotNull(message = \"" + desc + " 不能为空\")");
        }
        return jsr;
    }
}
