package io.github.wslxm.springbootplus2.manage.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Address;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.AddressVO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.AddressQuery;

import java.util.List;

/**
 *  Mapper
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author ws
 * @email 1720696548@qq.com
 * @date 2023-03-24 10:04:20
 */
public interface AddressMapper extends BaseMapper<Address> {

    /**
     * id 查询
     *
     * @param id
     * @return List<Address>
     */
    AddressVO findId(String id);

    /**
     * 列表查询/分页查询
     *
     * @param page
     * @param query
     * @return java.util.List<Address>
     */
    List<AddressVO> list(IPage<AddressVO> page, AddressQuery query);

}

