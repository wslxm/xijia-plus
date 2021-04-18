package com.ws.ldy.modules.sys.xj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.cache.JvmCache;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.modules.sys.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.modules.sys.xj.mapper.XjAdminConfigMapper;
import com.ws.ldy.modules.sys.xj.model.dto.XjAdminConfigDTO;
import com.ws.ldy.modules.sys.xj.model.entity.XjAdminConfig;
import com.ws.ldy.modules.sys.xj.service.XjAdminConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public XjAdminConfig findByCode(String code) {
        if (JvmCache.getConfigMap().isEmpty()) {
            List<XjAdminConfig> list = this.list();
            if (!list.isEmpty()) {
                for (XjAdminConfig xjAdminConfig : list) {
                    JvmCache.getConfigMap().put(xjAdminConfig.getCode(), xjAdminConfig);
                }
            }
        }
        return JvmCache.getConfigMap().get(code);
    }

    @Override
    public boolean insert(XjAdminConfigDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        // 判code重复
        if (this.count(new LambdaQueryWrapper<XjAdminConfig>().eq(XjAdminConfig::getCode, dto.getCode())) > 0) {
            throw new ErrorException(RType.DICT_DUPLICATE);
        }
        boolean b = this.save(dto.convert(XjAdminConfig.class));
        JvmCache.delConfigMap();
        return b;
    }

    @Override
    public boolean upd(XjAdminConfigDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        //
        XjAdminConfig config = this.getById(dto.getId());
        if (!config.getCode().equals(dto.getCode())) {
            if (this.count(new LambdaQueryWrapper<XjAdminConfig>().eq(XjAdminConfig::getCode, dto.getCode())) > 0) {
                throw new ErrorException(RType.DICT_DUPLICATE);
            }
        }
        boolean b = this.updateById(dto.convert(XjAdminConfig.class));
        JvmCache.delConfigMap();
        return b;
    }
}
