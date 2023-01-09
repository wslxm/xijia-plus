package io.github.wslxm.springbootplus2.core.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("all")
public interface Admin{

    // 【动态值】, 如有需要根据业务指定
    @Getter
    @AllArgsConstructor
    enum Position implements IEnum<Integer> {
        V0(0, "系统管理员(老板)"),    // -
        V1(1, "部门经理"),    // -
        V2(2, "员工"),    // -
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
        V0(0, "管理端 - 测试消息"),    // -
        V1(1, "管理端 - 系统通知"),    // -
        V2(2, "管理端 - 用户信息变动"),    // -已配置路由
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
}
