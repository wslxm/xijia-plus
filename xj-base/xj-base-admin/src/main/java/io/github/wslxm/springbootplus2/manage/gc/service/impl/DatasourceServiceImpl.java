package io.github.wslxm.springbootplus2.manage.gc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.gc.mapper.DatasourceMapper;
import io.github.wslxm.springbootplus2.manage.gc.model.entity.Datasource;
import io.github.wslxm.springbootplus2.manage.gc.model.query.DatasourceQuery;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.DatasourceVO;
import io.github.wslxm.springbootplus2.manage.gc.service.DatasourceService;
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
public class DatasourceServiceImpl extends BaseServiceImpl<DatasourceMapper, Datasource> implements DatasourceService {


    @Override
    public IPage<DatasourceVO> findPage(DatasourceQuery query) {
        LambdaQueryWrapper<Datasource> queryWrapper = new LambdaQueryWrapper<Datasource>()
                .orderByDesc(Datasource::getCreateTime)
                .select(Datasource.class, info -> !"db_password".equals(info.getColumn()))
                .like(StringUtils.isNotBlank(query.getDbTitle()), Datasource::getDbTitle, query.getDbTitle())
                .like(StringUtils.isNotBlank(query.getDbName()), Datasource::getDbName, query.getDbName());
         return  BeanDtoVoUtil.pageVo(this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper), DatasourceVO.class);
    }


    @Override
    public DatasourceVO findId(String id) {
        DatasourceVO vo = BeanDtoVoUtil.convert(this.getById(id), DatasourceVO.class);
        vo.setDbPassword(null);
        return vo;
    }
}
