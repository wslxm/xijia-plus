package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.sys.mapper.LogMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Log;
import io.github.wslxm.springbootplus2.manage.sys.model.query.LogQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.LogVO;
import io.github.wslxm.springbootplus2.manage.sys.service.LogService;
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
public class LogServiceImpl extends BaseServiceImpl<LogMapper, Log> implements LogService {

    @Override
    public IPage<LogVO> findPage(LogQuery query) {
        LambdaQueryWrapper<Log> queryWrapper = new LambdaQueryWrapper<Log>()
                .orderByDesc(Log::getCreateTime)
                .likeRight(StringUtils.isNotBlank(query.getFullName()), Log::getFullName, query.getFullName())
                .likeRight(StringUtils.isNotBlank(query.getUri()), Log::getUri, query.getUri())
                .likeRight(StringUtils.isNotBlank(query.getClassDesc()), Log::getClassDesc, query.getClassDesc())
                .likeRight(StringUtils.isNotBlank(query.getMethodDesc()), Log::getMethodDesc, query.getMethodDesc())
                .eq(StringUtils.isNotBlank(query.getMethod()), Log::getMethod, query.getMethod())
                .eq(query.getState() != null, Log::getState, query.getState())
                .between(query.getCreateTimeStart() != null && query.getCreateTimeEnd() != null, Log::getCreateTime, query.getCreateTimeStart(), query.getCreateTimeEnd());
        return BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), LogVO.class);
    }
}
