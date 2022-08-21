//package io.github.wslxm.springbootplus2.manage.admin.service.impl;
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import io.github.wslxm.springbootplus2.core.base.service.impl.BaseIServiceImpl;
//import io.github.wslxm.springbootplus2.manage.admin.mapper.RoleAuthMapper;
//import io.github.wslxm.springbootplus2.manage.admin.model.entity.RoleAuth;
//import io.github.wslxm.springbootplus2.manage.admin.service.RoleAuthService;
//import org.springframework.stereotype.Service;
//
//
///**
// * @author wangsong
// */
//@Service
//public class RoleAuthServiceImpl extends BaseIServiceImpl<RoleAuthMapper, RoleAuth> implements RoleAuthService {
//
//	@Override
//	public boolean delByRoleId(String roleId) {
//		return this.remove(new LambdaQueryWrapper<RoleAuth>().eq(RoleAuth::getRoleId, roleId));
//	}
//}
//
