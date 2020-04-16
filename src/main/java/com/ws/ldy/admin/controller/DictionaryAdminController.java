package com.ws.ldy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.admin.model.dto.DictionaryAdminDto;
import com.ws.ldy.admin.model.entity.DictionaryAdmin;
import com.ws.ldy.admin.model.vo.DictionaryAdminVo;
import com.ws.ldy.admin.service.impl.DictionaryAdminServiceImpl;
import com.ws.ldy.base.controller.BaseController;
import com.ws.ldy.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
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


    @ApiOperation("根据类型查询字典表")
    @RequestMapping(value = "/findByType", method = RequestMethod.GET)
    public Result<List<DictionaryAdminVo>> findByType(String type) {
        List<DictionaryAdmin> dictionaryList = dictionaryAdminServiceImpl.findByType(type);
        List<DictionaryAdminVo> dictionaryAdminVos = this.listVo(dictionaryList, DictionaryAdminVo.class);
        for (DictionaryAdminVo dictionary : dictionaryAdminVos) {
            dictionary.setName(dictionary.getValue());
        }
        return success(dictionaryAdminVos);
    }


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "记录数", required = true, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "字典类型", required = true, paramType = "query"),
    })
    public Result<IPage<DictionaryAdminVo>> findPage(String type) {
        Page<DictionaryAdmin> page = dictionaryAdminServiceImpl.page(this.getPage(), new LambdaQueryWrapper<DictionaryAdmin>()
                .orderByAsc(DictionaryAdmin::getId)
                .eq(DictionaryAdmin::getType, type)
        );
        IPage<DictionaryAdminVo> convert = page.convert(item -> item.convert(DictionaryAdminVo.class));
        return success(convert);

//        Page<DictionaryAdmin> dictionaryAdminPage = dictionaryAdminServiceImpl.selectPage(
//                this.getPage(), new QueryCriteria().eq("type", type).orderByAsc("id")
//        );
//        return success(this.pageVo(dictionaryAdminPage, DictionaryAdminVo.class));
    }


    @RequestMapping(value = "/save/{type}", method = RequestMethod.POST)
    @ApiOperation("添加/修改 t=1 添加，=2修改")
    public Result<Void> save(@PathVariable Integer type, DictionaryAdminDto dictionaryAdminDto) {
        if (type == 1) {
            dictionaryAdminServiceImpl.save(dictionaryAdminDto.convert(DictionaryAdmin.class));
        } else {
            dictionaryAdminServiceImpl.save(dictionaryAdminDto.convert(DictionaryAdmin.class));
        }
        return success();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation("批量删除/单删除--要删除的数据Id数组")
    public Result<Void> delete(Integer[] ids) {
        dictionaryAdminServiceImpl.removeByIds(Arrays.asList(ids));
        return success();
    }
}
