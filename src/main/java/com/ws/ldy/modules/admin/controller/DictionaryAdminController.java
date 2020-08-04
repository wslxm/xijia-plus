package com.ws.ldy.modules.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.common.utils.StringUtil;
import com.ws.ldy.config.error.ErrorException;
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
 *   字典表
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
    @ApiOperation(value = "查询所有", notes = "")
    public R<List<DictionaryAdminVo>> findList() {
        List<DictionaryAdmin> list = baseService.list(new LambdaQueryWrapper<DictionaryAdmin>().orderByAsc(DictionaryAdmin::getSort));
        return R.successFind(BeanDtoVoUtil.listVo(list, DictionaryAdminVo.class));
    }


    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    @ApiOperation(value = "Code查询", notes = "无限层次, 树架构，只能传递字符串Code, 不能传递字符串数字Code")
    public R<DictionaryAdminVo> findByCode(@RequestParam String code) {
        // 不能传递字符串数字来查询
        if (StringUtil.isInteger(code)) {
            throw new ErrorException(RType.SYSTEM_PARAMETER_IS_NO);
        }
        DictionaryAdminVo dict = baseService.findByCodeFetchDictVO(code);
        return R.success(dict);
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes = "字符串Code不能重复,  数字类型的Code可以重复")
    public R<Void> insert(@RequestBody DictionaryAdminDto dictionaryAdminDto) {
        dictionaryAdminDto.setCode(dictionaryAdminDto.getCode().trim());
        if (!StringUtil.isInteger(dictionaryAdminDto.getCode())
                && baseService.count(new LambdaQueryWrapper<DictionaryAdmin>().eq(DictionaryAdmin::getCode, dictionaryAdminDto.getCode())) > 0) {
            // 字符串code 为string时不能重复, 为Integer时可以重复
            throw new ErrorException(RType.ADMIN_DICT_DUPLICATE);
        }
        baseService.save(dictionaryAdminDto.convert(DictionaryAdmin.class));
        return R.successInsert();
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "编辑", notes = "编辑后当前字典的字符串Code不能与其他字典的字符串Code重复， 不编辑Code 和数字类型的Code 不受影响")
    public R<Void> upd(@RequestBody DictionaryAdminDto dictionaryAdminDto) {
        dictionaryAdminDto.setCode(dictionaryAdminDto.getCode().trim());
        //原数据
        DictionaryAdmin dict = baseService.getById(dictionaryAdminDto.getId());
        // 修改了code 判断--> 字符串code 为string时不能重复, 为Integer时可以重复
        if (!dict.getCode().equals(dictionaryAdminDto.getCode().trim())) {
            if (!StringUtil.isInteger(dictionaryAdminDto.getCode())
                    && baseService.count(new LambdaQueryWrapper<DictionaryAdmin>().eq(DictionaryAdmin::getCode, dictionaryAdminDto.getCode())) > 0) {
                throw new ErrorException(RType.ADMIN_DICT_DUPLICATE);
            }
        }
        baseService.updateById(dictionaryAdminDto.convert(DictionaryAdmin.class));
        return R.successUpdate();
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据Code删除", notes = "删除当前Code数据以及该Code下所有的子层级数据")
    public R<Void> del(@RequestParam String id) {
        List<String> ids = baseService.findByIdFetchIds(id);
        baseService.removeByIds(ids);
        return R.successDelete();
    }


    @RequestMapping(value = "/updBySort", method = RequestMethod.PUT)
    @ApiOperation(value = "修改排序", notes = "排序数字越小,越靠前")
    public R<Void> updBySort(@RequestParam String id, @RequestParam Integer sort) {
        DictionaryAdmin dict = new DictionaryAdmin();
        dict.setId(id);
        dict.setSort(sort);
        baseService.updateById(dict);
        return R.successUpdate();
    }
}
