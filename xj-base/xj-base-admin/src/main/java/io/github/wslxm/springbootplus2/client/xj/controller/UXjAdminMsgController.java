package io.github.wslxm.springbootplus2.client.xj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminMsgQuery;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminMsgVO;
import io.github.wslxm.springbootplus2.manage.xj.service.XjAdminMsgService;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 订单-->及时消息通知表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-09-23 10:40:23
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_CLIENT+ "/xj/msg")
@Api(value = "UXjAdminMsgController", tags = "yh--base-plus--消息通知")
public class UXjAdminMsgController extends BaseController<XjAdminMsgService> {


    @GetMapping(value = "/list")
    @ApiOperation(value = "分页查询")
    public R<IPage<XjAdminMsgVO>> list(@ModelAttribute @Validated XjAdminMsgQuery query) {
        return R.successFind(baseService.list(query));
    }


    @PutMapping(value = "/{id}/read")
    @ApiOperation(value = "消息修改为已读")
    public R<Boolean> updRead(@PathVariable String id) {
        return R.successUpdate(baseService.updRead(id));
    }


    @ApiOperation(value = "查询未读数量(当前登录用户)")
    @GetMapping(value = "/findUnreadNum")
    public R<Integer> findUnreadNum() {
        return R.successFind(baseService.findUnreadNum());
    }
}
