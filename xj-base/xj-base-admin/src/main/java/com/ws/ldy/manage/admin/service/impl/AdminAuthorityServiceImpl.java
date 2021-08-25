package com.ws.ldy.manage.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ws.ldy.common.cache.AuthCacheKeyUtil;
import com.ws.ldy.common.cache.CacheKey;
import com.ws.ldy.core.base.service.impl.BaseIServiceImpl;
import com.ws.ldy.core.cache.CacheUtil;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.enums.Admin;
import com.ws.ldy.core.enums.Base;
import com.ws.ldy.core.utils.BeanDtoVoUtil;
import com.ws.ldy.core.utils.EnumUtil;
import com.ws.ldy.core.utils.id.IdUtil;
import com.ws.ldy.core.utils.other.ClassUtil;
import com.ws.ldy.manage.admin.mapper.AdminAuthorityMapper;
import com.ws.ldy.manage.admin.model.dto.AdminAuthorityDTO;
import com.ws.ldy.manage.admin.model.entity.AdminAuthority;
import com.ws.ldy.manage.admin.model.entity.AdminRole;
import com.ws.ldy.manage.admin.model.entity.AdminRoleAuth;
import com.ws.ldy.manage.admin.model.vo.AdminAuthorityVO;
import com.ws.ldy.manage.admin.service.AdminAuthorityService;
import com.ws.ldy.manage.admin.service.AdminRoleAuthService;
import com.ws.ldy.manage.admin.service.AdminRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class AdminAuthorityServiceImpl extends BaseIServiceImpl<AdminAuthorityMapper, AdminAuthority> implements AdminAuthorityService {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private AdminRoleAuthService adminRoleAuthService;
    @Autowired
    private AdminRoleService adminRoleService;


    /**
     * 查询所有权限数据, 根据不同的端的枚举code 拼接最顶级的目录，顶级目录ID = -1
     *
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    @Override
    public List<AdminAuthorityVO> findList(Integer type, String pid) {
        // refreshAuthCache
        Map<String, AdminAuthority> authMap = CacheUtil.getMap(CacheKey.AUTH_MAP_KEY.getKey(), AdminAuthority.class);
        List<AdminAuthority> list = new ArrayList<>();
        for (AdminAuthority item : authMap.values()) {
            // 判断类型
            if (type != null && !item.getType().equals(type)) {
                continue;
            }
            // 判断pid
            if (StringUtils.isNotBlank(pid)) {
                if (pid.equals(item.getId()) || pid.equals(item.getPid())) {
                    list.add(item);
                }
            }
            if (StringUtils.isBlank(pid)) {
                list.add(item);
            }
        }
        // 查询所有
        // List<AdminAuthority> list = this.list(new LambdaQueryWrapper<AdminAuthority>()
        //         .orderByDesc(AdminAuthority::getType)
        //         .orderByDesc(AdminAuthority::getMethod)
        // );
        List<AdminAuthorityVO> adminAuthorityVOList = BeanDtoVoUtil.listVo(list, AdminAuthorityVO.class);
        // pid='' 的数据设置 pid 为枚举字典的code 值
        adminAuthorityVOList.forEach(i -> {
            if (StringUtils.isBlank(i.getPid())) {
                i.setPid(EnumUtil.getByCode(i.getType(), Admin.AuthorityType.class).getValue().toString());
            }
        });
        // 生成Admin.AuthorityType 的权限数据放入列表, 有多少条枚举字段就添加几条数据进去, id=枚举的code, pid=''的也设置为了枚举code, 同等于设置了父子级关系
        for (Admin.AuthorityType authorityType : Admin.AuthorityType.values()) {
            // 只拼接指定id的数据
            if (authorityType.getValue().equals(type)) {
                AdminAuthorityVO adminAuthorityVO = new AdminAuthorityVO();
                adminAuthorityVO.setId(authorityType.getValue().toString());
                adminAuthorityVO.setDesc(authorityType.getDesc());
                // 设置顶级 pid
                adminAuthorityVO.setPid("-1");
                adminAuthorityVO.setMethod("");
                adminAuthorityVO.setType(null);
                adminAuthorityVO.setState(null);
                adminAuthorityVO.setDisable(null);
                adminAuthorityVOList.add(adminAuthorityVO);
            }
            // 所有数据
            if (type == null) {
                AdminAuthorityVO adminAuthorityVO = new AdminAuthorityVO();
                adminAuthorityVO.setId(authorityType.getValue().toString());
                adminAuthorityVO.setDesc(authorityType.getDesc());
                // 设置顶级 pid
                adminAuthorityVO.setPid("-1");
                adminAuthorityVO.setMethod("");
                adminAuthorityVO.setType(null);
                adminAuthorityVO.setState(null);
                adminAuthorityVO.setDisable(null);
                adminAuthorityVOList.add(adminAuthorityVO);
            }
        }
        return adminAuthorityVOList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean upd(AdminAuthorityDTO dto) {
        // 更新
        boolean b = this.updateById(dto.convert(AdminAuthority.class));
        // 刷新缓存
        this.refreshAuthCache();
        return b;
    }


    /**
     *   接口自动扫描（1、项目启动时自动执行   2、设置了权限授权状态更新
     *   <p>
     *       扫描添加接口信息，扫描启动类下的所有包
     *       存在修改（不修改原数据的禁用启动和权限状态,防止重启项目时修改被还原）
     *       不存在添加
     *       多余的生成
     *   </p>
     *
     * @return void
     * @date 2019/11/25 0025 9:02
     */
    @SuppressWarnings("all")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean refreshAuthDB() {
        log.info("  @.@...正在更新接口资源,所有被权限管理的接口将被打印出来…… ^.^ ");

        // 获取启动类注解上需要扫描的路径
        Map<String, Object> annotatedBeans = context.getBeansWithAnnotation(SpringBootApplication.class);
        Class<?> aClass = annotatedBeans.values().toArray()[0].getClass();
        SpringBootApplication annotation = aClass.getAnnotation(SpringBootApplication.class);
        String[] packages = annotation.scanBasePackages();

        // 获取所有需要被扫描的文件
        List<Class<?>> classByPackageName = new ArrayList<>();
        for (String aPackage : packages) {
            List<Class<?>> classes = ClassUtil.getClasses(aPackage);
            classByPackageName.addAll(classes);
        }
        // 扫描包，获得包下的所有类
        // ClassUtil.getClasses(PACKAGE_NAME);
        // 当前当前数据库已经存在的所有url权限列表--> key=url，value=对象，获取后移除Map中已取出，最后剩下的全部删除
        List<AdminAuthority> list = this.list();
        Map<String, AdminAuthority> authorityMap = new HashMap<>();
        if (list != null && list.size() > 0) {
            authorityMap = list.stream().collect(Collectors.toMap(item -> AuthCacheKeyUtil.getAuthKey(item.getMethod(), item.getUrl()), item -> item));
        }
        // 所有需要修改list |添加list |删除的Ids
        List<AdminAuthority> updAuth = new ArrayList<>();
        List<AdminAuthority> addAuth = new ArrayList<>();
        List<String> delIds = new ArrayList<>();
        int classNum = 0;
        // 遍历所有类
        for (Class<?> classInfo : classByPackageName) {
            // 类上存在 @Api 注解 + @RequestMapping 的类进行下一步操作
            RequestMapping requestMappingClass = classInfo.getDeclaredAnnotation(RequestMapping.class);
            if (requestMappingClass == null) {
                continue;
            }
            // 判断当前类是否需要保存到接口权限内（目前：PC_ADMIN=平台 / PC_USER  用户端），,代码生成器生成的代码默认(BaseConstant.InterfaceType.RELEASE)不需要任何权限可访问,需手动修改BaseConstant.InterfaceType. 参数
            Integer uriType = null;
            Integer state = null;
            // 获取类名/类注释
            Api api = classInfo.getDeclaredAnnotation(Api.class);
            String classDesc =  classInfo.getSimpleName();
            String className =  classDesc;
            if (api != null) {
                classDesc = api.tags()[0];
                className = api.value();
            }
            String url = requestMappingClass.value()[0];
            if (url.indexOf(BaseConstant.Uri.apiAdmin) != -1) {
                // 管理端 | 默认需登录+授权
                uriType = Admin.AuthorityType.V0.getValue();
                state = Admin.AuthorityState.V2.getValue();
            } else if (url.indexOf(BaseConstant.Uri.apiClient) != -1) {
                // 用户端 | 默认需登录
                uriType = Admin.AuthorityType.V1.getValue();
                state = Admin.AuthorityState.V1.getValue();
            } else if (url.indexOf(BaseConstant.Uri.apiOpen) != -1) {
                // 通用 | 默认无需登录+无需授权
                uriType = Admin.AuthorityType.V2.getValue();
                state = Admin.AuthorityState.V0.getValue();
            } else if (url.indexOf(BaseConstant.Uri.apiOauth2) != -1) {
                // Oauth2.0接口 | 默认需Oauth2.0授权
                uriType = Admin.AuthorityType.V3.getValue();
                state = Admin.AuthorityState.V3.getValue();
            } else {
                // 其他 api默认放行
                uriType = Admin.AuthorityType.V2.getValue();
                state = Admin.AuthorityState.V0.getValue();
            }
            if (uriType != null) {
                String classLog = "  接口类：--------------@.@[" + classDesc + "-" + className + "]--";
                log.info(String.format("%-100s", classLog).replace(" ", "-"));
                if (authorityMap.containsKey( AuthCacheKeyUtil.getAuthKey("", url))) {
                    // 存在修改
                    AdminAuthority updAuthority = authorityMap.get( AuthCacheKeyUtil.getAuthKey("", url));
                    updAuthority.setUrl(url);
                    updAuthority.setDesc(classDesc);
                    updAuthority.setType(uriType);
                    updAuthority.setState(state);
                    // 添加/处理方法上的权限
                    this.putMethods(classInfo, authorityMap, updAuthority, updAuth, addAuth);
                    updAuth.add(updAuthority);
                    // 移除Map中已取出的数据
                    authorityMap.remove( AuthCacheKeyUtil.getAuthKey("", url));
                } else {
                    // 不存在新添加
                    AdminAuthority addAuthority = new AdminAuthority();
                    addAuthority.setId(IdUtil.snowflakeId());
                    addAuthority.setPid("");
                    addAuthority.setMethod("");
                    addAuthority.setUrl(url);
                    addAuthority.setDesc(classDesc);
                    addAuthority.setType(uriType);
                    addAuthority.setState(state);
                    addAuthority.setDisable(Base.Disable.V0.getValue());
                    // 添加/处理方法上的权限
                    this.putMethods(classInfo, authorityMap, addAuthority, updAuth, addAuth);
                    addAuth.add(addAuthority);
                }
                //被管理的类数量
                classNum++;
            }
        }
        //
        log.info("  本次刷新接口+类 总数量为:{} ,如接口 [备注信息] 或 [请求方式] 或 [终端] 发送改变,则已被刷新", updAuth.size());
        log.info("  本次添加接口+类 总量为:  {}", addAuth.size());
        // 修改
        if (updAuth.size() > 0) {
            this.updateBatchById(updAuth, 1024);
        }
        // 新增
        if (addAuth.size() > 0) {
            // 判断新增的接口中是否有重复的url，如果Url有重复直接将直接抛出异常
            Map<String, AdminAuthority> addUrls = null;
            try {
                addUrls = list.stream().collect(Collectors.toMap(item ->  AuthCacheKeyUtil.getAuthKey(item.getMethod(), item.getUrl()), item -> item));
            } catch (Exception e) {
                log.info("接口 URI 地址重复命名,不能存在相同的【请求方式+请求uri】");
                e.printStackTrace();
            }
            // 添加权限
            this.saveBatch(addAuth, 1024);
            // 给所有角色分配新接口的权限
            List<AdminRole> roles = adminRoleService.list();
            if (roles.size() > 0) {
                List<AdminRoleAuth> addRoleAuth = new LinkedList<>();
                for (AdminRole role : roles) {
                    for (AdminAuthority adminAuthority : addAuth) {
                        addRoleAuth.add(new AdminRoleAuth(adminAuthority.getId(), role.getId()));
                    }
                }
                // 更新
                adminRoleAuthService.saveBatch(addRoleAuth, 1024);
            }
        }
        // 删除
        if (authorityMap.size() > 0) {
            authorityMap.forEach((k, v) -> delIds.add(v.getId()));
            this.removeByIds(delIds);
        }
        log.info("  本次删除接口+类 总数量为: {}", delIds.size());
        log.info("  当前被管理的 总类数量为:  {}", classNum);
        log.info("  当前被管理的 总接口数量为: {}", ((updAuth.size() + addAuth.size()) - classNum));
        log.info("  @.@...");
        return true;
    }


    /**
     * 添加指定类的所有接口权限到 athorityList
     *
     * @param classInfo    当前类
     * @param authorityMap 当前数据库存在权限
     * @param authority    类的权限数据
     * @return void
     * @date 2019/11/25 0025 9:02
     */
    private void putMethods(Class<?> classInfo, Map<String, AdminAuthority> authorityMap, AdminAuthority authority, List<AdminAuthority> updAuth, List<AdminAuthority> addAuth) {
        // 获取类的所有方法循环添加方法级权限
        for (Method method : classInfo.getDeclaredMethods()) {
            // 获取 url | 请求方式
            String url = null;
            String requestMethod = null;
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            if (requestMapping != null) {
                url = authority.getUrl() + requestMapping.value()[0];
                requestMethod = requestMapping.method()[0].name();
            }
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            if (url == null && getMapping != null) {
                url = authority.getUrl() + (getMapping.value().length > 0 ? getMapping.value()[0] : "");
                requestMethod = "GET";
            }
            PostMapping postMapping = method.getAnnotation(PostMapping.class);
            if (url == null && postMapping != null) {
                url = authority.getUrl() + (postMapping.value().length > 0 ? postMapping.value()[0] : "");
                requestMethod = "POST";
            }
            PutMapping putMapping = method.getAnnotation(PutMapping.class);
            if (url == null && putMapping != null) {
                url = authority.getUrl() + (putMapping.value().length > 0 ? putMapping.value()[0] : "");
                requestMethod = "PUT";
            }
            DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
            if (url == null && deleteMapping != null) {
                url = authority.getUrl() + (deleteMapping.value().length > 0 ? deleteMapping.value()[0] : "");
                requestMethod = "DELETE";
            }
            if (url == null) {
                continue;
            }
            // 获取swagger注释
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            String desc = method.getName();
            if (apiOperation != null) {
                desc = apiOperation.value();
            }
            // 日志输出, 使用占位方式让日志对齐
            log.info("  接口资源：[{}]  -->  [{}]  -->  [{}] ", String.format("%-6s", requestMethod), String.format("%-40s", url), desc);
            // 存在修改，不存在新添加
            // url = url + ":" + methodName;
            if (authorityMap.containsKey( AuthCacheKeyUtil.getAuthKey(requestMethod, url))) {
                AdminAuthority updAuthority = authorityMap.get( AuthCacheKeyUtil.getAuthKey(requestMethod, url));
                updAuthority.setPid(authority.getId());
                updAuthority.setDesc(desc);
                updAuthority.setUrl(url);
                updAuthority.setMethod(requestMethod);
                updAuthority.setType(authority.getType());
                updAuth.add(updAuthority);
                // 移除Map中已取出的数据
                authorityMap.remove( AuthCacheKeyUtil.getAuthKey(requestMethod, url));
            } else {
                AdminAuthority addAuthority = new AdminAuthority();
                addAuthority.setId(IdUtil.snowflakeId());
                addAuthority.setPid(authority.getId());
                addAuthority.setDesc(desc);
                addAuthority.setUrl(url);
                addAuthority.setMethod(requestMethod);
                addAuthority.setType(authority.getType());
                addAuthority.setState(authority.getState());
                addAuthority.setDisable(authority.getDisable());
                addAuth.add(addAuthority);
            }
        }
    }


    /**
     * 获取接口列表，给指定角色的拥有的权限数据赋予选中状态
     *
     * @param roleId
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    @Override
    public List<AdminAuthorityVO> findByRoleIdAuthorityChecked(String roleId) {
        // 获取当前角色拥有的url权限列表
        List<AdminRoleAuth> roleIds = adminRoleAuthService.list(new LambdaQueryWrapper<AdminRoleAuth>()
                .select(AdminRoleAuth::getRoleId, AdminRoleAuth::getAuthId, AdminRoleAuth::getId)
                .eq(AdminRoleAuth::getRoleId, roleId)
        );
        List<String> roleAuthIds = roleIds != null ? roleIds.stream().map(AdminRoleAuth::getAuthId).collect(Collectors.toList()) : new ArrayList<>();
        // 获取所有管理端的url,请求方式排序( PC_admin)
        List<AdminAuthority> authorityList = this.list(new LambdaQueryWrapper<AdminAuthority>()
                .orderByAsc(AdminAuthority::getMethod)
                .eq(AdminAuthority::getType, Admin.AuthorityType.V0.getValue())
        );
        // 返回数据处理
        if (authorityList == null || authorityList.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<AdminAuthorityVO> adminAuthorityVOList = BeanDtoVoUtil.listVo(authorityList, AdminAuthorityVO.class);
            adminAuthorityVOList.forEach(authVO -> {
                if (roleAuthIds.contains(authVO.getId())) {
                    authVO.setIsChecked(true);
                } else {
                    authVO.setIsChecked(false);
                }
            });
            return adminAuthorityVOList;
        }
    }


    /**
     * 获取权限列表，给指定角色的拥有的权限数据赋予选中状态(树结构)
     * @param roleId
     * @return
     */
    @Override
    public List<AdminAuthorityVO> findByRoleIdAuthorityTreeChecked(String roleId) {
        // 获取当前角色拥有的url权限列表
        List<AdminRoleAuth> roleIds = adminRoleAuthService.list(new LambdaQueryWrapper<AdminRoleAuth>()
                .select(AdminRoleAuth::getRoleId, AdminRoleAuth::getAuthId, AdminRoleAuth::getId)
                .eq(AdminRoleAuth::getRoleId, roleId)
        );
        List<String> roleAuthIds = roleIds != null ? roleIds.stream().map(AdminRoleAuth::getAuthId).collect(Collectors.toList()) : new ArrayList<>();
        // 获取所有管理端的url,请求方式排序( PC_admin)
        List<AdminAuthority> authorityList = this.list(new LambdaQueryWrapper<AdminAuthority>()
                .orderByAsc(AdminAuthority::getMethod)
                .eq(AdminAuthority::getType, Admin.AuthorityType.V0.getValue())
        );
        List<AdminAuthorityVO> respAuthorityVOList = new ArrayList<>();
        // 返回数据处理
        if (authorityList == null || authorityList.isEmpty()) {
            return respAuthorityVOList;
        } else {
            List<AdminAuthorityVO> adminAuthorityVOList = BeanDtoVoUtil.listVo(authorityList, AdminAuthorityVO.class);
            adminAuthorityVOList.forEach(authVO -> {
                if (roleAuthIds.contains(authVO.getId())) {
                    authVO.setIsChecked(true);
                } else {
                    authVO.setIsChecked(false);
                }
                // 拼接下级tree数据
                if ("".equals(authVO.getPid()) || "0".equals(authVO.getPid())) {
                    adminAuthorityVOList.forEach(authTwoVO -> {
                        if (authTwoVO.getPid().equals(authVO.getId())) {
                            if (authVO.getAuthoritys() == null) {
                                ArrayList<AdminAuthorityVO> authorityVOS = new ArrayList<>();
                                authorityVOS.add(authTwoVO);
                                authVO.setAuthoritys(authorityVOS);
                            } else {
                                authVO.getAuthoritys().add(authTwoVO);
                            }
                        }
                    });
                    respAuthorityVOList.add(authVO);
                }
            });
            return respAuthorityVOList;
        }
    }


    /**
     * 获取用户的url权限列表，只返回未禁用的 需要登录+授权的url
     *
     * @param  userId 用户id
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    @Override
    public List<String> findByUserIdaAndDisableFetchAuthority(String userId) {
        List<AdminAuthority> auth = baseMapper.findByUserIdaAndDisableFetchAuthority(
                userId, Base.Disable.V0.getValue(), Admin.AuthorityState.V2.getValue()
        );
        if (auth == null) {
            return new ArrayList<>();
        } else {
            return auth.stream().map(p -> AuthCacheKeyUtil.getCacheKey(p.getMethod(), p.getUrl())).collect(Collectors.toList());
        }
    }


    /**
     * 刷新权限数据
     * @author wangsong
     * @date 2020/8/22 0022 22:35
     * @return void
     * @version 1.0.0
     */
    @Override
    public void refreshAuthCache() {
        // 查询权限表中所有接口
        List<AdminAuthority> authorityList = this.list(new LambdaQueryWrapper<AdminAuthority>()
                .orderByDesc(AdminAuthority::getType)
                .orderByDesc(AdminAuthority::getMethod)
        );
        // 缓存所有接口数据到 jvm
        Map<String, AdminAuthority> authMap = authorityList.stream().collect(Collectors.toMap(p -> AuthCacheKeyUtil.getCacheKey(p.getMethod(), p.getUrl()), auth -> auth));

        //
        CacheUtil.set(CacheKey.AUTH_MAP_KEY.getKey(), authMap);

        // 数据统计
        int authorityCount = 0;
        int authorityCountState2 = 0;
        int authorityCountState1 = 0;
        int authorityCountState0 = 0;
        for (AdminAuthority auth : authorityList) {
            // 所有被管理的权限,管理端，需登录/授权的接口数量
            if (StringUtils.isNotBlank(auth.getPid()) && auth.getState().equals(Admin.AuthorityState.V2.getValue())) {
                authorityCountState2++;
            }
            // 需登录接口数量
            if (StringUtils.isNotBlank(auth.getPid()) && auth.getState().equals(Admin.AuthorityState.V1.getValue())) {
                authorityCountState1++;
            }
            // 放行接口数量
            if (StringUtils.isNotBlank(auth.getPid()) && auth.getState().equals(Admin.AuthorityState.V0.getValue())) {
                authorityCountState0++;
            }
            // 总接口数
            if (StringUtils.isNotBlank(auth.getPid())) {
                authorityCount++;
            }
        }
        log.info("权限数据加载成功, 当前 [无需登录] 的接口数量为:    {} ", authorityCountState0);
        log.info("权限数据加载成功, 当前 [只需登录] 的接口数量为:    {}", authorityCountState1);
        log.info("权限数据加载成功, 当前 [需登录/授权] 的接口数量为: {}", authorityCountState2);
        log.info("权限数据加载成功, 当前 [所有接口] 的接口数量为:    {}", authorityCount);
    }



    /**
     * 子级找父级
     *
     * @param
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    @Override
    public AdminAuthority findFatherAuth(String uri) {
        Map<String, AdminAuthority> authMap = CacheUtil.getMap(CacheKey.AUTH_MAP_KEY.getKey(), AdminAuthority.class);
        AdminAuthority adminAuthority = authMap.get(uri);
        if (adminAuthority == null) {
            return null;
        }
        for (AdminAuthority auth : authMap.values()) {
            if (adminAuthority.getPid().equals(auth.getId())) {
                return auth;
            }
        }
        return null;
    }
}

