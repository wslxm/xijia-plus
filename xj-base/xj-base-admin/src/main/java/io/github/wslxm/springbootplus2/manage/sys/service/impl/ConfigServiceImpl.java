package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.common.cache.CacheKey;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.core.config.error.ErrorException;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.result.ResultType;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.sys.mapper.ConfigMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.ConfigDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Config;
import io.github.wslxm.springbootplus2.manage.sys.model.query.ConfigQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.ConfigVO;
import io.github.wslxm.springbootplus2.manage.sys.service.ConfigService;
import io.github.wslxm.springbootplus2.starter.redis.lock.XjDistributedLock;
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
public class ConfigServiceImpl extends BaseServiceImpl<ConfigMapper, Config> implements ConfigService {

    @Override
    public IPage<ConfigVO> findPage(ConfigQuery query) {
        LambdaQueryWrapper<Config> queryWrapper = new LambdaQueryWrapper<Config>()
                .orderByAsc(Config::getSort)
                .orderByDesc(Config::getCreateTime)
                .eq(StringUtils.isNotBlank(query.getCode()), Config::getCode, query.getCode())
                .eq(query.getType() != null, Config::getType, query.getType())
                .likeRight(StringUtils.isNotBlank(query.getName()), Config::getName, query.getName());
        IPage<ConfigVO> resPage = null;
        resPage = BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), ConfigVO.class);
        // 如果是富文本不返回内容
        for (ConfigVO xjAdminConfigVO : resPage.getRecords()) {
            if (xjAdminConfigVO.getType().equals(Base.ConfigType.V3.getValue())) {
                xjAdminConfigVO.setContent("富文本内容请点击详情查看");
            }
        }
        return resPage;
    }

    @Override
    public ConfigVO findId(String id) {
        return BeanDtoVoUtil.convert(this.getById(id), ConfigVO.class);
    }

    @Override
    @XjDistributedLock(lockName = "'xj-sys-config_'+#dto.code", waitTime = 5L)
    public String insert(ConfigDTO dto) {
        // 判code重复
        if (this.count(new LambdaQueryWrapper<Config>().eq(Config::getCode, dto.getCode())) > 0) {
            throw new ErrorException(ResultType.CONFIG_DUPLICATE);
        }
        Config entity = dto.convert(Config.class);
        boolean b = this.save(entity);
        return entity.getId();
    }

    @Override
    @CacheEvict(value = CacheKey.CONFIG_LIST_BY_CODE, allEntries = true)
    @XjDistributedLock(lockName = "'xj-sys-config_'+#dto.code", waitTime = 5L)
    public boolean upd(String id, ConfigDTO dto) {
        Config config = this.getById(id);
        if (!config.getCode().equals(dto.getCode())) {
            if (this.count(new LambdaQueryWrapper<Config>().eq(Config::getCode, dto.getCode())) > 0) {
                throw new ErrorException(ResultType.CONFIG_DUPLICATE);
            }
        }
        Config entity = dto.convert(Config.class);
        entity.setId(id);
        return this.updateById(entity);
    }


    @Override
    @Cacheable(value = CacheKey.CONFIG_LIST_BY_CODE, key = "#code", unless = "#result == null")
    public ConfigVO findByCode(String code) {
        Config xjAdminConfig = this.getOne(new LambdaQueryWrapper<Config>().eq(Config::getCode, code));
        return BeanDtoVoUtil.convert(xjAdminConfig, ConfigVO.class);
    }
}
