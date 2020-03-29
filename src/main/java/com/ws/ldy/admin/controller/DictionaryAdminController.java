package com.ws.ldy.admin.controller;

import com.ws.ldy.admin.entity.DictionaryAdmin;
import com.ws.ldy.admin.service.impl.DictionaryAdminServiceImpl;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.common.query.QueryCriteria;
import com.ws.ldy.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * TODO  字典表
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@RestController
@RequestMapping("/dictionaryAdmin")
@Api(tags = {"Admin-Dictionary"}, description = "字典管理")
public class DictionaryAdminController extends BaseController {

    @Autowired
    private DictionaryAdminServiceImpl dictionaryAdminServiceImpl;

    /**
     * TODO  根据类型查询Type
     *
     * @param type 字典类型
     * @return Map<String, Object>
     */
    @GetMapping("/findByType")
    @ApiOperation("根据类型查询字典表")
    public Result findByType(String type) {
        List<DictionaryAdmin> dictionarys = dictionaryAdminServiceImpl.findByType(type);
        for (DictionaryAdmin dictionary : dictionarys) {
            dictionary.setName(dictionary.getValue());
        }
        return Result.success(dictionarys);
    }

    /**
     * TODO  分页查询
     *
     * @param page  页数
     * @param limit 记录数
     * @return Map<String, Object>
     */
    @GetMapping("/findAll")
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "字典类型", required = true, paramType = "query"),
    })
    public Result findAll(Integer page, Integer limit, String type) {
        Map<String, Map<String, Object>> param = new HashMap<>(2);
        QueryCriteria.equal(param, "type", type);
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Page<DictionaryAdmin> dictionaryAdmins = dictionaryAdminServiceImpl.fingPage(page, limit, param, sort);
        return Result.success(dictionaryAdmins.getContent(), dictionaryAdmins.getTotalPages());
    }


    /**
     * @param type            t=1 添加，=2修改
     * @param dictionaryAdmin 对象数据
     */
    @PostMapping("/save/{type}")
    @ApiOperation("添加/修改")
    public Result save(@PathVariable Integer type, DictionaryAdmin dictionaryAdmin) {
        if (type == 1) {
            dictionaryAdminServiceImpl.save(dictionaryAdmin);
        } else {
            dictionaryAdminServiceImpl.save(dictionaryAdmin);
        }
        return Result.success("success");
    }


    /**
     * TODO  批量删除/单删除
     *
     * @param ids 要删除的数据Id数组
     */
    @PostMapping("/delete")
    @ApiOperation("批量删除/单删除")
    public Result delete(Integer[] ids) {
        dictionaryAdminServiceImpl.deleteByIds(ids);
        return Result.success("success");
    }
}
