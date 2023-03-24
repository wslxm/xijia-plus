package io.github.wslxm.springbootplus2.manage.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.wslxm.springbootplus2.core.base.model.BasePage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import io.github.wslxm.springbootplus2.core.constant.SymbolConst;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.test.mapper.GcTestMapper;
import io.github.wslxm.springbootplus2.manage.test.service.GcTestService;
import io.github.wslxm.springbootplus2.manage.test.model.entity.GcTest;
import io.github.wslxm.springbootplus2.manage.test.model.vo.GcTestVO;
import io.github.wslxm.springbootplus2.manage.test.model.dto.GcTestDTO;
import io.github.wslxm.springbootplus2.manage.test.model.query.GcTestQuery;
import org.apache.commons.lang3.StringUtils;
import java.util.List;




/**
 * 代码生成测试表 ServiceImpl
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-09-09 18:26:48
 */
@Service
public class GcTestServiceImpl extends BaseServiceImpl<GcTestMapper, GcTest> implements GcTestService {

    @Override
    public BasePage<GcTestVO> findPage(GcTestQuery query) {
        LambdaQueryWrapper<GcTest> queryWrapper = new LambdaQueryWrapper<GcTest>().orderByDesc(GcTest::getCreateTime);
        queryWrapper.select(GcTest.class, info -> !"text_two".equals(info.getColumn())
                  && !"text_three".equals(info.getColumn()));
        queryWrapper.likeRight(StringUtils.isNotBlank(query.getName()), GcTest::getName, query.getName());
        queryWrapper.eq(query.getSex() != null, GcTest::getSex, query.getSex());
        queryWrapper.likeRight(StringUtils.isNotBlank(query.getLike()), GcTest::getLike, query.getLike());
        queryWrapper.eq(query.getCity() != null, GcTest::getCity, query.getCity());
        queryWrapper.eq(query.getDisable() != null, GcTest::getDisable, query.getDisable());
        if (StringUtils.isNotBlank(query.getTime()) && query.getTime().split(SymbolConst.COMMA).length >= 1) {
            queryWrapper.between(GcTest::getTime, query.getTime().split(",")[0], query.getTime().split(",")[1]);
        }
        queryWrapper.likeRight(StringUtils.isNotBlank(query.getTimeTwo()), GcTest::getTimeTwo, query.getTimeTwo());
        queryWrapper.likeRight(StringUtils.isNotBlank(query.getCascader()), GcTest::getCascader, query.getCascader());
        Page<GcTest> page = this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper);
        return BeanDtoVoUtil.pageVo(page, GcTestVO.class);
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
