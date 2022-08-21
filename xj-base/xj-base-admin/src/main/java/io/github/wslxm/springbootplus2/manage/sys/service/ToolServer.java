package io.github.wslxm.springbootplus2.manage.sys.service;


import io.github.wslxm.springbootplus2.manage.sys.model.vo.ToolJvmInfoVO;

/**
 *  @author wangsong
 */
public interface ToolServer {


    /**
     * 查询 jvm 信息
     *
     * @return io.github.wslxm.springbootplus2.manage.xj.model.vo.ToolJvmInfoVO
     * @version 1.0.0
     */
    ToolJvmInfoVO jvmInfo();
}
