package io.github.wslxm.springbootplus2.starter.baidu.constant;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wangsong
 */
@Getter
public enum TextExtractTypeEnum {
    //
    V1(1, "通用文字识别 "),
    V2(2, "通用文字识别高精度版"),
    V3(3, "通用文字识别（含位置信息版）"),
    V4(4, "通用文字识别（含位置信息高精度版） "),
    V5(5, "通用文字识别（含生僻字版） "),
    V6(6, "网络图片文字识别 "),
    V7(7, "身份证识别 (必须使用真实身份证,否则返回数据身份证号码为空)"),
    V8(8, "银行卡识别"),
    V9(9, "驾驶证识别"),
    V10(10, "行驶证识别 "),
    V11(11, "车牌识别 "),
    V12(12, "营业执照识别 "),
    V13(13, "通用票据识别 "),
    V14(14, "自定义模板文字识别(不可使用)"),
    V15(15, "表格文字识别同步接口 "),
    V16(16, "表格文字识别(不可使用) "),
    V17(17, "表格识别结果(不可使用) "),
    V18(18, "表格识别轮询接口(不可使用) "),
    V19(19, "试卷分析与识别 "),
    V20(20, "仪器仪表盘读数识别 "),
    V21(21, "网络图片文字识别（含位置版） ");

    private Integer code;
    private String msg;

    TextExtractTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
