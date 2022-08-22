package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.manage.sys.mapper.RoleUserMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.RoleUser;
import io.github.wslxm.springbootplus2.manage.sys.service.RoleUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangsong
 */
@Service
public class RoleUserServiceImpl extends BaseServiceImpl<RoleUserMapper, RoleUser> implements RoleUserService {


	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updUserRole(String userId, List<String> roleIds) {
		//删除原角色所有权限数据
		this.remove(new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getUserId, userId));
		if (roleIds == null || roleIds.size() <= 0) {
			return true;
		}
		List<RoleUser> roleUserList = new ArrayList<>();
		roleIds.forEach(roleId -> roleUserList.add(new RoleUser(roleId, userId)));
		return this.saveBatch(roleUserList, 1024);
	}


	@Override
	public boolean delByUserId(String userId) {
		return this.remove(new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getUserId, userId));
	}

	@Override
	public boolean delByRoleId(String roleId) {
		return this.remove(new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getRoleId, roleId));
	}

}
