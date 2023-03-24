package io.github.wslxm.springbootplus2.manage.sys.service;

import io.github.wslxm.springbootplus2.core.base.model.BasePage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.BannerDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Banner;
import io.github.wslxm.springbootplus2.manage.sys.model.query.BannerQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.BannerVO;

import java.util.List;

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
public interface BannerService extends IService<Banner> {

    /**
     * 列表查询
     *
     * @param query query
     * @return com.baomidou.mybatisplus.core.metadata.BasePage<io.github.wslxm.springbootplus2.manage.xj.model.vo.BannerVO>
     * @version 1.0.0
     */
    BasePage<BannerVO> findPage(BannerQuery query);


    /**
     * 根据位置 查询
     * @param position
     * @return
     */
    List<BannerVO> findByPosition(Integer position);

    /**
     * 添加
     *
     * @param dto dto
     * @return java.lang.String
     * @version 1.0.0
     */
    String insert(BannerDTO dto);

    /**
     * 编辑
     *
     * @param id  id
     * @param dto dto
     * @return boolean
     * @version 1.0.0
     */
    boolean upd(String id, BannerDTO dto);

    /**
     * 删除
     *
     * @param id id
     * @return boolean
     * @version 1.0.0
     */
    boolean del(String id);
}

