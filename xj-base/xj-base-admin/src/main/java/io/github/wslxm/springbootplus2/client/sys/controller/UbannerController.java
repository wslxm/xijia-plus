package io.github.wslxm.springbootplus2.client.sys.controller;

import io.github.wslxm.springbootplus2.core.base.annotation.XjCurrentLimit;
import io.github.wslxm.springbootplus2.core.base.annotation.XjSecret;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.BannerVO;
import io.github.wslxm.springbootplus2.manage.sys.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * banner表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-23 23:14:01
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_CLIENT + "/sys/banner")
@Api(value = "UbannerController", tags = "yh--base-plus--banner")
public class UbannerController extends BaseController<BannerService> {

    @GetMapping(value = "/list/{position}")
    @ApiOperation(value = "列表-位置查询")
    @ApiImplicitParam(name = "position", value = "位置(字典code)", required = true, paramType = "path", example = "")
    @XjCurrentLimit(qbs = 200)
    public Result<List<BannerVO>> list(@PathVariable @XjSecret Integer position) {
        return Result.successFind(baseService.findByPosition(position));
    }
}
