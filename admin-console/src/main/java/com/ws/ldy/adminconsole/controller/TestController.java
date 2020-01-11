package com.ws.ldy.adminconsole.controller;

import com.ws.ldy.adminconsole.entity.UserAdmin;
import com.ws.ldy.adminconsole.service.impl.UserAdminServiceImpl;
import com.ws.ldy.admincore.controller.BaseController;
import com.ws.ldy.admincore.controller.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController extends BaseController {

    @Autowired
    private UserAdminServiceImpl userAdminServiceImpl;

    @RequestMapping("/api/test")
    @ResponseBody
    public ResponseData test() {
        Map<Integer, Map<String, Object>> param = new HashMap<>(2);
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        //测试精准查询
//        param.put(1, new HashMap<String, Object>() {{
//            put("id", "1");
//            put("username", "王松");
//        }});
//        Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(dao.userDao, 1, 100, param, sort);
//        System.out.println(userPages.getContent().toString());


        //测试模糊查询
//        param.put(2, new HashMap<String, Object>() {{
//            put("address", "四川");
//        }});
//        Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(dao.userDao, 1, 100, param, sort);
//        System.out.println(userPages.getContent().toString());


        //测试区间查询 start：开始 , ent :结束
//        param.put(3, new HashMap<String, Object>() {{
//            put("time", new HashMap<String, Object>() {{
//                put("start", "2019-11-14 20:00:00");
//                put("ent", "2019-11-15 20:00:00");
//            }});
//        }});
//        Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(dao.userDao, 1, 100, param, sort);
//        System.out.println(userPages.getContent().toString());


        //测试大于大于传入值
//        param.put(4, new HashMap<String, Object>() {{
//            put("age", 23);
//        }});
//        Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(dao.userDao, 1, 100, param, sort);
//        System.out.println(userPages.getContent().toString());


        //测试小于等于传入值
//        param.put(5, new HashMap<String, Object>() {{
//            put("age", 22);
//        }});
//        Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(dao.userDao, 1, 100, param, sort);
//        System.out.println(userPages.getContent().toString());
//        return new Data(userPages.getContent(), userPages.getTotalPages()).getResData();


        //测试大于传入值
//        param.put(6, new HashMap<String, Object>() {{
//            put("time", "2019-11-15 00:00:00");
//        }});
//        Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(dao.userDao, 1, 100, param, sort);
//        System.out.println(userPages.getContent().toString());
//        return new Data(userPages.getContent(), userPages.getTotalPages()).getResData();


        //测试大于传入值
        param.put(7, new HashMap<String, Object>() {{
            put("time", "2019-11-15 00:00:00");
        }});
        Page<UserAdmin> userPages = userAdminServiceImpl.fingPage(1, 100, param, sort);
        System.out.println(userPages.getContent().toString());
        //返回参数
        return ResponseData.success(userPages.getContent(), userPages.getTotalPages());

    }
}
