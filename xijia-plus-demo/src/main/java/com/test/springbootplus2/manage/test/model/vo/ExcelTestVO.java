package com.test.springbootplus2.manage.test.model.vo;


import lombok.Data;
import lombok.ToString;

/**
 * excel测试对象
 *
 * @author wangsong
 * @version 1.0.0
 * @mail 1720696548@qq.com
 * @date 2022/8/22 0022 20:52
 */
@Data
@ToString(callSuper = true)
public class ExcelTestVO {

    /**
     * 用户编号"
     */
    private String userNo;

    /**
     * 用户名称"
     */
    private String username;

    /**
     * 用户电话"
     */
    private String userPhone;
}
