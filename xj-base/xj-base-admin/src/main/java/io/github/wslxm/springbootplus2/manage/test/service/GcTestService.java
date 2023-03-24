package io.github.wslxm.springbootplus2.manage.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.core.base.model.BasePage;
import io.github.wslxm.springbootplus2.manage.test.model.entity.GcTest;
import io.github.wslxm.springbootplus2.manage.test.model.vo.GcTestVO;
import io.github.wslxm.springbootplus2.manage.test.model.dto.GcTestDTO;
import io.github.wslxm.springbootplus2.manage.test.model.query.GcTestQuery;

/**
 * 代码生成测试表 Service
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-09-09 18:26:48
 */
public interface GcTestService extends IService<GcTest> {

    /**
     * 列表查询
     *
     * @param query query
     * @return 分页列表数据
     */
    BasePage<GcTestVO> findPage(GcTestQuery query);

    /**
     * id 查询
     *
     * @param id id
     * @return GcTestVO
     */
    GcTestVO findId(String id);

    /**
     * 添加
     *
     * @param dto dto
     * @return 主键id
     */
    String insert(GcTestDTO dto);

    /**
     * id 编辑
     *
     * @param id id
     * @param dto dto
     * @return boolean
     */
    boolean upd( String id, GcTestDTO dto);

    /**
     * 删除
     *
     * @param id id
     * @return boolean
     */
    boolean del(String id);

}

