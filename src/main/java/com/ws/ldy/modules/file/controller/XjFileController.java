package com.ws.ldy.modules.file.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.file.model.dto.XjFileDTO;
import com.ws.ldy.modules.file.model.entity.XjFile;
import com.ws.ldy.modules.file.model.vo.XjFileVO;
import com.ws.ldy.modules.file.service.XjFileService;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 常用工具文件管理
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-17 16:21:46
 */
@RestController
@RequestMapping(BaseConstant.Sys.API + "/file/xjFile")
@Api(value ="XjFileController" ,tags = "独立功能--常用工具文件管理", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class XjFileController extends BaseController<XjFileService>  {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes= "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query",example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query",example = "20"),
            @ApiImplicitParam(name = "name", value = "文件名(标题)", required = false, paramType = "query",example = ""),

    })
    public R<IPage<XjFileVO>> findPage( 
                                            @RequestParam(required = false) String name
                                              ) {
        Page<XjFile> page = baseService.page(this.getPage(), new LambdaQueryWrapper<XjFile>()
                .orderByDesc(XjFile::getCreateTime)
                 .like(StringUtils.isNotBlank(name),XjFile::getName,name)

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, XjFileVO.class));
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes= "")
    public R<XjFileVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert( baseService.getById(id),XjFileVO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes= "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated XjFileDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        XjFile xjFile = dto.convert(XjFile.class);
        return R.successInsert(baseService.save(xjFile));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes= "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated XjFileDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.successUpdate(baseService.updateById(dto.convert(XjFile.class)));
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
