package io.github.wslxm.springbootplus2.manage.test.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.test.mapper.GcTestMapper;
import io.github.wslxm.springbootplus2.manage.test.model.dto.GcTestDTO;
import io.github.wslxm.springbootplus2.manage.test.model.entity.GcTest;
import io.github.wslxm.springbootplus2.manage.test.model.query.GcTestQuery;
import io.github.wslxm.springbootplus2.manage.test.model.vo.GcTestVO;
import io.github.wslxm.springbootplus2.manage.test.service.GcTestService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 代码生成测试表 ServiceImpl
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-06-30 11:07:08
 */
@Service
public class GcTestServiceImpl extends BaseIServiceImpl<GcTestMapper, GcTest> implements GcTestService {

    @Override
    public IPage<GcTestVO> list(GcTestQuery query) {
        // mybatis-plus 模式
//        LambdaQueryWrapper<GcTest> queryWrapper = new LambdaQueryWrapper<GcTest>()
//                .select(GcTest.class, info -> !"text_two".equals(info.getColumn())
//                          && !"text_three".equals(info.getColumn()))
//                .likeRight(StringUtils.isNotBlank(query.getName()), GcTest::getName, query.getName())
//                .eq(query.getAge() != null, GcTest::getAge, query.getAge())
//                .eq(query.getSex() != null, GcTest::getSex, query.getSex())
//                .likeRight(StringUtils.isNotBlank(query.getLike()), GcTest::getLike, query.getLike())
//
//                .orderByDesc(GcTest::getCreateTime);
//        Page<GcTest> page = new Page<>(query.getCurrent(), query.getSize());
//        page = query.getCurrent() <= 0 ? page.setRecords(this.list(queryWrapper)) : this.page(page, queryWrapper);
//        return BeanDtoVoUtil.pageVo(page, GcTestVO.class);

        // xml sql 模式
         IPage<GcTestVO> page = new Page<>(query.getCurrent(), query.getSize());
         List<GcTestVO> list = baseMapper.list(query.getCurrent() <= 0 ? null : page, query);
         return page.setRecords(list);
    }

    @Override
    public GcTestVO findId(String id) {
        return BeanDtoVoUtil.convert(this.getById(id), GcTestVO.class);
    }

    @Override
    public String insert(GcTestDTO dto) {
        GcTest entity = dto.convert(GcTest.class);
        boolean b = this.save(entity);
        return entity.getId();
    }

    @Override
    public boolean upd(String id, GcTestDTO dto) {
        GcTest entity = dto.convert(GcTest.class);
        entity.setId(id);
        return this.updateById(entity);
    }

    @Override
    public boolean del(String id) {
        return this.removeById(id);
    }
}
