package com.ws.ldy.modules.yw.pets.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.pets.model.entity.PetsPetInfo;
import com.ws.ldy.modules.yw.pets.model.vo.PetsPetInfoVO;
import com.ws.ldy.modules.yw.pets.service.PetsPetInfoService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


/**
 * 宠物表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:54:33
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/pets/petsPetInfo")
@Api(value = "PetsPetInfoController", tags = "宠物表")
public class PetsPetInfoController extends BaseController<PetsPetInfoService> {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query", example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query", example = "20"),
            @ApiImplicitParam(name = "userId", value = "宠物用户id", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "nickname", value = "宠物名称", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "type", value = "类别(宠物字典code)", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "startJoinTime", value = "查询开始加入时间", required = false, paramType = "query", example = ""),
            @ApiImplicitParam(name = "endJoinTime", value = "查询结束加入时间", required = false, paramType = "query", example = ""),

    })
    public R<IPage<PetsPetInfoVO>> findPage(@RequestParam(required = false) String userId,
                                            @RequestParam(required = false) String nickname,
                                            @RequestParam(required = false) String type,
                                            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startJoinTime,
                                            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endJoinTime
    ) {
        Page<PetsPetInfoVO> page = baseService.findPage(this.getPage(), userId, nickname, type, startJoinTime, endJoinTime);
        return R.successFind(page);
    }

    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    @ApiOperation(value = "宠物详情", notes = "")
    public R<PetsPetInfoVO> findId(@RequestParam String id) {
        return R.successFind(BeanDtoVoUtil.convert(baseService.getById(id), PetsPetInfoVO.class));
    }


    @RequestMapping(value = "/findTotal", method = RequestMethod.GET)
    @ApiOperation(value = "查询宠物总数量", notes = "")
    public R<Integer> findTotal() {
        return R.success(baseService.count(new LambdaQueryWrapper<PetsPetInfo>().ne(PetsPetInfo::getNickname,"")));
    }
}
