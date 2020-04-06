package com.ws.ldy.base.query;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * TODO  分页传递对应
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/4/3 0003 16:20
 * @return
 */
@Data
@AllArgsConstructor
public class IPage {
    /**
     * 页数
     */
    private Integer page;
    /**
     * 记录数
     */
    private Integer limit;
}
