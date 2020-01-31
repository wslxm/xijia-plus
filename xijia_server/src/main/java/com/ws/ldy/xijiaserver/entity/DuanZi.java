package com.ws.ldy.xijiaserver.entity;

import lombok.Data;

import java.util.Date;

/**
 * TODO   段子
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/1/31 0031 10:31
 * @return
 */
@Data
public class DuanZi {
    /**
     * id
     */
    private int id;
    /**
     * 内容
     */
    private String content;
    /**
     * 类型
     */
    private int type;
    /**
     * 发布人id
     */
    private int userId;
    /**
     * 是否展示 : 1展示/0隐藏
     */
    private int show;
    /**
     * 发布时间
     */
    private Date time;

    public DuanZi(String content, int type, Date time) {
        this.content = content;
        this.type = type;
        this.time = time;
    }
}
