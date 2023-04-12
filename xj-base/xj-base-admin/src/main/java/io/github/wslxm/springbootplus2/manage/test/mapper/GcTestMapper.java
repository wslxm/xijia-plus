package io.github.wslxm.springbootplus2.manage.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.wslxm.springbootplus2.core.base.model.BasePage;
import io.github.wslxm.springbootplus2.manage.test.model.entity.GcTest;
import io.github.wslxm.springbootplus2.manage.test.model.vo.GcTestVO;
import io.github.wslxm.springbootplus2.manage.test.model.query.GcTestQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 代码生成测试表 Mapper
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-09-09 18:26:48
 */
public interface GcTestMapper extends BaseMapper<GcTest> {

    /**
     * id 查询
     *
     * @param id
     * @return List<GcTest>
     */
    GcTestVO findId(@Param("id") String id);

    /**
     * 列表查询/分页查询
     *
     * @param page
     * @param query
     * @return java.util.List<GcTest>
     */
    List<GcTestVO> list(@Param("page") BasePage<GcTestVO> page, @Param("query") GcTestQuery query);

}

