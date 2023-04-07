package io.github.wslxm.springbootplus2.manage.sys.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.wslxm.springbootplus2.common.auth.util.JwtUtil;
import io.github.wslxm.springbootplus2.common.cache.AuthCacheKeyUtil;
import io.github.wslxm.springbootplus2.common.cache.CacheKey;
import io.github.wslxm.springbootplus2.common.cache.XjCacheUtil;
import io.github.wslxm.springbootplus2.core.base.service.impl.BaseServiceImpl;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.enums.Base;
import io.github.wslxm.springbootplus2.core.utils.BeanDtoVoUtil;
import io.github.wslxm.springbootplus2.manage.sys.mapper.AuthorityMapper;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.AuthorityDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.entity.Authority;
import io.github.wslxm.springbootplus2.manage.sys.model.query.AuthorityByUserIdQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.query.AuthorityQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.AuthorityVO;
import io.github.wslxm.springbootplus2.manage.sys.service.AuthorityService;
import io.github.wslxm.springbootplus2.starter.redis.lock.XjDistributedLock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wangsong
 */
@Service
@Slf4j
public class AuthorityServiceImpl extends BaseServiceImpl<AuthorityMapper, Authority> implements AuthorityService {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private AuthorityMapper authorityMapper;

    /**
     * 顶级父id
     */
    private static final String PID = "0";


    /**
     * 查询所有权限数据, 根据不同的端的枚举code 拼接最顶级的目录，顶级目录ID = -1
     *
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    @Override
    public List<AuthorityVO> list(AuthorityQuery query) {
        String loginUserId = ObjectUtil.defaultIfNull(query.getIsLoginUser(), () -> JwtUtil.getJwtUser(request).getUserId(), null);
        boolean isTree = ObjectUtil.defaultIfNull(query.getIsTree(), false);

        // 查询权限
        AuthorityByUserIdQuery authorityByUserIdQuery = new AuthorityByUserIdQuery();
        authorityByUserIdQuery.setUserId(loginUserId);
        authorityByUserIdQuery.setPid(query.getPid());
        authorityByUserIdQuery.setType(query.getType());
        authorityByUserIdQuery.setState(query.getState());
        authorityByUserIdQuery.setDisable(query.getDisable());
        authorityByUserIdQuery.setAsc(query.getAsc());
        authorityByUserIdQuery.setDesc(query.getDesc());

        List<Authority> authoritys = authorityMapper.list(authorityByUserIdQuery);
        List<AuthorityVO> adminAuthorityVOList = BeanDtoVoUtil.listVo(authoritys, AuthorityVO.class);

        // 3、resurt，返回list
        if (!isTree || adminAuthorityVOList.size() <= 1) {
            return adminAuthorityVOList;
        }
        // 4、result，返回tree
        List<AuthorityVO> adminAuthorityVoTree = new ArrayList<>();
        // 循环处理数据,获取第一级类数据
        for (AuthorityVO authVO : adminAuthorityVOList) {
            if ("".equals(authVO.getPid()) || PID.equals(authVO.getPid())) {
                // 循环处理数据,获取第二级方法数据
                List<AuthorityVO> nextAuthorityVoTree = new ArrayList<>();
                for (AuthorityVO authTwoVO : adminAuthorityVOList) {
                    if (authTwoVO.getPid().equals(authVO.getId())) {
                        nextAuthorityVoTree.add(authTwoVO);
                    }
                }
                authVO.setAuthoritys(nextAuthorityVoTree);
                adminAuthorityVoTree.add(authVO);
            }
        }
        return adminAuthorityVoTree;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    @Caching(evict = {
            @CacheEvict(value = CacheKey.AUTH_MAP_ALL, allEntries = true),
    })
    public Boolean upd(String id, AuthorityDTO dto) {
        // 更新
        Authority entity = dto.convert(Authority.class);
        entity.setId(id);
        boolean b = this.updateById(entity);
        this.refreshAuthCache();
        return b;
    }


    /**
     * 接口自动扫描（1、项目启动时自动执行   2、设置了权限授权状态更新
     * <p>
     * 扫描添加接口信息，扫描启动类下的所有包
     * 存在修改（不修改原数据的禁用启动和权限状态,防止重启项目时修改被还原）
     * 不存在添加
     * 多余的生成
     * </p>
     *
     * @return void
     * @date 2019/11/25 0025 9:02
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @XjDistributedLock(waitTime = 0, leaseTime = 10L)
    public Boolean refreshAuthDb() {

        // 获取启动类注解上需要扫描的路径
        Map<String, Object> annotatedBeans = context.getBeansWithAnnotation(SpringBootApplication.class);
        Class<?> aClass = annotatedBeans.values().toArray()[0].getClass();
        SpringBootApplication annotation = aClass.getAnnotation(SpringBootApplication.class);
        String[] packages = annotation.scanBasePackages();

        // 获取所有需要被扫描的文件
        log.info("  @.@...下方扫包错误信息正常现象，无需处理 ");
        Set<Class<?>> classByPackageName = new HashSet<>();
        for (String aPackage : packages) {
            Set<Class<?>> classes = cn.hutool.core.util.ClassUtil.scanPackage(aPackage);
            classByPackageName.addAll(classes);
        }

        log.info("  @.@...正在更新接口资源,所有被权限管理的接口将被打印出来…… ^.^ ");
        // 扫描包，获得包下的所有类
        // ClassUtil.getClasses(PACKAGE_NAME);
        // 当前数据库已经存在的所有url权限列表--> key=url，value=对象，获取后移除Map中已取出, 最后剩下的全部删除
        List<Authority> list = this.list();
        Map<String, Authority> authorityMap = new HashMap<>();
        if (list != null && list.size() > 0) {
            authorityMap = list.stream().collect(Collectors.toMap(item -> AuthCacheKeyUtil.getAuthKey(item.getMethod(), item.getUrl()), item -> item));
        }
        // 所有需要修改list |添加list |删除的Ids
        List<Authority> updAuth = new ArrayList<>();
        List<Authority> addAuth = new ArrayList<>();
        List<String> delIds = new ArrayList<>();
        int classNum = 0;
        // 类上url
        List<String> classUrl = new ArrayList<>();
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
            String classDesc = classInfo.getSimpleName();
            String className = classDesc;
            if (api != null) {
                classDesc = api.tags()[0];
                className = api.value();
            }
            String url = requestMappingClass.value()[0];
            if (url.contains(BaseConstant.Uri.API_ADMIN)) {
                // 管理端 | 默认需登录
                uriType = Base.AuthorityType.V0.getValue();
                state = Base.AuthorityState.V1.getValue();
            } else if (url.contains(BaseConstant.Uri.API_CLIENT)) {
                // 用户端 | 默认需登录
                uriType = Base.AuthorityType.V1.getValue();
                state = Base.AuthorityState.V0.getValue();
            } else if (url.contains(BaseConstant.Uri.API_OPEN)) {
                // 通用 | 默认无需登录+无需授权
                uriType = Base.AuthorityType.V2.getValue();
                state = Base.AuthorityState.V0.getValue();
            } else if (url.contains(BaseConstant.Uri.API_OAUTH2)) {
                // Oauth2.0接口 | 默认需Oauth2.0授权
                uriType = Base.AuthorityType.V3.getValue();
                state = Base.AuthorityState.V3.getValue();
            } else {
                // 其他 api默认放行
                uriType = Base.AuthorityType.V2.getValue();
                state = Base.AuthorityState.V0.getValue();
            }
            String classLog = "  接口类：--------------@.@[" + classDesc + "-" + className + "]--";
            log.info(String.format("%-100s", classLog).replace(" ", "-"));
            if (authorityMap.containsKey(AuthCacheKeyUtil.getAuthKey("", url))) {
                // 存在修改
                Authority updAuthority = authorityMap.get(AuthCacheKeyUtil.getAuthKey("", url));
                updAuthority.setUrl(url);
                updAuthority.setPid(PID);
                updAuthority.setDesc(classDesc);
                updAuthority.setType(uriType);
                updAuthority.setState(state);
                // 添加/处理方法上的权限
                this.putMethods(classInfo, authorityMap, updAuthority, updAuth, addAuth);
                updAuth.add(updAuthority);
                // 移除Map中已取出的数据
                authorityMap.remove(AuthCacheKeyUtil.getAuthKey("", url));
            } else {
                // 不存在新添加
                Authority addAuthority = new Authority();
                addAuthority.setId(IdUtil.getSnowflakeNextIdStr());
                addAuthority.setPid(PID);
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
            classUrl.add(url);
            //被管理的类数量
            classNum++;
        }
        log.info("检查多个类 URI 地址是否重复命名, 多个类的 URI 跟路径不能相同");
        classUrl.stream().collect(Collectors.toMap(item -> item, item -> item));
        log.info("  本次刷新接口+类 总数量为:{} ,如接口 [备注信息] 或 [请求方式] 或 [终端] 发送改变,则已被刷新", updAuth.size());
        log.info("  本次添加接口+类 总量为:  {}", addAuth.size());
        // 修改
        if (updAuth.size() > 0) {
            this.updateBatchById(updAuth, 1024);
        }
        // 新增
        if (addAuth.size() > 0) {
            // 判断新增的接口中是否有重复的url，如果Url有重复直接将直接抛出异常
            log.info("检查接口 URI 地址是否存在重复命名,不能存在多个【请求方式+请求uri】完全相同的URL");
            addAuth.stream().collect(Collectors.toMap(item -> AuthCacheKeyUtil.getAuthKey(item.getMethod(), item.getUrl()), item -> item));
            // 添加权限
            this.saveBatch(addAuth, 1024);
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
    private void putMethods(Class<?> classInfo, Map<String, Authority> authorityMap, Authority authority, List<Authority> updAuth, List<Authority> addAuth) {
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
                requestMethod = RequestMethod.GET.name();
            }
            PostMapping postMapping = method.getAnnotation(PostMapping.class);
            if (url == null && postMapping != null) {
                url = authority.getUrl() + (postMapping.value().length > 0 ? postMapping.value()[0] : "");
                requestMethod = RequestMethod.POST.name();
            }
            PutMapping putMapping = method.getAnnotation(PutMapping.class);
            if (url == null && putMapping != null) {
                url = authority.getUrl() + (putMapping.value().length > 0 ? putMapping.value()[0] : "");
                requestMethod = RequestMethod.PUT.name();
            }
            DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
            if (url == null && deleteMapping != null) {
                url = authority.getUrl() + (deleteMapping.value().length > 0 ? deleteMapping.value()[0] : "");
                requestMethod = RequestMethod.DELETE.name();
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
            /// 存在修改，不存在新添加
            if (authorityMap.containsKey(AuthCacheKeyUtil.getAuthKey(requestMethod, url))) {
                Authority updAuthority = authorityMap.get(AuthCacheKeyUtil.getAuthKey(requestMethod, url));
                updAuthority.setPid(authority.getId());
                updAuthority.setDesc(desc);
                updAuthority.setUrl(url);
                updAuthority.setMethod(requestMethod);
                updAuthority.setType(authority.getType());
                updAuth.add(updAuthority);
                // 移除Map中已取出的数据
                authorityMap.remove(AuthCacheKeyUtil.getAuthKey(requestMethod, url));
            } else {
                Authority addAuthority = new Authority();
                addAuthority.setId(IdUtil.getSnowflakeNextIdStr());
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


    @Override
    public void refreshAuthCache() {
        Map<String, Authority> listAllToMap = XjCacheUtil.findAuthAllToMap();
        // 数据统计
        int authorityCount = 0;
        int authorityCountState2 = 0;
        int authorityCountState1 = 0;
        int authorityCountState0 = 0;
        for (Authority auth : listAllToMap.values()) {
            // 所有被管理的权限,管理端，需登录/授权的接口数量
            if (StringUtils.isNotBlank(auth.getPid()) && auth.getState().equals(Base.AuthorityState.V2.getValue())) {
                authorityCountState2++;
            }
            // 需登录接口数量
            if (StringUtils.isNotBlank(auth.getPid()) && auth.getState().equals(Base.AuthorityState.V1.getValue())) {
                authorityCountState1++;
            }
            // 放行接口数量
            if (StringUtils.isNotBlank(auth.getPid()) && auth.getState().equals(Base.AuthorityState.V0.getValue())) {
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
     * 查询所有
     */
    @Override
    @Cacheable(value = CacheKey.AUTH_MAP_ALL)
    public Map<String, Authority> findListAllToMap() {
        // 查询权限表中所有接口
        List<Authority> authorityList = this.list(new LambdaQueryWrapper<Authority>()
                .orderByDesc(Authority::getType)
                .orderByDesc(Authority::getMethod)
        );
        return authorityList.stream().collect(Collectors.toMap(p -> AuthCacheKeyUtil.getCacheKey(p.getMethod(), p.getUrl()), auth -> auth));
    }
}

