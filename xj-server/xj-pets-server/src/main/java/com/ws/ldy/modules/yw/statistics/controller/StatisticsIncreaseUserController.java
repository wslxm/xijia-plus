package com.ws.ldy.modules.yw.statistics.controller;

import com.ws.ldy.common.result.R;
import com.ws.ldy.enums.BaseConstant;
import com.ws.ldy.modules.yw.statistics.model.vo.StatisticsIncreaseUserVO;
import com.ws.ldy.modules.yw.statistics.service.StatisticsIncreaseUserService;
import com.ws.ldy.modules.sys.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户增长每小时统计表
 * <p>
 *  ::本代码由[兮家小二]提供的代码生成器生成,如有问题,请手动修改 ::作者CSDN:https://blog.csdn.net/qq_41463655 
 * </p>
 * @author wangsong
 * @email 1720696548@qq.com
 * @date 2020-12-29 10:50:16
 */
@RestController
@RequestMapping(BaseConstant.Uri.apiAdmin + "/statistics/statisticsIncreaseUser")
@Api(value = "StatisticsIncreaseUserController", tags = "statistics--增长--用户增长统计")
public class StatisticsIncreaseUserController extends BaseController<StatisticsIncreaseUserService> {


    @RequestMapping(value = "/findStatistics", method = RequestMethod.GET)
    @ApiOperation(value = "近n天用户增长统计(天/数量)", notes = "")
    @ApiImplicitParam(name = "dayNum", value = "查询n天数据", required = true, paramType = "query", example = "")
    public R<StatisticsIncreaseUserVO> findStatistics(@RequestParam Integer dayNum) {
        return R.successFind(baseService.findStatistics(dayNum));
    }

}
