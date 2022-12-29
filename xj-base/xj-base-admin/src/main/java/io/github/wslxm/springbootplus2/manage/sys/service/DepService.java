package io.github.wslxm.springbootplus2.manage.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Dep;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DepVO;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.DepDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.DepQuery;
import java.util.List;

/**
 * base--sys--组织机构 Service
 *
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>

 * @author ws
 * @email 1720696548@qq.com
 * @date 2022-12-29 09:57:30
 */
public interface DepService extends IService<Dep> {

    /**
     * 树结构数据查询
     *
     * @param query query
     * @return 分页列表数据
     */
    List<DepVO> tree(DepQuery query);

    /**
     * 列表查询
     *
     * @param query query
     * @return 分页列表数据
     */
    IPage<DepVO> findPage(DepQuery query);

    /**
     * id 查询
     *
     * @param id id
     * @return DepVO
     */
    DepVO findId(String id);

    /**
     * 添加
     *
     * @param dto dto
     * @return 主键id
     */
    String insert(DepDTO dto);

    /**
     * id 编辑
     *
     * @param id id
     * @param dto dto
     * @return boolean
     */
    boolean upd( String id, DepDTO dto);

    /**
     * 删除 (同时删除下级数据)
     *
     * @param id id
     * @return boolean
     */
    boolean del(String id);

}

