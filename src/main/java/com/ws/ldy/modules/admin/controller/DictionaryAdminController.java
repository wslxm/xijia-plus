package com.ws.ldy.modules.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.base.BaseConstant;
import com.ws.ldy.modules.admin.model.dto.DictionaryAdminDto;
import com.ws.ldy.modules.admin.model.entity.DictionaryAdmin;
import com.ws.ldy.modules.admin.model.vo.DictionaryAdminVo;
import com.ws.ldy.modules.admin.service.DictionaryAdminService;
import com.ws.ldy.others.base.controller.BaseController;
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
public class DictionaryAdminController extends BaseController<DictionaryAdminService> {


    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation("列表查询")
    public R<List<DictionaryAdminVo>> findList() {
        List<DictionaryAdmin> list = baseService.list(new LambdaQueryWrapper<DictionaryAdmin>().orderByAsc(DictionaryAdmin::getSort));
        return R.successFind(BeanDtoVoUtil.listVo(list, DictionaryAdminVo.class));
    }


    @RequestMapping(value = "/findCode", method = RequestMethod.GET)
    @ApiOperation("Code查询,最多向下2级，A-> BB -> CCCC")
    public R<DictionaryAdminVo> findCode(@RequestParam String code) {
        DictionaryAdminVo dict = baseService.findCode(code);
        return R.success(dict);
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation("添加")
    public R<Void> save(@RequestBody DictionaryAdminDto dictionaryAdminDto) {
        baseService.save(dictionaryAdminDto.convert(DictionaryAdmin.class));
        return R.successInsert();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiOperation("编辑")
    public R<Void> update(@RequestBody DictionaryAdminDto dictionaryAdminDto) {
        baseService.updateById(dictionaryAdminDto.convert(DictionaryAdmin.class));
        return R.successUpdate();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("ID删除")
    public R<Void> delete(String id) {
        baseService.removeById(id);
        return R.successDelete();
    }


    @RequestMapping(value = "/updSort", method = RequestMethod.PUT)
    @ApiOperation("修改排序")
    public R<Void> updSort(@RequestParam String id, @RequestParam Integer sort) {
        DictionaryAdmin dict = new DictionaryAdmin();
        dict.setId(id);
        dict.setSort(sort);
        baseService.updateById(dict);
        return R.successUpdate();
    }
}
