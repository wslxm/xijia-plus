package io.github.wslxm.springbootplus2.manage.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.User;
import io.github.wslxm.springbootplus2.manage.sys.model.query.UserQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author wangsong
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 列表查询
     *
     * @param page         page
     * @param query        query
     * @param page         page
     * @param query        query
     * @param createUserId createUserId
     * @return java.util.List<io.github.wslxm.springbootplus2.manage.admin.model.vo.UserVO>
     * @version 1.0.0
     */
    List<UserVO> list(IPage<UserVO> page, UserQuery query, String createUserId);

    /**
     * 根据角色Id查询指定用户信息
     *
     * @param roleId roleId
     * @return java.util.List<io.github.wslxm.modules.admin.model.entity.User>
     * @author wangsong
     * @date 2020/8/9 0009 10:17
     * @version 1.0.1
     */
    List<User> findByRoleId(@Param("roleId") String roleId);

}
