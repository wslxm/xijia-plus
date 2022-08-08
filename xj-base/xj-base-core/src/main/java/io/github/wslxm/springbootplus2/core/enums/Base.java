package io.github.wslxm.springbootplus2.core.enums;


import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("all")
public interface Base{

    // -
    @Getter
    @AllArgsConstructor
    enum ConfigType implements IEnum<Integer> {
        V0(0, "文本"),    // -
        V1(1, "图片"),    // -
        V2(2, "开关"),    // -
        V3(3, "富文本"),    // -
        V4(4, "markdown 文本"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // -
    @Getter
    @AllArgsConstructor
    enum OrganRoot implements IEnum<Integer> {
        V1(1, "一级"),    // -
        V2(2, "二级"),    // -
        V3(3, "三级"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // vue代码生成使用
    @Getter
    @AllArgsConstructor
    enum VueFieldType implements IEnum<Integer> {
        V1(1, "文本-(input)"),    // -
        V2(2, "数字-(number)"),    // -
        V3(3, "密码-(password)"),    // -
        V4(4, "单选-(radio)"),    // -
        V5(5, "多选-(checkbox)"),    // -
        V6(6, "下拉选择-(select-单选)"),    // -
        V7(7, "下拉选择 (select-单选+搜索)"),    // -
        V8(8, "下拉选择 (select-多选+搜索)"),    // -
        V9(9, "开关-(switch)"),    // -
        V10(10, "日期-(data)"),    // yyyy-MM-dd
        V11(11, "日期时间-(datetime)"),    // yyyy-MM-dd hh:mm:ss
        V12(12, "时间-(time)"),    // -
        V13(13, "文件上传(默认单图)"),    // -
        V14(14, "文件上传(多图)"),    // -
        V15(15, "文件上传（缩略图）"),    // -
        V16(16, "文件上传（附件）"),    // -
        V17(17, "大文本(textarea)"),    // -
        V18(18, "富文本(tinymce)"),    // vue-tinymce 富文本插件
        V19(19, "md 编辑器"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【固定值】用于代码生成默认使用的code值
    @Getter
    @AllArgsConstructor
    enum Default implements IEnum<Integer> {
        V1(1, "默认值 1"),    // -
        V2(2, "默认值 2"),    // -
        V3(3, "默认值 3"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【固定值】
    @Getter
    @AllArgsConstructor
    enum Deleted implements IEnum<Integer> {
        V0(0, "正常"),    // -
        V1(1, "已删除"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【固定值】
    @Getter
    @AllArgsConstructor
    enum Disable implements IEnum<Integer> {
        V0(0, "启用"),    // -
        V1(1, "禁用"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【固定值】
    @Getter
    @AllArgsConstructor
    enum Gender implements IEnum<Integer> {
        V0(0, "未知"),    // -
        V1(1, "男"),    // -
        V2(2, "女"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【固定值】：用于编辑指定接口的【权限状态】
    @Getter
    @AllArgsConstructor
    enum AuthorityState implements IEnum<Integer> {
        V0(0, "无"),    // -
        V1(1, "需登录"),    // -
        V2(2, "需登录+授权"),    // -
        V3(3, "需Oauth2 授权"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【固定值】：用于新接口自动生成【权限状态】
    @Getter
    @AllArgsConstructor
    enum AuthorityType implements IEnum<Integer> {
        V0(0, "管理端接口"),    // 管理端, 类上标有该参数所有接口都会默认被列为-[需登录+需授权]
        V1(1, "用户端接口"),    // 用户端, 类上标有该参数所有接口都会默认被列为-[需登录]
        V2(2, "通用接口"),    // 通用接口, 类上标有该参数所有接口都会默认被列为-[无需登录,无需授权]
        V3(3, "Oauth2 接口"),    // 接口默认默认需通过 appId，appSecret生成的 accessToken 来进行oauth2 令牌验证可访问
        ;
        private Integer value;
        private String desc;
    }

    // 【固定值】
    @Getter
    @AllArgsConstructor
    enum MenuRoot implements IEnum<Integer> {
        V1(1, "顶部菜单"),    // -
        V2(2, "菜单"),    // -
        V3(3, "页面"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【固定值】
    @Getter
    @AllArgsConstructor
    enum BannerIsSkip implements IEnum<Integer> {
        V0(0, "否"),    // -
        V1(1, "内部跳转"),    // -
        V2(2, "外部跳转"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【固定值】
    @Getter
    @AllArgsConstructor
    enum IsRead implements IEnum<Integer> {
        V0(0, "未读"),    // -
        V1(1, "已读"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【固定值】
    @Getter
    @AllArgsConstructor
    enum BlacklistType implements IEnum<Integer> {
        V1(1, "白名单"),    // -
        V2(2, "黑名单"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【固定值】
    @Getter
    @AllArgsConstructor
    enum PayChannel implements IEnum<Integer> {
        V1(1, "支付宝"),    // -
        V2(2, "微信"),    // -
        V3(3, "银联"),    // -
        V4(4, "其他"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【固定值】用于记录支付交易请求状态
    @Getter
    @AllArgsConstructor
    enum PayState implements IEnum<Integer> {
        V0(0, "已发起"),    // -
        V1(1, "回调成功"),    // -
        V2(2, "交易失败"),    // -
        V3(3, "交易成功"),    // -
        V4(4, "订单异常"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【固定值】
    @Getter
    @AllArgsConstructor
    enum PayType implements IEnum<Integer> {
        V1(1, "支付"),    // -
        V2(2, "充值"),    // -
        V3(3, "退款"),    // -
        V4(4, "商家打款"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【固定值】
    @Getter
    @AllArgsConstructor
    enum WalletType implements IEnum<Integer> {
        V1(1, "收入"),    // -
        V2(2, "支出"),    // -
        ;
        private Integer value;
        private String desc;
    }
}