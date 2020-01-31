package com.ws.ldy.xijiaserver.xjservice;

import com.ws.ldy.admincore.controller.vo.ResponseData;

/**
 * TODO  (广场)提供各种段子数据
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/1/31 0031 10:28
 */
public interface SquareService {

    /**
     * TODO  搞笑段子,使用接口RollToolsApi：  https://github.com/MZCretin/RollToolsApi
     * @param type 1,分页列表，2,随机
     * @param page 页数
     * @return java.util.List<java.lang.String>
     * @author 王松
     * @mail 1720696548@qq.com
     * @date 2020/1/31 0031 11:16
     */
    ResponseData duanzi(int type, Integer page);
}
