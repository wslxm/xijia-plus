package com.ws.ldy.modules.yw.astory.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.astory.model.dto.XjAstoryCategoryDTO;
import com.ws.ldy.modules.yw.astory.model.entity.XjAstoryCategory;
import com.ws.ldy.modules.yw.astory.model.vo.XjAstoryCategoryVO;
import com.ws.ldy.modules.yw.astory.service.XjAstoryCategoryService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 段子分类表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-20 22:04:03
 */
@RestController
@RequestMapping(BaseConstant.Sys.API + "/astory/xjAstoryCategory")
@Api(value ="XjAstoryCategoryController" ,tags = "独立功能--段子分类", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class XjAstoryCategoryController extends BaseController<XjAstoryCategoryService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes= "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query",example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query",example = "20"),
            @ApiImplicitParam(name = "type", value = "类别名称", required = false, paramType = "query",example = ""),

    })
    public R<IPage<XjAstoryCategoryVO>> findPage( 
                                            @RequestParam(required = false) String type
                                              ) {
        Page<XjAstoryCategory> page = baseService.page(this.getPage(), new LambdaQueryWrapper<XjAstoryCategory>()
                .orderByDesc(XjAstoryCategory::getCreateTime)
                 .eq(StringUtils.isNotBlank(type),XjAstoryCategory::getType,type)

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, XjAstoryCategoryVO.class));
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes= "")
    public R<XjAstoryCategoryVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert( baseService.getById(id),XjAstoryCategoryVO.class));
    }



    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有", notes= "")
    public R<List<XjAstoryCategoryVO>> findList() {
        return R.successFind(BeanDtoVoUtil.listVo( baseService.list(),XjAstoryCategoryVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes= "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated XjAstoryCategoryDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        XjAstoryCategory xjAstoryCategory = dto.convert(XjAstoryCategory.class);
        return R.successInsert(baseService.save(xjAstoryCategory));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes= "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated XjAstoryCategoryDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.successUpdate(baseService.updateById(dto.convert(XjAstoryCategory.class)));
    }


    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ApiOperation(value = "ID删除", notes= "")
    public R<Boolean> del(@RequestParam String id) {
        return R.successDelete(baseService.removeById(id));
    }


    @RequestMapping(value = "/delByIds", method = RequestMethod.DELETE)
    @ApiOperation(value = "批量ID删除", notes= "")
    public R<Boolean> delByIds(@RequestParam String[] ids) {
        return R.successDelete(baseService.removeByIds(Arrays.asList(ids)));
    }
}
