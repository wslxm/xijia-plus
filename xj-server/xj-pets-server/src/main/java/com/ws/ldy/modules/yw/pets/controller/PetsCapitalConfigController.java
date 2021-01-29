package com.ws.ldy.modules.yw.pets.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pets.model.dto.PetsCapitalConfigDTO;
import com.ws.ldy.modules.yw.pets.model.entity.PetsCapitalConfig;
import com.ws.ldy.modules.yw.pets.model.vo.PetsCapitalConfigVO;
import com.ws.ldy.modules.yw.pets.service.PetsCapitalConfigService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 互助资金抽成配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-12-29 10:52:46
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/pets/petsCapitalConfig")
@Api(value ="PetsCapitalConfigController" ,tags = "互助资金抽成配置表")
public class PetsCapitalConfigController extends BaseController<PetsCapitalConfigService>  {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes= "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query",example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query",example = "20"),
 
    })
    public R<IPage<PetsCapitalConfigVO>> findPage( ) {
        Page<PetsCapitalConfig> page = baseService.page(this.getPage(), new LambdaQueryWrapper<PetsCapitalConfig>()
                .orderByDesc(PetsCapitalConfig::getCreateTime)
 
        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, PetsCapitalConfigVO.class));
    }


    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
    @ApiOperation(value = "ID编辑", notes= "必须传递ID")
    public R<Boolean> upd(@RequestBody @Validated PetsCapitalConfigDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.successUpdate(baseService.updateById(dto.convert(PetsCapitalConfig.class)));
    }
}
