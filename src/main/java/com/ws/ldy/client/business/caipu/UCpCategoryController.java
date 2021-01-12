package com.ws.ldy.client.business.caipu;

import com.ws.ldy.common.result.R;
import com.ws.ldy.modules.business.caipu.model.vo.CpCategoryVO;
import com.ws.ldy.modules.business.caipu.service.CpCategoryService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 字典表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-10-04 21:44:55
 */
@RestController
@RequestMapping("/consumer/caipu/cpCategory")
@Api(value = "CpCategory", tags = "独立功能--菜谱类别(用户端)")
public class UCpCategoryController extends BaseController<CpCategoryService> {

    @RequestMapping(value = "/findTree", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有 Tree", notes = "")
    public R<List<CpCategoryVO>> findTree() {
        return R.successFind(baseService.findTree());
    }


}
