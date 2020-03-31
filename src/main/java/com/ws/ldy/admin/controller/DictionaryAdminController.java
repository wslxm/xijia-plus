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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * TODO  字典表
 *
 * @author wangsong
 * @WX-QQ 1720696548
 * @date Sun Nov 24 11:23:12 CST 2019
 */
@RestController
@RequestMapping("/dictionaryAdmin")
@Api(value = "DictionaryAdminController", tags = "代字典管理")
public class DictionaryAdminController extends BaseController {

    @Autowired
    private DictionaryAdminServiceImpl dictionaryAdminServiceImpl;


    @ApiOperation("根据类型查询字典表")
    @RequestMapping(value = "/findByType", method = RequestMethod.GET)
    public Result findByType(String type) {
        List<DictionaryAdmin> dictionarys = dictionaryAdminServiceImpl.findByType(type);
        for (DictionaryAdmin dictionary : dictionarys) {
            dictionary.setName(dictionary.getValue());
        }
        return success(dictionarys);
    }


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "字典类型", required = true, paramType = "query"),
    })
    public Result findAll(String type) {
        Page<DictionaryAdmin> dictionaryAdmins = dictionaryAdminServiceImpl.page(
                this.getPage(),
                new QueryCriteria().equal("type", type).build(),
                new QueryCriteria().orderByAsc("id").getSort()
        );
        return success(dictionaryAdmins.getContent(), dictionaryAdmins.getTotalPages());
    }


    @RequestMapping(value = "/save/{type}", method = RequestMethod.POST)
    @ApiOperation("添加/修改 t=1 添加，=2修改")
    public Result save(@PathVariable Integer type, DictionaryAdmin dictionaryAdmin) {
        if (type == 1) {
            dictionaryAdminServiceImpl.save(dictionaryAdmin);
        } else {
            dictionaryAdminServiceImpl.save(dictionaryAdmin);
        }
        return success("success");
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("批量删除/单删除--要删除的数据Id数组")
    public Result delete(Integer[] ids) {
        dictionaryAdminServiceImpl.deleteByIds(ids);
        return success("success");
    }
}
