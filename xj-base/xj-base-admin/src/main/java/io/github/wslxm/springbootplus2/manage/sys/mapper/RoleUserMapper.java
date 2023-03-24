package io.github.wslxm.springbootplus2.manage.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.RoleUser;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.SysUserRolesVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangsong
 */
public interface RoleUserMapper extends BaseMapper<RoleUser> {


    /**
     * 查询用户的角色数据
     * @author wangsong
     * @mail 1720696548@qq.com
     * @date 2023/3/14 0014 15:58 
     * @version 1.0.0
     */
    List<SysUserRolesVO> findUserRoles(@Param("userIds") List<String> userIds);
}
