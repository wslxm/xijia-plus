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
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-23 23:14:01
 */
public interface XjAdminBannerService extends IService<XjAdminBanner> {

    IPage<XjAdminBannerVO> list(XjAdminBannerQuery query);

    String insert(XjAdminBannerDTO dto);

    boolean upd( String id, XjAdminBannerDTO dto);

    boolean del(String id);
}

