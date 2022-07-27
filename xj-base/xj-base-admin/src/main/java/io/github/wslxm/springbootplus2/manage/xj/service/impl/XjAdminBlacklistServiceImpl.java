package io.github.wslxm.springbootplus2.manage.xj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.common.cache.CacheKey;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.xj.mapper.XjAdminBlacklistMapper;
import io.github.wslxm.springbootplus2.manage.xj.model.dto.XjAdminBlacklistDTO;
import io.github.wslxm.springbootplus2.manage.xj.model.entity.XjAdminBlacklist;
import io.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminBlacklistQuery;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminBlacklistVO;
import io.github.wslxm.springbootplus2.manage.xj.service.XjAdminBlacklistService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 黑名单
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-11-27 22:44:49
 */
@Service
public class XjAdminBlacklistServiceImpl extends BaseIServiceImpl<XjAdminBlacklistMapper, XjAdminBlacklist> implements XjAdminBlacklistService {

    @Override
    public IPage<XjAdminBlacklistVO> list(XjAdminBlacklistQuery query) {
        LambdaQueryWrapper<XjAdminBlacklist> queryWrapper = new LambdaQueryWrapper<XjAdminBlacklist>()
                .orderByDesc(XjAdminBlacklist::getCreateTime)
                .eq(query.getType() != null, XjAdminBlacklist::getType, query.getType())
                .eq(query.getDisable() != null, XjAdminBlacklist::getDisable, query.getDisable())
                .eq(StringUtils.isNotBlank(query.getIp()), XjAdminBlacklist::getIp, query.getIp());
        if (query.getCurrent() <= 0) {
            IPage<XjAdminBlacklistVO> page = new Page<>();
            return page.setRecords(BeanDtoVoUtil.listVo(this.list(queryWrapper), XjAdminBlacklistVO.class));
        } else {
            return BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), XjAdminBlacklistVO.class);
        }
    }


    @Override
    @Cacheable(value = CacheKey.BLACK_LIST_BY_TYPE, key = "#type")
    public List<XjAdminBlacklistVO> listByType(Integer type) {
        List<XjAdminBlacklist> list = this.list(new LambdaQueryWrapper<XjAdminBlacklist>()
                .orderByDesc(XjAdminBlacklist::getCreateTime)
                .eq(XjAdminBlacklist::getType, type)
                .eq(XjAdminBlacklist::getDisable, Base.Disable.V0.getValue())
        );
        return BeanDtoVoUtil.listVo(list, XjAdminBlacklistVO.class);
    }

    @Override
    @CacheEvict(value = CacheKey.BLACK_LIST_BY_TYPE, key = "#dto.type")
    public String insert(XjAdminBlacklistDTO dto) {
        XjAdminBlacklist entity = dto.convert(XjAdminBlacklist.class);
        boolean b = this.save(entity);
        return entity.getId();
    }

    @Override
    @CacheEvict(value = CacheKey.BLACK_LIST_BY_TYPE, allEntries = true)
    public Boolean upd(String id, XjAdminBlacklistDTO dto) {
        XjAdminBlacklist entity = dto.convert(XjAdminBlacklist.class);
        entity.setId(id);
        return this.updateById(entity);
    }

    @Override
    @CacheEvict(value = CacheKey.BLACK_LIST_BY_TYPE, allEntries = true)
    public Boolean del(String id) {
        return this.removeById(id);
    }
}
