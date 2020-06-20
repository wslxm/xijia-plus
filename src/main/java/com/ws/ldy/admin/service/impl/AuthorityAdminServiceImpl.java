package com.ws.ldy.admin.service.impl;

import com.ws.ldy.admin.mapper.AuthorityAdminMapper;
import com.ws.ldy.admin.model.entity.AuthorityAdmin;
import com.ws.ldy.admin.service.AuthorityAdminService;
import com.ws.ldy.base.enums.BaseConstant;
import com.ws.ldy.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.common.utils.ClassUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class AuthorityAdminServiceImpl extends BaseIServiceImpl<AuthorityAdminMapper, AuthorityAdmin> implements AuthorityAdminService {
    /**
     * url权限注解扫包范围
     */
    private final static String PACKAGE_NAME = "com.ws.ldy";


    @Override
    public List<AuthorityAdmin> findUserIdRoleAuthority(Integer userId) {
        return this.findUserIdRoleAuthority(userId);
    }


    /**
     * TODO  添加接口--扫描包下所有类
     *
     * @return void
     * @date 2019/11/25 0025 9:02
     */
    @Override
    @Transactional
    public void refreshAuthority() {
        // 扫描包，获得包下的所有类
        List<Class<?>> classByPackageName = ClassUtils.getClasses(PACKAGE_NAME);

        // 当前当前数据库已经存在的所有url权限列表--> key=url，value=对象，获取后移除Map中已取出，最后剩下的全部删除
        Map<String, AuthorityAdmin> authorityMap = new HashMap();
        List<AuthorityAdmin> authorityList = this.list();
        if (authorityList != null && authorityList.size() > 0) {
            authorityList.forEach(item -> authorityMap.put(item.getUrl(), item));
        }

        // 遍历所有类
        for (Class<?> classInfo : classByPackageName) {
            // 类上存在 @Api 注解 + @RequestMapping 的类进行下一步操作
            Api apiClass = classInfo.getDeclaredAnnotation(Api.class);
            RequestMapping requestMappingClass = classInfo.getDeclaredAnnotation(RequestMapping.class);
            if (apiClass == null || requestMappingClass == null) {
                continue;
            }
            // 判断当前类是否需要保存到接口权限内（目前：PC_ADMIN=平台 ） 需要
            if (apiClass.description().equals(BaseConstant.InterfaceType.PC_ADMIN)) {
                String url = requestMappingClass.value()[0];
                System.out.println("当前类信息-->" + apiClass.value() + "-->" + apiClass.tags()[0] + " --> " + url);
                if (authorityMap.containsKey(url)) {
                    // 存在修改
                    AuthorityAdmin updAuthority = authorityMap.get(url);
                    updAuthority.setUrl(url);                               // 接口URL
                    updAuthority.setDesc(apiClass.tags()[0]);               // 接口描叙
                    this.updateById(updAuthority);
                    // 添加方法上的权限
                    this.putMethods(classInfo, authorityMap, updAuthority);
                    // 移除Map中已取出
                    authorityMap.remove(url);
                } else {
                    // 不存在新添加
                    AuthorityAdmin addAuthority = new AuthorityAdmin();
                    addAuthority.setPid(0);                               // 请求Pid
                    addAuthority.setMethod("");                           // 请求方式
                    addAuthority.setUrl(url);                             // 接口URL
                    addAuthority.setDesc(apiClass.tags()[0]);             // 接口描叙
                    this.save(addAuthority);
                    // 添加方法上的权限
                    this.putMethods(classInfo, authorityMap, addAuthority);
                }
            }
        }
        // 删除多余数据
        if (authorityMap.size() > 0) {
            List<Integer> delIds = new ArrayList<>();
            authorityMap.forEach((k, v) -> {
                delIds.add(v.getId());
            });
            this.removeByIds(delIds);
        }
    }


    /**
     * TODO    添加指定类的所有接口权限到athorityList
     *
     * @param classInfo    当前类
     * @param authorityMap 当前数据库存在权限
     * @param authority    类的权限数据
     * @return void
     * @date 2019/11/25 0025 9:02
     */
    private void putMethods(Class<?> classInfo, Map<String, AuthorityAdmin> authorityMap, AuthorityAdmin authority) {
        // 获取类的所有方法
        Method[] methods = classInfo.getDeclaredMethods();

        //循环添加方法级权限
        for (Method method : methods) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            if (requestMapping == null || apiOperation == null) {
                log.info(method.getDeclaringClass().getName() + "." + method.getName() + "方法没有@ApiOperation 或 @RequestMapping注解");
                continue;
            }
            // url
            String url = authority.getUrl() + requestMapping.value()[0];
            // 请求方式
            String requestMethod = requestMapping.method()[0].name();
            // 描叙
            String desc = apiOperation.value();
            log.info("方法:{} URL:{} 请求方式:{} 描叙:{} ", method.getDeclaringClass().getName() + "." + method.getName(), url, requestMethod, desc);
            // 存在修改，不存在新添加
            if (authorityMap.containsKey(url)) {
                // 获取已经有权限（根据权限名）
                AuthorityAdmin updAuthority = authorityMap.get(url);
                updAuthority.setPid(authority.getId());   // 类权限id（父级id）
                updAuthority.setDesc(desc);               // 权限描叙
                updAuthority.setUrl(url);                 // 接口url
                updAuthority.setMethod(requestMethod);    // 请求方式
                this.updateById(updAuthority);
                // 移除Map中已取出
                authorityMap.remove(url);
            } else {
                AuthorityAdmin addAuthority = new AuthorityAdmin();
                addAuthority.setPid(authority.getId());   // 类权限id（父级id）
                addAuthority.setDesc(desc);               // 权限描叙
                addAuthority.setUrl(url);                 // 接口url
                addAuthority.setMethod(requestMethod);    // 请求方式
                this.save(addAuthority);
            }
        }
    }
}

