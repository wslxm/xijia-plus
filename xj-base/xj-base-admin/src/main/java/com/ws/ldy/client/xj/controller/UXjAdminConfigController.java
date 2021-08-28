package com.ws.ldy.client.xj.controller;

import com.ws.ldy.core.base.controller.BaseController;
import com.ws.ldy.core.constant.BaseConstant;
import com.ws.ldy.core.result.R;
import com.ws.ldy.manage.xj.model.vo.XjAdminConfigVO;
import com.ws.ldy.manage.xj.service.XjAdminConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 系统全局数据信息配置表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-08-31 18:31:44
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiClient + "/xj/config")
@Api(value = "XjAdminConfigController", tags = "yh--base-plus--全局配置")
public class UXjAdminConfigController extends BaseController<XjAdminConfigService> {


    @GetMapping(value = "/findByCode")
    @ApiOperation(value = "CODE查询")
    public R<XjAdminConfigVO> findByCode(@RequestParam String code) {
        return R.successFind(baseService.findByCode(code));
    }
}
