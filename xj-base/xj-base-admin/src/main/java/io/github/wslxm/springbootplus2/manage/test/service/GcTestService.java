package io.github.wslxm.springbootplus2.manage.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.manage.test.model.entity.GcTest;
import io.github.wslxm.springbootplus2.manage.test.model.vo.GcTestVO;
import io.github.wslxm.springbootplus2.manage.test.model.dto.GcTestDTO;
import io.github.wslxm.springbootplus2.manage.test.model.query.GcTestQuery;

/**
 * 代码生成测试表
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author ws
 * @email 1720696548@qq.com
 * @date 2021-12-08 11:39:01
 */
public interface GcTestService extends IService<GcTest> {

    /**
     * 列表查询
     *
     * @param query query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<io.github.wslxm.springbootplus2.manage.test.model.vo.GcTestVO>
     * @version 1.0.0
     */
    IPage<GcTestVO> list(GcTestQuery query);

    /**
     * id 查询
     *
     * @param id id
     * @return io.github.wslxm.springbootplus2.manage.test.model.vo.GcTestVO
     * @version 1.0.0
     */
    GcTestVO findId(String id);


    /**
     * 添加
     *
     * @param dto dto
     * @return java.lang.String
     * @version 1.0.0
     */
    String insert(GcTestDTO dto);

    /**
     * 编辑
     *
     * @param id  id
     * @param dto dto
     * @return boolean
     * @version 1.0.0
     */
    boolean upd(String id, GcTestDTO dto);

    /**
     * 删除
     *
     * @param id id
     * @return boolean
     * @version 1.0.0
     */
    boolean del(String id);
}

