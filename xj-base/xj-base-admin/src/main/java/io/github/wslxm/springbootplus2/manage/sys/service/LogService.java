package io.github.wslxm.springbootplus2.manage.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Log;
import io.github.wslxm.springbootplus2.manage.sys.model.query.LogQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.LogVO;

/**
 * 操作记录表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-10-28 20:44:32
 */
public interface LogService extends IService<Log> {

    /**
     * 列表查询
     *
     * @param query query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<io.github.wslxm.springbootplus2.manage.xj.model.vo.LogVO>
     * @version 1.0.0
     */
    IPage<LogVO> findPage(LogQuery query);

}

