package io.github.wslxm.springbootplus2.client.sys.controller;

import io.github.wslxm.springbootplus2.manage.sys.model.vo.ConfigVO;
import io.github.wslxm.springbootplus2.manage.sys.service.ConfigService;
import io.github.wslxm.springbootplus2.core.base.controller.BaseController;
import io.github.wslxm.springbootplus2.core.constant.BaseConstant;
import io.github.wslxm.springbootplus2.core.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * yh--base-plus--全局配置
 * <p>
 * ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655
 * </p>
 *
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-31 18:31:44
 */
@RestController
@RequestMapping(BaseConstant.Uri.API_CLIENT + "/sys/config")
public class UconfigController extends BaseController<ConfigService> {


    /**
     * CODE查询
     *
     * @param code
     * @return
     */
    @GetMapping(value = "/findByCode")
    public Result<ConfigVO> findByCode(@RequestParam String code) {
        return Result.successFind(baseService.findByCode(code));
    }

}
