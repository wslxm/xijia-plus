package io.github.wslxm.springbootplus2.manage.gc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.gc.mapper.XjAdminDatasourceMapper;
import io.github.wslxm.springbootplus2.manage.gc.model.entity.XjAdminDatasource;
import io.github.wslxm.springbootplus2.manage.gc.model.query.XjAdminDatasourceQuery;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.XjAdminDatasourceVO;
import io.github.wslxm.springbootplus2.manage.gc.service.XjAdminDatasourceService;
import org.springframework.stereotype.Service;

/**
 * 代码生成数据源维护表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-11-04 20:11:08
 */
@Service
public class XjAdminDatasourceServiceImpl extends BaseIServiceImpl<XjAdminDatasourceMapper, XjAdminDatasource> implements XjAdminDatasourceService {


    @Override
    public IPage<XjAdminDatasourceVO> list(XjAdminDatasourceQuery query) {
        LambdaQueryWrapper<XjAdminDatasource> queryWrapper = new LambdaQueryWrapper<XjAdminDatasource>()
                .orderByDesc(XjAdminDatasource::getCreateTime)
                .like(StringUtils.isNotBlank(query.getDbTitle()), XjAdminDatasource::getDbTitle, query.getDbTitle())
                .like(StringUtils.isNotBlank(query.getDbName()), XjAdminDatasource::getDbName, query.getDbName());
        if (query.getCurrent() <= 0) {
            // list
            IPage<XjAdminDatasourceVO> page = new Page<>();
            return page.setRecords(BeanDtoVoUtil.listVo(this.list(queryWrapper), XjAdminDatasourceVO.class));
        } else {
            // page
            return  BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), XjAdminDatasourceVO.class);
        }
    }


    @Override
    public XjAdminDatasourceVO findId(String id) {
        return BeanDtoVoUtil.convert(this.getById(id),XjAdminDatasourceVO.class);
    }
}
