package com.ws.ldy.modules.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.admin.model.entity.AdminMsg;
import com.ws.ldy.modules.admin.model.vo.AdminMsgVO;
import com.ws.ldy.modules.admin.service.AdminMsgService;
import com.ws.ldy.others.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 订单-->及时消息通知表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author  wangsong
 * @email  1720696548@qq.com
 * @date  2020-09-23 10:40:23
 */
@RestController
@RequestMapping("/admin/adminMsg")
@Api(value ="AdminMsgController" ,tags = "及时消息通知表", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class AdminMsgController extends BaseController<AdminMsgService>  {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes= "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query",example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query",example = "20"),

    })
    public R<IPage<AdminMsgVO>> findPage(
                                              ) {
        Page<AdminMsg> page = baseService.page(this.getPage(), new LambdaQueryWrapper<AdminMsg>()
                .orderByDesc(AdminMsg::getCreateTime)

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, AdminMsgVO.class));
    }

//    @RequestMapping(value = "/findId", method = RequestMethod.GET)
//    @ApiOperation(value = "ID查询", notes= "")
//    public R<AdminMsgVO> findId(@RequestParam String id) {
//        return R.successFind(BeanDtoVoUtil.convert( baseService.getById(id),AdminMsgVO.class));
//    }
//
//
//    @RequestMapping(value = "/insert", method = RequestMethod.POST)
//    @ApiOperation(value = "添加", notes= "必须不传递ID")
//    public R<Boolean> insert(@RequestBody @Validated AdminMsgDTO dto) {
//        if (StringUtils.isNotBlank(dto.getId())) {
//            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
//        }
//        AdminMsg adminMsg = dto.convert(AdminMsg.class);
//        return R.successInsert(baseService.save(adminMsg));
//    }
//
//
//    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
//    @ApiOperation(value = "ID编辑", notes= "必须传递ID")
//    public R<Boolean> upd(@RequestBody @Validated AdminMsgDTO dto) {
//        if (StringUtils.isBlank(dto.getId())) {
//            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
//        }
//        return R.successUpdate(baseService.updateById(dto.convert(AdminMsg.class)));
//    }
//
//
//    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
//    @ApiOperation(value = "ID删除", notes= "")
//    public R<Boolean> del(@RequestParam String id) {
//        return R.successDelete(baseService.removeById(id));
//    }
//
//
//    @RequestMapping(value = "/delByIds", method = RequestMethod.DELETE)
//    @ApiOperation(value = "批量ID删除", notes= "")
//    public R<Boolean> delByIds(@RequestParam String[] ids) {
//        return R.successDelete(baseService.removeByIds(Arrays.asList(ids)));
//    }
}
