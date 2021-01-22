package com.ws.ldy.modules.sys.xj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.ldy.common.result.R;
import com.ws.ldy.common.utils.BeanDtoVoUtil;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.sys.xj.model.entity.XjAdminMsg;
import com.ws.ldy.modules.sys.xj.model.vo.XjAdminMsgVO;
import com.ws.ldy.modules.sys.xj.service.XjAdminMsgService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
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
@RequestMapping(BaseConstant.Sys.API + "/admin/adminMsg")
@Api(value ="XjAdminMsgController" ,tags = "base-plus--消息通知", consumes = BaseConstant.InterfaceType.PC_ADMIN)
public class XjAdminMsgController extends BaseController<XjAdminMsgService>  {


    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询", notes= "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页数", required = true, paramType = "query",example = "1"),
            @ApiImplicitParam(name = "size", value = "记录数", required = true, paramType = "query",example = "20"),

    })
    public R<IPage<XjAdminMsgVO>> findPage(
                                              ) {
        Page<XjAdminMsg> page = baseService.page(this.getPage(), new LambdaQueryWrapper<XjAdminMsg>()
                .orderByDesc(XjAdminMsg::getCreateTime)

        );
        return R.successFind(BeanDtoVoUtil.pageVo(page, XjAdminMsgVO.class));
    }

//    @RequestMapping(value = "/findId", method = RequestMethod.GET)
//    @ApiOperation(value = "ID查询", notes= "")
//    public R<XjAdminMsgVO> findId(@RequestParam String id) {
//        return R.successFind(BeanDtoVoUtil.convert( baseService.getById(id),XjAdminMsgVO.class));
//    }
//
//
//    @RequestMapping(value = "/insert", method = RequestMethod.POST)
//    @ApiOperation(value = "添加", notes= "必须不传递ID")
//    public R<Boolean> insert(@RequestBody @Validated XjAdminMsgDTO dto) {
//        if (StringUtils.isNotBlank(dto.getId())) {
//            throw new ErrorException(RType.PARAM_ID_REQUIRED_FALSE);
//        }
//        XjAdminMsg adminMsg = dto.convert(XjAdminMsg.class);
//        return R.successInsert(baseService.save(adminMsg));
//    }
//
//
//    @RequestMapping(value = "/upd", method = RequestMethod.PUT)
//    @ApiOperation(value = "ID编辑", notes= "必须传递ID")
//    public R<Boolean> upd(@RequestBody @Validated XjAdminMsgDTO dto) {
//        if (StringUtils.isBlank(dto.getId())) {
//            throw new ErrorException(RType.PARAM_ID_REQUIRED_TRUE);
//        }
//        return R.successUpdate(baseService.updateById(dto.convert(XjAdminMsg.class)));
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
