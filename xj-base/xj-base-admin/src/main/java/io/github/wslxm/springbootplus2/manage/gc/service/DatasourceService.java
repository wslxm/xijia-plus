package io.github.wslxm.springbootplus2.manage.gc.service;

import io.github.wslxm.springbootplus2.core.base.model.BasePage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.core.base.model.BasePage;
import io.github.wslxm.springbootplus2.manage.gc.model.entity.Datasource;
import io.github.wslxm.springbootplus2.manage.gc.model.query.DatasourceQuery;
import io.github.wslxm.springbootplus2.manage.gc.model.vo.DatasourceVO;

/**
 * 代码生成数据源维护表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-11-04 20:11:08
 */
public interface DatasourceService extends IService<Datasource> {

    /**
     * 列表查询
     *
     * @param query query
     * @return com.baomidou.mybatisplus.core.metadata.BasePage<io.github.wslxm.springbootplus2.manage.gc.model.vo.DatasourceVO>
     * @version 1.0.0
     */
    BasePage<DatasourceVO> findPage(DatasourceQuery query);

    /**
     * id查询
     *
     * @param id id
     * @return com.baomidou.mybatisplus.core.metadata.BasePage<io.github.wslxm.springbootplus2.manage.gc.model.vo.DatasourceVO>
     * @version 1.0.0
     */
    DatasourceVO findId(String id);
}

