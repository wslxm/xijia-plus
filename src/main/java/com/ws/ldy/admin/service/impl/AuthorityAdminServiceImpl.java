package com.ws.ldy.admin.service.impl;

import com.ws.ldy.admin.dao.AuthorityAdminDao;
import com.ws.ldy.admin.entity.AuthorityAdmin;
import com.ws.ldy.admin.service.AuthorityAdminService;
import com.ws.ldy.base.service.impl.BaseServiceImpl;
import com.ws.ldy.common.annotation.LdyAuthority;
import com.ws.ldy.common.utils.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AuthorityAdminServiceImpl extends BaseServiceImpl<AuthorityAdmin, Integer> implements AuthorityAdminService {

    /**
     * url权限注解扫包范围
     */
    private final static String PACKAGE_NAME = "com.ws.ldy";
    /**
     * 权限dao
     */
    @Autowired
    private AuthorityAdminDao authorityAdminDao;

    @Override
    public List<AuthorityAdmin> findUserIdRoleAuthority(Integer userId) {
        return authorityAdminDao.findUserIdRoleAuthority(userId);
    }


    /**
     * TODO    包所有类
     *
     * @return void
     * @date 2019/11/25 0025 9:02
     */
    @Override
    public void putClass() {
        // 扫描包，获得包下的所有类
        List<Class<?>> classByPackageName = ClassUtil.getClasses(PACKAGE_NAME);
        // 当前当前数据库已经存在的url权限列表
        List<AuthorityAdmin> list = authorityAdminDao.findAll();
        Map<String, AuthorityAdmin> map = new HashMap();
        list.forEach(item -> map.put(item.getName(), item));
        // 需保存的权限聚合
        List<AuthorityAdmin> athorityList = new ArrayList<>();
        AuthorityAdmin authority = null;
        RequestMapping reqClass = null;
        for (Class<?> classInfo : classByPackageName) {
            // 判断该类上属否存在 @LdyAuthority 注解
            LdyAuthority ldyClass = classInfo.getDeclaredAnnotation(LdyAuthority.class);
            if (ldyClass != null) {
                System.out.println("类--》" + ldyClass.value()[0] + "-->" + ldyClass.value()[1]);
                //存在修改，不存在新添加
                if (map.containsKey(ldyClass.value()[0])) {
                    //权限名
                    authority = map.get(ldyClass.value()[0]);
                } else {
                    authority = new AuthorityAdmin();
                }
                //本类所有方法
                authority.setPid(0);
                authority.setName(ldyClass.value()[0]);
                authority.setDesc(ldyClass.value()[1]);
                reqClass = classInfo.getDeclaredAnnotation(RequestMapping.class);
                authority.setUrl(reqClass.value()[0]);
                // 添加类级别权限,返回添加信息
                AuthorityAdmin save = authorityAdminDao.save(authority);
                // 添加方法级权限至athorityList
                this.putMethods(classInfo, athorityList, map, save);
            }
        }
        //添加所有方法级权限
        authorityAdminDao.saveAll(athorityList);
    }


    /**
     * TODO    添加指定类的所有接口权限到athorityList
     *
     * @param classInfo
     * @param authority    方法类的权限数据
     * @param athorityList 所有方法的权限集
     * @param map          当前数据库存在权限
     * @return void
     * @date 2019/11/25 0025 9:02
     */
    private void putMethods(Class<?> classInfo, List<AuthorityAdmin> athorityList, Map<String, AuthorityAdmin> map, AuthorityAdmin authority) {
        Method[] methods = classInfo.getDeclaredMethods();
        AuthorityAdmin auth = null;
        //循环添加方法级权限
        for (Method method : methods) {
            LdyAuthority ldyMethod = method.getAnnotation(LdyAuthority.class);
            if (ldyMethod == null) {
                continue;
            }
            //存在修改，不存在新添加
            if (map.containsKey(ldyMethod.value()[0])) {
                //获取已经有权限（根据权限名）
                auth = map.get(ldyMethod.value()[0]);
            } else {
                auth = new AuthorityAdmin();
            }
            auth.setPid(authority.getId());            // 类权限id（父级id）
            auth.setName(ldyMethod.value()[0]);        // 权限名称
            auth.setDesc(ldyMethod.value()[1]);        // 权限描叙
            //判断接口类型
            RequestMapping allMethod = method.getDeclaredAnnotation(RequestMapping.class);
            PostMapping postMethod = method.getDeclaredAnnotation(PostMapping.class);
            GetMapping getMethod = method.getDeclaredAnnotation(GetMapping.class);
            DeleteMapping deleteMethod = method.getDeclaredAnnotation(DeleteMapping.class);
            PutMapping putMethod = method.getDeclaredAnnotation(PutMapping.class);
            if (allMethod != null) {
                auth.setUrl(authority.getUrl() + allMethod.value()[0]);  // 接口url
                auth.setType("All");  // 支持所有请求方式
            } else if (postMethod != null) {
                auth.setUrl(authority.getUrl() + postMethod.value()[0]);
                auth.setType("POST");
            } else if (getMethod != null) {
                auth.setUrl(authority.getUrl() + getMethod.value()[0]);
                auth.setType("GET");
            } else if (deleteMethod != null) {
                auth.setUrl(authority.getUrl() + deleteMethod.value()[0]);
                auth.setType("DELETE");
            } else if (putMethod != null) {
                auth.setUrl(authority.getUrl() + putMethod.value()[0]);
                auth.setType("PUT");
            }
            athorityList.add(auth);
            System.out.println("方法--》" + ldyMethod.value()[0] + "-->" + ldyMethod.value()[1] + "-->" + auth.getType());
        }
    }
}

