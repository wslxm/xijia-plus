package io.github.wslxm.springbootplus2.client.xj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.wslxm.springbootplus2.manage.xj.model.query.XjAdminBannerQuery;
import io.github.wslxm.springbootplus2.manage.xj.model.vo.XjAdminBannerVO;
import io.github.wslxm.springbootplus2.manage.xj.service.XjAdminBannerService;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
@RequestMapping(BaseConstant.Uri.apiClient + "/xj/banner")
@Api(value = "UXjAdminBannerController", tags = "yh--base-plus--banner")
public class UXjAdminBannerController extends BaseController<XjAdminBannerService> {


    @GetMapping(value = "/list/{position}")
    @ApiOperation(value = "列表-位置查询")
    @ApiImplicitParam(name = "position", value = "位置(字典code)", required = true, paramType = "path", example = "")
    public R<IPage<XjAdminBannerVO>> list(@PathVariable Integer position) {
        XjAdminBannerQuery query = new XjAdminBannerQuery();
        query.setPosition(position);
        return R.successFind(baseService.list(query));
    }
}
