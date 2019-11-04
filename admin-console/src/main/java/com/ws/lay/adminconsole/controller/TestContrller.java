package com.ws.lay.adminconsole.controller;


import com.ws.lay.adminconsole.controller.base.BaseContoller;
import com.ws.lay.adminconsole.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestContrller extends BaseContoller<User,Integer> {

//    @Resource
//    private UserServiceImpl service;

    @RequestMapping("test")
    @ResponseBody
    public void test(){
        //添加
        User user = new User();
        user.setUser("张三");
        user.setPass("123");
        service.userServiceImpl.save(dao.userDao,user);
        //查询所有
        List<User> all = service.userServiceImpl.findAll(dao.userDao);
        System.out.println(all.toString());

        //id查询
        int id = 0;
        for (  User user1 :all){
            id = user1.getId();
            break;
        }
        User user2 = service.userServiceImpl.get(dao.userDao,id);
        System.out.println(user2.toString());
        //修改
        user2.setPass("456");
        User user3 = service.userServiceImpl.save(dao.userDao,user2);
        System.out.println(user3.toString());
        //删除
        Boolean resutl = service.userServiceImpl.delete(dao.userDao, id);
        System.out.println(resutl);
    }
}
