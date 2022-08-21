package io.github.wslxm.springbootplus2.manage.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.github.wslxm.springbootplus2.manage.sys.model.query.LogQuery;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.LogVO;
import io.github.wslxm.springbootplus2.manage.sys.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 操作记录表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-10-28 20:44:32
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_ADMIN+"/sys/log")
@Api(value = "LogController", tags = "base--sys--操作记录")
public class LogController extends BaseController<LogService> {


    @GetMapping(value = "/findPage")
    @ApiOperation(value = "分页查询")
    public R<IPage<LogVO>> findPage(@ModelAttribute @Validated LogQuery query) {
        return R.success(baseService.findPage(query));
    }

}
