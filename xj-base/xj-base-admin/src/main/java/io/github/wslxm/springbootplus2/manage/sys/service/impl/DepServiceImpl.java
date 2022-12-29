package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.core.utils.tree.TreeUtil;
import io.github.wslxm.springbootplus2.manage.sys.mapper.DepMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.DepDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Dep;
import io.github.wslxm.springbootplus2.manage.sys.model.query.DepQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DepVO;
import io.github.wslxm.springbootplus2.manage.sys.service.DepService;
import io.github.wslxm.springbootplus2.manage.test.model.vo.GcMenuVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * base--sys--组织机构 ServiceImpl
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-12-29 09:57:30
 */
@Service
public class DepServiceImpl extends BaseServiceImpl<DepMapper, Dep> implements DepService {


    @Override
    public List<DepVO> tree(DepQuery query) {
        LambdaQueryWrapper<Dep> queryWrapper = new LambdaQueryWrapper<Dep>().orderByDesc(Dep::getCreateTime);
        List<Dep> list = this.list(queryWrapper);
        List<DepVO> listVo = BeanDtoVoUtil.listVo(list, DepVO.class);
        return TreeUtil.nextTree(listVo, "0").getChildren();
    }


    @Override
    public IPage<DepVO> findPage(DepQuery query) {
        LambdaQueryWrapper<Dep> queryWrapper = new LambdaQueryWrapper<Dep>().orderByDesc(Dep::getCreateTime);
        queryWrapper.likeRight(StringUtils.isNotBlank(query.getPid()), Dep::getPid, query.getPid());
        queryWrapper.likeRight(StringUtils.isNotBlank(query.getName()), Dep::getName, query.getName());
        queryWrapper.likeRight(StringUtils.isNotBlank(query.getCode()), Dep::getCode, query.getCode());
        queryWrapper.eq(query.getDisable() != null, Dep::getDisable, query.getDisable());
        Page<Dep> page = this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper);
        return BeanDtoVoUtil.pageVo(page, DepVO.class);
    }

    @Override
    public DepVO findId(String id) {
        return BeanDtoVoUtil.convert(this.getById(id), DepVO.class);
    }

    @Override
    public String insert(DepDTO dto) {
        Dep entity = dto.convert(Dep.class);
        boolean b = this.save(entity);
        return entity.getId();
    }

    @Override
    public boolean upd(String id, DepDTO dto) {
        Dep entity = dto.convert(Dep.class);
        entity.setId(id);
        return this.updateById(entity);
    }

    @Override
    public boolean del(String id) {
        List<Dep> list = this.list(new LambdaQueryWrapper<Dep>().select(Dep::getId, Dep::getPid));
        List<DepVO> listVos = BeanDtoVoUtil.listVo(list, DepVO.class);
        List<String> nextIds = TreeUtil.nextIds(listVos, id);
        nextIds.add(id);
        return this.removeBatchByIds(nextIds);
    }

}
