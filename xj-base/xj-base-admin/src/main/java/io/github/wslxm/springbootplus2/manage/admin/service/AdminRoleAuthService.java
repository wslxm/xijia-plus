package io.github.wslxm.springbootplus2.manage.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminRoleAuth;

/**
 *   角色+Url权限
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Mon Nov 25 11:38:29 CST 2019
 */
public interface AdminRoleAuthService extends IService<AdminRoleAuth> {

	/**
	 * 删除指定角色 关联的权限
	 *
	 * @param roleId
	 * @return boolean
	 * @author wangsong
	 * @date 2022/8/18 14:31
	 */
	boolean delByRoleId(String roleId);

}
