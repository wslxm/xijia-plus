package io.github.wslxm.springbootplus2.manage.xj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.common.cache.CacheKey;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.result.RType;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.xj.mapper.XjAdminConfigMapper;
import io.github.wslxm.springbootplus2.manage.xj.model.dto.XjAdminConfigDTO;
import io.github.wslxm.springbootplus2.manage.xj.model.entity.XjAdminConfig;
import io.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminConfigQuery;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminConfigVO;
import io.github.wslxm.springbootplus2.manage.xj.service.XjAdminConfigService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 系统全局数据信息配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-31 18:31:44
 */
@Service
public class XjAdminConfigServiceImpl extends BaseIServiceImpl<XjAdminConfigMapper, XjAdminConfig> implements XjAdminConfigService {

    @Override
    public IPage<XjAdminConfigVO> list(XjAdminConfigQuery query) {
        LambdaQueryWrapper<XjAdminConfig> queryWrapper = new LambdaQueryWrapper<XjAdminConfig>()
                .orderByAsc(XjAdminConfig::getSort)
                .orderByDesc(XjAdminConfig::getCreateTime)
                .eq(StringUtils.isNotBlank(query.getCode()), XjAdminConfig::getCode, query.getCode())
                .eq(query.getType() != null, XjAdminConfig::getType, query.getType())
                .like(StringUtils.isNotBlank(query.getName()), XjAdminConfig::getName, query.getName());
        IPage<XjAdminConfigVO> resPage = null;
        if (query.getCurrent() <= 0) {
            resPage.setRecords(BeanDtoVoUtil.listVo(this.list(queryWrapper), XjAdminConfigVO.class));
        } else {
            resPage = BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), XjAdminConfigVO.class);
        }
        // 如果是富文本不返回内容
        for (XjAdminConfigVO xjAdminConfigVO : resPage.getRecords()) {
            if (xjAdminConfigVO.getType().equals(Base.ConfigType.V3.getValue())) {
                xjAdminConfigVO.setContent("富文本内容请点击详情查看");
            }
        }
        return resPage;
    }

    @Override
    public XjAdminConfigVO findId(String id) {
        return BeanDtoVoUtil.convert(this.getById(id), XjAdminConfigVO.class);
    }

    @Override
    public String insert(XjAdminConfigDTO dto) {
        // 判code重复
        if (this.count(new LambdaQueryWrapper<XjAdminConfig>().eq(XjAdminConfig::getCode, dto.getCode())) > 0) {
            throw new ErrorException(RType.DICT_DUPLICATE);
        }
        XjAdminConfig entity = dto.convert(XjAdminConfig.class);
        boolean b = this.save(entity);
        return entity.getId();
    }

    @Override
    @CacheEvict(value = CacheKey.CONFIG_LIST_BY_CODE, allEntries = true)
    public boolean upd(String id, XjAdminConfigDTO dto) {
        XjAdminConfig config = this.getById(id);
        if (!config.getCode().equals(dto.getCode())) {
            if (this.count(new LambdaQueryWrapper<XjAdminConfig>().eq(XjAdminConfig::getCode, dto.getCode())) > 0) {
                throw new ErrorException(RType.DICT_DUPLICATE);
            }
        }
        XjAdminConfig entity = dto.convert(XjAdminConfig.class);
        entity.setId(id);
        return this.updateById(entity);
    }


    @Override
    @Cacheable(value = CacheKey.CONFIG_LIST_BY_CODE, key = "#code")
    public XjAdminConfigVO findByCode(String code) {
        XjAdminConfig xjAdminConfig = this.getOne(new LambdaQueryWrapper<XjAdminConfig>().eq(XjAdminConfig::getCode, code));
        return BeanDtoVoUtil.convert(xjAdminConfig, XjAdminConfigVO.class);
    }
}
