package io.github.wslxm.springbootplus2.manage.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.admin.model.dto.AdminDepDTO;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminDep;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminDepQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminDepVO;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminUserDepVO;

import java.util.List;

/**
 * 基础表--组织机构
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author ws
 * @email 1720696548@qq.com
 * @date 2021-09-30 16:10:57
 */
public interface AdminDepService extends IService<AdminDep> {

    /**
     * 列表查询
     *
     * @param query query
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminDepVO>
     * @version 1.0.0
     */
    List<AdminDepVO> list(AdminDepQuery query);

    /**
     * 添加
     *
     * @param dto dto
     * @return java.lang.String
     * @version 1.0.0
     */
    String insert(AdminDepDTO dto);

    /**
     * 编辑
     *
     * @param id  id
     * @param dto dto
     * @return boolean
     * @version 1.0.0
     */
    boolean upd(String id, AdminDepDTO dto);

    /**
     * 删除
     *
     * @param id id
     * @return boolean
     * @version 1.0.0
     */
    boolean del(String id);

    /**
     * 通过当前的部门/公司id获取层级数据（上/下当前级部门/公司信息）
     *
     * @return boolean
     * @author wangsong
     * @date 2021/10/1 0001 10:33
     * @version 1.0.1
     */
    public AdminUserDepVO findNextDeps(List<AdminDepVO>  depVOs, String depIds);
}

