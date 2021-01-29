package com.ws.ldy.client.yw.pets.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.client.yw.pets.model.vo.NewDeclareVO;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.result.RType;
import com.ws.ldy.config.auth.util.JwtUtil;
import com.ws.ldy.config.error.ErrorException;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import com.ws.ldy.modules.yw.pets.model.dto.PetsDeclareDTO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsDeclarePageVO;
import com.ws.ldy.modules.yw.pets.model.vo.PetsDeclareVO;
import com.ws.ldy.modules.yw.pets.service.PetsDeclareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 申报信息表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:53:17
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiClient + "/pets/petsDeclare")
@Api(value = "UPetsDeclareController", tags = "yh--申报信息表")
public class UPetsDeclareController extends BaseController<PetsDeclareService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "申报查询/状态tab状态", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "state", value = "状态( 字典code)", required = false, paramType = "query", example = "0"),

    })
    public R<IPage<PetsDeclarePageVO>> findPage(@RequestParam(required = false) Integer state) {
        String userId = JwtUtil.getJwtUser(request).getUserId();
        Page<PetsDeclarePageVO> page = baseService.findPage(this.getPage(), null, userId,null, state, null, null);
        return R.successFind(page);
    }


    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "ID查询-申报详情(查询宠物/医院/主人信息)", notes = "")
    public R<PetsDeclareVO> findId(@RequestParam String id) {
        PetsDeclareVO vo = baseService.findId(id);
        return R.successFind(vo);
    }


    /**
     * {
     *   "petId": "1344457840030507010",
     *   "hospitalId": "1344479885359382529",
     *   "declareMoney": 5000,
     *   "payChannel": 1,
     *   "sickRecordPic": "/pic",
     *   "expenseDetailsPic": "/pic",
     *   "expenseInvoicePic": "/pic",
     *   "pathogeny": "病因"
     * }
     * @author wangsong
     * @param dto
     * @date 2020/12/31 0031 18:15
     * @return com.ws.ldy.common.result.R<java.lang.Boolean>
     * @version 1.0.0
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "添加-提交申报", notes = "必须不传递ID")
    public R<Boolean> insert(@RequestBody @Validated PetsDeclareDTO dto) {
        if (StringUtils.isNotBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
        }
        return R.successInsert(baseService.insert(dto));
    }


    @RequestMapping(value = "/resubmit", method = RequestMethod.PUT)
    @ApiOperation(value = "申报失败重新提交申报", notes = "")
    public R<Boolean> upd(@RequestBody @Validated PetsDeclareDTO dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
        }
        return R.success(baseService.upd(dto));
    }


    @RequestMapping(value = "/newDeclareSuccess", method = RequestMethod.GET)
    @ApiOperation(value = "app首页--最新互助(最后一天申报成功的数据)", notes = "")
    public R<NewDeclareVO> newDeclareSuccess() {
        NewDeclareVO newDeclareVO = baseService.newDeclareSuccess();
        return R.success(newDeclareVO);
    }
}
