package com.ws.ldy.modules.yw.test.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.yw.test.model.dto.File2DTO;
import com.ws.ldy.modules.yw.test.model.entity.File2;
import com.ws.ldy.modules.yw.test.model.vo.File2VO;
import com.ws.ldy.modules.yw.test.service.File2Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 额外功能表--常用工具文件管理
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2021-01-20 19:16:31
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/test/file2")
@Api(value ="File2Controller" ,tags = "额外功能表--常用工具文件管理")
public class File2Controller extends BaseController<File2Service>  {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes= "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query",example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query",example = "20"),
 
    })
    public R<IPage<File2VO>> findPage(
                                              ) {
        Page<File2> page = baseService.page(this.getPage(), new LambdaQueryWrapper<File2>()
                .orderByDesc(File2::getCreateTime)
 
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, File2VO.class));
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询", notes= "")
    public R<File2VO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert( baseService.getById(id),File2VO.class));
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加", notes= "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated File2DTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        File2 file2 = dto.convert(File2.class);
        return R.successInsert(baseService.save(file2));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes= "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated File2DTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.successUpdate(baseService.updateById(dto.convert(File2.class)));
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
