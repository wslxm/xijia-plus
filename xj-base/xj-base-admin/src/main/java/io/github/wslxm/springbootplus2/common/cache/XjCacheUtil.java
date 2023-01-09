package io.github.wslxm.springbootplus2.common.cache;

import io.github.wslxm.springbootplus2.core.constant.BooleanConst;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.core.utils.bean.SpringContextUtil;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Authority;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.BlacklistVO;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.ConfigVO;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.DictionaryVO;
import io.github.wslxm.springbootplus2.manage.sys.service.BlacklistService;
import io.github.wslxm.springbootplus2.manage.sys.service.impl.AuthorityServiceImpl;
import io.github.wslxm.springbootplus2.manage.sys.service.impl.BlacklistServiceImpl;
import io.github.wslxm.springbootplus2.manage.sys.service.impl.ConfigServiceImpl;
import io.github.wslxm.springbootplus2.manage.sys.service.impl.DictionaryServiceImpl;

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
    public static List<String> findBlacklistByType(Integer type) {
        // 获取bean
        String beanName = toLowerCaseFirstOne(BlacklistServiceImpl.class.getSimpleName());
        BlacklistService bean = (BlacklistService) SpringContextUtil.getBean(beanName);
        // 调用方法处理数据
        List<BlacklistVO> blacklistVos = bean.listByType(type);
        if (!blacklistVos.isEmpty()) {
            return blacklistVos.stream().map(BlacklistVO::getIp).collect(Collectors.toList());
        }
        return new ArrayList<>(0);
    }

    /**
     * 获取全局配置 （根据code查询）
     *
     * @param code
     * @return
     */
    public static ConfigVO findConfigByCode(String code) {
        // 获取bean
        String beanName = toLowerCaseFirstOne(ConfigServiceImpl.class.getSimpleName());
        ConfigServiceImpl bean = (ConfigServiceImpl) SpringContextUtil.getBean(beanName);
        // 调用方法处理数据
        return bean.findByCode(code);
    }

    /**
     * 获取全局配置 （根据code查询）, 针对布尔参数
     *
     * @param code
     * @return 数据不存在或内容不为true, 都将返回false
     */
    public static boolean findConfigBooleanByCode(String code) {
        // 获取bean
        String beanName = toLowerCaseFirstOne(ConfigServiceImpl.class.getSimpleName());
        ConfigServiceImpl bean = (ConfigServiceImpl) SpringContextUtil.getBean(beanName);
        // 调用方法处理数据
        ConfigVO configVO = bean.findByCode(code);
        if (configVO == null) {
            return false;
        }
        return BooleanConst.TRUE_STE.equals(configVO.getContent());
    }


    /**
     * 查询字典数据
     * isDisable 是否查询禁用数据 =true 查询*默认   =false 不查询
     *
     * @return
     */
    public static List<DictionaryVO> findListAll(Boolean isDisable) {
        String beanName = toLowerCaseFirstOne(DictionaryServiceImpl.class.getSimpleName());
        DictionaryServiceImpl bean = (DictionaryServiceImpl) SpringContextUtil.getBean(beanName);
        // 查询并处理数据
        List<DictionaryVO> list = bean.findListAll();
        // 复制一份数据，防止原数据被改动
        List<DictionaryVO> copyList = BeanDtoVoUtil.listVo(list, DictionaryVO.class);
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
    public static Map<String, Authority> findAuthAllToMap() {
        String beanName = toLowerCaseFirstOne(AuthorityServiceImpl.class.getSimpleName());
        AuthorityServiceImpl bean = (AuthorityServiceImpl) SpringContextUtil.getBean(beanName);
        return bean.findListAllToMap();
    }

//
//	/**
//	 * 获取 登录用户 有权限的 Uri 接口列表
//	 *
//	 * @param userId
//	 * @return
//	 */
//	public static List<String> findAuthByUserId(String userId) {
//		// 获取bean
//		String beanName = toLowerCaseFirstOne(AuthorityIServiceImpl.class.getSimpleName());
//		AuthorityIServiceImpl bean = (AuthorityIServiceImpl) SpringContextUtil.getBean(beanName);
//		// 调用方法处理数据
//		return bean.findByUserIdAuthority(userId);
//	}


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
