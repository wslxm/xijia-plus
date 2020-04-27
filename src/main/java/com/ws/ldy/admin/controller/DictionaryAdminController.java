package com.ws.ldy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.admin.model.dto.DictionaryAdminDto;
import com.ws.ldy.admin.model.entity.DictionaryAdmin;
import com.ws.ldy.admin.model.vo.DictionaryAdminVo;
import com.ws.ldy.admin.service.impl.DictionaryAdminServiceImpl;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.config.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@Api(value = "DictionaryAdminController", tags = "字典管理")
public class DictionaryAdminController extends BaseController {

    @Resource
    private DictionaryAdminServiceImpl dictionaryAdminServiceImpl;


//    @ApiOperation("根据类型查询字典表")
//    @RequestMapping(value = "/findByType", method = RequestMethod.GET)
//    public Result<List<DictionaryAdminVo>> findByType(String code) {
//      //  List<DictionaryAdmin> dictionaryList = dictionaryAdminServiceImpl.findByType(code);
//        List<DictionaryAdmin> dictionaryList = dictionaryAdminServiceImpl.list(new LambdaQueryWrapper<DictionaryAdmin>().eq(DictionaryAdmin::getCode, code));
//        List<DictionaryAdminVo> dictionaryAdminVos = this.listVo(dictionaryList, DictionaryAdminVo.class);
//        for (DictionaryAdminVo dictionary : dictionaryAdminVos) {
//            dictionary.setName(dictionary.getValue());
//        }
//        return success(dictionaryAdminVos);
//    }

    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation("列表查询")
    public Result<List<DictionaryAdminVo>> findList() {
        List<DictionaryAdmin> list = dictionaryAdminServiceImpl.list(new LambdaQueryWrapper<DictionaryAdmin>().orderByAsc(DictionaryAdmin::getSort));
        return successFind(listVoStream(list, DictionaryAdminVo.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("添加")
    public Result<Void> save(@RequestBody DictionaryAdminDto dictionaryAdminDto) {
        dictionaryAdminServiceImpl.save(dictionaryAdminDto.convert(DictionaryAdmin.class));
        return successInsert();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation("编辑")
    public Result<Void> update(@RequestBody DictionaryAdminDto dictionaryAdminDto) {
        dictionaryAdminServiceImpl.updateById(dictionaryAdminDto.convert(DictionaryAdmin.class));
        return successUpdate();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("ID删除")
    public Result<Void> delete(Integer id) {
        dictionaryAdminServiceImpl.removeById(id);
        return successDelete();
    }


    @RequestMapping(value = "/updSort", method = RequestMethod.PUT)
    @ApiOperation("修改排序")
    public Result<Void> updSort(@RequestParam Integer id, @RequestParam Integer sort) {
        DictionaryAdmin dict = new DictionaryAdmin();
        dict.setId(id);
        dict.setSort(sort);
        dictionaryAdminServiceImpl.updateById(dict);
        return successUpdate();
    }
}
