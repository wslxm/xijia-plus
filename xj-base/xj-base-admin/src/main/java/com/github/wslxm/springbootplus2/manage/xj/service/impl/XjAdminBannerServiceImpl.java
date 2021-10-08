package com.github.wslxm.springbootplus2.manage.xj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import com.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import com.github.wslxm.springbootplus2.manage.xj.mapper.XjAdminBannerMapper;
import com.github.wslxm.springbootplus2.manage.xj.model.dto.XjAdminBannerDTO;
import com.github.wslxm.springbootplus2.manage.xj.model.entity.XjAdminBanner;
import com.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminBannerQuery;
import com.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminBannerVO;
import com.github.wslxm.springbootplus2.manage.xj.service.XjAdminBannerService;
import org.springframework.stereotype.Service;

/**
 * banner表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-23 23:14:01
 */
@Service
public class XjAdminBannerServiceImpl extends BaseIServiceImpl<XjAdminBannerMapper, XjAdminBanner> implements XjAdminBannerService {

    @Override
    public IPage<XjAdminBannerVO> list(XjAdminBannerQuery query) {
        LambdaQueryWrapper<XjAdminBanner> queryWrapper = new LambdaQueryWrapper<XjAdminBanner>()
                .orderByAsc(XjAdminBanner::getPosition)
                .orderByAsc(XjAdminBanner::getSort)
                .orderByDesc(XjAdminBanner::getCreateTime)
                .eq(query.getPosition() != null, XjAdminBanner::getName, query.getPosition())
                .eq(StringUtils.isNotBlank(query.getName()), XjAdminBanner::getName, query.getName());
        if (query.getCurrent() <= 0) {
            IPage<XjAdminBannerVO> page = new Page<>();
            return page.setRecords(BeanDtoVoUtil.listVo(this.list(queryWrapper), XjAdminBannerVO.class));
        } else {
            return BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), XjAdminBannerVO.class);
        }
    }

    @Override
    public String insert(XjAdminBannerDTO dto) {
        XjAdminBanner entity = dto.convert(XjAdminBanner.class);
        boolean b = this.save(entity);
        return entity.getId();
    }

    @Override
    public boolean upd(String id, XjAdminBannerDTO dto) {
        XjAdminBanner entity = dto.convert(XjAdminBanner.class);
        entity.setId(id);
        return this.updateById(entity);
    }

    @Override
    public boolean del(String id) {
        return this.removeById(id);
    }
}
