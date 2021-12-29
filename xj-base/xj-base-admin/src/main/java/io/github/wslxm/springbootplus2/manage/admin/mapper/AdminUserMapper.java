package io.github.wslxm.springbootplus2.manage.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminUser;
import io.github.wslxm.springbootplus2.manage.admin.model.query.AdminUserQuery;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminUserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author wangsong
 */
public interface AdminUserMapper extends BaseMapper<AdminUser> {

    /**
     * 列表查询
     *
     * @param page         page
     * @param query        query
     * @param page         page
     * @param query        query
     * @param createUserId createUserId
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminUserVO>
     * @version 1.0.0
     */
    List<AdminUserVO> list(IPage<AdminUserVO> page, AdminUserQuery query, String createUserId);

    /**
     * 根据角色Id查询指定用户信息
     *
     * @param roleId roleId
     * @return java.util.List<io.github.wslxm.modules.admin.model.entity.AdminUser>
     * @author wangsong
     * @date 2020/8/9 0009 10:17
     * @version 1.0.1
     */
    List<AdminUser> findByRoleId(@Param("roleId") String roleId);

}
