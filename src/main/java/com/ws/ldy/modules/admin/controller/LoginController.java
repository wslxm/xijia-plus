/* 登录由 jwt+ springSecurity 来处理，config/auth包下 */

//package com.ws.ldy.config.springSecurity.controller;
//
//
//import com.ws.ldy.admin.model.entity.UserAdmin;
//import com.ws.ldy.admin.model.vo.LoginVo;
//import com.ws.ldy.admin.service.UserAdminService;
//import com.ws.ldy.base.controller.BaseController;
//import com.ws.ldy.base.enums.BaseConstant;
//import com.ws.ldy.common.result.Result;
//import com.ws.ldy.common.result.ResultEnum;
//import com.ws.ldy.common.user.AdminUserUtils;
//import com.ws.ldy.common.utils.UUIDUtils;
//import com.ws.ldy.config.error.ErrorException;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// *   登录
// *
// * @author ws
// * @mail 1720696548@qq.com
// * @date 2020/3/30 0030 19:53
// * @return
// */
//@RestController
//@RequestMapping("/loginAdmin")
//@Api(value = "LoginController", tags = "登录", description = BaseConstant.InterfaceType.PC_ADMIN)
//public class LoginController extends BaseController<UserAdminService> {
//
//    /**
//     *  登录
//     *
//     * @param account
//     * @param password
//     * @return com.ws.ldy.common.result.Result<com.ws.ldy.admin.model.vo.LoginVo>
//     * @author ws
//     * @mail 1720696548@qq.com
//     * @date 2020/3/30 0030 19:50
//     */
//    @ApiOperation("登录")
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "account", value = "账号", required = true, paramType = "query"),
//            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query")
//    })
//    public Result<LoginVo> login(@RequestParam String account, @RequestParam String password) {
//        // 1、登录
//        UserAdmin user = baseService.findByAccount(account);
//        if (user == null) {
//            throw new ErrorException(ResultEnum.SYS_ERROR_CODE_500.getCode(), "没有用户信息");
//        }
//        if (!user.getPassword().equals(password)) {
//            throw new ErrorException(ResultEnum.SYS_ERROR_CODE_500.getCode(), "密码错误");
//        }
//        // 2、登录成功保存用户信息
//        String token = UUIDUtils.creatUUID();
//        session.setAttribute(AdminUserUtils.ADMIN + token, user);
//        return Result.success(new LoginVo(token, user.getUsername(), user.getHead()));
//
//        // 3、保存权限信息
//
//
//        // 1.根据用户名称查询到user用户
//
////            if (userDetails == null) {
////                return null;
////            }
////            // 2.查询该用户对应的权限
////            List<PermissionEntity> permissionList = userMapper.findPermissionByUsername(userName);
////            ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
////            permissionList.forEach((a) -> {
////                grantedAuthorities.add(new SimpleGrantedAuthority(a.getPermTag()));
////            });
////            log.info(">>permissionList:{}<<",permissionList);
////            // 设置权限
////            userDetails.setAuthorities(grantedAuthorities);
////            return userDetails;
//        //    }
//        // }
//    }
//
//
//    /**
//     *   退出登录
//     *
//     * @return com.ws.ldy.common.result.Result
//     * @author ws
//     * @mail 1720696548@qq.com
//     * @date 2020/3/30 0030 19:51
//     */
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    @ApiOperation("退出登录")
//    public Result<Void> logout() {
//        session.removeAttribute(AdminUserUtils.getToken());
//        return Result.success();
//    }
//
//
////    //添加权限 ，参数是需要新增的权限名
////    @RequestMapping("/addAuth")
////    public Map<String,Object> addAuth(String authName){
////        Map<String,Object> map = new HashMap<>();
////        if (StringUtils.isBlank(authName)){
////            map.put("data","权限名称不可空，参数名authName");
////            return map;
////        }
////        try {
////            //========================================================
////            //这一段仅仅是更新当前登录用户的权限列表 ，登出后将释放 ，当再次从数据库获取权限数据时将还原 ，因此如果需要持久性的更改权限，
////            // 还需要修改数据库信息 ，懒得写 ，这里就不做修改数据库演示了
////            //
////            // 得到当前的认证信息
////            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////            //  生成当前的所有授权
////            List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
////            // 添加 ROLE_VIP 授权
////            updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + authName));
////            // 生成新的认证信息
////            Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
////            // 重置认证信息
////            SecurityContextHolder.getContext().setAuthentication(newAuth);
////            //========================================================
////            map.put("data","权限 "+authName+" 添加成功");
////        }catch (Exception e){
////            e.printStackTrace();
////            map.put("data","权限添加失败");
////        }
////        return map;
////    }
//
//}
