package com.ws.ldy.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.XiJiaServer;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.common.utils.ClassUtil;
import com.ws.ldy.common.utils.EnumUtil;
import com.ws.ldy.common.utils.IdUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.enums.Enums;
import com.ws.ldy.modules.admin.mapper.AdminAuthorityMapper;
import com.ws.ldy.modules.admin.mapper.AdminRoleAuthMapper;
import com.ws.ldy.modules.admin.model.entity.AdminAuthority;
import com.ws.ldy.modules.admin.model.entity.AdminRoleAuth;
import com.ws.ldy.modules.admin.model.vo.AdminAuthorityVO;
import com.ws.ldy.modules.admin.service.AdminAuthorityService;
import com.ws.ldy.others.base.service.impl.BaseIServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
public class AdminAuthorityServiceImpl extends BaseIServiceImpl<AdminAuthorityMapper, AdminAuthority> implements AdminAuthorityService {

    /**
     * url权限注解扫包范围( 直接获取启动类的包路径)
     */
    private final static String PACKAGE_NAME = XiJiaServer.class.getPackage().getName();


    @Autowired
    private AdminRoleAuthMapper adminRoleAuthMapper;


    /**
     *  查询所有权限数据，根据不同的端的枚举code 拼接最顶级的目录，顶级目录ID = -1
     *
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    @Override
    public List<AdminAuthorityVO> findList() {
        List<AdminAuthority> list = this.list(new LambdaQueryWrapper<AdminAuthority>()
                .orderByDesc(AdminAuthority::getType)
                .orderByDesc(AdminAuthority::getMethod)
        );
        List<AdminAuthorityVO> adminAuthorityVOList = BeanDtoVoUtil.listVo(list, AdminAuthorityVO.class);
        // 拼下级数据, pid等于枚举字典的code值
        adminAuthorityVOList.forEach(i -> {
            if (StringUtils.isBlank(i.getPid())) {
                Enums.Admin.AuthorityType byCode = EnumUtil.getByCode(i.getType(), Enums.Admin.AuthorityType.class);
                i.setPid(byCode.getValue().toString());
            }
        });
        // 添加终端数据，有多少条枚举字段就拼接几条数据
        for (Enums.Admin.AuthorityType authorityType : Enums.Admin.AuthorityType.values()) {
            AdminAuthorityVO adminAuthorityVO = new AdminAuthorityVO();
            adminAuthorityVO.setId(authorityType.getValue().toString());
            adminAuthorityVO.setPid("-1"); //顶级目录Id
            adminAuthorityVO.setType(-1); //游历在法律之外
            adminAuthorityVO.setDesc(authorityType.getDesc());
            adminAuthorityVO.setState(-1);
            adminAuthorityVO.setDisable(-1);
            adminAuthorityVOList.add(adminAuthorityVO);
        }
        return adminAuthorityVOList;
    }


    /**
     *   添加接口--扫描包下所有类
     *
     * @return void
     * @date 2019/11/25 0025 9:02
     */
    @Override
    @Transactional
    public void refreshAuthDB() {
        log.info("  @.@...正在更新接口资源,所有被权限管理的接口将被打印出来…… ^.^ ");
        // 扫描包，获得包下的所有类
        List<Class<?>> classByPackageName = ClassUtil.getClasses(PACKAGE_NAME);
        // 当前当前数据库已经存在的所有url权限列表--> key=url，value=对象，获取后移除Map中已取出，最后剩下的全部删除
        Map<String, AdminAuthority> authorityMap = this.list().stream().collect(Collectors.toMap(AdminAuthority::getUrl, item -> item));
        //
        List<AdminAuthority> updAuth = new ArrayList<>();  // 所有需要修改
        List<AdminAuthority> addAuth = new ArrayList<>();  // 所有需要添加
        List<String> delIds = new ArrayList<>();           // 所有需要删除的Id
        int classNum = 0;
        // 遍历所有类
        for (Class<?> classInfo : classByPackageName) {
            // 类上存在 @Api 注解 + @RequestMapping 的类进行下一步操作
            Api apiClass = classInfo.getDeclaredAnnotation(Api.class);
            RequestMapping requestMappingClass = classInfo.getDeclaredAnnotation(RequestMapping.class);
            if (apiClass == null || requestMappingClass == null) {
                continue;
            }
            // 判断当前类是否需要保存到接口权限内（目前：PC_ADMIN=平台 / PC_USER  用户端）
            Integer uriType = null;
            Integer state = null;
            if (apiClass.consumes().equals(BaseConstant.InterfaceType.PC_ADMIN)) {
                // 管理端
                uriType = Enums.Admin.AuthorityType.AUTHORITY_TYPE_0.getValue();
                // 默认需登录+授权
                state = Enums.Admin.AuthorityState.AUTHORITY_STATE_2.getValue();
            } else if (apiClass.consumes().equals(BaseConstant.InterfaceType.PC_USER)) {
                //  用户端
                uriType = Enums.Admin.AuthorityType.AUTHORITY_TYPE_1.getValue();
                //  默认需登录
                state = Enums.Admin.AuthorityState.AUTHORITY_STATE_1.getValue();
            }
            //
            if (uriType != null) {
                String url = requestMappingClass.value()[0];
                //System.out.println("当前类信息-->" + apiClass.value() + "-->" + apiClass.tags()[0] + " --> " + url);
                String classLog = "  接口类：--------------@.@[" + apiClass.tags()[0] + "-" + apiClass.value() + "]--";
                log.info(String.format("%-100s", classLog).replace(" ", "-"));
                if (authorityMap.containsKey(url)) {
                    // 存在修改
                    AdminAuthority updAuthority = authorityMap.get(url);
                    updAuthority.setUrl(url);                               // 接口URL
                    updAuthority.setDesc(apiClass.tags()[0]);               // 接口描叙
                    updAuthority.setType(uriType);                          // 终端
                    // 添加方法上的权限
                    this.putMethods(classInfo, authorityMap, updAuthority, updAuth, addAuth);
                    //
                    updAuth.add(updAuthority);
                    // 移除Map中已取出的数据
                    authorityMap.remove(url);
                } else {
                    // 不存在新添加
                    AdminAuthority addAuthority = new AdminAuthority();
                    addAuthority.setId(IdUtil.snowflakeId());
                    addAuthority.setPid("");                              // 父类Pid
                    addAuthority.setMethod("");                           // 请求方式
                    addAuthority.setUrl(url);                             // 接口URL
                    addAuthority.setDesc(apiClass.tags()[0]);             // 接口描叙
                    addAuthority.setType(uriType);                        // 终端
                    addAuthority.setState(state);                         // 授权(类是在游历在法律之外的)
                    addAuthority.setDisable(Enums.Base.Disable.DISABLE_0.getValue());  // 默认启用(类是在游历在法律之外的)
                    // 添加方法上的权限
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
        // 更新数据库
        if (updAuth.size() > 0) {
            this.updateBatchById(updAuth, 1024);
        }
        if (addAuth.size() > 0) {
            this.saveBatch(addAuth, 1024);

        }
        if (authorityMap.size() > 0) {
            // 删除多余数据
            authorityMap.forEach((k, v) -> delIds.add(v.getId()));
            this.removeByIds(delIds);
        }
        log.info("  本次删除接口+类 总数量为: {}", delIds.size());
        log.info("  当前被管理的 总类数量为:  {}", classNum);
        log.info("  当前被管理的 总接口数量为: {}", ((updAuth.size() + addAuth.size()) - classNum));
        log.info("  @.@...");
    }


    /**
     *     添加指定类的所有接口权限到 athorityList
     *
     * @param classInfo    当前类
     * @param authorityMap 当前数据库存在权限
     * @param authority    类的权限数据
     * @return void
     * @date 2019/11/25 0025 9:02
     */
    private void putMethods(Class<?> classInfo, Map<String, AdminAuthority> authorityMap, AdminAuthority authority, List<AdminAuthority> updAuth, List<AdminAuthority> addAuth) {
        // 获取类的所有方法
        Method[] methods = classInfo.getDeclaredMethods();
        //循环添加方法级权限
        for (Method method : methods) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            if (requestMapping == null || apiOperation == null) {
                // log.info("  接口资源：[{}]  -->  [{}]  -->  [{}] ", String.format("%-6s", requestMethod), String.format("%-40s", url), "NO");
                // log.info(method.getDeclaringClass().getName() + "." + method.getName() + "方法没有@ApiOperation 或 @RequestMapping注解");
                continue;
            }
            String url = authority.getUrl() + requestMapping.value()[0]; // url
            String requestMethod = requestMapping.method()[0].name();    // 请求方式
            String desc = apiOperation.value();
            // 日志输出, 使用占位方式让日志对齐
            log.info("  接口资源：[{}]  -->  [{}]  -->  [{}] ", String.format("%-6s", requestMethod), String.format("%-40s", url), desc);
            // 存在修改，不存在新添加
            if (authorityMap.containsKey(url)) {
                // 获取已经有权限（根据权限名）
                AdminAuthority updAuthority = authorityMap.get(url);
                updAuthority.setPid(authority.getId());        // 类权限id（父级id）
                updAuthority.setDesc(desc);                    // 权限描叙
                updAuthority.setUrl(url);                      // 接口url
                updAuthority.setMethod(requestMethod);         // 请求方式
                updAuthority.setType(authority.getType());     // 终端
                updAuth.add(updAuthority);
                // 移除Map中已取出的数据
                authorityMap.remove(url);
            } else {
                AdminAuthority addAuthority = new AdminAuthority();
                addAuthority.setId(IdUtil.snowflakeId());
                addAuthority.setPid(authority.getId());           // 类权限id（父级id）
                addAuthority.setDesc(desc);                       // 权限描叙
                addAuthority.setUrl(url);                         // 接口url
                addAuthority.setMethod(requestMethod);            // 请求方式
                addAuthority.setType(authority.getType());        // 终端
                addAuthority.setState(authority.getState());      // 默认授权方式,随父节点
                addAuthority.setDisable(authority.getDisable());  // 默认启用
                addAuth.add(addAuthority);
            }
        }
        // 不论添加编辑,类的数据授权+禁用状态永远= -1
        authority.setState(-1);      // 授权(类是在游历在法律之外的)
        authority.setDisable(-1);    // 默认启用(类是在游历在法律之外的)
    }


    /**
     * 获取用户的url 权限列表，给指定角色的有的权限数据赋予选中状态
     *
     * @param roleId
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    @Override
    public List<AdminAuthorityVO> findByRoleIdAuthorityChecked(String roleId) {
        // 获取当前角色拥有的url权限列表
        List<AdminRoleAuth> roleIds = adminRoleAuthMapper.selectList(new LambdaQueryWrapper<AdminRoleAuth>().eq(AdminRoleAuth::getRoleId, roleId));
        List<String> roleAuthIds = roleIds != null ? roleIds.stream().map(AdminRoleAuth::getAuthId).collect(Collectors.toList()) : new ArrayList<>();
        // 获取所有管理端的url,请求方式排序( PC_admin)
        List<AdminAuthority> authorityList = this.list(new LambdaQueryWrapper<AdminAuthority>()
                .orderByAsc(AdminAuthority::getMethod)
                .eq(AdminAuthority::getType, Enums.Admin.AuthorityType.AUTHORITY_TYPE_0.getValue())
        );
        // 返回数据处理
        if (authorityList == null || authorityList.size() <= 0) {
            return null;
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
     * 获取用户的url权限列表，只返回未禁用的 需要登录+授权的url
     *
     * @param  userId 用户id
     * @return void
     * @date 2019/11/25 0025 11:55
     */
    @Override
    public List<String> findByUserIdaAndDisableFetchAuthority(String userId) {
        List<AdminAuthority> auth = baseMapper.findByUserIdaAndDisableFetchAuthority(
                userId, Enums.Base.Disable.DISABLE_0.getValue(), Enums.Admin.AuthorityState.AUTHORITY_STATE_2.getValue()
        );
        if (auth == null) {
            return null;
        } else {
            return auth.stream().map(AdminAuthority::getUrl).collect(Collectors.toList());
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
        List<AdminAuthority> authorityList = this.list(null);
        // 缓存所有接口数据到 jvm
        BaseConstant.Cache.AUTH_MAP = authorityList.stream().collect(Collectors.toMap(AdminAuthority::getUrl, auth -> auth));
        // 数据统计
        int authorityCount = 0;
        int authorityCountState2 = 0;
        int authorityCountState1 = 0;
        int authorityCountState0 = 0;
        for (AdminAuthority auth : authorityList) {
            // 所有被管理的权限,管理端，需登录/授权的接口数量
            if (StringUtils.isNotBlank(auth.getPid()) && auth.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_2.getValue())) {
                authorityCountState2++;
            }
            // 需登录接口数量
            if (StringUtils.isNotBlank(auth.getPid()) && auth.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_1.getValue())) {
                authorityCountState1++;
            }
            // 放行接口数量
            if (StringUtils.isNotBlank(auth.getPid()) && auth.getState().equals(Enums.Admin.AuthorityState.AUTHORITY_STATE_0.getValue())) {
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
}

