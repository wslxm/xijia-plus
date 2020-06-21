package com.ws.ldy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.admin.model.dto.DictionaryAdminDto;
import com.ws.ldy.admin.model.entity.DictionaryAdmin;
import com.ws.ldy.admin.model.vo.DictionaryAdminVo;
import com.ws.ldy.admin.service.impl.DictionaryAdminServiceImpl;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.base.enums.BaseConstant;
import com.ws.ldy.common.result.Result;
import com.ws.ldy.common.utils.BeanDtoVoUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "DictionaryAdminController", tags = "字典管理", description = BaseConstant.InterfaceType.PC_ADMIN)
public class DictionaryAdminController extends BaseController<DictionaryAdminServiceImpl> {



    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation("列表查询")
    public Result<List<DictionaryAdminVo>> findList() {
        List<DictionaryAdmin> list = baseService.list(new LambdaQueryWrapper<DictionaryAdmin>().orderByAsc(DictionaryAdmin::getSort));
        return Result.successFind(BeanDtoVoUtils.listVo(list, DictionaryAdminVo.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("添加")
    public Result<Void> save(@RequestBody DictionaryAdminDto dictionaryAdminDto) {
        baseService.save(dictionaryAdminDto.convert(DictionaryAdmin.class));
        return Result.successInsert();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation("编辑")
    public Result<Void> update(@RequestBody DictionaryAdminDto dictionaryAdminDto) {
        baseService.updateById(dictionaryAdminDto.convert(DictionaryAdmin.class));
        return Result.successUpdate();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("ID删除")
    public Result<Void> delete(Integer id) {
        baseService.removeById(id);
        return Result.successDelete();
    }


    @RequestMapping(value = "/updSort", method = RequestMethod.PUT)
    @ApiOperation("修改排序")
    public Result<Void> updSort(@RequestParam Integer id, @RequestParam Integer sort) {
        DictionaryAdmin dict = new DictionaryAdmin();
        dict.setId(id);
        dict.setSort(sort);
        baseService.updateById(dict);
        return Result.successUpdate();
    }
}
