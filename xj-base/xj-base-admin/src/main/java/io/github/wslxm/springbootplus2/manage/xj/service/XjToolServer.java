package io.github.wslxm.springbootplus2.manage.xj.service;


import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjToolJvmInfoVO;

/**
 *  @author wangsong
 */
public interface XjToolServer {


    /**
     * 查询 jvm 信息
     *
     * @return io.github.wslxm.springbootplus2.manage.xj.model.vo.XjToolJvmInfoVO
     * @version 1.0.0
     */
    XjToolJvmInfoVO jvmInfo();
}
