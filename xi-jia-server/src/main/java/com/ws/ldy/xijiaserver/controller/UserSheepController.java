package com.ws.ldy.xijiaserver.controller;

import com.ws.ldy.admincore.common.vo.ResponseData;
import com.ws.ldy.admincore.controller.BaseController;
import com.ws.ldy.xijiaserver.entity.UserSheep;
import com.ws.ldy.xijiaserver.service.impl.UserSheepServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.util.HashMap;
import java.util.Map;


/**
 * TODO  代码生成器自动生成，请自定义描叙
 *
 * @author wangsong
 * @email 1720696548
 * @date Sun Feb 23 10:48:11 CST 2020
 */
@RestController
@RequestMapping("/userSheep")
@Api(tags = {"xi-jia-server-UserSheep"},description = "")
public class UserSheepController extends BaseController {

    @Autowired
    private UserSheepServiceImpl service;

    /**
     * TODO  分页查询
     *
     * @param page   页数
     * @param limit  记录数
     * @return Map<String, Object>
     */
    @GetMapping("/findAll")
    @ApiOperation("分页查询")
    @ApiImplicitParams({
           @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
           @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
    })
    public ResponseData findAll(Integer page, Integer limit) {
        //查询条件
        Map<String, Object> param = new HashMap<>(2);
        param.put("id", getString("id", ""));
        //排序
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Page<UserSheep> userSheeps = service.page(page, limit, param, sort);
        return ResponseData.success(userSheeps.getContent(), userSheeps.getTotalPages());
    }


    /**
     * TODO  添加/修改
     *
     * @param type=1 添加，type=2修改
     * @param userSheep 对象数据
     * @return java.lang.String
     */
    @PostMapping("/save/{type}")
    @ApiOperation("添加/修改")
    @ApiImplicitParams({
           @ApiImplicitParam(name = "type", value = "类型1:添加，2:修改", required = true, paramType = "query"),
    })
    public ResponseData save(@PathVariable Integer type, UserSheep userSheep) {
        if (type == 1) {
            service.save(userSheep);
        } else {
            service.save(userSheep);
        }
        return ResponseData.success("success");
    }


    /**
     * TODO  批量删除/单删除
     *
     * @param ids 要删除的数据Id数组
     */
    @ApiOperation("删除/批量删除")
    @ApiImplicitParams({
          @ApiImplicitParam(name = "ids", value = "要删除id集", required = true, paramType = "query"),
    })
    @PostMapping("/delete")
    public ResponseData delete(Integer[] ids) {
        service.deleteByIds(ids);
        return ResponseData.success("success");
    }
}
