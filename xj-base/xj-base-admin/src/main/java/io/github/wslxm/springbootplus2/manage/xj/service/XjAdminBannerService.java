package io.github.wslxm.springbootplus2.manage.xj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.xj.model.dto.XjAdminBannerDTO;
import io.github.wslxm.springbootplus2.manage.xj.model.entity.XjAdminBanner;
import io.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminBannerQuery;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminBannerVO;

/**
 * banner表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-23 23:14:01
 */
public interface XjAdminBannerService extends IService<XjAdminBanner> {

    /**
     * 列表查询
     *
     * @param query query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminBannerVO>
     * @version 1.0.0
     */
    IPage<XjAdminBannerVO> list(XjAdminBannerQuery query);

    /**
     * 添加
     *
     * @param dto dto
     * @return java.lang.String
     * @version 1.0.0
     */
    String insert(XjAdminBannerDTO dto);

    /**
     * 编辑
     *
     * @param id  id
     * @param dto dto
     * @return boolean
     * @version 1.0.0
     */
    boolean upd(String id, XjAdminBannerDTO dto);

    /**
     * 删除
     *
     * @param id id
     * @return boolean
     * @version 1.0.0
     */
    boolean del(String id);
}

