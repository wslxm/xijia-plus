package io.github.wslxm.springbootplus2.manage.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wslxm.springbootplus2.core.base.model.BasePage;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.SysUser;
import io.github.wslxm.springbootplus2.manage.sys.model.query.SysUserQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.SysUserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author wangsong
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 列表查询
     *
     * @param page         page
     * @param query        query
     * @param page         page
     * @param query        query
     * @param createUserId createUserId
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.vo.SysUserVO>
     * @version 1.0.0
     */
    List<SysUserVO> list(Page<SysUserVO> page, SysUserQuery query, String createUserId);

    /**
     * 根据角色Id查询指定用户信息
     *
     * @param roleId roleId
     * @return java.util.List<io.github.wslxm.modules.admin.model.entity.User>
     * @author wangsong
     * @date 2020/8/9 0009 10:17
     * @version 1.0.1
     */
    List<SysUser> findByRoleId(@Param("roleId") String roleId);

}
