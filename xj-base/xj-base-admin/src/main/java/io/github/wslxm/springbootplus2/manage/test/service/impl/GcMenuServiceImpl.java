package io.github.wslxm.springbootplus2.manage.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import io.github.wslxm.springbootplus2.core.constant.SymbolConst;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.core.utils.tree.TreeUtil;
import io.github.wslxm.springbootplus2.manage.test.mapper.GcMenuMapper;
import io.github.wslxm.springbootplus2.manage.test.service.GcMenuService;
import io.github.wslxm.springbootplus2.manage.test.model.entity.GcMenu;
import io.github.wslxm.springbootplus2.manage.test.model.vo.GcMenuVO;
import io.github.wslxm.springbootplus2.manage.test.model.dto.GcMenuDTO;
import io.github.wslxm.springbootplus2.manage.test.model.query.GcMenuQuery;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

/**
 * 基础表--菜单 ServiceImpl
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-12-28 20:24:04
 */
@Service
public class GcMenuServiceImpl extends BaseServiceImpl<GcMenuMapper, GcMenu> implements GcMenuService {


    @Override
    public List<GcMenuVO> tree(GcMenuQuery query) {
        LambdaQueryWrapper<GcMenu> queryWrapper = new LambdaQueryWrapper<GcMenu>().orderByDesc(GcMenu::getCreateTime);
        List<GcMenu> list = this.list(queryWrapper);
        List<GcMenuVO> listVo = BeanDtoVoUtil.listVo(list, GcMenuVO.class);
        return TreeUtil.nextTree(listVo, "0").getChildren();
    }


    @Override
    public IPage<GcMenuVO> findPage(GcMenuQuery query) {
        LambdaQueryWrapper<GcMenu> queryWrapper = new LambdaQueryWrapper<GcMenu>().orderByDesc(GcMenu::getCreateTime);
        queryWrapper.likeRight(StringUtils.isNotBlank(query.getPid()), GcMenu::getPid, query.getPid());
        queryWrapper.likeRight(StringUtils.isNotBlank(query.getName()), GcMenu::getName, query.getName());
        Page<GcMenu> page = this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper);
        return BeanDtoVoUtil.pageVo(page, GcMenuVO.class);
    }

    @Override
    public GcMenuVO findId(String id) {
        return BeanDtoVoUtil.convert(this.getById(id), GcMenuVO.class);
    }

    @Override
    public String insert(GcMenuDTO dto) {
        GcMenu entity = dto.convert(GcMenu.class);
        boolean b = this.save(entity);
        return entity.getId();
    }

    @Override
    public boolean upd(String id, GcMenuDTO dto) {
        GcMenu entity = dto.convert(GcMenu.class);
        entity.setId(id);
        return this.updateById(entity);
    }

    @Override
    public boolean del(String id) {
         List<GcMenu> list = this.list(new LambdaQueryWrapper<GcMenu>().select(GcMenu::getId, GcMenu::getPid));
         List<GcMenuVO> listVos = BeanDtoVoUtil.listVo(list, GcMenuVO.class);
         List<String> nextIds = TreeUtil.nextIds(listVos, id);
         nextIds.add(id);
         return this.removeBatchByIds(nextIds);
    }

}
