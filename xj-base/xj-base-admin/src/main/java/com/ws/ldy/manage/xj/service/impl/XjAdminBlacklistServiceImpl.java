package com.ws.ldy.manage.xj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.cache.CacheKey;
import com.ws.ldy.core.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.core.cache.CacheUtil;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.manage.xj.mapper.XjAdminBlacklistMapper;
import com.ws.ldy.manage.xj.model.dto.XjAdminBlacklistDTO;
import com.ws.ldy.manage.xj.model.entity.XjAdminBlacklist;
import com.ws.ldy.manage.xj.model.query.XjAdminBlacklistQuery;
import com.ws.ldy.manage.xj.model.vo.XjAdminBlacklistVO;
import com.ws.ldy.manage.xj.service.XjAdminBlacklistService;
import org.springframework.stereotype.Service;

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
                .eq(query.getType() != null, XjAdminBlacklist::getType, query.getType()
                );
        if (query.getCurrent() <= 0) {
            IPage<XjAdminBlacklistVO> page = new Page<>();
            return page.setRecords(BeanDtoVoUtil.listVo(this.list(queryWrapper), XjAdminBlacklistVO.class));
        } else {
            return BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), XjAdminBlacklistVO.class);
        }
    }

    @Override
    public String insert(XjAdminBlacklistDTO dto) {
        XjAdminBlacklist entity = dto.convert(XjAdminBlacklist.class);
        boolean b = this.save(entity);
        // 置空缓存
        CacheUtil.del(CacheKey.BLACK_LIST.getKey());
        return entity.getId();
    }

    @Override
    public Boolean upd(String id, XjAdminBlacklistDTO dto) {
        XjAdminBlacklist entity = dto.convert(XjAdminBlacklist.class);
        entity.setId(id);
        boolean b = this.updateById(entity);
        // 置空缓存
        CacheUtil.del(CacheKey.BLACK_LIST.getKey());
        return b;
    }

    @Override
    public Boolean del(String id) {
        boolean b = this.removeById(id);
        // 置空缓存
        CacheUtil.del(CacheKey.BLACK_LIST.getKey());
        return b;
    }
}
