package io.github.wslxm.springbootplus2.manage.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
import io.github.wslxm.springbootplus2.manage.admin.mapper.AdminRoleAuthMapper;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminRoleAuth;
import io.github.wslxm.springbootplus2.manage.admin.service.AdminRoleAuthService;
import org.springframework.stereotype.Service;


/**
 * @author wangsong
 */
@Service
public class AdminRoleAuthServiceImpl extends BaseIServiceImpl<AdminRoleAuthMapper, AdminRoleAuth> implements AdminRoleAuthService {

	@Override
	public boolean delByRoleId(String roleId) {
		return this.remove(new LambdaQueryWrapper<AdminRoleAuth>().eq(AdminRoleAuth::getRoleId, roleId));
	}
}

