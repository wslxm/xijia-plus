package com.ws.ldy.adminconsole.service.impl;

import com.ws.ldy.adminconsole.entity.AuthorityAdmin;
import com.ws.ldy.adminconsole.service.AuthorityAdminService;
import com.ws.ldy.adminconsole.service.base.impl.BaseAdminConsoleServiceImpl;
import com.ws.ldy.admincore.annotation.LdyAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class AuthorityAdminServiceImpl extends BaseAdminConsoleServiceImpl<AuthorityAdmin, Integer> implements AuthorityAdminService {


    /**
     * TODO    包所有类
     *
     * @return void
     * @date 2019/11/25 0025 9:02
     */
    @Override
    public void putClass(List<Class<?>> classByPackageName) {
        //当前存在url权限列表
        List<AuthorityAdmin> list = dao.authorityAdminDao.findAll();
        Map<String, AuthorityAdmin> map = new HashMap();
        list.forEach(item -> map.put(item.getName(), item));
        //保存权限
        List<AuthorityAdmin> athorityList = new ArrayList<>();
        AuthorityAdmin authority = null;
        for (Class<?> classInfo : classByPackageName) {
            // 判断该类上属否存在 @LdyAuthority 注解
            LdyAuthority ldyClass = classInfo.getDeclaredAnnotation(LdyAuthority.class);
            if (ldyClass != null) {
                System.out.println("类--》" + ldyClass.value()[0] + "-->" + ldyClass.value()[1]);
                RequestMapping reqClass = classInfo.getDeclaredAnnotation(RequestMapping.class);
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
                authority.setUrl(reqClass.value()[0]);
                //添加,返回添加信息
                AuthorityAdmin save = dao.authorityAdminDao.save(authority);
                putMethods(classInfo, athorityList, map, save);
            }
        }
        //添加所有方法级权限
        dao.authorityAdminDao.saveAll(athorityList);
    }

    @Override
    public List<AuthorityAdmin> findUserIdRoleAuthority(Integer userId) {
        return dao.authorityAdminDao.findUserIdRoleAuthority(userId);
    }


    /**
     * TODO    本类所有方法
     *
     * @param classInfo
     * @param authority    方法类的权限数据
     * @param athorityList 所有方法的权限集
     * @param map          当前数据库存在权限
     * @return void
     * @date 2019/11/25 0025 9:02
     */
    public void putMethods(Class<?> classInfo, List<AuthorityAdmin> athorityList, Map<String, AuthorityAdmin> map, AuthorityAdmin authority) {
        Method[] methods = classInfo.getDeclaredMethods();
        AuthorityAdmin auth = null;
        for (Method method : methods) {
            LdyAuthority ldyMethod = method.getAnnotation(LdyAuthority.class);
            if (ldyMethod == null) {
                continue;
            }
            RequestMapping reqMethod = method.getDeclaredAnnotation(RequestMapping.class);
            String url = reqMethod.value()[0];
            String updUrl = reqMethod.value()[0];
            if (url.lastIndexOf("}") != -1) {
                int index = url.lastIndexOf("/");
                updUrl = url.substring(0, index);
            }
            //存在修改，不存在新添加
            if (map.containsKey(ldyMethod.value()[0])) {
                //权限名
                auth = map.get(ldyMethod.value()[0]);
            } else {
                auth = new AuthorityAdmin();
            }
            //父级id
            auth.setPid(authority.getId());
            auth.setName(ldyMethod.value()[0]);
            auth.setDesc(ldyMethod.value()[1]);
            auth.setUrl(authority.getUrl() + updUrl);
            athorityList.add(auth);
            System.out.println("方法--》" + ldyMethod.value()[0] + "-->" + ldyMethod.value()[1]);
        }
    }
}

