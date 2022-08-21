package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.sys.mapper.BannerMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.BannerDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Banner;
import io.github.wslxm.springbootplus2.manage.sys.model.query.BannerQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.BannerVO;
import io.github.wslxm.springbootplus2.manage.sys.service.BannerService;
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
public class BannerServiceImpl extends BaseIServiceImpl<BannerMapper, Banner> implements BannerService {

    @Override
    public IPage<BannerVO> findPage(BannerQuery query) {
        LambdaQueryWrapper<Banner> queryWrapper = new LambdaQueryWrapper<Banner>()
                .orderByAsc(Banner::getPosition)
                .orderByAsc(Banner::getSort)
                .orderByDesc(Banner::getCreateTime)
                .eq(query.getPosition() != null, Banner::getPosition, query.getPosition())
                .eq(StringUtils.isNotBlank(query.getName()), Banner::getName, query.getName());
        return BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), BannerVO.class);
    }

    @Override
    public String insert(BannerDTO dto) {
        Banner entity = dto.convert(Banner.class);
        boolean b = this.save(entity);
        return entity.getId();
    }

    @Override
    public boolean upd(String id, BannerDTO dto) {
        Banner entity = dto.convert(Banner.class);
        entity.setId(id);
        return this.updateById(entity);
    }

    @Override
    public boolean del(String id) {
        return this.removeById(id);
    }
}
