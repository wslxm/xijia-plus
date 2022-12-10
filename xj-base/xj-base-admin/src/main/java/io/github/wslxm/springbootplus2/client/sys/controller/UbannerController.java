package io.github.wslxm.springbootplus2.client.sys.controller;

import io.github.wslxm.springbootplus2.common.annotation.XjCurrentLimit;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import io.github.wslxm.springbootplus2.manage.sys.model.vo.BannerVO;
import io.github.wslxm.springbootplus2.manage.sys.service.BannerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * yh--base-plus--banner
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-23 23:14:01
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_CLIENT + "/sys/banner")
public class UbannerController extends BaseController<BannerService> {

    /**
     * 列表-位置查询
     *
     * @param position 位置(字典code)
     * @return io.github.wslxm.springbootplus2.core.result.Result<java.util.List < io.github.wslxm.springbootplus2.manage.sys.model.vo.BannerVO>>
     * @author wangsong
     * @date 2022/12/10 0010 14:06
     * @version 1.0.0
     */
    @GetMapping(value = "/list/{position}")
    @XjCurrentLimit(qbs = 200)
    public Result<List<BannerVO>> list(@PathVariable Integer position) {
        return Result.successFind(baseService.findByPosition(position));
    }
}
