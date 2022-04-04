package io.github.wslxm.springbootplus2.cache;

import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.core.utils.bean.SpringContextUtil;
import io.github.wslxm.springbootplus2.manage.admin.model.entity.AdminAuthority;
import io.github.wslxm.springbootplus2.manage.admin.model.vo.AdminDictionaryVO;
import io.github.wslxm.springbootplus2.manage.admin.service.impl.AdminAuthorityServiceImpl;
import io.github.wslxm.springbootplus2.manage.admin.service.impl.AdminDictionaryServiceImpl;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminBlacklistVO;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminConfigVO;
import io.github.wslxm.springbootplus2.manage.xj.service.XjAdminBlacklistService;
import io.github.wslxm.springbootplus2.manage.xj.service.impl.XjAdminBlacklistServiceImpl;
import io.github.wslxm.springbootplus2.manage.xj.service.impl.XjAdminConfigServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统 缓存缓存对象获取 类
 * <P>
 *     系统缓存对象 --  缓存key  CacheKey
 * </P>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2022/3/23 14:36
 */
public class XjCacheUtil {

	/**
	 * 获取黑名单白名单 IP集 （通过type获取）
	 *
	 * @param type
	 * @return List<IP>
	 */
	public static List<String> listByType(Integer type) {
		// 获取bean
		String beanName = toLowerCaseFirstOne(XjAdminBlacklistServiceImpl.class.getSimpleName());
		XjAdminBlacklistService bean = (XjAdminBlacklistService) SpringContextUtil.getBean(beanName);
		// 调用方法处理数据
		List<XjAdminBlacklistVO> xjAdminBlacklistVOS = bean.listByType(type);
		if (!xjAdminBlacklistVOS.isEmpty()) {
			return xjAdminBlacklistVOS.stream().map(XjAdminBlacklistVO::getIp).collect(Collectors.toList());
		}
		return new ArrayList<>(0);
	}

	/**
	 * 获取全局配置 （根据code查询）
	 *
	 * @param code
	 * @return
	 */
	public static XjAdminConfigVO getConfigByCode(String code) {
		// 获取bean
		String beanName = toLowerCaseFirstOne(XjAdminConfigServiceImpl.class.getSimpleName());
		XjAdminConfigServiceImpl bean = (XjAdminConfigServiceImpl) SpringContextUtil.getBean(beanName);
		// 调用方法处理数据
		return bean.findByCode(code);
	}


	/**
	 * 查询字典数据
	 * isDisable 是否查询禁用数据 =true 查询*默认   =false 不查询
	 *
	 * @return
	 */
	public static List<AdminDictionaryVO> findListALL(Boolean isDisable) {
		String beanName = toLowerCaseFirstOne(AdminDictionaryServiceImpl.class.getSimpleName());
		AdminDictionaryServiceImpl bean = (AdminDictionaryServiceImpl) SpringContextUtil.getBean(beanName);
		// 查询并处理数据
		List<AdminDictionaryVO> list = bean.findListALL();
		// 复制一份数据，防止原数据被改动
		List<AdminDictionaryVO> copyList = BeanDtoVoUtil.listVo(list, AdminDictionaryVO.class);
		isDisable = isDisable == null ? true : isDisable;
		if (!isDisable) {
			copyList = copyList.stream().filter(p -> p.getDisable().equals(Base.Disable.V0.getValue())).collect(Collectors.toList());
		}
		return copyList;
	}


	/**
	 * 查询系统的所有接口权限数据
	 *
	 * @return
	 */
	public static Map<String, AdminAuthority> findListAllToMap() {
		String beanName = toLowerCaseFirstOne(AdminAuthorityServiceImpl.class.getSimpleName());
		AdminAuthorityServiceImpl bean = (AdminAuthorityServiceImpl) SpringContextUtil.getBean(beanName);
		return bean.findListAllToMap();
	}


	/**
	 * 获取 登录用户 有权限的 Uri 接口列表
	 *
	 * @param userId
	 * @return
	 */
	public static List<String> findByUserIdAuthority(String userId) {
		// 获取bean
		String beanName = toLowerCaseFirstOne(AdminAuthorityServiceImpl.class.getSimpleName());
		AdminAuthorityServiceImpl bean = (AdminAuthorityServiceImpl) SpringContextUtil.getBean(beanName);
		// 调用方法处理数据
		return bean.findByUserIdAuthority(userId);
	}


	/**
	 * 首字母转小写
	 *
	 * @author wangsong
	 * @email 1720696548@qq.com
	 * @date 2022/3/23 15:21
	 */
	private static String toLowerCaseFirstOne(String str) {
		if (Character.isLowerCase(str.charAt(0))) {
			return str;
		} else {
			return Character.toLowerCase(str.charAt(0)) + str.substring(1);
		}
	}
}
