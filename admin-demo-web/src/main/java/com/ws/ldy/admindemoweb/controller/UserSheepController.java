package com.ws.ldy.admindemoweb.controller;

import com.ws.ldy.admindemoweb.controller.base.BaseAdminDemoWebController;
import com.ws.ldy.admindemoweb.entity.UserSheep;

import com.ws.ldy.admincore.controller.vo.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;



/**
 * TODO  代码生成器自动生成，请自定义描叙
 *
 * @author  wangsong
 * @WX-QQ  1720696548
 * @date  Fri Nov 22 21:18:10 CST 2019
 */
@Controller
@RequestMapping("/userSheep")
public class UserSheepController extends BaseAdminDemoWebController {

    /**
     * TODO  分页查询
     *
     * @param page   页数
     * @param limit  记录数
     * @param id-xxx 查询条件参数，id表示id查询
     * @return Map<String, Object>
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public Map<String, Object> findAll(Integer page, Integer limit, Integer id) {
        Map<String, Object> param = new HashMap<>(2);
        param.put("id", id);
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Page<UserSheep> userSheeps = service.userSheepServiceImpl.page(dao.userSheepDao, page, limit, param, sort);
        return new Data(userSheeps.getContent(), userSheeps.getTotalPages()).getResData();
    }


    /**
     * TODO  添加/修改
     *
     * @param type t=1 添加，=2修改
     * @param userSheep 对象数据
     * @return java.lang.String
     */
    @RequestMapping("/save/{type}")
    @ResponseBody
    public String save(@PathVariable Integer type, UserSheep userSheep) {
        if (type == 1) {
            service.userSheepServiceImpl.save(dao.userSheepDao, userSheep);
        } else {
            service.userSheepServiceImpl.save(dao.userSheepDao, userSheep);
        }
        return "success";
    }


    /**
     * TODO  批量删除/单删除
     *
     * @param ids 要删除的数据Id数组
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(Integer[] ids) {
        service.userSheepServiceImpl.deleteByIds(dao.userSheepDao, ids);
        return "success";
    }
}
