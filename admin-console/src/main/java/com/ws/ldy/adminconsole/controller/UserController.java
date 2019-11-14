package com.ws.ldy.adminconsole.controller;

import com.ws.ldy.adminconsole.controller.base.BaseContoller;
import com.ws.ldy.adminconsole.entity.User;
import com.ws.ldy.admincore.controller.vo.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * TODO  系统用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@Controller
@RequestMapping("/userAdmin")
public class UserController extends BaseContoller {

    /***
     * TODO  分页查询
     * @date 2019/11/14 15:20
     * @return Map<String, Object>
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public Map<String, Object> findAll(int page, int limit,Integer id) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("id",id);
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        //查询所有
        Page<User> users = service.userServiceImpl.page(dao.userDao, page, limit, param, sort);
        return new Data(users.getContent(), users.getTotalPages()).getResData();
    }


    /***
     * TODO  添加/修改
     * @param type t=1 添加，=2修改
     * @param user 对象数据
     * @date 2019/11/14 17:34
     * @return java.lang.String
     */
    @RequestMapping("/save/{type}")
    @ResponseBody
    public String save(@PathVariable Integer type, User user) {
        if (type == 1) {
            user.setTime(new Date());
            service.userServiceImpl.save(dao.userDao, user);
        } else {
            service.userServiceImpl.save(dao.userDao, user);
        }
        return "success";
    }


    /**
     * TODO  批量删除/单删除
     *
     * @param ids 要删除的数据Id数组
     * @author 王松
     * @WX-QQ 1720696548
     * @date 2019/11/14 18:17
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(Integer[] ids) {
        service.userServiceImpl.deleteByIds(dao.userDao, ids);
        return "success";
    }
}
