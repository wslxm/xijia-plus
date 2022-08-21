package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.common.cache.CacheKey;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.sys.mapper.BlacklistMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.BlacklistDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Blacklist;
import io.github.wslxm.springbootplus2.manage.sys.model.query.BlacklistQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.BlacklistVO;
import io.github.wslxm.springbootplus2.manage.sys.service.BlacklistService;
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
public class BlacklistServiceImpl extends BaseIServiceImpl<BlacklistMapper, Blacklist> implements BlacklistService {

    @Override
    public IPage<BlacklistVO> findPage(BlacklistQuery query) {
        LambdaQueryWrapper<Blacklist> queryWrapper = new LambdaQueryWrapper<Blacklist>()
                .orderByDesc(Blacklist::getCreateTime)
                .eq(query.getType() != null, Blacklist::getType, query.getType())
                .eq(query.getDisable() != null, Blacklist::getDisable, query.getDisable())
                .eq(StringUtils.isNotBlank(query.getIp()), Blacklist::getIp, query.getIp());
        return BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), BlacklistVO.class);
    }


    @Override
    @Cacheable(value = CacheKey.BLACK_LIST_BY_TYPE, key = "#type")
    public List<BlacklistVO> listByType(Integer type) {
        List<Blacklist> list = this.list(new LambdaQueryWrapper<Blacklist>()
                .orderByDesc(Blacklist::getCreateTime)
                .eq(Blacklist::getType, type)
                .eq(Blacklist::getDisable, Base.Disable.V0.getValue())
        );
        return BeanDtoVoUtil.listVo(list, BlacklistVO.class);
    }

    @Override
    @CacheEvict(value = CacheKey.BLACK_LIST_BY_TYPE, key = "#dto.type")
    public String insert(BlacklistDTO dto) {
        Blacklist entity = dto.convert(Blacklist.class);
        boolean b = this.save(entity);
        return entity.getId();
    }

    @Override
    @CacheEvict(value = CacheKey.BLACK_LIST_BY_TYPE, allEntries = true)
    public Boolean upd(String id, BlacklistDTO dto) {
        Blacklist entity = dto.convert(Blacklist.class);
        entity.setId(id);
        return this.updateById(entity);
    }

    @Override
    @CacheEvict(value = CacheKey.BLACK_LIST_BY_TYPE, allEntries = true)
    public Boolean del(String id) {
        return this.removeById(id);
    }
}
