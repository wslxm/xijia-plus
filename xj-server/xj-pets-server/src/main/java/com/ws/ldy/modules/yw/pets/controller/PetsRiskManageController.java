package com.ws.ldy.modules.yw.pets.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pets.model.entity.PetsRiskManage;
import com.ws.ldy.modules.yw.pets.model.vo.PetsRiskManageVO;
import com.ws.ldy.modules.yw.pets.service.PetsRiskManageService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 风控管理表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 11:03:55
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/pets/petsRiskManage")
@Api(value = "PetsRiskManageController", tags = "风控管理表")
public class PetsRiskManageController extends BaseController<PetsRiskManageService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "风控列表--分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "fullName", value = "姓名", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "phone", value = "电话", required = false, paramType = "query", example = ""),
    })
    public R<IPage<PetsRiskManageVO>> findPage( @RequestParam(required = false) String fullName,  @RequestParam(required = false) String phone) {
        Page<PetsRiskManage> page = baseService.page(this.getPage(), new LambdaQueryWrapper<PetsRiskManage>()
                .orderByDesc(PetsRiskManage::getCreateTime)
                .like(StringUtils.isNotBlank(fullName), PetsRiskManage::getFullName, fullName)
                .like(StringUtils.isNotBlank(phone), PetsRiskManage::getPhone, phone)

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, PetsRiskManageVO.class));
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "风控详情--ID查询/申报数据list在申报查询接口中获取", notes = "")
    public R<PetsRiskManageVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), PetsRiskManageVO.class));
    }
}
