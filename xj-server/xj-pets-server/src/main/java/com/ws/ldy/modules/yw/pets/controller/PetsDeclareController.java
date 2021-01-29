package com.ws.ldy.modules.yw.pets.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.idempotent.annotation.ApiIdempotent;
import com.ws.ldy.common.idempotent.annotation.ApiIdempotentAuth;
import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.yw.pets.model.dto.PetsDeclareUpdStateDTO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsDeclareFindHomePageVO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsDeclarePageVO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsDeclareVO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsDeclaretWindControlVO;
import com.ws.ldy.modules.yw.pets.service.PetsDeclareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 申报信息表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 13:48:14
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/pets/petsDeclare")
@Api(value = "PetsDeclareController", tags = "申报信息表")
public class PetsDeclareController extends BaseController<PetsDeclareService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "id", value = "id", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "fullName", value = "姓名", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "state", value = "申报状态(字典code)", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "startPassTime", value = "开始-审核通过时间", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "endPassTime", value = "结束-审核通过时间", required = false, paramType = "query", example = ""),
    })
    public R<IPage<PetsDeclarePageVO>> findPage(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) Integer state,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startPassTime,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endPassTime
    ) {
        Page<PetsDeclarePageVO> page = baseService.findPage(this.getPage(), id, null, fullName, state, startPassTime, endPassTime);
        return R.successFind(page);
    }


    @RequestMapping(value = "/findHomePage", method = RequestMethod.GET)
    @ApiOperation(value = "首页审核数据/分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
    })
    public R<IPage<PetsDeclareFindHomePageVO>> findHomePage() {
        Page<PetsDeclareFindHomePageVO> homePage = baseService.findHomePage(this.getPage());
        return R.successFind(homePage);
    }


    @RequestMapping(value = "/findListWindControl", method = RequestMethod.GET)
    @ApiOperation(value = "申报列表--(作用1：风控管理查询指定用户记录)", notes = "")
    public R<List<PetsDeclaretWindControlVO>> findListWindControl(@RequestParam String userId) {
        List<PetsDeclaretWindControlVO> windControlVOList = baseService.findListWindControl(userId);
        return R.success(windControlVOList);
    }


    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询(主人/医院/宠物数据)", notes = "")
    @ApiIdempotent
    public R<PetsDeclareVO> findId(@RequestParam String id) {
        PetsDeclareVO vo = baseService.findId(id);
        return R.successFind(vo);
    }


    @RequestMapping(value = "/updState", method = RequestMethod.PUT)
    @ApiOperation(value = "申报通过/不通过-并录入实际发放金额", notes = "必须传递ID")
    @ApiImplicitParam(name = "IDEMPOTENT_TOKEN", value = "幂等token | id查询返回 header", required = true, paramType = "header", example = "0")
    @ApiIdempotentAuth
    public R<Boolean> updState(@RequestBody @Validated PetsDeclareUpdStateDTO dto) {
        return R.success(baseService.updState(dto));
    }
}
