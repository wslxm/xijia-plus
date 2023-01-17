package io.github.wslxm.springbootplus2.manage.sys.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.dto.MsgDTO;
import io.github.wslxm.springbootplus2.manage.sys.model.query.MsgQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.MsgFindAllNumVO;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.MsgVO;
import io.github.wslxm.springbootplus2.manage.sys.service.MsgService;
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
@RequestMapping(BaseConstant.Uri.API_ADMIN+ "/sys/msg")
@Api(value = "MsgController", tags = "base--sys--消息通知")
public class MsgController extends BaseController<MsgService> {


    @GetMapping(value = "/findPage")
    @ApiOperation(value = "列表查询")
    public Result<IPage<MsgVO>> findPage(@ModelAttribute @Validated MsgQuery query) {
        return Result.success(baseService.findPage(query));
    }


    @PostMapping
    @ApiOperation(value = "添加/发送消息")
    public Result<String> insert(@RequestBody @Validated MsgDTO dto) {
        dto.setIsWebsocket(true);
        return Result.success(baseService.insert(dto));
    }


    @PutMapping(value = "/{id}/read")
    @ApiOperation(value = "消息修改为已读")
    public Result<Boolean> updRead(@PathVariable String id) {
        return Result.successUpdate(baseService.updRead(id));
    }


    @ApiOperation(value = "查询未读数量(当前登录用户)")
    @GetMapping(value = "/findUnreadNum")
    public Result<Long> findUnreadNum() {
        return Result.successFind(baseService.findUnreadNum());
    }


    @ApiOperation(value = "查询全部/已读/未读数量(当前登录用户)")
    @GetMapping(value = "/findAllNum")
    public Result<MsgFindAllNumVO> findAllNum() {
        return Result.successFind(baseService.findAllNum());
    }
}
