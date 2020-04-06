package com.ws.ldy.admin.controller;

import com.ws.ldy.admin.entity.AuthorityAdmin;
import com.ws.ldy.admin.entity.DictionaryAdmin;
import com.ws.ldy.admin.service.impl.AuthorityAdminServiceImpl;
import com.ws.ldy.admin.service.impl.DictionaryAdminServiceImpl;
import com.ws.ldy.admin.service.impl.UserAdminServiceImpl;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.base.query.DeleteCriteria;
import com.ws.ldy.base.query.UpdateCriteria;
import com.ws.ldy.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO  代码生成
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/2/9 0009 20:33
 */
@SuppressWarnings("all")
@RestController
@RequestMapping("/test")
@Api(value = "TestController", tags = "测试接口")
public class TestController extends BaseController {

    @Autowired
    private UserAdminServiceImpl userAdminService;
    @Autowired
    private DictionaryAdminServiceImpl dictionaryAdminService;
    @Autowired
    private AuthorityAdminServiceImpl authorityAdminService;

    @ApiOperation("修改方法代码测试")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Integer> update(String address, String age) {
        int update = dictionaryAdminService.update(new UpdateCriteria<DictionaryAdmin>()
                .set("value", "root")
                .set("key", "root")
                .set("desc", "字典表系统自身1")
                .eq("id", 2)
        );
        return success(update);
    }

    @ApiOperation("In修改方法代码测试")
    @RequestMapping(value = "/updateIn", method = RequestMethod.PUT)
    public Result<Integer> updateIn(String address, String age) {
        List<Integer> ids = new ArrayList<Integer>() {{
            add(11);
            add(12);
        }};
        int update = dictionaryAdminService.update(new UpdateCriteria<DictionaryAdmin>()
                .set("value", "999")
                .set("key", "999")
                .set("desc", "999")
                .in("id", ids)
        );
        return success(update);
    }


    @ApiOperation("删除方法代码测试")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result<Integer> delete(Integer id, Integer pid) {
        int result = authorityAdminService.delete(new DeleteCriteria<AuthorityAdmin>()
                .eq("id", id)
                .eq("pid", pid)
        );
        return success(result);
    }

    @ApiOperation("逻辑删除方法代码测试")
    @RequestMapping(value = "/deleted", method = RequestMethod.DELETE)
    public Result<Integer> deleted(Integer id, Integer pid) {
        int result = authorityAdminService.deleted(new DeleteCriteria<AuthorityAdmin>()
                .eq("id", id)
                .eq("pid", pid)
        );
        return success(result);
    }


    @ApiOperation("In删除方法代码测试")
    @RequestMapping(value = "/deleteIn", method = RequestMethod.DELETE)
    public Result<Integer> deleteIn() {
        List<Integer> ids = new ArrayList<Integer>() {{
            add(11);
            add(12);
        }};
        int result = dictionaryAdminService.delete(new DeleteCriteria<AuthorityAdmin>()
                .in("id", ids)
        );
        return success(result);
    }
}
//    int update = userAdminService.update(new UpdateCriteria()
//            .set("address", address)
//            .set("age", age)
//            .eq("account", 1720696548)
//    );


///**
//  * TODO  测试service 通用查询方法 fingPage 使用
//  * @author 王松
//  * @mail  1720696548@qq.com
//  * @date  2020/1/13 0013 22:28
// *
// * ----- key = equal                精准搜索 字符串/数字/时间
// * ----- key = like                 模糊搜索 字符串/数字/时间
// * ----- key = between              两者之间， key ：字段名，start：开始 , ent :结束  (数字/时间)
// * ----- key = greaterThanOrEqualTo 大于或等于传入值（字符串/数字/时间）
// * ----- key = lessThanOrEqualTo    小于或等于传入值（字符串/数字/时间）
// * ----- key = greaterThan          大于传入值（字符串/数字/时间）
// * ----- key = lessThan             小于传入值（字符串/数字/时间）
//  */
//@Controller
//public class TestController extends BaseController {
//
//    @Autowired
//    private UserAdminServiceImpl userAdminServiceImpl;
//
//
//    /**
//     * TODO   测试通用查询： 使用 QueryCriteria 自封装工具类
//     * @author 王松
//     * @mail  1720696548@qq.com
//     * @date  2020/1/13 0013 22:25
//     * @return com.ws.ldy.common.result.ResponseData
//     */
//    @RequestMapping("/api/test")
//    @ResponseBody
//    public ResponseData test() {
//        Map<String, Map<String, Object>> params = new HashMap<>(2);
//        Sort sort = new Sort(Sort.Direction.ASC, "id");
//
//        // 精准查询
//        // QueryCriteria.equal(params,"id","1");
//        // QueryCriteria.equal(params,"username","王松");
//
//        // 模糊查询
//        //QueryCriteria.like(params,"address","四川");
//
//     // 区间查询
//       QueryCriteria.between(params, "time", "2019-11-14 20:00:00", "2019-11-15 20:00:00");
//
//       // 大于或等于传入值（字符串/数字/时间）
//       QueryCriteria.greaterThanOrEqualTo(params, "age", 23);
//
//       //小于或等于传入值（字符串/数字/时间）
//       QueryCriteria.lessThanOrEqualTo(params, "age", 22);
//
//       //大于传入值（字符串/数字/时间）
//       QueryCriteria.greaterThan(params, "time", "2019-11-15 00:00:00");
//
//        // 小于传入值（字符串/数字/时间）
//        QueryCriteria.lessThan(params, "time", "2019-11-15 00:00:00");
//
//        Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(1, 100, params, sort);
//        System.out.println(userPages.getContent().toString());
//        return ResponseData.success(userPages.getContent(), userPages.getTotalPages());
//    }
//
//
//
//
//    /**
//     * TODO   测试通用查询： 原始方式
//     * @author 王松
// * @mail  1720696548@qq.com
// * @date  2020/1/13 0013 22:25
// * @return com.ws.ldy.common.result.ResponseData
// */
//  @RequestMapping("/api/test")
//  @ResponseBody
//  public ResponseData test() {
//      Map<Integer, Map<String, Object>> param = new HashMap<>(2);
//      Sort sort = new Sort(Sort.Direction.ASC, "id");
//      //测试精准查询
//        param.put(1, new HashMap<String, Object>() {{
//            put("id", "1");
//            put("username", "王松");
//        }});
//        Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(dao.userDao, 1, 100, param, sort);
//        System.out.println(userPages.getContent().toString());
//
//
//      //测试模糊查询
//        param.put(2, new HashMap<String, Object>() {{
//            put("address", "四川");
//        }});
//        Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(dao.userDao, 1, 100, param, sort);
//        System.out.println(userPages.getContent().toString());
//
//
//      //测试区间查询 start：开始 , ent :结束
//        param.put(3, new HashMap<String, Object>() {{
//            put("time", new HashMap<String, Object>() {{
//                put("start", "2019-11-14 20:00:00");
//                put("ent", "2019-11-15 20:00:00");
//            }});
//        }});
//        Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(dao.userDao, 1, 100, param, sort);
//        System.out.println(userPages.getContent().toString());
//
//
//      //测试大于大于传入值
//        param.put(4, new HashMap<String, Object>() {{
//            put("age", 23);
//        }});
//        Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(dao.userDao, 1, 100, param, sort);
//        System.out.println(userPages.getContent().toString());
//
//    //测试小于等于传入值
//        param.put(5, new HashMap<String, Object>() {{
//            put("age", 22);
//        }});
//        Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(dao.userDao, 1, 100, param, sort);
//        System.out.println(userPages.getContent().toString());
//        return new Data(userPages.getContent(), userPages.getTotalPages()).getResData();
//      //测试大于传入值
//        param.put(6, new HashMap<String, Object>() {{
//            put("time", "2019-11-15 00:00:00");
//        }});
//        Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(dao.userDao, 1, 100, param, sort);
//        System.out.println(userPages.getContent().toString());
//        return new Data(userPages.getContent(), userPages.getTotalPages()).getResData();
//     //测试大于传入值
//      param.put(7, new HashMap<String, Object>() {{
//          put("time", "2019-11-15 00:00:00");
//      }});
//      Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(1, 100, param, sort);
//      System.out.println(userPages.getContent().toString());
//      //返回参数
//      return ResponseData.success(userPages.getContent(), userPages.getTotalPages());
//  }
//}
