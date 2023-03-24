package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.wslxm.springbootplus2.core.base.model.BasePage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import io.github.wslxm.springbootplus2.core.constant.SymbolConst;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.core.utils.tree.TreeUtil;
import io.github.wslxm.springbootplus2.manage.sys.mapper.AddressMapper;
import io.github.wslxm.springbootplus2.manage.sys.service.AddressService;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Address;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.AddressVO;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.AddressDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.AddressQuery;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

/**
 *  ServiceImpl
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2023-03-24 10:04:20
 */
@Service
public class AddressServiceImpl extends BaseServiceImpl<AddressMapper, Address> implements AddressService {


    @Override
    public List<AddressVO> tree(AddressQuery query) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<Address>().orderByDesc(Address::getCreateTime);
        List<Address> list = this.list(queryWrapper);
        List<AddressVO> listVo = BeanDtoVoUtil.listVo(list, AddressVO.class);
        return TreeUtil.nextTree(listVo, "0").getChildren();
    }


    @Override
    public BasePage<AddressVO> findPage(AddressQuery query) {
        LambdaQueryWrapper<Address> queryWrapper = new LambdaQueryWrapper<Address>().orderByDesc(Address::getCreateTime);
        queryWrapper.likeRight(StringUtils.isNotBlank(query.getPid()), Address::getPid, query.getPid());
        queryWrapper.likeRight(StringUtils.isNotBlank(query.getName()), Address::getName, query.getName());
        queryWrapper.likeRight(StringUtils.isNotBlank(query.getCode()), Address::getCode, query.getCode());
        queryWrapper.eq(query.getLevel() != null, Address::getLevel, query.getLevel());
        Page<Address> page = this.page(new Page<>(query.getCurrent(), query.getSize()), queryWrapper);
        return BeanDtoVoUtil.pageVo(page, AddressVO.class);
    }

    @Override
    public AddressVO findId(String id) {
        return BeanDtoVoUtil.convert(this.getById(id), AddressVO.class);
    }

    @Override
    public String insert(AddressDTO dto) {
        Address entity = dto.convert(Address.class);
        boolean b = this.save(entity);
        return entity.getId();
    }

    @Override
    public boolean upd(String id, AddressDTO dto) {
        Address entity = dto.convert(Address.class);
        entity.setId(id);
        return this.updateById(entity);
    }

    @Override
    public boolean del(String id) {
         List<Address> list = this.list(new LambdaQueryWrapper<Address>().select(Address::getId, Address::getPid));
         List<AddressVO> listVos = BeanDtoVoUtil.listVo(list, AddressVO.class);
         List<String> nextIds = TreeUtil.nextIds(listVos, id);
         nextIds.add(id);
         return this.removeBatchByIds(nextIds);
    }

}
