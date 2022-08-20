package io.github.wslxm.springbootplus2.manage.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.manage.admin.mapper.AdminRoleUserMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminRoleUser;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminRoleUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangsong
 */
@Service
public class AdminRoleUserServiceImpl extends BaseIServiceImpl<AdminRoleUserMapper, AdminRoleUser> implements AdminRoleUserService {


	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updUserRole(String userId, List<String> roleIds) {
		//删除原角色所有权限数据
		this.remove(new LambdaQueryWrapper<AdminRoleUser>().eq(AdminRoleUser::getUserId, userId));
		if (roleIds == null || roleIds.size() <= 0) {
			return true;
		}
		List<AdminRoleUser> roleUserList = new ArrayList<>();
		roleIds.forEach(roleId -> roleUserList.add(new AdminRoleUser(roleId, userId)));
		return this.saveBatch(roleUserList, 1024);
	}


	@Override
	public boolean delByUserId(String userId) {
		return this.remove(new LambdaQueryWrapper<AdminRoleUser>().eq(AdminRoleUser::getUserId, userId));
	}

	@Override
	public boolean delByRoleId(String roleId) {
		return this.remove(new LambdaQueryWrapper<AdminRoleUser>().eq(AdminRoleUser::getRoleId, roleId));
	}

}
