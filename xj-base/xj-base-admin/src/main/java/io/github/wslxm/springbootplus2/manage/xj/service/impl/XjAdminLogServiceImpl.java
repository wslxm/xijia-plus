package io.github.wslxm.springbootplus2.manage.xj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.xj.mapper.XjAdminLogMapper;
import io.github.wslxm.springbootplus2.manage.xj.model.entity.XjAdminLog;
import io.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminLogQuery;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminLogVO;
import io.github.wslxm.springbootplus2.manage.xj.service.XjAdminLogService;
import org.springframework.stereotype.Service;

/**
 * 操作记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-10-28 20:44:32
 */
@Service
public class XjAdminLogServiceImpl extends BaseIServiceImpl<XjAdminLogMapper, XjAdminLog> implements XjAdminLogService {

    @Override
    public IPage<XjAdminLogVO> findPage(XjAdminLogQuery query) {
        LambdaQueryWrapper<XjAdminLog> queryWrapper = new LambdaQueryWrapper<XjAdminLog>()
                .orderByDesc(XjAdminLog::getCreateTime)
                .likeRight(StringUtils.isNotBlank(query.getFullName()), XjAdminLog::getFullName, query.getFullName())
                .likeRight(StringUtils.isNotBlank(query.getUri()), XjAdminLog::getUri, query.getUri())
                .likeRight(StringUtils.isNotBlank(query.getClassDesc()), XjAdminLog::getClassDesc, query.getClassDesc())
                .likeRight(StringUtils.isNotBlank(query.getMethodDesc()), XjAdminLog::getMethodDesc, query.getMethodDesc())
                .eq(StringUtils.isNotBlank(query.getMethod()), XjAdminLog::getMethod, query.getMethod())
                .eq(query.getState() != null, XjAdminLog::getState, query.getState())
                .between(query.getCreateTimeStart() != null && query.getCreateTimeEnd() != null, XjAdminLog::getCreateTime, query.getCreateTimeStart(), query.getCreateTimeEnd());
        return BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), XjAdminLogVO.class);
    }
}
