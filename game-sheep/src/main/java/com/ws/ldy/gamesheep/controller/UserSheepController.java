package com.ws.ldy.gamesheep.controller;

import com.ws.ldy.admincore.controller.vo.Data;
import com.ws.ldy.gamesheep.controller.base.BaseSheepController;
import com.ws.ldy.gamesheep.entity.UserSheep;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO  养羊用户
 *
 * @author 王松
 * @WX-QQ 1720696548
 * @date 2019/11/13 13:38
 */
@Controller
@RequestMapping("/userSheep")
public class UserSheepController extends BaseSheepController {

    /***
     * TODO  分页查询
     * @date 2019/11/14 15:20
     * @return Map<String, Object>
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public Map<String, Object> findAll() {
        List<UserSheep> users = service.userService.findAll(dao.userDao);
        return new Data(users).getResData();
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
    public String save(@PathVariable Integer type, UserSheep user) {
        if (type == 1) {
            user.setTime(new Date());
            service.userService.save(dao.userDao, user);
        } else {
            service.userService.save(dao.userDao, user);
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
        service.userService.deleteByIds(dao.userDao, ids);
        return "success";
    }
}
