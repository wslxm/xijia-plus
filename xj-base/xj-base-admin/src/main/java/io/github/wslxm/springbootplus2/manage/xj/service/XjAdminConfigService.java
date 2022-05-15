package io.github.wslxm.springbootplus2.manage.xj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.xj.model.dto.XjAdminConfigDTO;
import io.github.wslxm.springbootplus2.manage.xj.model.entity.XjAdminConfig;
import io.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminConfigQuery;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminConfigVO;

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
public interface XjAdminConfigService extends IService<XjAdminConfig> {

    /**
     * 列表查询
     *
     * @param query query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminConfigVO>
     * @version 1.0.0
     */
    IPage<XjAdminConfigVO> list(XjAdminConfigQuery query);


    /**
     * id查询
     * @author wangsong
     * @param id
     * @date 2022/5/15 0015 11:33
     * @return io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminConfigVO
     * @version 1.0.0
     */
    public XjAdminConfigVO findId(String id);


    /**
     * 添加
     *
     * @param dto dto
     * @return java.lang.String
     * @version 1.0.0
     */
    String insert(XjAdminConfigDTO dto);

    /**
     * 编辑
     *
     * @param id  id
     * @param dto dto
     * @return boolean
     * @version 1.0.0
     */
    boolean upd(String id, XjAdminConfigDTO dto);

    /**
     * code 查询
     *
     * @param code code
     * @return io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminConfigVO
     * @version 1.0.0
     */
    XjAdminConfigVO findByCode(String code);

}

