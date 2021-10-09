package io.github.wslxm.springbootplus2.core.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("all")
public interface Admin{

    // -【动态值】 如有需要根据业务指定
    @Getter
    @AllArgsConstructor
    enum Terminal implements IEnum<Integer> {
        V1(1, "系统端"),    // -
        V2(2, "A端"),    // -
        V3(3, "B端"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【动态值】, 如有需要根据业务指定
    @Getter
    @AllArgsConstructor
    enum Position implements IEnum<Integer> {
        V0(0, "系统管理员"),    // -
        V1(1, "平台人员"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【动态值】
    @Getter
    @AllArgsConstructor
    enum BannerPosition implements IEnum<Integer> {
        V1(1, "用户端首页"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【动态值】
    @Getter
    @AllArgsConstructor
    enum MsgType implements IEnum<Integer> {
        V0(0, "测试消息"),    // -
        V1(1, "系统通知"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【动态值】
    @Getter
    @AllArgsConstructor
    enum MsgUserType implements IEnum<Integer> {
        V1(1, "用户端"),    // -
        V2(2, "管理端"),    // -
        ;
        private Integer value;
        private String desc;
    }

    // 【动态值】当前支付业务
    @Getter
    @AllArgsConstructor
    enum PayBusiness implements IEnum<Integer> {
        V1(1, "用户下单"),    // -
        V2(2, "vip 充值/续费"),    // -
        V3(3, "月卡购买"),    // -
        V4(4, "其他"),    // -
        ;
        private Integer value;
        private String desc;
    }
}