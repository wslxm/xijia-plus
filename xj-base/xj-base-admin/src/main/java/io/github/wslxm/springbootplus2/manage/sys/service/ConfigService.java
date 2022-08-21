package io.github.wslxm.springbootplus2.manage.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.ConfigDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Config;
import io.github.wslxm.springbootplus2.manage.sys.model.query.ConfigQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.ConfigVO;

/**
 * 系统全局数据信息配置表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-31 18:31:44
 */
public interface ConfigService extends IService<Config> {

    /**
     * 列表查询
     *
     * @param query query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<io.github.wslxm.springbootplus2.manage.xj.model.vo.ConfigVO>
     * @version 1.0.0
     */
    IPage<ConfigVO> findPage(ConfigQuery query);


    /**
     * id查询
     * @author wangsong
     * @param id
     * @date 2022/5/15 0015 11:33
     * @return io.github.wslxm.springbootplus2.manage.xj.model.vo.ConfigVO
     * @version 1.0.0
     */
    public ConfigVO findId(String id);


    /**
     * 添加
     *
     * @param dto dto
     * @return java.lang.String
     * @version 1.0.0
     */
    String insert(ConfigDTO dto);

    /**
     * 编辑
     *
     * @param id  id
     * @param dto dto
     * @return boolean
     * @version 1.0.0
     */
    boolean upd(String id, ConfigDTO dto);

    /**
     * code 查询
     *
     * @param code code
     * @return io.github.wslxm.springbootplus2.manage.xj.model.vo.ConfigVO
     * @version 1.0.0
     */
    ConfigVO findByCode(String code);

}

